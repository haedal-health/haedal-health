package com.haedal.config.filter;

import com.haedal.service.UserService;
import com.haedal.util.JwtTokenUtils;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Collection;
import java.util.Date;
import java.util.Map;
import java.util.Set;

@Slf4j
@RequiredArgsConstructor
public class JwtTokenFilter extends OncePerRequestFilter{

    private final String key;
    private final UserService userService;

    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        //Resolve Header
        final String header = request.getHeader(HttpHeaders.AUTHORIZATION);
        if(header == null || !header.startsWith("Bearer ") )//jwt token은 bearer안에 담겨있음으로 헤더에도 bear담겨야함
        {
            log.error("Error occurs while getting header. header is null or invalid"); //header파싱 실패시
            filterChain.doFilter(request, response); //다음 필터로 넘김
            return;
        }
        //Token Validation
        final String token = header.split(" ")[1].trim(); //header : Bearer+" "+token으로 구성됨
        //token 만료 여부 확인
        if(JwtTokenUtils.isExpired(token, key)){
            //ture-> 만료됨
            log.error("Error occurs bcs key is expired.");
            filterChain.doFilter(request, response); //다음 필터로 넘김
            return;
        }
        //get Username from token(claims)
        String userName = JwtTokenUtils.getUserName(token, key);
        // user valid check


        //save Object in security context holer

        //filter 넘기기
        filterChain.doFilter(request, response);
    }



}
