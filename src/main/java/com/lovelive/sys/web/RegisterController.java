package com.lovelive.sys.web;

import com.lovelive.common.util.VerifyCodeUtils;
import com.lovelive.sys.entity.OperationLog;
import com.lovelive.sys.base.BaseController;
import com.lovelive.sys.anno.LogAnnotation;
import com.lovelive.user.entity.User;
import com.lovelive.user.service.IUserService;
import com.lovelive.user.util.UserUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.ShiroException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

@Controller
public class RegisterController extends BaseController {

    /**
     * 登录页面的验证码name
     */
    private static final String CAPTCHA_NAME = "validateCode";

    private IUserService userService;

    @Autowired
    public RegisterController(IUserService userService) {
        this.userService = userService;
    }

    /**
     * 注册
     */
    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String register() {
        if (SecurityUtils.getSubject().isAuthenticated()) {
            //已经登录
            return "redirect:/login";
        } else {
            //未登录
            return "register/registerIndex";
        }
    }

    /**
     * 注册请求
     */
    @LogAnnotation(mold = OperationLog.OPER_TYPE_MULTIPLE, methods = "注册用户")
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String register(@ModelAttribute("account") String account, @ModelAttribute("password") String password, HttpServletRequest request, Model model) {

        //验证码校验
        if (VerifyCodeUtils.getVerify(request) == null || !VerifyCodeUtils.getVerify(request).equals(request.getParameter(CAPTCHA_NAME).toUpperCase())) {
            model.addAttribute("errorVerify", "验证码错误");    //错误信息
            return "register/registerIndex";
        }

        User user = new User();
        populate(user, request);

        if (user.getAccount() == null || user.getAccount().equals("")) {
            model.addAttribute("errorAccount", "账号不能为空!");
            return "register/registerIndex";
        } else if (user.getUserName() == null || user.getUserName().equals("")) {
            model.addAttribute("errorUserName", "用户名称不能为空!");
            return "register/registerIndex";
        } else if (user.getPassword() == null || user.getPassword().equals("")) {
            model.addAttribute("errorPassword", "密码不能为空!");
            return "register/registerIndex";
        } else {
            if (userService.existedAccount(user.getAccount())) {
                model.addAttribute("errorAccount", "账号已存在!");
                return "register/registerIndex";
            }

            if (userService.existedUserName(user.getUserName())) {
                model.addAttribute("errorUserName", "用户名称已存在!");
                return "register/registerIndex";
            }
        }

        user.setPassword(UserUtils.entryptPassword(user.getPassword()));

        try {
            userService.insertUser(user);    //保存用户

            //登录
            UsernamePasswordToken token = new UsernamePasswordToken(account, password, true);
            Subject subject = SecurityUtils.getSubject();
            subject.login(token);

        } catch (ShiroException se) {
            return "register/registerSuccess";
        } catch (Exception e) {
            model.addAttribute("errorMsg", "注册失败!");
            return "register/registerIndex";
        }
        return "register/registerSuccess";
    }

}
