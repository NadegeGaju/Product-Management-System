package com.example.ecommerce.configurations;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.HandlerInterceptor;

public class AuthenticationInterceptor
        implements HandlerInterceptor {
//    private JwtUtil jwtUtil = new JwtUtil();

//    @Override
//    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//        String authHeader = request.getHeader("Authorization");
//        if (authHeader != null && authHeader.startsWith("Bearer ")) {
//            String token = authHeader.substring(7);
//            String username = jwtUtil.extractUsername(token);
//
//            if (username != null && jwtUtil.validateToken(token, username)) {
//                // Token is valid, proceed with the request
//                return true;
//            }
//        }
//        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
//        return false;
//    }
}
