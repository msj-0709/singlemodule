package com.example.singlemodule.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.security.web.authentication.logout.SimpleUrlLogoutSuccessHandler;

import lombok.extern.slf4j.Slf4j;

@Configuration //bean을 사용하기 위해 쓴다.
@EnableWebSecurity
@Slf4j
//application.yml에서도 설정이 가능하지만, 몇 가지 부분 설정만 가능하므로 config class에서 기본 설정을 해주도록 설정 함
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	/**
	 * 
	 * @Override protected void configure(final AuthenticationManagerBuilder auth)
	 *           throws Exception {
	 *           auth.inMemoryAuthentication().withUser("user1").password(passwordEncoder().encode("user1Pass")).roles("USER")
	 *           .and().withUser("user2").password(passwordEncoder().encode("user2Pass")).roles("USER").and()
	 *           .withUser("admin").password(passwordEncoder().encode("adminPass")).roles("ADMIN");
	 *           }
	 */

	//passwordencoder는 스프링 시큐리티의 인터페이스 객체이다. 비밀번호를 암호화하는 역할이다.
	@Bean //스프링 컨테이너에 빈으로 등록
	public PasswordEncoder passwordEncoder() {
		log.info("invoke passwordEncoder().....");
		//패스워드 암호화 방식으로 BCryptPasswordEncoder 적용/ BCryptPasswordEncoder=bcrypt라는 해시함수를 이용하여 패스워드 암호화하는 구현체
		return new BCryptPasswordEncoder();
	}

	// @formatter:off
	@Override
	protected void configure(final HttpSecurity http) throws Exception {
		http
		.csrf().disable()//@EnableWebSecurity 는 기본적으로 csrf공격을 방지하는 기능을 지원하고 있다. csrf 비활성화
		.authorizeRequests()//요청에 대한 권한 지정
	    .antMatchers("/admin/**").hasRole("ADMIN") //특정권한을 가진 사람만 접근 가능
	    .antMatchers("/anonymous*").anonymous() //login되지 않은 사용자도 접근할 수 있다. 인증되지 않은 사용자가 접근 가능.
	    .antMatchers("/login").permitAll() //login page에 한해서는 모든 접근을 허용한다.
	    .anyRequest().authenticated().and()//어더한 요청이 들어오든 보안검사를 실행
	    .formLogin()//로그인 페이지와 기타 로그인 처리 및 성공 실패 처리를 사용하겠다는 의미
	    .loginPage("/login")//사용자가 따로 만든 로그인페이지를 사용하려고 할 때 설정한다
	    .loginProcessingUrl("/perform_login")//로그인 즉 인증 처리를 하는 url을 설정 //즉 html에서 이 요청이 실행되면 인증처리하는 필터가 호출 됨. 아이디와 비번을 가져와서 인증처리
				//UsernamePasswordAuthenticationFilter가 실행되게 되는 것
	    .defaultSuccessUrl("/")//정상적으로 인증 성공하였을 경우 이동하는 페이지를 설정하는 것이다. 설정하지 않는 경우 디폴트 값은 "/"이다.
	    .failureUrl("/login?error=true")//인증 실패했을 경우 설정. 정상적 인증 성공 후 별도의 처리가 필요한 경우는 succesHandler
	    .failureHandler(authenticationFailureHandler())//인증 실패 후 별도의 처리가 필요한 경우 커스텀 핸들러를 등록하여 설정할 수 있다.
	    .and()
	    .logout()//로그아웃 처리
	    .logoutUrl("/perform_logout") //로그아웃 요청 경로 즉 logout 처리 url을 말한다.
	    .deleteCookies("JSESSIONID")//logout후 쿠키 삭제
	    .logoutSuccessHandler(logoutSuccessHandler()) //logout 성공 후 핸들러
	    ;
	}
	// @formatter:on

	@Bean
	public LogoutSuccessHandler logoutSuccessHandler() {
		SimpleUrlLogoutSuccessHandler handler = new SimpleUrlLogoutSuccessHandler();
		handler.setDefaultTargetUrl("/"); //logout 할시 logout이 되고 갈 url을 뜻한다.
		return handler;
	}


	//로그인 인증하는 과정에서 실패할 경우의 동작을 말한다.
	@Bean
	public AuthenticationFailureHandler authenticationFailureHandler() {
		return new SimpleUrlAuthenticationFailureHandler();
	}
}
