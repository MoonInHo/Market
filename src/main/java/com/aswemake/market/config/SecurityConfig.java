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

    /**
     * 로그인, 로그아웃 성공시 핸들러를 구현하여
     * 추가적인 처리작업 가능
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(request -> request
                        .requestMatchers("/api/members/sign-up").anonymous()
                        .requestMatchers("/api/products/**", "/api/orders/**").permitAll()
                        .anyRequest().authenticated()
                )
        ;
        http
                .formLogin(form -> form
                        .loginProcessingUrl("/api/members/sign-in")
                        .usernameParameter("userId")
                        .defaultSuccessUrl("/api/products")
                        .failureUrl("/api/members/sign-in-fail")
                )
        ;
        http
                .logout(logout -> logout
                        .logoutUrl("/api/members/sign-out")
                        .deleteCookies("JSESSIONID")
                        .logoutSuccessUrl("/api/products")
                )
        ;

        return http.build();
    }
}
