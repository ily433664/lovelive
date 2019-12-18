package com.lovelive.sys.enums;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 操作类型
 */
public enum OperTypeEnums {

    /**
     * 查询
     */
    QUERY("query", "查询"),
    /**
     * 增加
     */
    ADD("add", "增加"),
    /**
     * 修改
     */
    EDIT("edit", "修改"),
    /**
     * 删除
     */
    DELETE("delete", "删除"),
    /**
     * 导出
     */
    EXPORT("export", "导出"),
    /**
     * 导入
     */
    IMPORT("import", "导入"),
    /**
     * 综合操作
     */
    MULTIPLE("multiple", "综合操作");

    private String code;

    private String name;

    OperTypeEnums(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public static String code(OperTypeEnums enums) {
        return enums.getCode();
    }

    private static Map<String, String> recordStatusCNMap = new LinkedHashMap<>();

    static {
        for (OperTypeEnums enums : OperTypeEnums.values()) {
            recordStatusCNMap.put(enums.getCode(), enums.getName());
        }
    }

    /**
     * 获取记录的审核状态集合, code-name
     */
    public static Map<String, String> getRecordStatusCNMap() {
        return recordStatusCNMap;
    }

    private static Map<String, String> recordStatusNCMap = new LinkedHashMap<>();

    static {
        for (OperTypeEnums enums : OperTypeEnums.values()) {
            recordStatusNCMap.put(enums.getName(), enums.getCode());
        }
    }

    /**
     * 获取记录的审核状态集合, name-code
     */
    public static Map<String, String> getRecordStatusNCMap() {
        return recordStatusNCMap;
    }

    /**
     * 根据code获取name
     */
    public static String getStatusOfCode(String statusCode) {
        for (OperTypeEnums enums : OperTypeEnums.values()) {
            if (enums.getCode().equals(statusCode)) {
                return enums.getName();
            }
        }
        return null;
    }

    /**
     * 根据name获取code
     */
    public static String getStatusOfName(String statusName) {
        for (OperTypeEnums enums : OperTypeEnums.values()) {
            if (enums.getName().equals(statusName)) {
                return enums.getCode();
            }
        }
        return null;
    }

}
