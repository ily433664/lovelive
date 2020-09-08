package com.lovelive.modules.sys.utils;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

/**
 * 密码工具类
 *
 * @author dHe
 */
@Component
@Lazy(false)
public class PasswordUtils {

    public static void main(String[] args) {

        String password = "lovelive";
        String hashed = BCrypt.hashpw(password, BCrypt.gensalt());
        System.out.println(hashed);

        String hashed01 = BCrypt.hashpw(password, BCrypt.gensalt());
        System.out.println(hashed01);

        String hashed02 = BCrypt.hashpw(password, BCrypt.gensalt());
        System.out.println(hashed02);

        String hashed03 = BCrypt.hashpw(password, BCrypt.gensalt());
        System.out.println(hashed03);

        String hashed04 = BCrypt.hashpw(password, BCrypt.gensalt());
        System.out.println(hashed04);

        String hashed05 = BCrypt.hashpw(password, BCrypt.gensalt());
        System.out.println(hashed05);

        String hashed2 = BCrypt.hashpw(password, BCrypt.gensalt(12));

        String candidate = "123456";

        if (BCrypt.checkpw(candidate, hashed05))
            System.out.println("It matches");
        else
            System.out.println("It does not match");
    }

    /**
     * 生成加密后的密码
     */
    public static String entryptPassword(String plainPassword) {
        return BCrypt.hashpw(plainPassword, BCrypt.gensalt());
    }

    /**
     * 验证密码
     *
     * @param plainPassword 明文密码
     * @param password      密文密码
     * @return 验证成功返回true
     */
    public static boolean validatePassword(String plainPassword, String password) {
        return BCrypt.checkpw(plainPassword, password);
    }

}
