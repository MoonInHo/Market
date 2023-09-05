package com.aswemake.market.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(request -> request
                        .requestMatchers("/api/members/test").permitAll() // TODO test 사용 후 삭제
                        .requestMatchers("/api/members/sign-up").anonymous()
                        .anyRequest().authenticated()
                )
        ;
        http
                .formLogin(form -> form
                        .loginProcessingUrl("/api/members/sign-in")
                        .usernameParameter("userId")
                        .defaultSuccessUrl("/api/members/test") // TODO success / failure handler 추가 테스트 사용 후 삭제
                        .failureUrl("/api/members/sign-in-fail")
                )
        ;
        http
                .logout(logout -> logout
                        .logoutUrl("/api/members/sign-out")
                        .deleteCookies("JSESSIONID")
                        .logoutSuccessUrl("/api/members/test") // TODO 테스트 사용 후 삭제
                )
        ;

        return http.build();
    }
}
