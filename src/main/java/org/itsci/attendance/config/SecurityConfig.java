package org.itsci.attendance.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeRequests(configurer -> {
            configurer.antMatchers().permitAll();
//            configurer.antMatchers("/user-profile/**").authenticated()
//                    .antMatchers("/product/**").hasRole("MANAGER")
//                    .antMatchers("/shop/**").hasRole("MANAGER")
//                    .antMatchers("/category/**").hasRole("ADMIN")
//                    .antMatchers("/member/**").hasRole("ADMIN");
        });

        http.exceptionHandling(configurer -> {
            configurer.accessDeniedPage("/access-denied");
        });

        http.formLogin(configurer -> {
                    try {
                        configurer.loginPage("/login")
                                .loginProcessingUrl("/authenticate")
                                .permitAll()
                                .and()
                                .logout().permitAll();
                    } catch (Exception e) { e.printStackTrace(); }
                }
        );
        return http.build();
    }
}
