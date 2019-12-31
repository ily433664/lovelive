package com.lovelive.sys.enums;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 操作类型
 *
 * @author dhe
 * @date 2018-1-18
 */
public enum OperationTypeEnums {

    // states:1为使用中,0为停止使用
    /**
     * 查询
     */
    QUERY("query", "查询", 1),
    /**
     * 增加
     */
    ADD("add", "增加", 1),
    /**
     * 修改
     */
    EDIT("edit", "修改", 1),
    /**
     * 删除
     */
    DELETE("delete", "删除", 1),
    /**
     * 导出
     */
    EXPORT("export", "导出", 1),
    /**
     * 导入
     */
    IMPORT("import", "导入", 1),
    /**
     * 综合操作
     */
    MULTIPLE("multiple", "综合操作", 1);

    private String value;

    private String name;

    private int states;

    OperationTypeEnums(String value, String name, int states) {
        this.value = value;
        this.name = name;
        this.states = states;
    }

    private static Map<String, String> enumVNMap = new LinkedHashMap<>();

    static {
        for (OperationTypeEnums enums : OperationTypeEnums.values()) {
            if (enums.states == 1) {
                enumVNMap.put(enums.getValue(), enums.getName());
            }
        }
    }

    /**
     * 获取实体类的状态集合, value-name
     */
    public static Map<String, String> getEnumVNMap() {
        return enumVNMap;
    }

    private static Map<String, String> enumNVMap = new LinkedHashMap<>();

    static {
        for (OperationTypeEnums enums : OperationTypeEnums.values()) {
            if (enums.states == 1) {
                enumNVMap.put(enums.getName(), enums.getValue());
            }
        }
    }

    /**
     * 获取实体类的状态集合, name-value
     */
    public Map<String, String> getEnumNVMap() {
        return enumNVMap;
    }

    /**
     * 根据 value 获取 name
     */
    public String getNameOfValue(String value) {
        for (OperationTypeEnums enums : OperationTypeEnums.values()) {
            if (enums.getValue().equals(value)) {
                return enums.getName();
            }
        }
        return null;
    }

    /**
     * 根据 name 获取 value
     */
    public String getValueOfName(String statusName) {
        for (OperationTypeEnums enums : OperationTypeEnums.values()) {
            if (enums.getName().equals(statusName)) {
                return enums.getValue();
            }
        }
        return null;
    }

    public String getValue() {
        return value;
    }

    public String getName() {
        return name;
    }

    public int getStates() {
        return states;
    }
}
