package com.lovelive.sys.web;

import com.lovelive.common.base.BaseController;
import com.lovelive.common.uitls.VerifyCodeUtils;
import com.lovelive.sys.utils.Global;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 验证码 controller
 *
 * @author dhe
 * @date 2019-4-26
 */
@Controller
public class VerifyController extends BaseController {

    /**
     * 验证码
     */
    @RequestMapping(value = "/getCaptcha", method = RequestMethod.GET)
    public void getCaptcha(HttpServletRequest request, HttpServletResponse response) {

        try {
            VerifyCodeUtils.outputVerifyImage(Global.getVerifyWidth(), Global.getVerifyHeight(), request, response.getOutputStream(), Global.getVerifySize());
        } catch (IOException e) {
            log.error("", e);
        }

    }

}
