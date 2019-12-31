package com.lovelive.jwt.web;

import com.lovelive.common.base.BaseController;
import com.lovelive.common.uitls.BooleanUtils;
import com.lovelive.jwt.entity.TokenModel;
import com.lovelive.jwt.utils.JwtUtils;
import com.lovelive.sys.entity.User;
import com.lovelive.sys.service.IUserService;
import com.lovelive.sys.utils.PasswordUtils;
import io.jsonwebtoken.Claims;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * token controller
 *
 * @author dHe
 * @date 2019-8-9
 */
@Api(value = "token", description = "鉴权token接口", tags = "tokenAPI")
@RestController
@RequestMapping(value = "/tokens")
public class TokenController extends BaseController {

    private IUserService userService;

    @Autowired
    public TokenController(IUserService userService) {
        this.userService = userService;
    }

    /**
     * 检查用户的登录信息，然后创建并返回给前端 jwt token 令牌
     */
    @ApiOperation(value = "获取token")
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> login(@RequestParam String username, @RequestParam String password) {

        try {
            if (StringUtils.isEmpty(username)) {
                return new ResponseEntity<>("username can not be empty", HttpStatus.NOT_FOUND);
            }

            if (StringUtils.isEmpty(password)) {
                return new ResponseEntity<>("password can not be empty", HttpStatus.NOT_FOUND);
            }

            //TODO  验证账号
            User user = userService.getUserByAccount(username);
            if (user == null) {
                return new ResponseEntity<>("username or password error", HttpStatus.OK);
            }

            if (BooleanUtils.isNotTrue(PasswordUtils.validatePassword(password, user.getPassword()))) {
                return new ResponseEntity<>("username or password error", HttpStatus.OK);
            }

            TokenModel tokenModel = JwtUtils.createTokenModel(user);
            return new ResponseEntity<>(tokenModel.getToken(), HttpStatus.OK);
        } catch (Exception e) {
            log.error("", e);
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @ApiOperation(value = "销毁token")
    @RequestMapping(method = RequestMethod.DELETE)
    public ResponseEntity<?> logout(HttpServletRequest request) {
        try {
            Claims claims = JwtUtils.checkToken(request);
            log.info("claims : {}" + claims);

            //TODO  销毁token

            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            log.error("", e);
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
