package com.lovelive.common.enums;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Entity状态
 *
 * @author dHe
 * @date 2019-4-26
 */
public enum EntityStatusEnums {

    // states:1为使用中,0为停止使用
    /**
     * 状态：正常
     */
    NORMAL(0, "正常", 1),
    /**
     * 状态：锁定
     */
    LOCKED(1, "锁定", 1),
    /**
     * 状态：停用
     */
    DISABLED(9, "停用", 1);

    private Integer value;

    private String name;

    private int states;

    EntityStatusEnums(Integer value, String name, int states) {
        this.value = value;
        this.name = name;
        this.states = states;
    }

    private static Map<Integer, String> enumVNMap = new LinkedHashMap<>();

    static {
        for (EntityStatusEnums enums : EntityStatusEnums.values()) {
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
        for (EntityStatusEnums enums : EntityStatusEnums.values()) {
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
        for (EntityStatusEnums enums : EntityStatusEnums.values()) {
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
        for (EntityStatusEnums enums : EntityStatusEnums.values()) {
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

    public int getStates() {
        return states;
    }
}
