package com.zerobase.fastlms.configuration;


import com.zerobase.fastlms.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@RequiredArgsConstructor
// security web 활성화 어노테이션
@EnableWebSecurity
@Configuration
public class SecurityConfiguration {

    private final MemberService memberService;

    @Bean
    PasswordEncoder getPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // failureHandler에 bean 주입
    @Bean
    UserAuthenticationFailureHandler getFailureHandler() {
        return new UserAuthenticationFailureHandler();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        // csrf를 작동안하도록 설정
        http
            .csrf().disable();

        // 주소에 대한 권한 설정
        http
            .authorizeRequests(auth -> auth
                        // requestMatchers에 대해 페이지 접근 가능하게 설정
                        .requestMatchers("/",
                                "/member/register",
//                                "/member/register_complete",
                                "/member/email-auth"
                        )
                        .permitAll()
                        .anyRequest().authenticated()

                        );

        http.formLogin(form -> form
                        .loginPage("/member/login")
                        .failureHandler(getFailureHandler())
                        .permitAll()
            );

        http.logout(logout -> logout
                        .logoutRequestMatcher(new AntPathRequestMatcher("/member/logout"))
                        // 로그아웃 후 이동페이지
                        .logoutSuccessUrl("/")
                        // 초기화
                        .invalidateHttpSession(true)
                    );

        return http.build();
    }

    protected AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
        AuthenticationManagerBuilder authenticationManagerBuilder = http.getSharedObject(AuthenticationManagerBuilder.class);
        authenticationManagerBuilder.userDetailsService(memberService).passwordEncoder(getPasswordEncoder());

        return authenticationManagerBuilder.build();
    }

}
