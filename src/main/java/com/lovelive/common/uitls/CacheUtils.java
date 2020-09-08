package com.lovelive.common.uitls;

import com.lovelive.modules.sys.entity.MailConfig;
import com.lovelive.modules.sys.entity.Permission;
import com.lovelive.modules.sys.entity.Role;
import com.lovelive.modules.sys.service.IMailConfigService;
import com.lovelive.modules.sys.service.IPermissionService;
import com.lovelive.modules.sys.service.IRoleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

/**
 * Cache 工具类
 *
 * @author dHe
 */
public class CacheUtils {

    private static final Logger log = LoggerFactory.getLogger(CacheUtils.class);

    private static RedisUtils redisUtils = SpringContextHolder.getBean(RedisUtils.class);

    private static final String SYS_CACHE = "sys-cache:";

    public static final String ROLE_CACHE = "role-cache:";

    public static final String PERMISSION_CACHE = "permission-cache:";

    public static final String TOKEN_CACHE = "token-cache:";

    public static final String MAIL_CONFIG_CACHE = "mail-config-cache";

    private CacheUtils() {
    }

    public static Object get(String key) {
        return get(SYS_CACHE, key);
    }

    public static Object get(String cacheName, String key) {
        return redisUtils.get(cacheName + key);
    }

    public static void put(String key, Object value) {
        put(SYS_CACHE, key, value);
    }

    public static void put(String key, Object value, int seconds) {
        put(SYS_CACHE, key, value, seconds);
    }

    public static void put(String cacheName, String key, Object value) {
        redisUtils.set(cacheName + key, value);
    }

    public static void put(String cacheName, String key, Object value, int seconds) {
        redisUtils.set(cacheName + key, value, seconds);
    }

    public static void remove(String key) {
        remove(SYS_CACHE, key);
    }

    public static void remove(String cacheName, String key) {
        redisUtils.delete(cacheName + key);
    }

    public static Object getAndRemove(String key) {
        Object object = get(key);
        if (object != null) {
            remove(key);
        }
        return object;
    }

    public static Object getAndRemove(String cacheName, String key) {
        Object object = get(cacheName, key);
        if (object != null) {
            remove(cacheName, key);
        }
        return object;
    }

    public static Object expire(String key) {
        return redisUtils.expire(SYS_CACHE + key);
    }

    public static void expire(String cacheName, String key) {
        redisUtils.expire(cacheName + key);
    }

    public static void clearRedisCache(byte[] pattern) {
        Set<byte[]> keys = redisUtils.keys(pattern);
        if (keys != null) {
            keys.forEach(key -> {
                redisUtils.delete(key);
            });
        }
    }

    public static void clearRedisCache(String pattern) {
        if (!pattern.endsWith("*")) {
            pattern += "*";
        }
        Set<String> keys = redisUtils.keys(pattern);
        if (keys != null) {
            keys.forEach(key -> {
                redisUtils.delete(key);
            });
        }
    }

    public static void clearAllRedisCache() {
        redisUtils.flushDB();
    }

    /**
     * 获取所有的 Role
     *
     * @return
     */
    public static List<Role> getAllRole() {
        List<Role> roleList = (List<Role>) get(CacheUtils.ROLE_CACHE);
        if (CollectionUtils.isEmpty(roleList)) {

            synchronized (CacheUtils.ROLE_CACHE) {
                roleList = (List<Role>) get(CacheUtils.ROLE_CACHE);
                if (CollectionUtils.isEmpty(roleList)) {
                    IRoleService roleService = SpringContextHolder.getBean(IRoleService.class);
                    roleList = roleService.findAllNormal();
                    if (CollectionUtils.isEmpty(roleList)) {
                        roleList = new ArrayList<>();
                    }
                    put(CacheUtils.ROLE_CACHE, roleList);
                }
            }
        }
        return roleList;
    }

    /**
     * 获取所有的 Permission
     *
     * @return
     */
    public static List<Permission> getAllPermission() {
        List<Permission> permissionList = (List<Permission>) get(CacheUtils.PERMISSION_CACHE);
        if (CollectionUtils.isEmpty(permissionList)) {

            synchronized (CacheUtils.PERMISSION_CACHE) {
                permissionList = (List<Permission>) get(CacheUtils.PERMISSION_CACHE);
                if (CollectionUtils.isEmpty(permissionList)) {
                    IPermissionService permissionService = SpringContextHolder.getBean(IPermissionService.class);
                    permissionList = permissionService.findAllNormal();
                    if (CollectionUtils.isEmpty(permissionList)) {
                        permissionList = new ArrayList<>();
                    }
                    put(CacheUtils.PERMISSION_CACHE, permissionList);
                }
            }
        }
        return permissionList;
    }

    /**
     * 获取邮箱配置
     *
     * @return
     */
    public static MailConfig getMailConfig() {
        MailConfig mailConfig = (MailConfig) get(CacheUtils.MAIL_CONFIG_CACHE);
        if (mailConfig == null) {

            synchronized (CacheUtils.MAIL_CONFIG_CACHE) {
                mailConfig = (MailConfig) get(CacheUtils.MAIL_CONFIG_CACHE);
                if (mailConfig == null) {
                    IMailConfigService mailConfigService = SpringContextHolder.getBean(IMailConfigService.class);
                    mailConfig = mailConfigService.getMailConfig();
                    boolean update = false;
                    if (mailConfig == null) {
                        //初始化
                        mailConfig = new MailConfig();
                        update = true;
                    }
                    if (update) {
                        mailConfigService.saveMailConfig(mailConfig);
                    }
                    put(CacheUtils.MAIL_CONFIG_CACHE, mailConfig);
                }
            }
        }
        return mailConfig;
    }

}
