package com.lovelive.modules.sys.web;

import com.lovelive.common.base.BaseController;
import com.lovelive.modules.sys.anno.LogAnnotation;
import com.lovelive.modules.sys.entity.RolePermission;
import com.lovelive.modules.sys.entity.User;
import com.lovelive.modules.sys.entity.UserRole;
import com.lovelive.modules.sys.enums.OperationTypeEnums;
import com.lovelive.modules.sys.service.IShortLinkService;
import com.lovelive.modules.sys.service.IUserService;
import com.lovelive.modules.sys.utils.UserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

/**
 * 用户 controller
 *
 * @author dHe
 */
@RestController
@RequestMapping(value = "/user")
public class UserController extends BaseController {

    private IUserService userService;

    private IShortLinkService changeLinkService;

    @Autowired
    public UserController(IUserService userService, IShortLinkService changeLinkService) {
        this.userService = userService;
        this.changeLinkService = changeLinkService;
    }

    @RequestMapping(value = "getUserNameById/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getUserNameById(@PathVariable("id") Long id, HttpServletRequest request, Model model) {
        User user = userService.getUserById(id);
        return new ResponseEntity<>(user.getUsername(), HttpStatus.OK);
    }

    @RequestMapping(value = "getUserNameByAccount/{account}", method = RequestMethod.GET)
    public ResponseEntity<?> getUserNameByAccount(@PathVariable("account") String account, HttpServletRequest request, Model model) {
        User user = userService.getUserByAccount(account);
        return new ResponseEntity<>(user.getUsername(), HttpStatus.OK);
    }

    @LogAnnotation(mold = OperationTypeEnums.QUERY, description = "显示当前用户信息")
    @RequestMapping(value = "getUserRoleByAccount", method = RequestMethod.GET)
    public ResponseEntity<?> getUserRoleByAccount(Model model) {

        User user = UserUtils.getUser();

        StringBuilder roles = new StringBuilder();
        StringBuilder perms = new StringBuilder();

        Set<UserRole> userRoles = user.getUserRoles();
        for (UserRole userRole : userRoles) {
            roles.append(userRole.getRole().getName()).append(", ");

            Set<RolePermission> rolePermissions = userRole.getRole().getRolePermissions();
            for (RolePermission rolePermission : rolePermissions) {
                perms.append(rolePermission.getPermission().getId()).append(", ");
            }
        }
        if (roles.length() > 0) {
            roles = roles.deleteCharAt(roles.length() - 2);
        }
        if (perms.length() > 0) {
            perms = perms.deleteCharAt(roles.length() - 2);
        }

        Map<String, Object> map = new LinkedHashMap<>();
        map.put("name", user.getUsername());
        map.put("role", roles.toString());
        map.put("perm", perms.toString());

        return new ResponseEntity<>(map, HttpStatus.OK);
    }

}
