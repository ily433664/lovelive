package com.lovelive.common.uitls;

import com.lovelive.sys.utils.Global;

import java.util.LinkedHashMap;
import java.util.Map;

public class ResponseEntity {

    /**
     * 请求成功
     */
    public static final String OK_STATUS = "0000";
    /**
     * 数据格式错误，或者必填项未填写
     */
    public static final String BAD_REQUEST_STATUS = "1002";
    /**
     * 系统限制操作，流程错误，权限不足
     */
    public static final String UNAUTHORIZED_STATUS = "1004";
    /**
     * 系统出错
     */
    public static final String SERVER_ERROR_STATUS = "1005";

    private String status = ResponseEntity.OK_STATUS;

    private StringBuilder message = new StringBuilder();

    private Map<String, Object> resultData = new LinkedHashMap<String, Object>();

    private Map<String, Object> explainData = new LinkedHashMap<String, Object>();

    public ResponseEntity() {
    }

    public ResponseEntity(String status) {
        this.status = status;
    }

    public ResponseEntity(String status, String message) {
        this.status = status;
        this.message.append(message);
    }

    public ResponseEntity(String status, String message, Map<String, Object> resultData) {
        this.status = status;
        this.message.append(message);
        this.resultData = resultData;
    }

    public ResponseEntity(String status, String message, Map<String, Object> resultData, Map<String, Object> explainData) {
        this.status = status;
        this.message.append(message);
        this.resultData = resultData;
        if (BooleanUtils.isTrue(Global.isResponseExplainDisplay())) {
            this.explainData = explainData;
        }
    }

    public ResponseEntity addMessage(String message) {
        this.message.append(message);
        return this;
    }

    public ResponseEntity addResult(String key, Object value) {
        this.resultData.put(key, value);
        return this;
    }

    public ResponseEntity putAllResult(Map<String, Object> map) {
        this.resultData.putAll(map);
        return this;
    }

    public ResponseEntity addExplain(String key, Object value) {
        if (BooleanUtils.isTrue(Global.isResponseExplainDisplay())) {
            this.explainData.put(key, value);
        }
        return this;
    }

    public ResponseEntity putAllExplain(Map<String, Object> map) {
        if (BooleanUtils.isTrue(Global.isResponseExplainDisplay())) {
            this.explainData.putAll(map);
        }
        return this;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public StringBuilder getMessage() {
        return message;
    }

    public void setMessage(StringBuilder message) {
        this.message = message;
    }

    public Map<String, Object> getResultData() {
        return resultData;
    }

    public void setResultData(Map<String, Object> resultData) {
        this.resultData = resultData;
    }

    public Map<String, Object> getExplainData() {
        return explainData;
    }

    public void setExplainData(Map<String, Object> explainData) {
        if (BooleanUtils.isTrue(Global.isResponseExplainDisplay())) {
            this.explainData = explainData;
        }
    }

}
