package com.study.outclass.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
     http.formLogin(form -> form
             .loginPage("/user/login")
             .defaultSuccessUrl("/")
             .loginProcessingUrl("/user/login")
             .failureUrl("/user/login/error")
             .usernameParameter("email")
             .passwordParameter("password")
             .permitAll());

     http.authorizeHttpRequests(request -> request
             .requestMatchers( "/images/**").permitAll()
             .requestMatchers("/", "/user/**").permitAll()
             .requestMatchers("/tutor/**").hasRole("TUTER")
             .anyRequest().authenticated());

     http.logout(Customizer.withDefaults());

     //예외 처리
//     http.exceptionHandling(exception -> exception
//             .accessDeniedPage(
//                     new CustimAuthenticationEntryPoint().toString()
//             ));

     return http.build();
  }

  @Bean
  public PasswordEncoder passwordEncoder(){
      return new BCryptPasswordEncoder();
  }
}
