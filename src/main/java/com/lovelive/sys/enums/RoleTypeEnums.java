package com.lovelive.sys.enums;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 角色类型
 *
 * @author dHe
 */
public enum RoleTypeEnums {

    // states:1为使用中,0为停止使用
    /**
     * 普通用户
     */
    USER("user", "普通用户", 1),
    /**
     * 管理员
     */
    ADMIN("admin", "管理员", 1),
    /**
     * 系统管理员
     */
    SYSTEM_ADMIN("system_admin", "系统管理员", 1);

    private String value;

    private String name;

    private int states;

    RoleTypeEnums(String value, String name, int states) {
        this.value = value;
        this.name = name;
        this.states = states;
    }

    private static Map<String, String> enumVNMap = new LinkedHashMap<>();

    static {
        for (RoleTypeEnums enums : RoleTypeEnums.values()) {
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
        for (RoleTypeEnums enums : RoleTypeEnums.values()) {
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
        for (RoleTypeEnums enums : RoleTypeEnums.values()) {
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
        for (RoleTypeEnums enums : RoleTypeEnums.values()) {
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
}
