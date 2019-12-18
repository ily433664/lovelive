package com.lovelive.sys.utils;

import java.io.File;

/**
 * 发送消息类，统一调用这里定义的方法，建议不要将发送操作直接写在这里，而是调用具体的实现工具，参考MailUtils
 *
 * @author dHe
 * @date 2019-08-23
 */
public abstract class AbstractSendMessage {

    /**
     * 发送邮件
     *
     * @param to      接收人
     * @param subject 标题
     * @param text    内容
     */
    abstract String sendMail(String to, String subject, String text);

    /**
     * 发送邮件
     *
     * @param to         接收人
     * @param subject    标题
     * @param text       内容
     * @param attachment 附件
     */
    abstract String sendMail(String to, String subject, String text, File attachment);

    /**
     * 发送邮件
     *
     * @param to          接收人
     * @param subject     标题
     * @param text        内容
     * @param attachments 附件
     */
    abstract String sendMail(String to, String subject, String text, File[] attachments);

    /**
     * 发送邮件
     *
     * @param tos     接收人
     * @param subject 标题
     * @param text    内容
     */
    abstract String sendMail(String[] tos, String subject, String text);

    /**
     * 发送邮件
     *
     * @param tos        接收人
     * @param subject    标题
     * @param text       内容
     * @param attachment 附件
     */
    abstract String sendMail(String[] tos, String subject, String text, File attachment);

    /**
     * 发送邮件
     *
     * @param tos         接收人
     * @param subject     标题
     * @param text        内容
     * @param attachments 附件
     */
    abstract String sendMail(String[] tos, String subject, String text, File[] attachments);

    /**
     * 发送短信
     *
     * @param to   接收人
     * @param text 内容
     */
    abstract String sendMobile(String to, String text);

    /**
     * 发送短信
     *
     * @param tos  接收人
     * @param text 内容
     */
    abstract String sendMobile(String[] tos, String text);

    /**
     * 发送微信消息
     *
     * @param to   接收人
     * @param text 内容
     */
    abstract String sendWeChat(String to, String text);

    /**
     * 发送微信消息
     *
     * @param tos  接收人
     * @param text 内容
     */
    abstract String sendWeChat(String[] tos, String text);

}
