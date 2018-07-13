package com.lovelive.sys.base;

import java.util.LinkedHashMap;
import java.util.Map;

public enum RecordStatusEnum {

    /**
     * 状态：未提交
     */
    UNSUBMITTED(0, "未提交"),
    /**
     * 状态：未审核
     */
    UNAUDITED(1, "未审核"),
    /**
     * 状态：通过
     */
    PASS(2, "通过"),
    /**
     * 状态：不通过
     */
    REJECT(3, "不通过 "),
    /**
     * 状态：退回修改
     */
    BACK(4, "退回修改 "),
    /**
     * 状态：删除
     */
    DELETED(9, "删除");

    private Integer code;

    private String name;

    RecordStatusEnum(Integer code, String name) {
        this.code = code;
        this.name = name;
    }

    private static Map<Integer, String> recordStatusCNMap = new LinkedHashMap<>();

    static {
        for (RecordStatusEnum recordStatus : RecordStatusEnum.values()) {
            recordStatusCNMap.put(recordStatus.getCode(), recordStatus.getName());
        }
    }

    /**
     * 获取记录的审核状态集合, code-name
     */
    public static Map<Integer, String> getRecordStatusCNMap() {
        return recordStatusCNMap;
    }

    private static Map<String, Integer> recordStatusNCMap = new LinkedHashMap<>();

    static {
        for (RecordStatusEnum recordStatus : RecordStatusEnum.values()) {
            recordStatusNCMap.put(recordStatus.getName(), recordStatus.getCode());
        }
    }

    /**
     * 获取记录的审核状态集合, name-code
     */
    public static Map<String, Integer> getRecordStatusNCMap() {
        return recordStatusNCMap;
    }

    /**
     * 根据code获取name
     */
    public static String getStatusOfCode(String statusCode) {
        for (RecordStatusEnum recordStatus : RecordStatusEnum.values()) {
            if (recordStatus.getCode().equals(statusCode)) {
                return recordStatus.getName();
            }
        }
        return null;
    }

    /**
     * 根据name获取code
     */
    public static Integer getStatusOfName(String statusName) {
        for (RecordStatusEnum recordStatus : RecordStatusEnum.values()) {
            if (recordStatus.getName().equals(statusName)) {
                return recordStatus.getCode();
            }
        }
        return null;
    }

    public Integer getCode() {
        return code;
    }

    public String getName() {
        return name;
    }
}
