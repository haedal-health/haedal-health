package com.haedal.service;

import com.haedal.model.entity.Pass;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class TokenParsingService {
    public String getEmail(Authentication authentication){

        OAuth2User oAuth2User = (OAuth2User) authentication.getPrincipal();
        Map<String, Object> attributes = oAuth2User.getAttributes();
        Map<String, String> att = (Map<String, String>) attributes.get("kakao_account");
        System.out.println(att.get("email"));
        return att.get("email");
    }
}
