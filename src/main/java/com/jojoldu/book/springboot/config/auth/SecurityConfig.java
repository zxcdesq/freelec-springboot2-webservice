package com.jojoldu.book.springboot.config.auth;

import com.jojoldu.book.springboot.domain.user.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@RequiredArgsConstructor
@EnableWebSecurity  // Spring security 설정 활성화
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final CustomOAuth2UserService customOAuth2UserService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
             http
                .csrf().disable()
                .headers().frameOptions().disable() // h2-console 화면 사용을 위해 옵션 disable
                .and()
                    .authorizeRequests()    //URL별 권한 관리를 설정하는 옵션의 시작점. 아래 antMatchers 사용하기 위해 필수.
                    .antMatchers("/", "/css/**", "/images/**", "/js/**", "/h2-console/**", "/profile").permitAll() // 전체 열람 권한
                    .antMatchers("/api/v1/**").hasRole(Role.USER.name())   // USER 권한 가진 사람만.
                    .anyRequest().authenticated()   //설정된 값 이외 나머지 URL
                .and()
                    .logout()   // 로그아웃 설정 진입점
                        .logoutSuccessUrl("/") // 로그아웃 성공시 / 주소로 이동
                .and()
                    .oauth2Login() // oauth2 로그인 설정 진입점
                        .userInfoEndpoint() // 로그인 성공 이후 사용자 정보를 가져올 때 설정들
                            .userService(customOAuth2UserService);
    }
}
