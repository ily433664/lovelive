package com.lovelive.sys.utils;

import com.lovelive.common.enums.EntityStatusEnums;
import com.lovelive.sys.entity.MailConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.util.Assert;

import javax.mail.internet.MimeMessage;
import java.io.File;
import java.util.Properties;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 邮件util
 */
public class MailUtils {

    private static final Logger LOGGER = LoggerFactory.getLogger(MailUtils.class);

    private static ExecutorService threadPool = Executors.newFixedThreadPool(10);

    /**
     * 设置基础配置
     *
     * @return 配置好的工具
     */
    private static JavaMailSenderImpl createMailSender(MailConfig mailConfig) {
        Assert.notNull(mailConfig, "MailConfig must not be null");
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost(mailConfig.getHost());
        mailSender.setPort(mailConfig.getPort());
        mailSender.setUsername(mailConfig.getUsername());
        mailSender.setPassword(mailConfig.getPassword());
        mailSender.setDefaultEncoding("Utf-8");
        Properties p = new Properties();
        p.setProperty("mail.smtp.timeout", mailConfig.getTimeout());
        p.setProperty("mail.smtp.auth", "false");
        mailSender.setJavaMailProperties(p);
        return mailSender;
    }

    /**
     * 发送邮件的具体操作
     *
     * @param mailConfig  邮件配置
     * @param to          接受人
     * @param subject     主题
     * @param text        发送内容
     * @param attachments 附件
     */
    public static String sendMail(MailConfig mailConfig, String to, String subject, String text, File[] attachments) {

        StringBuilder result = new StringBuilder();

        try {
            //邮件的基础配置
            JavaMailSenderImpl mailSender = MailUtils.createMailSender(mailConfig);
            MimeMessage mimeMessage = mailSender.createMimeMessage();
            // 设置utf-8或GBK编码，否则邮件会有乱码
            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
            messageHelper.setFrom(mailConfig.getEmailFrom(), mailConfig.getPersonal());
            messageHelper.setTo(to);
            messageHelper.setSubject(subject);
            messageHelper.setText(text, true);
            if (attachments != null) {
                for (File attachment : attachments) {
                    if (attachment.exists()) {
                        messageHelper.addAttachment(attachment.getName(), attachment);//附件
                    }
                }
            }
            mailSender.send(mimeMessage);
        } catch (Exception e) {
            System.out.println("=======================================");
            System.out.println("toUser : " + to);
            System.out.println("subject : " + subject);
            System.out.println("text : " + text);
            LOGGER.error("", e);
            throw new RuntimeException(e);
        }

        return result.toString();
    }

    /**
     * 单线程发送邮件
     *
     * @param tos         收件人
     * @param subject     主题
     * @param text        内容
     * @param attachments 附件
     */
    public static String runSendMail(String[] tos, String subject, String text, File[] attachments) {
        //TODO  获取邮件配置
        MailConfig mailConfig = null;
        if (mailConfig == null || mailConfig.getStatus() != EntityStatusEnums.NORMAL.getValue()) {
            LOGGER.info("MailConfig is not use");
            return "MailConfig is not use";
        }
        StringBuilder result = new StringBuilder();
        for (String to : tos) {
            result.append(MailUtils.sendMail(mailConfig, to, subject, text, attachments));
        }
        return result.toString();
    }

    /**
     * 启动多线程发送邮件
     * 如果是直接使用，则调用该方法
     *
     * @param tos         收件人
     * @param subject     主题
     * @param text        内容
     * @param attachments 附件
     */
    public static void runSendMailThread(String[] tos, String subject, String text, File[] attachments) {
        try {
            //TODO  获取邮件配置
            MailConfig mailConfig = null;
            if (mailConfig == null || mailConfig.getStatus() != EntityStatusEnums.NORMAL.getValue()) {
                LOGGER.info("MailConfig is not use");
                return;
            }
            for (String to : tos) {
                MailThread mailThread = new MailThread(mailConfig, to, subject, text, attachments);
                Thread th = new Thread(mailThread);
                threadPool.execute(th);
            }
        } catch (Exception e) {
            LOGGER.error("", e);
        }
    }

    /**
     * 发送邮件线程
     */
    public static class MailThread implements Runnable {

        //邮件配置
        private MailConfig mailConfig;
        //收件人
        private String to;
        //主题
        private String subject;
        //内容
        private String text;
        //附件
        private File[] attachments;

        private MailThread() {
        }

        public MailThread(MailConfig mailConfig, String to, String subject, String text, File[] attachments) {
            this.mailConfig = mailConfig;
            this.to = to;
            this.subject = subject;
            this.text = text;
            this.attachments = attachments;
        }

        public void run() {
            MailUtils.sendMail(mailConfig, to, subject, text, attachments);
        }

        public String getTo() {
            return to;
        }

        public void setTo(String to) {
            this.to = to;
        }

        public String getSubject() {
            return subject;
        }

        public void setSubject(String subject) {
            this.subject = subject;
        }

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }
    }

    public static void main(String[] args) {
        MailConfig mailConfig = new MailConfig();
        mailConfig.setHost("smtp.dgut.edu.cn");
        mailConfig.setPort(25);
        mailConfig.setUsername("jiuye@dgut.edu.cn");
        mailConfig.setPassword("Jy22861918");
        mailConfig.setEmailFrom("jiuye@dgut.edu.cn");
        mailConfig.setTimeout("25000");
        mailConfig.setPersonal("测试发件人");
        String to = "dhe@dcampus.com";
        String subject = "测试邮件主题";
        String text = "测试邮件内容";
        try {
            MailUtils.sendMail(mailConfig, to, subject, text, null);
            //MailUtils.runSendMailThread(new String[]{to}, subject, text);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
