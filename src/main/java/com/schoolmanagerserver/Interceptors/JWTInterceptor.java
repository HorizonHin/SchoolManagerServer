package com.schoolmanagerserver.Interceptors;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.schoolmanagerserver.pojos.Result;
import com.schoolmanagerserver.utils.JWTUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class JWTInterceptor implements HandlerInterceptor {

    @Autowired
    JWTUtil jwtUtil;

    @Autowired
    ObjectMapper objectMapper;


    public static final Logger logger = LoggerFactory.getLogger(JWTInterceptor.class);

   @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
       String token = request.getHeader("Authorization");
       logger.info("收到的请求: {} {}", request.getMethod(), request.getRequestURI());
       if (token == null || !token.startsWith("Bearer ")) {
           logger.info("因为token被拦截：{}", token);
           response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
           response.setContentType("application/json");
           response.getWriter().write(objectMapper.writeValueAsString(Result.fail("没有登录")));
           return false;
       }
       token = token.substring(7);
       boolean isVerified=jwtUtil.verifyToken(token);
       if (isVerified)
           logger.info("{} jwtUtil.verifyToken验证通过", request.getRequestURI());
       else {
           response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
           logger.info("jwtUtil.verifyToken验证不通过");
           response.setContentType("application/json");
           response.getWriter().write(objectMapper.writeValueAsString(Result.fail("Token验证不通过")));
           return false;
       }
       return isVerified;
   }
}
