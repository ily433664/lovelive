package com.lovelive.sys.web;

import com.lovelive.common.util.VerifyCodeUtils;
import com.lovelive.sys.entity.OperationLog;
import com.lovelive.sys.base.BaseController;
import com.lovelive.sys.anno.LogAnnotation;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
public class LoginController extends BaseController {

    /**
     * 验证码位数
     */
    private static final int VERIFY_SIZE = 4;

    /**
     * 验证码图片宽度
     */
    private static final int VERIFY_WIDTH = 90;

    /**
     * 验证码图片高度
     */
    private static final int VERIFY_HEIGHT = 30;

    /**
     * 登录页面
     */
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login() {

        if (SecurityUtils.getSubject().isAuthenticated()) {
            //已经登录
            return "redirect:/loginAssign";
        } else {
            //未登录
            return "login";
        }
    }

    /**
     * 登录请求
     */
    @LogAnnotation(mold = OperationLog.OPER_TYPE_MULTIPLE, methods = "登录")
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(@RequestParam(FormAuthenticationFilter.DEFAULT_USERNAME_PARAM) String username, HttpServletRequest request, Model model) {

        String exception = (String) request.getAttribute(FormAuthenticationFilter.DEFAULT_ERROR_KEY_ATTRIBUTE_NAME);
        String errorMsg;
        if (exception != null) {
            if (exception.contains("AuthenticationException")) {
                errorMsg = "验证码错误 ！";
            } else if (exception.contains("UnknownAccountException") || exception.contains("IncorrectCredentialsException")) {
                errorMsg = "账号或密码错误 ！";
            } else if (exception.contains("LockedAccountException")) {
                errorMsg = "用户未激活 ！";
            } else if (exception.contains("DisabledAccountException")) {
                errorMsg = "用户被禁用 ！";
            } else if (exception.contains("AccountException")) {
                errorMsg = "用户状态异常";
            } else {
                errorMsg = "登录出错";
                System.out.println(exception);
            }
        } else {
            errorMsg = "";
        }

        model.addAttribute("errorMsg", errorMsg);    //错误信息
        model.addAttribute(FormAuthenticationFilter.DEFAULT_USERNAME_PARAM, username);    //用户名

        return "login";
    }

    /**
     * 登录成功，页面分派
     */
    @RequestMapping(value = "/loginAssign", method = RequestMethod.GET)
    public String loginAssign() {
        //判断用户是否登录
        if (SecurityUtils.getSubject().isAuthenticated()) {
            return "redirect:/success";
        } else {
            return "redirect:/login";
        }

    }

    /**
     * 成功页面
     */
    @RequestMapping(value = "/success", method = RequestMethod.GET)
    public String success() {

        //判断用户是否登录
        if (SecurityUtils.getSubject().isAuthenticated()) {
            return "redirect:/user/showUserRoleByAccount";
        } else {
            return "redirect:/login";
        }

    }

    /**
     * 验证码
     */
    @RequestMapping(value = "/getCaptcha", method = RequestMethod.GET)
    public void getCaptcha(HttpServletRequest request, HttpServletResponse response) {

        try {
            VerifyCodeUtils.outputVerifyImage(VERIFY_WIDTH, VERIFY_HEIGHT, request, response.getOutputStream(), VERIFY_SIZE);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
