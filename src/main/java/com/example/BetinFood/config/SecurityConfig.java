package com.example.BetinFood.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /*@Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests((authz) -> authz
                .requestMatchers("/public/**")
                
            )
            .formLogin().loginPage("/login");
        return http.build();
    }*/
    /*@Bean
     public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
         http
             .authorizeHttpRequests((authorizeHttpRequests) ->
                 authorizeHttpRequests
                     .requestMatchers(HttpMethod.POST,"public/**").hasRole("USER").anyRequest().permitAll()
             )
             .formLogin().loginPage("/login");
         return http.build();
     }*/
     @Bean
     public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
         http
             .authorizeHttpRequests((authorizeHttpRequests) ->
                 authorizeHttpRequests
                     .anyRequest().permitAll()
             )
             .csrf((csrf) -> csrf.disable())
             .formLogin().loginPage("/login");
         return http.build();
     }
}