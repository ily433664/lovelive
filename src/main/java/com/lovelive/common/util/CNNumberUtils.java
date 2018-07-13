package com.lovelive.common.util;

import java.util.HashMap;
import java.util.Map;

public class CNNumberUtils {

    private static Map<Character, String> cnNumberMap;

    static {
        cnNumberMap = new HashMap<>();
        cnNumberMap.put('0', "○");
        cnNumberMap.put('1', "一");
        cnNumberMap.put('2', "二");
        cnNumberMap.put('3', "三");
        cnNumberMap.put('4', "四");
        cnNumberMap.put('5', "五");
        cnNumberMap.put('6', "六");
        cnNumberMap.put('7', "七");
        cnNumberMap.put('8', "八");
        cnNumberMap.put('9', "九");
    }

    public static String getCNNumberByInteger(Integer number) {
        if (number == null) {
            return null;
        }
        String numberStr = number.toString();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < numberStr.length(); i++) {
            char c = numberStr.charAt(i);
            sb.append(cnNumberMap.get(c));
        }
        return sb.toString();
    }

    public static String getCNNumberByString(String number) {
        if (number == null) {
            return null;
        }
        number = number.trim();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < number.length(); i++) {
            char c = number.charAt(i);

            sb.append(cnNumberMap.get(c));
        }
        return sb.toString();
    }

}
