package com.jk.board.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

/*
 * 모든 요청 URL이 스프링 시큐리티의 제어를 받도록 만드는 어노테이션이다.
 * 어노테이션을 사용하면 내부적으로 SpringSecurityFilterChain이 동작하여 URL 필터가 적용된다.
 */
@EnableWebSecurity
@Configuration
public class SecurityConfig {
	
	@Bean
	// 스프링 시큐리티의 세부 설정은 SecurityFilterChain 빈을 생성하여 설정할 수 있다.
	public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
		
		// 모든 인증(Authentication)되지 않은 요청을 허락한다.
		// 즉, 로그인을 하지 않더라도 모든 페이지에 접근이 가능하다. 
		httpSecurity.authorizeHttpRequests((authorizeHttpRequests) -> authorizeHttpRequests
				.requestMatchers(new AntPathRequestMatcher("/**")).permitAll());
		
		return httpSecurity.csrf(AbstractHttpConfigurer::disable).build();
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

}
