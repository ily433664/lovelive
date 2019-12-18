package com.lovelive.jwt.web;

import com.lovelive.jwt.config.JwtConfig;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@Api(value = "token", description = "鉴权token接口", tags = "tokenAPI")
@RestController("/tokens")
public class TokenController {

    private static final Logger log = LoggerFactory.getLogger(TokenController.class);

    /**
     * 检查用户的登录信息，然后创建并返回给前端 jwt token 令牌
     */
    @ApiOperation(value = "获取token")
    @ResponseBody
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> login(@RequestParam String username, @RequestParam String password) {

        try {
            if (StringUtils.isEmpty(username)) {
                return new ResponseEntity("username can not be empty", HttpStatus.NOT_FOUND);
            }

            if (StringUtils.isEmpty(password)) {
                return new ResponseEntity("password can not be empty", HttpStatus.NOT_FOUND);
            }

            //TODO  验证账号

            // 创建 Twt token 令牌，将username，roles写入令牌
            String authToken = Jwts.builder()
                    .setSubject(username)
                    .claim("roles", "testRole")
                    .setIssuedAt(new Date())
                    .setExpiration(JwtConfig.EXPIRATION_DATE)
                    .signWith(JwtConfig.SIGNATURE_ALGORITHM, JwtConfig.SECRET_KEY)
                    .compact();

            return new ResponseEntity(authToken, HttpStatus.OK);
        } catch (Exception e) {
            log.error("", e);
            return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @ApiOperation(value = "销毁token")
    @RequestMapping(method = RequestMethod.DELETE)
    @ResponseBody
    public ResponseEntity<?> logout(HttpServletRequest request) {

        try {
            String authToken = request.getHeader(JwtConfig.AUTHORIZATION);
            log.info("token[{}]" + authToken);

            // check the authorization
            if (authToken == null) {
                return new ResponseEntity(HttpStatus.OK);
            } else {
                // 使用JWT解析器检查签名是否对密钥“secretkey”有效
                Claims claims = Jwts.parser().setSigningKey(JwtConfig.SECRET_KEY).parseClaimsJws(authToken).getBody();
                log.info("claims : {}" + claims);

                //TODO  销毁token

                return new ResponseEntity(HttpStatus.OK);
            }
        } catch (Exception e) {
            log.error("", e);
            return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

}
