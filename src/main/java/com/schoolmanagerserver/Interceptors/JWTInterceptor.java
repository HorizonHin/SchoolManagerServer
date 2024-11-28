package com.schoolmanagerserver.Interceptors;

import com.schoolmanagerserver.utils.JWTUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class JWTInterceptor implements HandlerInterceptor {

    @Autowired
    JWTUtil jwtUtil;

   @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
       String token = request.getHeader("Authorization");
       if (token == null || !token.startsWith("Bearer ")) {
           response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
           response.getWriter().write("Missing or invalid Authorization header");
           return false;
       }
       token = token.substring(7);
       return jwtUtil.verifyToken(token);
   }
}
