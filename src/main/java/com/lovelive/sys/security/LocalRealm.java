package com.lovelive.sys.security;

import com.lovelive.common.util.Encodes;
import com.lovelive.common.util.VerifyCodeUtils;
import com.lovelive.sys.base.EntityStatusEnum;
import com.lovelive.sys.entity.LoginLog;
import com.lovelive.sys.service.ILogService;
import com.lovelive.common.util.NetworkUtil;
import com.lovelive.user.entity.RolePerm;
import com.lovelive.user.entity.User;
import com.lovelive.user.entity.UserRole;
import com.lovelive.user.service.IUserService;
import com.lovelive.user.util.UserUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.shiro.authc.*;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.util.Collection;
import java.util.Set;

/**
 * @author dhe
 * @version V1.0
 * @ClassName: LocalRealm
 * @Description: 本地登陆Realm
 * @date 2018年1月18日 下午5:57:47
 */

public class LocalRealm extends AuthorizingRealm {

    public static final String SUCCESS_LOGIN = "登录成功！";
    /**
     * 登录页面的验证码name
     */
    public static final String CAPTCHA_NAME = "validateCode";

    private static final Log log = LogFactory.getLog(LocalRealm.class);

    private IUserService userService;

    private ILogService logService;

    /**
     * 认证回调函数, 登录时调用
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken) throws AuthenticationException {

        UsernamePasswordToken token = (UsernamePasswordToken) authcToken;


        //验证码校验
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();

        if (VerifyCodeUtils.getVerify(request) == null || !VerifyCodeUtils.getVerify(request).equals(request.getParameter(CAPTCHA_NAME).toUpperCase())) {
            throw new AuthenticationException("验证码错误");
        }

        String username = token.getUsername();
        if (StringUtils.isBlank(username)) {
            throw new UnknownAccountException("用户名不能为空");
        }

        User user = userService.getUserByAccount(username);

        if (user != null) {
            if (user.getStatus() == null) {
                throw new AccountException("用户状态异常！");
            } else if (user.getStatus().equals(EntityStatusEnum.ENABLED.getCode())) {

                //加密验证
                byte[] salt = Encodes.decodeHex(user.getPassword().substring(0, 16));
                return new SimpleAuthenticationInfo(new Principal(user), user.getPassword().substring(16), ByteSource.Util.bytes(salt), getName());

                //return new SimpleAuthenticationInfo(new Principal(user), user.getPassword(), getName());

            } else if (user.getStatus().equals(EntityStatusEnum.LOCKED.getCode())) {
                throw new LockedAccountException("用户未激活 ！");
            } else if (user.getStatus().equals(EntityStatusEnum.DISABLED.getCode())) {
                throw new DisabledAccountException("用户被禁用 ！");
            } else {
                throw new AccountException("用户状态异常！");
            }
        } else {
            throw new UnknownAccountException("用户不存在");
        }
    }

    /**
     * 授权查询回调函数, 进行鉴权但缓存中无用户的授权信息时调用
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {

        Principal principal = null;

        @SuppressWarnings("unchecked")
        Collection<Principal> pColl = principals.fromRealm(getName());
        if (pColl != null && !pColl.isEmpty()) {
            principal = pColl.iterator().next();
        }
        if (principal == null) {
            return null;
        }

        User user = userService.getUserRoleByAccount(principal.getUserName());
        if (user != null) {
            SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();

            Set<UserRole> userRoles = user.getUserRoles();
            if (userRoles != null) {
                for (UserRole ur : userRoles) {

                    if (ur.getRole() != null
                            && ur.getRole().getCode() != null
                            && !ur.getRole().getCode().isEmpty()
                            && ur.getRole().getStatus() != null
                            && ur.getRole().getStatus().equals(EntityStatusEnum.ENABLED.getCode())) {
                        info.addRole(ur.getRole().getCode());
                    }
                }
            }

            UserUtils.putCache("user", user);

            //加载启用的自定义权限信息
            Set<UserRole> urSet = user.getUserRoles();
            if (urSet != null) {
                for (UserRole ur : urSet) {
                    if (ur.getRole() != null
                            && ur.getRole().getCode() != null
                            && !ur.getRole().getCode().isEmpty()
                            && ur.getRole().getStatus() != null
                            && ur.getRole().getStatus().equals(EntityStatusEnum.ENABLED.getCode())) {
                        info.addRole(ur.getRole().getName());
                        Set<RolePerm> rolePerms = ur.getRole().getRolePerms();
                        if (rolePerms != null) {
                            for (RolePerm rp : rolePerms) {
                                if (rp.getPerm() != null
                                        && rp.getPerm().getStatus() != null
                                        && rp.getPerm().getStatus().equals(EntityStatusEnum.ENABLED.getCode())) {
                                    info.addStringPermission(rp.getPerm().getId());
                                }
                            }
                        }
                    }
                }
            }

            // 更新用户登陆信息
            LoginLog loginLog = new LoginLog();
            loginLog.setUserId(user.getId());
            loginLog.setUserAccount(user.getAccount());
            HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
            loginLog.setLoginIP(NetworkUtil.getIpAddr(request));
            loginLog.setIfSuccess(true);
            loginLog.setResult(SUCCESS_LOGIN);
            logService.saveLoginLog(loginLog);

            log.info(user.getAccount() + " login ");
            return info;
        } else {
            return null;
        }
    }

    /**
     * 设定密码校验的Hash算法与迭代次数
     */
    @PostConstruct
    public void initCredentialsMatcher() {
        HashedCredentialsMatcher matcher = new HashedCredentialsMatcher(UserUtils.HASH_ALGORITHM);
        matcher.setHashIterations(UserUtils.HASH_INTERATIONS);
        setCredentialsMatcher(matcher);
    }

    /**
     * 清空用户关联权限认证，待下次使用时重新加载
     */
    public void clearCachedAuthorizationInfo(String principal) {
        SimplePrincipalCollection principals = new SimplePrincipalCollection(principal, getName());
        clearCachedAuthorizationInfo(principals);
        UserUtils.removeCache("user");
    }

    /**
     * 清空所有关联认证
     */
    public void clearAllCachedAuthorizationInfo() {
        Cache<Object, AuthorizationInfo> cache = getAuthorizationCache();
        if (cache != null) {
            for (Object key : cache.keys()) {
                cache.remove(key);
            }
        }
    }

    @Autowired
    public void setUserService(IUserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setLogService(ILogService logService) {
        this.logService = logService;
    }

}
