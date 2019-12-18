package com.lovelive.sys.utils;

import com.google.gson.Gson;
import com.lovelive.common.uitls.StringUtils;
import com.lovelive.sys.entity.MessageLog;
import com.lovelive.sys.enums.MessageTypeEnums;
import com.lovelive.sys.pojo.MessagePojo;
import com.lovelive.sys.pojo.MessageReceiverPojo;
import com.lovelive.sys.service.IMessageLogService;
import com.lovelive.sys.service.IMessageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 发送消息工具，统一使用该工具发送消息
 *
 * @author dHe
 * @date 2019-08-23
 */
@Component
public class SendMessageUtils {

    private static final Logger log = LoggerFactory.getLogger(SendMessageUtils.class);

    private static ExecutorService threadPool = Executors.newFixedThreadPool(10);

    private static AbstractSendMessage sendMessage;

    private static IMessageService messageService;

    private static IMessageLogService messageLogService;

    @Autowired
    public SendMessageUtils(AbstractSendMessage sendMessage, IMessageService messageService, IMessageLogService messageLogService) {
        SendMessageUtils.sendMessage = sendMessage;
        SendMessageUtils.messageService = messageService;
        SendMessageUtils.messageLogService = messageLogService;
    }

    /**
     * 执行发送消息，根据消息类型选择发送消息的方式
     *
     * @param messagePojo 消息信息
     * @param messageType 消息类型,使用枚举,MessageType
     * @param receivers   接收者信息
     */
    private static void sendMessage(MessagePojo messagePojo, String messageType, List<MessageReceiverPojo> receivers) {
        try {
            // 需要即时获取，以防MailSenderInfo有改变
            if (receivers == null || receivers.isEmpty()) {
                return;
            }
            MessageLog messageLog = new MessageLog();
            //messagePojo
            messageLog.setTitle(messagePojo.getSubject());
            messageLog.setMsgType(messageType);
            messageLog.setContent(messagePojo.getContent());
            messageLog.setCreator(messagePojo.getCreatorName());
            messageLog.setCreatorAccount(messagePojo.getCreatorAccount());
            if (messagePojo.getUrl() != null) {
                messageLog.setContent(messageLog.getContent() + "(" + messagePojo.getUrl() + ")");
            }

            //保存收件人信息
            List<String> toAccounts = new ArrayList<>();
            List<String> toNames = new ArrayList<>();
            if (StringUtils.isNotBlank(messagePojo.getTag())) {
                toAccounts.add("tag");
                toNames.add(messagePojo.getTag());
            }
            receivers.forEach(receiver -> {
                toAccounts.add(receiver.getReceiverAccount());
                toNames.add(receiver.getReceiverName());
            });
            messageLog.setToAccount(new Gson().toJson(toAccounts));
            messageLog.setToName(new Gson().toJson(toNames));

            String msg = "";
            boolean sendSuccess = false;
            String backMsg = "";
            String errorCode = "";
            try {
                if (MessageTypeEnums.SYSTEM.getValue().equals(messageType)) {
                    //系统内部消息
                    receivers.forEach(receiver -> {
                        //TODO  站内消息
                    });
                    sendSuccess = true;
                } else if (MessageTypeEnums.EMAIL.getValue().equals(messageType)) {
                    //邮件
                    for (MessageReceiverPojo messageReceiverPojo : receivers) {
                        sendMessage.sendMail(messageReceiverPojo.getReceiverEmail(), messagePojo.getSubject(), messagePojo.getContent(), messagePojo.getAttachments());
                    }
                    sendSuccess = true;
                } else if (MessageTypeEnums.WECHAT.getValue().equals(messageType)) {
                    //微信
                    for (MessageReceiverPojo messageReceiverPojo : receivers) {
                        //TODO
                        //sendMessage.sendMsgCenter(messageType, messagePojo);
                    }
                    sendSuccess = true;
                } else if (MessageTypeEnums.SMS.getValue().equals(messageType)) {
                    //短信
                    for (MessageReceiverPojo messageReceiverPojo : receivers) {
                        //TODO
                        sendMessage.sendMobile(messageReceiverPojo.getReceiverMobile(), messagePojo.getContent());
                    }
                    sendSuccess = true;
                } else {
                    msg = "not found messageType";
                    sendSuccess = false;
                    backMsg = "not found messageType";
                    errorCode = "500";
                }
            } catch (Exception e) {
                log.error("", e);
                msg = e.getMessage();
                sendSuccess = false;
                backMsg = e.getMessage();
                errorCode = "500";
            }

            messageLog.setSendSuccess(sendSuccess);
            messageLog.setBackMsg(backMsg);
            messageLog.setMsgCode(errorCode);

            messageLogService.saveMessageLog(messageLog);
        } catch (Exception e) {
            log.error("", e);
        }
    }

    /**
     * 启动线程发送多种消息（发送多种消息类型）
     *
     * @param messagePojo  消息信息
     * @param messageTypes 消息类型,使用枚举,MessageType
     * @param receivers    接收者信息
     */
    public static void runSendMsgThread(MessagePojo messagePojo, String[] messageTypes, List<MessageReceiverPojo> receivers) {
        //TODO  此处限制是否开启消息推送

        for (String messageType : messageTypes) {
            runSendMsgThread(messagePojo, messageType, receivers);
        }

    }

    /**
     * 启动线程发送消息（发送一种消息类型）
     *
     * @param messagePojo 消息信息
     * @param messageType 消息类型,使用枚举,MessageType
     * @param receivers   接收者信息
     */
    public static void runSendMsgThread(MessagePojo messagePojo, String messageType, List<MessageReceiverPojo> receivers) {
        //TODO  此处限制是否开启消息推送

        MessageSendThread messageSendThread = new MessageSendThread();
        messageSendThread.setMessagePojo(messagePojo);
        messageSendThread.setMessageType(messageType);
        messageSendThread.setReceivers(receivers);
        Thread th = new Thread(messageSendThread);
        threadPool.execute(th);
    }

    public static class MessageSendThread implements Runnable {
        private MessagePojo messagePojo;
        private String messageType;
        private List<MessageReceiverPojo> receivers;

        public void run() {
            //TODO  此处限制是否开启消息推送

            SendMessageUtils.sendMessage(messagePojo, messageType, receivers);
        }

        public MessagePojo getMessagePojo() {
            return messagePojo;
        }

        public void setMessagePojo(MessagePojo messagePojo) {
            this.messagePojo = messagePojo;
        }

        public String getMessageType() {
            return messageType;
        }

        public void setMessageType(String messageType) {
            this.messageType = messageType;
        }

        public List<MessageReceiverPojo> getReceivers() {
            return receivers;
        }

        public void setReceivers(List<MessageReceiverPojo> receivers) {
            this.receivers = receivers;
        }
    }

}
