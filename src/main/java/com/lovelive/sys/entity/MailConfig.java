package com.lovelive.sys.entity;

import com.lovelive.common.base.BaseEntity;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 短链接 Entity
 */
@Entity
@Table
public class MailConfig extends BaseEntity {

    /**
     * 服务器
     */
    private String host;
    /**
     * 端口
     */
    private Integer port;
    /**
     * 邮箱账号
     */
    private String username;
    /**
     * 邮箱授权码
     */
    private String password;
    /**
     * 发送人
     */
    private String emailFrom;
    /**
     * 超时时间
     */
    private String timeout;
    /**
     * 发件人名称
     */
    private String personal;

    public MailConfig() {
        super();
    }

    public MailConfig(String id) {
        super(id);
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmailFrom() {
        return emailFrom;
    }

    public void setEmailFrom(String emailFrom) {
        this.emailFrom = emailFrom;
    }

    public String getTimeout() {
        return timeout;
    }

    public void setTimeout(String timeout) {
        this.timeout = timeout;
    }

    public String getPersonal() {
        return personal;
    }

    public void setPersonal(String personal) {
        this.personal = personal;
    }
}


