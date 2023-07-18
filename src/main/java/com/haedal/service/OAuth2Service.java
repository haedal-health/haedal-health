package com.haedal.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.haedal.model.UserDto;
import com.haedal.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class OAuth2Service implements OAuth2UserService<OAuth2UserRequest, OAuth2User> {
    private final UserRepository userRepository;


    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {

        ObjectMapper objectMapper = new ObjectMapper();
        OAuth2UserService oAuth2UserService = new DefaultOAuth2UserService();
        // new DefaultOAuth2UserService는 Ouath2UserService의 구현체이다.
        // 이 구현체를 이용해서 user 정보를 가져 올 수 있다.

        OAuth2User oAuth2User = oAuth2UserService.loadUser(userRequest);

        String userNameAttributeName = userRequest
                .getClientRegistration()
                .getProviderDetails()
                .getUserInfoEndpoint()
                .getUserNameAttributeName(); // PK가 되는 정보

        Map<String, Object> attributes = oAuth2User.getAttributes(); // 사용자가 가지고 있는 정보
        Map<String,String> mapUser = objectMapper.convertValue(attributes.get("kakao_account"),Map.class);

        UserDto userDto = UserDto.of(null,  mapUser.get("email"),"01012345678", null);

        if(!userRepository.findByName(userDto.getName()).isPresent()){
            userRepository.save(userDto.toEntity(userDto));
        }


        // null을 반환하면 save, 아니면 정보 업데이트


        return new DefaultOAuth2User(
                Collections.singleton(new SimpleGrantedAuthority("ROLE_USER")),
                oAuth2User.getAttributes(),
                userNameAttributeName
        );
    }
}