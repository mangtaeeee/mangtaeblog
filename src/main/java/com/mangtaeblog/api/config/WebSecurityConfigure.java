//package com.mangtaeblog.api.config;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.provisioning.InMemoryUserDetailsManager;
//import org.springframework.security.web.SecurityFilterChain;
//
//@EnableWebSecurity
//@Configuration
//public class WebSecurityConfigure {
//
//    @Bean
//    public WebSecurityCustomizer configure() {
//        return (web) -> web.ignoring().antMatchers("/frontend/src/assets/*","/js/**","/lib/**","/favicon.ico");
//    }
//
//    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//
//        http
//                .authorizeRequests()
//                .antMatchers("/api/hello").permitAll()
//                .antMatchers("/api/login").authenticated()
//                .anyRequest().authenticated()
//                .and()
//                .csrf().disable();
//        return http.build();
//    }
//
//
//    @Bean
//    public BCryptPasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
//
//    @Bean
//    public UserDetailsService userDetailsService(BCryptPasswordEncoder passwordEncoder) {
//        //memory에 임시 사용자 계정 생성을 위해 객체생성
//        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
//        //password암호화
//        String password = passwordEncoder.encode("1234");
//
//        //사용자 ID 생성/
//        manager.createUser(User.withUsername("user")
//                .password(password)
//                .roles("USER")
//                .build());
//
//        manager.createUser(User.withUsername("manager")
//                .password(password)
//                .roles("MANAGER")
//                .build());
//
//        manager.createUser(User.withUsername("admin")
//                .password(password)
//                .roles("ADMIN")
//                .build());
//
//        return manager;
//    }
//}
