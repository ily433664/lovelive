package com.lovelive.sys.web;

import com.lovelive.common.base.BaseController;
import com.lovelive.sys.anno.LogAnnotation;
import com.lovelive.sys.entity.RolePermission;
import com.lovelive.sys.entity.User;
import com.lovelive.sys.entity.UserRole;
import com.lovelive.sys.enums.OperationTypeEnums;
import com.lovelive.sys.service.IChangeLinkService;
import com.lovelive.sys.service.IUserService;
import com.lovelive.sys.utils.UserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * 用户 controller
 *
 * @author dHe
 * @date 2019-4-26
 */
@RestController
@RequestMapping(value = "/user")
public class UserController extends BaseController {

    private IUserService userService;

    private IChangeLinkService changeLinkService;

    @Autowired
    public UserController(IUserService userService, IChangeLinkService changeLinkService) {
        this.userService = userService;
        this.changeLinkService = changeLinkService;
    }

    @RequestMapping(value = "getUserNameById", method = RequestMethod.GET)
    public ResponseEntity<?> getUserNameById(@RequestParam("id") String id, HttpServletRequest request, Model model) {
        User user = userService.getUserById(id);
        return new ResponseEntity<>(user.getUsername(), HttpStatus.OK);
    }

    @RequestMapping(value = "getUserNameByAccount", method = RequestMethod.GET)
    public ResponseEntity<?> getUserNameByAccount(@RequestParam("account") String account, HttpServletRequest request, Model model) {
        User user = userService.getUserByAccount(account);
        return new ResponseEntity<>(user.getUsername(), HttpStatus.OK);
    }

    @LogAnnotation(mold = OperationTypeEnums.QUERY, description = "显示当前用户信息")
    @RequestMapping(value = "getUserRoleByAccount", method = RequestMethod.GET)
    public ResponseEntity<?> getUserRoleByAccount(Model model) {

        User user = UserUtils.getUser();

        StringBuilder roles = new StringBuilder();
        StringBuilder perms = new StringBuilder();

        List<UserRole> userRoles = user.getUserRoles();
        for (UserRole userRole : userRoles) {
            roles.append(userRole.getRole().getName()).append(", ");

            List<RolePermission> rolePermissions = userRole.getRole().getRolePermissions();
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
