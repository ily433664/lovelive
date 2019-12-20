package com.lovelive.common.uitls;

/**
 * sql工具类
 *
 * @author dHe
 * @date 2019-6-18
 */
public class SQLHelper {

    private static final String[] SPECIAL_CHARACTERS = {"%", "_", "[", "]", "^"};

    /**
     * like查询
     *
     * @param value
     * @return
     */
    public static String containsLikeValue(String value) {
        return "%" + SQLHelper.escapeLikeValue(value) + "%";
    }

    /**
     * like查询匹配开头
     *
     * @param value
     * @return
     */
    public static String startsWithLikeValue(String value) {
        return "%" + SQLHelper.escapeLikeValue(value);
    }

    /**
     * like查询匹配结尾
     *
     * @param value
     * @return
     */
    public static String endsWithLikeValue(String value) {
        return SQLHelper.escapeLikeValue(value) + "%";
    }

    /**
     * 转义like查询中的特殊字符
     *
     * @param value
     * @return
     */
    public static String escapeLikeValue(String value) {
        for (String str : SPECIAL_CHARACTERS) {
            value = value.replace(str, "\\" + str);
        }
        return value;
    }

}
