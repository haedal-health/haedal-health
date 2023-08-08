package com.haedal.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

@Controller
public class UserLoginController {

    @GetMapping("/kakao")
    public String kakaoLogin(){
        System.out.println("카카오로그인");
        //return "";
        return "/main/kakaoLogin";
    }
    @GetMapping("/logintest")
    public String afterLogin(){
        System.out.println("카카오로그인");
        return "/main/afterlogin";
    }

    @ResponseBody
    @GetMapping("/loginInfo")
    public String getJson(Authentication authentication) {
        OAuth2User oAuth2User = (OAuth2User) authentication.getPrincipal();

        Map<String, Object> attributes = oAuth2User.getAttributes();
        System.out.println(attributes);
        return attributes.toString();
    }
}


