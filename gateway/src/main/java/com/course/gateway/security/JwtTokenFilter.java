package com.course.gateway.security;

import com.course.gateway.util.JwtUtil;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

public class JwtTokenFilter extends OncePerRequestFilter {

    private JwtUtil jwtUtil;

    private JwtUserDetailService userDetailService;

    @Override
    protected void doFilterInternal(HttpServletRequest req, HttpServletResponse resp, FilterChain fc) throws ServletException, IOException {
        final String authHeader = req.getHeader("Authorization");

        String tokenFromRequest = jwtUtil.getTokenFromRequest(req);
        String username = jwtUtil.getUsername(tokenFromRequest);

    }
}
