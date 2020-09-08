package com.lovelive.sys.utils;

import com.lovelive.common.uitls.StringUtils;
import com.lovelive.jwt.utils.JwtUtils;
import com.lovelive.sys.entity.User;
import com.lovelive.sys.service.IUserService;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * 用户工具类
 *
 * @author dHe
 */
@Component
public class UserUtils {

    private static IUserService userService;

    @Autowired
    public UserUtils(IUserService userService) {
        UserUtils.userService = userService;
    }

    public static User getUser() {
        Claims claims = JwtUtils.checkToken();
        if (claims != null) {
            String userId = claims.getId();
            if (StringUtils.isNotEmpty(userId)) {
                //TODO
                return userService.getUserByAccount("lovelive");
                //return userService.getUserById(userId);
            }
        }
        return null;
    }

    /**
     * 获取登录标识
     *
     * @return
     */
    public static String getIdentify() {
        return JwtUtils.getToken();
    }

    public static Long getUserId() {
        User user = UserUtils.getUser();
        return user != null ? user.getId() : null;
    }

    public static String getAccount() {
        User user = UserUtils.getUser();
        return user != null ? user.getAccount() : null;
    }

    public static String getUsername() {
        User user = UserUtils.getUser();
        return user != null ? user.getUsername() : null;
    }

    public static Date getLastLoginTime() {
        User user = UserUtils.getUser();
        return user != null ? user.getLastLoginTime() : null;
    }

}
