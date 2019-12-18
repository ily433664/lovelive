package com.lovelive.common.uitls;

import java.util.regex.Pattern;

/**
 * @author KFB
 */
public class RegExpValidatorUtil {

    /**
     * 非负整数
     */
    public static final String DIGIT = "^\\d+$";

    /**
     * 固定电话
     */
    public static final String TEL = "(^0\\d{2,3}-)?\\d{7,8}(-\\d{1,6})?$";

    /**
     * 手机号码
     */
    public static final String PHONE = "^1[0-9]{10}$";

    /**
     * 手机号码
     */
    public static final String TELORPHONE = TEL + "|" + PHONE;

    /**
     * 密码
     */
    public static final String PASSWORD = "^(?=.*?[a-zA-Z])(?=.*?\\d)(?=.*?[!@#$%&*()^.]).*$";

    /**
     * 邮箱
     */
    public static final String EMAIL = "[\\w!#$%&'*+/=?^_`{|}~-]+(?:\\.[\\w!#$%&'*+/=?^_`{|}~-]+)*@(?:[\\w](?:[\\w-]*[\\w])?\\.)+[\\w](?:[\\w-]*[\\w])?";

    /**
     * 身份证
     */
    public static final String ID_CARD = "(^(\\d{17})([0-9]|X|x)$)|(^\\d{15}$)|(^[A-Z][0-9]{9}$)|(^[A-Z][0-9]{6}\\([0-9A]\\)$)|(^[157][0-9]{6}\\([0-9]\\)$)";

    /**
     * 邮编
     */
    public static final String ZIP_CODE = "^[0-9]{6}$";


    public static final String FLOAT = "^0*\\d{1,2}(\\.\\d+)?$|^100$";

    /**
     * 防止被实例化
     */
    private RegExpValidatorUtil() {
    }

    /**
     * 校验非负整数
     *
     * @param str
     * @return
     */
    public static boolean isDigit(String str) {
        return match(DIGIT, str);
    }

    /**
     * 校验小于100的非负浮点数
     *
     * @param str
     * @return
     */
    public static boolean isFloat(String str) {
        return match(FLOAT, str);
    }

    /**
     * 校验固定电话
     *
     * @param str
     * @return
     */
    public static boolean isTel(String str) {
        return match(TEL, str);
    }

    /**
     * 校验手机号
     *
     * @param str
     * @return
     */
    public static boolean isPhone(String str) {
        return match(PHONE, str);
    }

    /**
     * 校验电话
     *
     * @param str
     * @return
     */
    public static boolean isTelOrPhone(String str) {
        return match(TELORPHONE, str);
    }

    /**
     * 校验邮箱地址
     *
     * @param str
     * @return
     */
    public static boolean isEmail(String str) {
        return match(EMAIL, str);
    }

    /**
     * 校验身份证号
     *
     * @param str
     * @return
     */
    public static boolean isIDCard(String str) {
        return match(ID_CARD, str);
    }

    /**
     * 校验邮编
     *
     * @param str
     * @return
     */
    public static boolean isZipCode(String str) {
        return match(ZIP_CODE, str);
    }

    /**
     * 校验密码由数字+字母+特殊符号(!@#$%&*()^.)组成
     *
     * @param str
     * @return
     */
    public static boolean isValidPwd(String str) {
        return match(PASSWORD, str);
    }

    /**
     * 校验正则表达式
     *
     * @param regex
     * @param str
     * @return
     */
    private static boolean match(String regex, String str) {
        if (str != null) {
            return Pattern.matches(regex, str);
        }
        return false;
    }

}
