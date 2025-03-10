package com.example.redis.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
    @Bean
    public SecurityFilterChain securityFilterChain(
            HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(
                                "/auth/my-profile"
                        ).authenticated()  // 인증이 필요하다 설정
                )
                .formLogin(formLogin -> formLogin
                        .loginPage("/auth/login")
                        .defaultSuccessUrl("/auth/my-profile")
                        .failureUrl("/auth/login?fail")
                        .permitAll()
                )
                .logout(logout -> logout
                        .logoutUrl("/auth/logout")
                        .logoutSuccessUrl("/auth/login")
                );
        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsManager(
            PasswordEncoder passwordEncoder) {
        UserDetails user1 = User.withUsername("user1")
                .password(passwordEncoder.encode("password1"))
                .build();
        UserDetails user2 = User.withUsername("user2")
                .password(passwordEncoder.encode("password2"))
                .build();
        return new InMemoryUserDetailsManager(user1, user2);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
