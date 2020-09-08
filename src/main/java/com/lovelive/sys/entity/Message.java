package com.lovelive.sys.entity;

import com.lovelive.common.base.BaseEntity;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * 消息
 *
 * @author dHe
 */
@Entity(name = "t_message")
public class Message extends BaseEntity {

    private static final long serialVersionUID = -1434476022778890148L;

    /**
     * 消息类型
     * MessageTypeEnums
     */
    @NotBlank
    private String type;

    /**
     * 已读消息
     */
    private boolean ready = false;

    /**
     * 读取时间
     */
    private Date readTime;

    /**
     * 发件人
     */
    @NotNull
    @ManyToOne(fetch = FetchType.EAGER)
    private User sender;

    /**
     * 收件人
     */
    @NotNull
    @ManyToOne(fetch = FetchType.EAGER)
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

    public Message(Long id) {
        super(id);
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean isReady() {
        return ready;
    }

    public void setReady(boolean ready) {
        this.ready = ready;
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
