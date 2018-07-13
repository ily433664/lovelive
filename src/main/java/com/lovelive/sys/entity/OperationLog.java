package com.lovelive.sys.entity;

import com.lovelive.sys.base.BaseEntity;

import java.io.Serializable;

/**
 * @author dhe
 * @version V1.0
 * @ClassName: OperationLog
 * @Description: 操作日志
 * @date 2018年1月18日 下午5:57:00
 */

public class OperationLog extends BaseEntity implements Serializable {

    /**
     * 操作类型：增加对象
     */
    public static final String OPER_TYPE_ADD = "ADD";
    /**
     * 操作类型：删除对象
     */
    public static final String OPER_TYPE_DELETE = "DELETE";
    /**
     * 操作类型：修改对象
     */
    public static final String OPER_TYPE_EDIT = "EDIT";
    /**
     * 操作类型：查询对象
     */
    public static final String OPER_TYPE_QUERY = "QUERY";
    /**
     * 操作类型：综合操作
     */
    public static final String OPER_TYPE_MULTIPLE = "MULTIPLE";
    private static final long serialVersionUID = -6488649748640248632L;
    private Integer id;
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
    private String operParameter;
    /**
     * 响应时间 ms
     */
    private long responseTime;
    /**
     * 操作是否成功,true:成功完成，没有系统异常   false:系统异常
     */
    private Boolean ifSuccess = true;
    /**
     * 本次操作的结果 ,如果系统异常则增加显示异常信息
     */
    private String resultContent;

    public OperationLog() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public long getResponseTime() {
        return responseTime;
    }

    public void setResponseTime(long responseTime) {
        this.responseTime = responseTime;
    }

    public Boolean getIfSuccess() {
        return ifSuccess;
    }

    public void setIfSuccess(Boolean ifSuccess) {
        this.ifSuccess = ifSuccess;
    }

    public String getResultContent() {
        return resultContent;
    }

    public void setResultContent(String resultContent) {
        this.resultContent = resultContent;
    }

}