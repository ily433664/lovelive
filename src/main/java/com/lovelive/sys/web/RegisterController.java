package com.lovelive.sys.web;

import com.lovelive.common.base.BaseController;
import com.lovelive.common.uitls.VerifyCodeUtils;
import com.lovelive.sys.anno.LogAnnotation;
import com.lovelive.sys.entity.User;
import com.lovelive.sys.enums.OperTypeEnums;
import com.lovelive.sys.service.IUserService;
import com.lovelive.sys.utils.PasswordUtils;
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
        if (false) {
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
    @LogAnnotation(mold = OperTypeEnums.MULTIPLE, description = "注册用户")
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String register(@ModelAttribute("account") String account, @ModelAttribute("password") String password, @ModelAttribute("name") String name, HttpServletRequest request, Model model) {

        //验证码校验
        if (VerifyCodeUtils.getVerify(request) == null || !VerifyCodeUtils.getVerify(request).equals(request.getParameter(CAPTCHA_NAME).toUpperCase())) {
            model.addAttribute("errorVerify", "验证码错误");    //错误信息
            return "register/registerIndex";
        }

        User user = new User();
        populate(user, request);

        String pattern = "^(?=.*?[a-z])(?=.*?\\d)(?=.*?[!@#$%&*()^.]).*$";  //密码校验,至少8位的数字+字母+特殊符号(!@#$%&*()^.)组成

        if (user.getAccount() == null || user.getAccount().equals("")) {
            model.addAttribute("errorAccount", "账号不能为空!");
            return "register/registerIndex";
        } else if (user.getUsername() == null || user.getUsername().equals("")) {
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

            if (userService.existedUsername(user.getUsername())) {
                model.addAttribute("errorUserName", "用户名称已存在!");
                return "register/registerIndex";
            }
        }

        user.setPassword(PasswordUtils.entryptPassword(user.getPassword()));

        try {
            userService.saveUser(user);    //保存用户

            //TODO  自动登录

        } catch (Exception e) {
            log.error("", e);
        }
        return "register/registerSuccess";
    }

}
