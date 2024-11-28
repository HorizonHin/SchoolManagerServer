package com.schoolmanagerserver.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Component
public class JWTUtil {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Value("${tokenExpireMs}")
    public int tokenExpireMs;

    @Value("${JWTKey}")
    private String JWTKey;

    //用以创建token
    public String create(Map<String, Object> claims) {
        return JWT.create()
                .withClaim("claims", claims)
                .withExpiresAt(new Date(System.currentTimeMillis() + tokenExpireMs))
                .sign(Algorithm.HMAC256(JWTKey));
    }

    //用以解析token
    public Map<String, Object> parseToken(String token) {
        return JWT.require(Algorithm.HMAC256(JWTKey))
                .build()
                .verify(token)
                .getClaim("claims")
                .asMap();
    }

    public boolean verifyToken(String token) {
        try {
            JWTVerifier verifier = JWT.require(Algorithm.HMAC256(JWTKey)).build();
            verifier.verify(token);
            return getToken(token) != null;
        } catch (JWTVerificationException e) {
            return false;
        }
    }

    public void storeToken(String token, Map<String, Object> claims) {
        stringRedisTemplate.opsForValue().set(token, token, tokenExpireMs, TimeUnit.MILLISECONDS);
        ThreadLocalUtil.set(token);
    }

    public String getToken(String token) {
        String cur = ThreadLocalUtil.get();
        if (Boolean.FALSE.equals(stringRedisTemplate.hasKey(token))) return null;
        return cur;
    }

    public void removeToken(String token) {
        stringRedisTemplate.delete(token);
        ThreadLocalUtil.remove();
    }
}
