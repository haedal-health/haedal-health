package com.haedal.config.filter;

import com.haedal.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RequiredArgsConstructor
public class JwtTokenFilter extends OncePerRequestFilter{

    private final String key;
    private final UserService userService;

    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        //Resolve Header

        //Token Validation

        //get Username from token
        // user valid check

        //save Object in security context holer

        //filter 넘기기
    }
}
