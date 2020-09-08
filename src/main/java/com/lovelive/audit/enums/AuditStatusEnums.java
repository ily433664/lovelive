package com.lovelive.audit.enums;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 审核状态
 *
 * @author dHe
 */
public enum AuditStatusEnums {

    // states:1为使用中,0为停止使用
    /**
     * 待审核
     */
    PENDING(0, "待审核", 1),
    /**
     * 审核通过
     */
    ADOPT(1, "审核通过", 1),
    /**
     * 审核不通过
     */
    REJECT(-1, "审核不通过", 1),
    /**
     * 撤销审核
     */
    UNDO(2, "撤销审核", 1);

    private Integer value;

    private String name;

    private int states;

    AuditStatusEnums(Integer value, String name, int states) {
        this.value = value;
        this.name = name;
        this.states = states;
    }

    private static Map<Integer, String> enumVNMap = new LinkedHashMap<>();

    static {
        for (AuditStatusEnums enums : AuditStatusEnums.values()) {
            if (enums.states == 1) {
                enumVNMap.put(enums.getValue(), enums.getName());
            }
        }
    }

    /**
     * 获取实体类的状态集合, value-name
     */
    public static Map<Integer, String> getEnumVNMap() {
        return enumVNMap;
    }

    private static Map<String, Integer> enumNVMap = new LinkedHashMap<>();

    static {
        for (AuditStatusEnums enums : AuditStatusEnums.values()) {
            if (enums.states == 1) {
                enumNVMap.put(enums.getName(), enums.getValue());
            }
        }
    }

    /**
     * 获取实体类的状态集合, name-value
     */
    public Map<String, Integer> getEnumNVMap() {
        return enumNVMap;
    }

    /**
     * 根据 value 获取 name
     */
    public String getNameOfValue(Integer value) {
        for (AuditStatusEnums enums : AuditStatusEnums.values()) {
            if (enums.getValue().equals(value)) {
                return enums.getName();
            }
        }
        return null;
    }

    /**
     * 根据 name 获取 value
     */
    public Integer getValueOfName(String statusName) {
        for (AuditStatusEnums enums : AuditStatusEnums.values()) {
            if (enums.getName().equals(statusName)) {
                return enums.getValue();
            }
        }
        return null;
    }

    public Integer getValue() {
        return value;
    }

    public String getName() {
        return name;
    }
}
