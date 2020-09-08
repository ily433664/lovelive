package com.lovelive.modules.sys.entity;

import com.lovelive.common.base.BaseEntity;

import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.Lob;
import javax.persistence.Table;

/**
 * 消息通知日志
 *
 * @author dHe
 */
@Entity(name = "t_message_log")
@Table(
        indexes = {
                @Index(name = "idx_msglog_msgType", columnList = "msgType"),
                @Index(name = "idx_msglog_sendSuccess", columnList = "sendSuccess")
        }
)
public class MessageLog extends BaseEntity {

    private static final long serialVersionUID = -5981111680095829116L;

    /**
     * 标题
     */
    private String title;

    /**
     * 消息类型
     * MessageTypeEnums
     */
    private String msgType;

    /**
     * 内容
     */
    @Lob
    private String content;

    /**
     * 发件人名称
     */
    private String creator;

    /**
     * 发件人账号
     */
    private String creatorAccount;

    /**
     * 收件人名称
     */
    @Lob
    private String toName;

    /**
     * 收件人账号
     */
    @Lob
    private String toAccount;

    /**
     * 是否发送成功
     */
    private Boolean sendSuccess;

    /**
     * 返回消息
     */
    @Lob
    private String backMsg;

    /**
     * 消息代码
     */
    private String msgCode;

    public MessageLog() {
        super();
    }

    public MessageLog(Long id) {
        super(id);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMsgType() {
        return msgType;
    }

    public void setMsgType(String msgType) {
        this.msgType = msgType;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public String getCreatorAccount() {
        return creatorAccount;
    }

    public void setCreatorAccount(String creatorAccount) {
        this.creatorAccount = creatorAccount;
    }

    public String getToName() {
        return toName;
    }

    public void setToName(String toName) {
        this.toName = toName;
    }

    public String getToAccount() {
        return toAccount;
    }

    public void setToAccount(String toAccount) {
        this.toAccount = toAccount;
    }

    public Boolean getSendSuccess() {
        return sendSuccess;
    }

    public void setSendSuccess(Boolean sendSuccess) {
        this.sendSuccess = sendSuccess;
    }

    public String getBackMsg() {
        return backMsg;
    }

    public void setBackMsg(String backMsg) {
        this.backMsg = backMsg;
    }

    public String getMsgCode() {
        return msgCode;
    }

    public void setMsgCode(String msgCode) {
        this.msgCode = msgCode;
    }
}
