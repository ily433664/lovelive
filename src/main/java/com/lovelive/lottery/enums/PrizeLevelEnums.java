package com.lovelive.lottery.enums;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 奖励等级
 *
 * @author dHe
 * @date 2019-5-9
 */
public enum PrizeLevelEnums {

    //states:1为使用中,0为停止使用
    /**
     * 普通
     */
    N(0, "normal", "普通", 1),
    /**
     * 稀有
     */
    R(1, "rare", "稀有", 1),
    /**
     * 超级稀有
     */
    SR(2, "super_rare", "超级稀有", 1),
    /**
     * 较高级的超级稀有
     */
    SSR(3, "superior_super_rare", "较高级的超级稀有", 1),
    /**
     * 极度稀有
     */
    UR(4, "ultra_rare", "极度稀有", 1);

    private Integer value;

    private String code;

    private String name;

    private int states;

    PrizeLevelEnums(Integer value, String code, String name, int states) {
        this.value = value;
        this.code = code;
        this.name = name;
        this.states = states;
    }

    private static Map<Integer, String> enumVNMap = new LinkedHashMap<>();

    static {
        for (PrizeLevelEnums enums : PrizeLevelEnums.values()) {
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
        for (PrizeLevelEnums enums : PrizeLevelEnums.values()) {
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
        for (PrizeLevelEnums enums : PrizeLevelEnums.values()) {
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
        for (PrizeLevelEnums enums : PrizeLevelEnums.values()) {
            if (enums.getName().equals(statusName)) {
                return enums.getValue();
            }
        }
        return null;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getStates() {
        return states;
    }

    public void setStates(int states) {
        this.states = states;
    }
}
