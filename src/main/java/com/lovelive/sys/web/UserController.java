package com.lovelive.sys.web;

import com.lovelive.common.base.BaseController;
import com.lovelive.sys.anno.LogAnnotation;
import com.lovelive.sys.entity.RolePerm;
import com.lovelive.sys.entity.User;
import com.lovelive.sys.entity.UserRole;
import com.lovelive.sys.enums.OperTypeEnums;
import com.lovelive.sys.service.IChangeLinkService;
import com.lovelive.sys.service.IUserService;
import com.lovelive.sys.utils.UserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


@Controller
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
    public String getUserNameById(@RequestParam("id") String id, HttpServletRequest request, Model model) {

        User user = userService.getUserById(id);
        request.setAttribute("name", user.getUsername());
        model.addAttribute("name", user.getUsername());

        changeLinkService.getChangeLinkByLongURL("aaa");

        return "test/showUser";
    }

    @RequestMapping(value = "getUserNameByAccount", method = RequestMethod.GET)
    public String getUserNameByAccount(@RequestParam("account") String account, HttpServletRequest request, Model model) {

        User user = userService.getUserByAccount(account);
        request.setAttribute("name", user.getUsername());
        model.addAttribute("name", user.getUsername());

        return "test/showUser";
    }

    @LogAnnotation(mold = OperTypeEnums.QUERY, description = "显示当前用户信息")
    @RequestMapping(value = "getUserRoleByAccount", method = RequestMethod.GET)
    public String getUserRoleByAccount(Model model) {

        User user = UserUtils.getUser();

        StringBuilder roles = new StringBuilder();
        StringBuilder perms = new StringBuilder();

        List<UserRole> userRoles = user.getUserRoles();
        for (UserRole userRole : userRoles) {
            roles.append(userRole.getRole().getName()).append(", ");

            List<RolePerm> rolePerms = userRole.getRole().getRolePerms();
            for (RolePerm rolePerm : rolePerms) {
                perms.append(rolePerm.getPerm().getId()).append(", ");
            }
        }
        if (roles.length() > 0) {
            roles = roles.deleteCharAt(roles.length() - 2);
        }
        if (perms.length() > 0) {
            perms = perms.deleteCharAt(roles.length() - 2);
        }

        model.addAttribute("name", user.getUsername());
        model.addAttribute("role", roles.toString());
        model.addAttribute("perm", perms.toString());

        return "test/showUser";
    }

}
