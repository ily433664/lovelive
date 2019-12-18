package com.lovelive.common.enums;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 状态
 */
public enum RecordStatusEnums {

    /**
     * 未提交
     */
    UNSUBMITTED(0, "未提交"),
    /**
     * 未审核
     */
    UNAUDITED(1, "未审核"),
    /**
     * 通过
     */
    PASS(2, "通过"),
    /**
     * 不通过
     */
    REJECT(3, "不通过 "),
    /**
     * 退回修改
     */
    BACK(4, "退回修改 "),
    /**
     * 删除
     */
    DELETED(9, "删除");

    private Integer code;

    private String name;

    RecordStatusEnums(Integer code, String name) {
        this.code = code;
        this.name = name;
    }

    public Integer getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    private static Map<Integer, String> recordStatusCNMap = new LinkedHashMap<>();

    static {
        for (RecordStatusEnums enums : RecordStatusEnums.values()) {
            recordStatusCNMap.put(enums.getCode(), enums.getName());
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
        for (RecordStatusEnums enums : RecordStatusEnums.values()) {
            recordStatusNCMap.put(enums.getName(), enums.getCode());
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
        for (RecordStatusEnums enums : RecordStatusEnums.values()) {
            if (enums.getCode().equals(statusCode)) {
                return enums.getName();
            }
        }
        return null;
    }

    /**
     * 根据name获取code
     */
    public static Integer getStatusOfName(String statusName) {
        for (RecordStatusEnums enums : RecordStatusEnums.values()) {
            if (enums.getName().equals(statusName)) {
                return enums.getCode();
            }
        }
        return null;
    }

}
