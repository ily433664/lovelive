package com.lovelive.user.util;

import com.lovelive.common.util.Digests;
import com.lovelive.common.util.Encodes;
import com.lovelive.sys.security.Principal;
import com.lovelive.user.entity.User;
import com.lovelive.user.service.IUserService;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @ClassName: UserUtils
 * @Description: 用户工具类
 * @author dhe
 * @date 2018年1月18日 下午6:11:28
 * @version V1.0
*/

@Service
@Lazy(false)
public class UserUtils {

	public static final String HASH_ALGORITHM = "SHA-1";
	public static final int HASH_INTERATIONS = 1024;
	public static final int SALT_SIZE = 8;

	private static IUserService userService;

	@Autowired
	public UserUtils(IUserService userService) {
		UserUtils.userService = userService;
	}

	/*public static void main(String[] args) {
		System.out.println(UserUtils.entryptPassword("abc"));
		System.out.println(UserUtils.validatePassword("test", "d8d6d48961e684937665ab1dee0dba987efdfefd29ef5cbf6559d5ec"));
	}*/

	/**
	 * 生成安全的密码，生成随机的16位salt并经过1024次 sha-1 hash
	 */
	public static String entryptPassword(String plainPassword) {
		byte[] salt = Digests.generateSalt(SALT_SIZE);
		byte[] hashPassword = Digests.sha1(plainPassword.getBytes(), salt, HASH_INTERATIONS);
		return Encodes.encodeHex(salt) + Encodes.encodeHex(hashPassword);
	}

	/**
	 * 验证密码
	 * @param plainPassword 明文密码
	 * @param password 密文密码
	 * @return 验证成功返回true
	 */
	public static boolean validatePassword(String plainPassword, String password) {
		byte[] salt = Encodes.decodeHex(password.substring(0,16));
		byte[] hashPassword = Digests.sha1(plainPassword.getBytes(), salt, HASH_INTERATIONS);
		return password.equals(Encodes.encodeHex(salt) + Encodes.encodeHex(hashPassword));
	}

	public static User getUser(){
		User user = (User) getCache("user");
		if (user == null){
			Principal principal = (Principal)SecurityUtils.getSubject().getPrincipal();
			if (principal != null){
				user = userService.getUserRoleByAccount(principal.getUserName());
				putCache("user", user);
			}
		}
		return user;
	}

	public static User getUser(boolean refreshed){
		if (refreshed){
			removeCache("user");
		}
		return getUser();
	}

	public static Long getUserId() {
		return getUser().getId();
	}
	public static String getUsername() {
		return getUser().getUserName();
	}
	public static String getCommonName() {
		return getUser().getUserName();
	}
	public static Date getLastLoginTime() {
		return getUser().getLastLoginTime();
	}

	public static List<String> getRoleNameList(){
		List<String> roleKeyList = new ArrayList<>((List<String>) getCache("roleKeyList"));
		User user = getUser();
		user.getUserRoles().forEach(userRole -> {
			if (userRole.getRole() != null && userRole.getRole().getName() != null) {
				roleKeyList.add(userRole.getRole().getName());
			}
		});
		putCache("roleKeyList", roleKeyList);
		return roleKeyList;
	}

	// ============== User Cache ==============
	public static Object getCache(String key) {
		return getCacheMap().get(key);
	}

	public static void putCache(String key, Object value) {
		getCacheMap().put(key, value);
	}

	public static void removeCache(String key) {
		getCacheMap().remove(key);
	}

	private static Map<String, Object> getCacheMap(){
		Principal principal = (Principal) SecurityUtils.getSubject().getPrincipal();
		return principal != null ? principal.getCacheMap() : new HashMap<>();
	}

}
