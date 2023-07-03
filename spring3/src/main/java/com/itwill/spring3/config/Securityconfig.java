package com.itwill.spring3.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@EnableMethodSecurity
@Configuration // 스프링 컨테이너에서 빈(bean)으로 생성, 관리 - 필요한 곳에 의존성 주입.
//@EnableWebSecurity
public class Securityconfig {
    // Spring Security 5 버전부터 비밀번호는 반드시 암호화를 해야 함.
    // 비밀번호를 암호화하지 않으면 HTTP 403(access denied, 접근 거부) 또는 HTTP 500(internal server, 내부 서버 오류)가 발생함. 
    // 비밀번호 인코더(Password encoder) 객체를 bean으로 생성해야 함.
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(); // 암호화하는 알고리즘 중 하나
    }
    
    // 로그인할 때 사용할 임시 사용자(메모리에 임시 저장) 생성
    /*
    @Bean
    public UserDetailsService inMemoryUserDetailsService() {
        // 사용자 상세 정보, DB에 들어가는 user 정보, Entity 개념
        UserDetails user1 = User
                    .withUsername("user1") // 로그인할 때 사용할 사용자 이름 (id)
                    .password(passwordEncoder().encode("1111")) // 로그인할 때 사용할 비밀번호
                    .roles("USER") // 사용자 권한(USER, ADMIN, ...)
                    .build();
        
        UserDetails user2 = User
                .withUsername("user2")
                .password(passwordEncoder().encode("2222"))
                .roles("USER", "ADMIN")
                .build();
        
        UserDetails user3 = User
                .withUsername("user3")
                .password(passwordEncoder().encode("3333"))
                .roles("ADMIN")
                .build();
        
        return new InMemoryUserDetailsManager(user1, user2, user3);
    }
    */
    
    // Security Filter 설정 bean
    // 로그인, 로그아웃 설정
    // 로그인 페이지 설정, 로그아웃 이후 이동할 페이지
    // 페이지 접근 권한 - 로그인해야만 접근 가능한 페이지, 로그인 없이 접근 가능한 페이지.
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        // CSRF 기능 활성화 하면, Ajax POST/PUT/DELETE 요청에서 CSRF토큰을 서버로 전송하지 않으면 403 에러가 발생.
        // -> CSRF 기능 비활성화
        http.csrf((csrf) -> csrf.disable());
        
        // 로그인 페이지 설정 - 스프링에서 제공하는 기본 로그인 페이지를 사용.
        http.formLogin(Customizer.withDefaults());
        
        // 로그아웃 이후 이동할 페이지
        http.logout((logout) -> logout.logoutSuccessUrl("/"));
        
        // 페이지 접근 권한 설정. 순서 중요
        /*
         * http.authorizeHttpRequests((authRequest) -> authRequest // 접근 권한을 설정할 수 있는 객체
         * .requestMatchers("/post/create", "/post/detail", "/post/modify", // 권한이 필요한
         * 페이지들을 설정 "/post/update", "/post/delete", "/api/reply/**") // .authenticated()
         * // 인증이 끝났으면(아이디, 패스워드가 일치하면, 로그인 됐으면, hasRole과 같이 쓸 수 없음), 권한 여부에 상관 없이
         * 아이디-패스워드가 일치하면 .hasRole("USER") // 위에 설정한 페이지들이 USER 권한 요구를 설정. .anyRequest()
         * // 위 페이지들 외 모든 페이지 .requestMatchers("/**") .permitAll()); // 권한없이 접근 전부
         * 허용하겠다.
         * 
         */
        // 단점: 새로운 요청 경로, controller를 작성할 때마다 config 자바 코드를 수정해야 함.
        // -> controller 메서드를 작성할 때 애너테이션을 사용해서 접근 권한을 설정할 수도 있음.
        // (1) SecurityConfig 클래스에서 @EnableGlobalMethodSecurity 애너테이션 설정
        // (2) 각각의 controller 메서드에서 @PreAuthorize 또는 @PostAuthorize 애너테이션을 사용.
        
        return http.build();
    }
    
    
}
