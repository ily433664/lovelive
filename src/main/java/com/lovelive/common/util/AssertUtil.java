package com.lovelive.common.util;

import java.util.Collection;
import java.util.Map;

/**
 * @author dhe
 * @Description: 对象比较、空判断等工具
 */
public class AssertUtil {

    private AssertUtil() {
    }

    private static boolean isNull(Object object) {
        return object == null;
    }

    private static boolean isNotNull(Object object) {
        return object != null;
    }

    public static boolean isEmpty(Object[] object) {
        return isNull(object) || object.length < 1;
    }

    public static boolean isNotEmpty(Object[] object) {
        return !isEmpty(object);
    }

    public static boolean isEmpty(Collection<?> collection) {
        return isNull(collection) || collection.size() < 1;
    }

    public static boolean isNotEmpty(Collection<?> collection) {
        return !isEmpty(collection);
    }

    public static boolean isEmpty(Map<?, ?> map) {
        return isNull(map) || map.size() < 1;
    }

    public static boolean isNotEmpty(Map<?, ?> map) {
        return !isEmpty(map);
    }

    public static boolean isEmpty(Object object) {
        if (object instanceof Collection) {
            return isEmpty((Collection<?>) object);
        } else if (object instanceof Map) {
            return isEmpty((Map<?, ?>) object);
        }
        return isNull(object) || "".equals(object);
    }

    public static boolean isNotEmpty(Object object) {
        return !isEmpty(object);
    }

    public static boolean isEmpty(String text) {
        return isNull(text) || text.trim().length() < 1;
    }

    public static boolean isNotEmpty(String text) {
        return !isEmpty(text);
    }

    public static boolean hasLength(String text) {
        return text != null && text.length() > 0;
    }

    public static boolean hasText(String text) {
        if (!hasLength(text)) {
            return false;
        }
        int strLen = text.length();
        for (int i = 0; i < strLen; i++) {
            if (!Character.isWhitespace(text.charAt(i))) {
                return true;
            }
        }
        return false;
    }

    /**
     * 判断指定字符是否为空白字符，空白符包含：空格、tab键、换行符
     */
    public static boolean isContain(String textToSearch, String substring) {
        if (hasLength(textToSearch) && hasLength(substring) && textToSearch.contains(substring)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 判断是否满足一下条件：
     * subType是superType的父类或接口
     * subType和superType为同一个类或同一个接口
     */
    public static boolean isAssignable(Class<?> superType, Class<?> subType) {
        if (isNotNull(superType) && isNotNull(subType) && superType.isAssignableFrom(subType)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 判断object能不能被转化为这个类：
     * 一这对象是本身类的一个对象
     * 这个对象能被转化为本身类所继承类（父类的父类等）和实现的接口（接口的父接口）强转
     */
    public static boolean isInstanceOf(Class<?> type, Object object) {
        if (isNotNull(type) && type.isInstance(object)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 判断字符串的内容是不是全是数字
     */
    public static boolean isNumeric(String text) {
        if (isEmpty(text)) {
            return false;
        }
        int size = text.length();
        for (int i = 0; i < size; i++) {
            if (!Character.isDigit(text.charAt(i))) {
                return false;
            }
        }
        return true;
    }

}

