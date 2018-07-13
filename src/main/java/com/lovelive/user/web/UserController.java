package com.lovelive.user.web;

import com.lovelive.sys.entity.OperationLog;
import com.lovelive.sys.base.BaseController;
import com.lovelive.sys.anno.LogAnnotation;
import com.lovelive.user.entity.RolePerm;
import com.lovelive.user.entity.User;
import com.lovelive.user.entity.UserRole;
import com.lovelive.user.service.IUserService;
import com.lovelive.user.util.UserUtils;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.Set;


@Controller
@RequestMapping(value = "/user")
public class UserController extends BaseController {

    private IUserService userService;

    @Autowired
    public UserController(IUserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "showUserNameById", method = RequestMethod.GET)
    public String showUserNameById(@RequestParam("id") int id, HttpServletRequest request, Model model) {

        User user = userService.getUserById(id);
        request.setAttribute("name", user.getUserName());
        model.addAttribute("name", user.getUserName());

        return "test/showUser";
    }

    @RequestMapping(value = "showUserNameByAccount", method = RequestMethod.GET)
    public String showUserNameByAccount(@RequestParam("account") String account, HttpServletRequest request, Model model) {

        User user = userService.getUserByAccount(account);
        request.setAttribute("name", user.getUserName());
        model.addAttribute("name", user.getUserName());

        return "test/showUser";
    }

    @RequestMapping(value = "showUserRoleByAccount", method = RequestMethod.GET)
    @LogAnnotation(mold = OperationLog.OPER_TYPE_QUERY, methods = "显示当前用户信息")
    public String showUserRoleByAccount(Model model) {

        //则用户能够通过shiro的校验，但是User对象为空
        if (!SecurityUtils.getSubject().isAuthenticated()) {
            SecurityUtils.getSubject().logout();
            return "redirect:/login";
        }
        User user = UserUtils.getUser();

        StringBuilder roles = new StringBuilder();
        StringBuilder perms = new StringBuilder();

        Set<UserRole> userRoles = user.getUserRoles();
        for (UserRole userRole : userRoles) {
            roles.append(userRole.getRole().getName()).append(", ");

            Set<RolePerm> rolePerms = userRole.getRole().getRolePerms();
            for (RolePerm rolePerm : rolePerms) {
                perms.append(rolePerm.getPerm().getId()).append(", ");
            }

        }

        model.addAttribute("name", user.getUserName());
        model.addAttribute("role", roles.deleteCharAt(roles.length() - 2).toString());
        model.addAttribute("perm", perms.deleteCharAt(perms.length() - 2).toString());

        return "test/showUser";
    }

}
