package com.lovelive.sys.web;

import com.lovelive.common.base.BaseController;
import com.lovelive.common.uitls.FileUtils;
import com.lovelive.common.uitls.VerifyCodeUtils;
import com.lovelive.sys.utils.Global;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.io.ByteArrayOutputStream;

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
    public ResponseEntity<?> getCaptcha() {
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            VerifyCodeUtils.outputVerifyImage(Global.getVerifyWidth(), Global.getVerifyHeight(), baos, Global.getVerifySize());
            return FileUtils.downloadFile("VerifyCode.jpg", baos.toByteArray());
        } catch (Exception e) {
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

}
