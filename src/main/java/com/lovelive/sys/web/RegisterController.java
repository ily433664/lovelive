package com.lovelive.sys.web;

import com.lovelive.common.base.BaseController;
import com.lovelive.common.uitls.VerifyCodeUtils;
import com.lovelive.sys.anno.LogAnnotation;
import com.lovelive.sys.entity.User;
import com.lovelive.sys.enums.OperationTypeEnums;
import com.lovelive.sys.service.IUserService;
import com.lovelive.sys.utils.PasswordUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * 注册 controller
 *
 * @author dHe
 */
@RestController
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
    public ResponseEntity<?> register() {
        try {
            //TODO  判断是否登录
            if (false) {
                //已经登录
                return new ResponseEntity<>("is login", HttpStatus.OK);
            } else {
                //未登录
                return new ResponseEntity<>("not login", HttpStatus.OK);
            }
        } catch (Exception e) {
            log.error("", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * 注册请求
     */
    @LogAnnotation(mold = OperationTypeEnums.MULTIPLE, description = "注册用户")
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ResponseEntity<?> register(@ModelAttribute("account") String account, @ModelAttribute("password") String password, @ModelAttribute("name") String name, HttpServletRequest request, Model model) {
        try {
            //验证码校验
            if (VerifyCodeUtils.getVerify(request) == null || !VerifyCodeUtils.getVerify(request).equals(request.getParameter(CAPTCHA_NAME).toUpperCase())) {
                return new ResponseEntity<>("验证码错误", HttpStatus.FORBIDDEN);
            }

            User user = new User();
            populate(user, request);

            String pattern = "^(?=.*?[a-z])(?=.*?\\d)(?=.*?[!@#$%&*()^.]).*$";  //密码校验,至少8位的数字+字母+特殊符号(!@#$%&*()^.)组成

            if (user.getAccount() == null || user.getAccount().equals("")) {
                return new ResponseEntity<>("账号不能为空", HttpStatus.FORBIDDEN);
            } else if (user.getUsername() == null || user.getUsername().equals("")) {
                return new ResponseEntity<>("用户名称不能为空", HttpStatus.FORBIDDEN);
            } else if (user.getPassword() == null || user.getPassword().equals("")) {
                return new ResponseEntity<>("密码不能为空", HttpStatus.FORBIDDEN);
            } else {
                if (userService.existedAccount(user.getAccount())) {
                    return new ResponseEntity<>("账号已存在", HttpStatus.FORBIDDEN);
                }
                if (userService.existedUsername(user.getUsername())) {
                    return new ResponseEntity<>("用户名称已存在", HttpStatus.FORBIDDEN);
                }
            }

            user.setPassword(PasswordUtils.entryptPassword(user.getPassword()));

            try {
                userService.saveUser(user);    //保存用户

                //TODO  自动登录

            } catch (Exception e) {
                log.error("", e);
            }
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            log.error("", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

}
