package com.schoolmanagerserver.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    public static final Logger logger = LoggerFactory.getLogger(JWTUtil.class);

    // 用以创建token
    public String create(Map<String, Object> claims) {
        logger.info("创建Token，Claims: {}", claims);
        String token = JWT.create()
                .withClaim("claims", claims)
                .withExpiresAt(new Date(System.currentTimeMillis() + tokenExpireMs))
                .sign(Algorithm.HMAC256(JWTKey));
        logger.info("Token 创建成功，Token: {}, 有效期: {} ms", token, tokenExpireMs);
        return token;
    }

    // 用以解析token
    public Map<String, Object> parseToken(String token) {
        logger.info("解析Token: {}", token);
        try {
            Map<String, Object> claims = JWT.require(Algorithm.HMAC256(JWTKey))
                    .build()
                    .verify(token)
                    .getClaim("claims")
                    .asMap();
            logger.info("Token 解析成功，Claims: {}", claims);
            return claims;
        } catch (JWTVerificationException e) {
            logger.error("Token 解析失败，错误信息: {}", e.getMessage());
            throw e; // 或者返回一个失败的响应
        }
    }

    // 用以验证Token
    public boolean verifyToken(String token) {
        logger.info("验证Token: {}", token);
        try {
            JWTVerifier verifier = JWT.require(Algorithm.HMAC256(JWTKey)).build();
            verifier.verify(token);
            boolean isValid = getToken(token) != null;
            if (isValid) {
                logger.info("Token 验证通过");
            } else {
                logger.warn("Token 不在有效期内或被删除");
            }
            return isValid;
        } catch (JWTVerificationException e) {
            logger.error("Token 验证失败，错误信息: {}", e.getMessage());
            return false;
        }
    }

    // 存储Token
    public void storeToken(String token, Map<String, Object> claims) {
        logger.info("存储Token: {}, Claims: {}", token, claims);
        stringRedisTemplate.opsForValue().set(token, token, tokenExpireMs, TimeUnit.MILLISECONDS);
        ThreadLocalUtil.set(token);
        logger.info("Token 存储成功，Token: {}, 有效期: {} ms", token, tokenExpireMs);
    }

    // 获取Token
    public String getToken(String token) {
        String cur = ThreadLocalUtil.get();
        logger.info("获取Token: {}", token);
        if (Boolean.FALSE.equals(stringRedisTemplate.hasKey(token))) {
            logger.warn("Token 不存在于Redis中，Token: {}", token);
            return null;
        }
        logger.info("Token 存在于Redis中，Token: {}", token);
        cur = token;
        ThreadLocalUtil.set(token);
        return cur;
    }

    // 删除Token
    public void removeToken(String token) {
        logger.info("删除Token: {}", token);
        stringRedisTemplate.delete(token);
        ThreadLocalUtil.remove();
        logger.info("Token 删除成功，Token: {}", token);
    }
}
