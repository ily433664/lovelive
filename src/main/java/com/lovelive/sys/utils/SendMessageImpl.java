package com.lovelive.sys.utils;

import org.springframework.stereotype.Component;

import java.io.File;

/**
 * 发送消息类，统一调用这里定义的方法，建议不要将发送操作直接写在这里，而是调用具体的实现工具，参考MailUtils
 *
 * @author dHe
 * @date 2019-08-23
 */
@Component
public class SendMessageImpl extends AbstractSendMessage {

    @Override
    String sendMail(String to, String subject, String text) {
        return MailUtils.runSendMail(new String[]{to}, subject, text, null);
    }

    @Override
    String sendMail(String to, String subject, String text, File attachment) {
        return MailUtils.runSendMail(new String[]{to}, subject, text, new File[]{attachment});
    }

    @Override
    String sendMail(String to, String subject, String text, File[] attachments) {
        return MailUtils.runSendMail(new String[]{to}, subject, text, attachments);
    }

    @Override
    String sendMail(String[] tos, String subject, String text) {
        return MailUtils.runSendMail(tos, subject, text, null);
    }

    @Override
    String sendMail(String[] tos, String subject, String text, File attachment) {
        return MailUtils.runSendMail(tos, subject, text, new File[]{attachment});
    }

    @Override
    String sendMail(String[] tos, String subject, String text, File[] attachments) {
        return MailUtils.runSendMail(tos, subject, text, attachments);
    }

    @Override
    String sendMobile(String to, String text) {
        return MobileUtils.runSendMobile(new String[]{to}, text);
    }

    @Override
    String sendMobile(String[] tos, String text) {
        return MobileUtils.runSendMobile(tos, text);
    }

    @Override
    String sendWeChat(String to, String text) {
        //TODO
        new RuntimeException("the method is not implement");
        return null;
    }

    @Override
    String sendWeChat(String[] tos, String text) {
        //TODO
        new RuntimeException("the method is not implement");
        return null;
    }
}
