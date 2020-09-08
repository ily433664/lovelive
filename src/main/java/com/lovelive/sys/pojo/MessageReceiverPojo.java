package com.lovelive.sys.pojo;

/**
 * 消息发送内容
 *
 * @author dHe
 */
public class MessageReceiverPojo {

    // 接收人信息----------------------------------------
    /**
     * 接收人id
     */
    private String receiverId;
    /**
     * 接收人学号
     */
    private String studentNo;

    /**
     * 接收人账号
     */
    private String receiverAccount;

    /**
     * 接收人名称
     */
    private String receiverName;

    /**
     * 接收人邮箱
     */
    private String receiverEmail;

    /**
     * 接收人手机号码
     */
    private String receiverMobile;


    private String receiverOpenId;

    /**
     * 接收人微信公众号openid
     */
    private String receiverWxOpenId;

    public MessageReceiverPojo() {
    }

    public MessageReceiverPojo(String receiverId, String receiverAccount, String receiverName) {
        this.receiverId = receiverId;
        this.receiverAccount = receiverAccount;
        this.receiverName = receiverName;
    }

    public MessageReceiverPojo(String receiverId, String receiverAccount, String receiverName, String receiverEmail) {
        this.receiverId = receiverId;
        this.receiverAccount = receiverAccount;
        this.receiverName = receiverName;
        this.receiverEmail = receiverEmail;
    }

    public MessageReceiverPojo(String receiverId, String receiverAccount, String receiverName, String receiverEmail, String receiverMobile) {
        this.receiverId = receiverId;
        this.receiverAccount = receiverAccount;
        this.receiverName = receiverName;
        this.receiverEmail = receiverEmail;
        this.receiverMobile = receiverMobile;
    }

    public MessageReceiverPojo(String receiverId, String receiverAccount, String receiverName, String receiverEmail, String receiverMobile, String receiverOpenId) {
        this.receiverId = receiverId;
        this.receiverAccount = receiverAccount;
        this.receiverName = receiverName;
        this.receiverEmail = receiverEmail;
        this.receiverMobile = receiverMobile;
        this.receiverOpenId = receiverOpenId;
    }

    public MessageReceiverPojo(String receiverId, String receiverAccount, String receiverName, String receiverEmail, String receiverMobile, String receiverOpenId, String receiverWxOpenId) {
        this.receiverId = receiverId;
        this.receiverAccount = receiverAccount;
        this.receiverName = receiverName;
        this.receiverEmail = receiverEmail;
        this.receiverMobile = receiverMobile;
        this.receiverOpenId = receiverOpenId;
        this.receiverWxOpenId = receiverWxOpenId;
    }

    public String getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(String receiverId) {
        this.receiverId = receiverId;
    }

    public String getStudentNo() {
        return studentNo;
    }

    public void setStudentNo(String studentNo) {
        this.studentNo = studentNo;
    }

    public String getReceiverAccount() {
        return receiverAccount;
    }

    public void setReceiverAccount(String receiverAccount) {
        this.receiverAccount = receiverAccount;
    }

    public String getReceiverName() {
        return receiverName;
    }

    public void setReceiverName(String receiverName) {
        this.receiverName = receiverName;
    }

    public String getReceiverEmail() {
        return receiverEmail;
    }

    public void setReceiverEmail(String receiverEmail) {
        this.receiverEmail = receiverEmail;
    }

    public String getReceiverMobile() {
        return receiverMobile;
    }

    public void setReceiverMobile(String receiverMobile) {
        this.receiverMobile = receiverMobile;
    }

    public String getReceiverOpenId() {
        return receiverOpenId;
    }

    public void setReceiverOpenId(String receiverOpenId) {
        this.receiverOpenId = receiverOpenId;
    }

    public String getReceiverWxOpenId() {
        return receiverWxOpenId;
    }

    public void setReceiverWxOpenId(String receiverWxOpenId) {
        this.receiverWxOpenId = receiverWxOpenId;
    }
}
