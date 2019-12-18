package com.lovelive.sys.entity;

import com.lovelive.common.base.BaseEntity;
import com.lovelive.sys.enums.MessageTypeEnums;

import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * @Description 消息通知
 * @Author dHe
 * @Date 2019/4/26
 */
@Entity
@Table(
        name = "t_message",
        indexes = {
                @Index(name = "idx_message_sender", columnList = "sender"),
                @Index(name = "idx_message_recipient", columnList = "recipient")
        }
)
public class Message extends BaseEntity {

    private static final long serialVersionUID = 1898989257895360795L;

    /**
     * 消息类型
     */
    private String type = MessageTypeEnums.SYSTEM.getValue();

    /**
     * 已读消息
     */
    private boolean read = false;

    /**
     * 读取时间
     */
    private Date readTime;

    /**
     * 发件人
     */
    private User sender;

    /**
     * 收件人
     */
    @NotNull
    private User recipient;

    /**
     * 标题
     */
    @NotBlank
    private String title;

    /**
     * 内容
     */
    @NotBlank
    @Lob
    private String content;

    public Message() {
        super();
    }

    public Message(String id) {
        super(id);
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean isRead() {
        return read;
    }

    public void setRead(boolean read) {
        this.read = read;
    }

    public Date getReadTime() {
        return readTime;
    }

    public void setReadTime(Date readTime) {
        this.readTime = readTime;
    }

    public User getSender() {
        return sender;
    }

    public void setSender(User sender) {
        this.sender = sender;
    }

    public User getRecipient() {
        return recipient;
    }

    public void setRecipient(User recipient) {
        this.recipient = recipient;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
