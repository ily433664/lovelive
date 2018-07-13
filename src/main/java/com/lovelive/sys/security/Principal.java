package com.lovelive.sys.security;

import com.lovelive.user.entity.User;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * @author dhe
 * @version V1.0
 * @ClassName: Principal
 * @Description: 授权用户信息，权限模块中登录用户信息封装
 * @date 2018年1月18日 下午5:56:40
 */

public class Principal implements Serializable {

    private static final long serialVersionUID = 1928114187860135253L;

    private Long id;
    private String userName;
    private String name;
    private Map<String, Object> cacheMap;

    public Principal(User user) {
        this.id = user.getId();
        this.userName = user.getAccount();
        this.name = user.getUserName();
    }

    public Principal(Long id, String account, String userName) {
        this.id = id;
        this.userName = account;
        this.name = userName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Map<String, Object> getCacheMap() {
        if (cacheMap == null) {
            cacheMap = new HashMap<>();
        }
        return cacheMap;
    }
}
