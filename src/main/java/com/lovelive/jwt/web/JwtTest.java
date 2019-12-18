package com.lovelive.jwt.web;

import com.lovelive.jwt.config.JwtConfig;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class JwtTest {

    public static void main(String args[]) {

        // 创建 Twt token 令牌，将username，roles写入令牌
        /*
        "iss":"Issuer —— 用于说明该JWT是由谁签发的"
        "sub":"Subject —— 用于说明该JWT面向的对象"
        "aud":"Audience —— 用于说明该JWT发送给的用户"
        "exp":"Expiration Time —— 数字类型，说明该JWT过期的时间"
        "nbf":"Not Before —— 数字类型，说明在该时间之前JWT不能被接受与处理"
        "iat":"Issued At —— 数字类型，说明该JWT何时被签发"
        "jti":"JWT ID —— 说明标明JWT的唯一ID"
        */

        String jwtToken = Jwts.builder()
                .setSubject("userName")
                .claim("roles", "roles")
                .claim("roles2", "roles2")
                .setIssuedAt(new Date())
                .setExpiration(JwtConfig.EXPIRATION_DATE)
                .signWith(JwtConfig.SIGNATURE_ALGORITHM, JwtConfig.SECRET_KEY)
                .compact();
        System.out.println(jwtToken);

        // 检查令牌
        try {
            final Claims claims = Jwts.parser().setSigningKey(JwtConfig.SECRET_KEY).parseClaimsJws(jwtToken).getBody();
            System.out.println(claims);
        } catch (JwtException e) {
            e.printStackTrace();
        }

        System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Calendar.getInstance().getTime()));

    }
}
