package com.chengyu.ciep_trading.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author CL
 */
@Component
public class JwtUtils {
    /**
     * Token时效：12小时
     */
    public static final long EXP = 60 * 60 * 12 * 1000L;

    /**
     * Token生成密钥
     */
    public static final String SECRET = "poiu1234qwer0987";

    /**
     * 用户id根据JWT规则生成Token
     *
     * @param id 用户id
     * @return Token
     */
    public static String generateToken(Integer id) {
        Map<String, Object> claims = new HashMap<>(1);
        claims.put("sub", id);
        return Jwts.builder()
                .setClaims(claims)
                // JWT规则：set 过期时间
                .setExpiration(new Date(System.currentTimeMillis() + EXP))
                // JWT规则：加密机制
                .signWith(SignatureAlgorithm.HS512, SECRET)
                .compact();
    }

    /**
     * Token根据JWT规则获取用户id
     *
     * @param token Token
     * @return 用户id
     */
    public static Integer getUserIdFromToken(String token) {
        int id;
        try {
            Claims claims = Jwts.parser()
                    .setSigningKey(SECRET)
                    .parseClaimsJws(token)
                    .getBody();
            // get 过期时间并判断是否过期
            Date date = claims.getExpiration();
            if (date.before(new Date())) {
                return null;
            }
            // 获取用户id
            id = Integer.parseInt(claims.getSubject());
        } catch (Exception e) {
            return null;
        }
        return id;
    }
}
