package com.haedal.config;


import com.haedal.config.filter.JwtTokenFilter;
import com.haedal.service.OAuth2Service;
import com.haedal.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Controller;


@EnableWebSecurity
@RequiredArgsConstructor
@Configuration
public class SecurityConfig {
    @Value("${jwt.secret-key}")
    private String key;
    private final UserService userService;
    private final OAuth2Service oAuth2Service;

    @Bean
    public SecurityFilterChain configure(HttpSecurity http) throws Exception {
        return http.csrf().disable() // csrf 보안 설정 사용 X

                .logout().disable() // 로그아웃 사용 X
                .cors().and()
                //.formLogin().disable()
                .authorizeRequests() // 사용자가 보내는 요청에 인증 절차 수행 필요
                    .antMatchers("/login", "/join").permitAll()
                    .antMatchers("/kakao").permitAll() // 해당 URL은 인증 절차 수행 생략 가능
                    .antMatchers("/**").authenticated() // 나머지 요청들은 모두 인증 절차 수행해야함
               //TODO : add Filter before
                    .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS) // jwt 사용하는 경우 사용
                .and()
                .addFilterBefore(new JwtTokenFilter(key, userService), UsernamePasswordAuthenticationFilter.class)
                //.oauth2Login() // OAuth2를 통한 로그인 사용
                //.defaultSuccessUrl("http://localhost:3000/kakkoLogin/", true) // 로그인 성공시 이동할 URL
                //.userInfoEndpoint() // 사용자가 로그인에 성공하였을 경우,
                //.userService(oAuth2Service) // 해당 서비스 로직을 타도록 설정
                //.and()
                //.and()
                .build();
    }
   @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring().antMatchers("/images/**", "/js/**", "/webjars/**","/css/**","../../resources/**");
    }

}