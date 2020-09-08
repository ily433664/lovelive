package com.lovelive.sys.entity;

import com.lovelive.common.base.BaseEntity;

import javax.persistence.*;

/**
 * 操作日志
 *
 * @author dhe
 */
@Entity(name = "t_operation_log")
public class OperationLog extends BaseEntity {

    private static final long serialVersionUID = -601470489372751748L;

    /**
     * 操作人账号
     */
    private String operAccount;

    /**
     * 操作人名称
     */
    private String operName;

    /**
     * 操作人角色
     */
    private String operRole;

    /**
     * IP地址
     */
    private String operIP;

    /**
     * 操作类型
     * OperationTypeEnums
     */
    private String operType;

    /**
     * 操作功能
     */
    private String operFunction;

    /**
     * 操作方法
     */
    private String operMethod;

    /**
     * 请求URI
     */
    private String operURI;

    /**
     * 请求参数
     */
    @Lob
    private String operParameter;

    /**
     * 代理
     */
    @Lob
    private String userAgent;

    /**
     * 响应时间 ms
     */
    private long responseTime;

    /**
     * 操作是否成功,true:成功完成，没有系统异常   false:系统异常
     */
    private Boolean successed;

    /**
     * 本次操作的结果 ,如果系统异常则增加显示异常信息
     */
    private String resultContent;

    public OperationLog() {
        super();
    }

    public OperationLog(Long id) {
        super(id);
    }

    public String getOperAccount() {
        return operAccount;
    }

    public void setOperAccount(String operAccount) {
        this.operAccount = operAccount;
    }

    public String getOperName() {
        return operName;
    }

    public void setOperName(String operName) {
        this.operName = operName;
    }

    public String getOperRole() {
        return operRole;
    }

    public void setOperRole(String operRole) {
        this.operRole = operRole;
    }

    public String getOperIP() {
        return operIP;
    }

    public void setOperIP(String operIP) {
        this.operIP = operIP;
    }

    public String getOperType() {
        return operType;
    }

    public void setOperType(String operType) {
        this.operType = operType;
    }

    public String getOperFunction() {
        return operFunction;
    }

    public void setOperFunction(String operFunction) {
        this.operFunction = operFunction;
    }

    public String getOperMethod() {
        return operMethod;
    }

    public void setOperMethod(String operMethod) {
        this.operMethod = operMethod;
    }

    public String getOperURI() {
        return operURI;
    }

    public void setOperURI(String operURI) {
        this.operURI = operURI;
    }

    public String getOperParameter() {
        return operParameter;
    }

    public void setOperParameter(String operParameter) {
        this.operParameter = operParameter;
    }

    public String getUserAgent() {
        return userAgent;
    }

    public void setUserAgent(String userAgent) {
        this.userAgent = userAgent;
    }

    public long getResponseTime() {
        return responseTime;
    }

    public void setResponseTime(long responseTime) {
        this.responseTime = responseTime;
    }

    public Boolean getSuccessed() {
        return successed;
    }

    public void setSuccessed(Boolean successed) {
        this.successed = successed;
    }

    public String getResultContent() {
        return resultContent;
    }

    public void setResultContent(String resultContent) {
        this.resultContent = resultContent;
    }

}