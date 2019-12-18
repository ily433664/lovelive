package com.lovelive.sys.utils;

import com.lovelive.sys.entity.User;
import com.lovelive.sys.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author dhe
 * @version V1.0
 * @ClassName: UserUtils
 * @Description: 用户工具类
 * @date 2018年1月18日 下午6:11:28
 */

@Component
public class UserUtils {

    private static IUserService userService;

    @Autowired
    public UserUtils(IUserService userService) {
        UserUtils.userService = userService;
    }

    public static User getUser() {
        //TODO
        User user = userService.getUserRoleByAccount("lovelive");
        return user;
    }

    public static String getUserId() {
        return getUser().getId();
    }

    public static String getAccount() {
        return getUser().getAccount();
    }

    public static String getUsername() {
        return getUser().getUsername();
    }

    public static Date getLastLoginTime() {
        return getUser().getLastLoginTime();
    }

}
