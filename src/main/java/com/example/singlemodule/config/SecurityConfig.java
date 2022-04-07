/*
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

@Configuration
@EnableWebSecurity
@Slf4j
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	*/
/**
	 * 
	 * @Override protected void configure(final AuthenticationManagerBuilder auth)
	 *           throws Exception {
	 *           auth.inMemoryAuthentication().withUser("user1").password(passwordEncoder().encode("user1Pass")).roles("USER")
	 *           .and().withUser("user2").password(passwordEncoder().encode("user2Pass")).roles("USER").and()
	 *           .withUser("admin").password(passwordEncoder().encode("adminPass")).roles("ADMIN");
	 *           }
	 *//*



	@Bean
	public PasswordEncoder passwordEncoder() {
		log.info("invoke passwordEncoder().....");
		return new BCryptPasswordEncoder();
	}

	//@formatter:off
	@Override
	protected void configure(final HttpSecurity http) throws Exception {
		http
		.csrf().disable()
		.authorizeRequests()
	    .antMatchers("/admin/**").hasRole("ADMIN")
	    .antMatchers("/anonymous*").anonymous()
	    .antMatchers("/login","/list","/layout","/test").permitAll()
	    .anyRequest().authenticated().and()
	    .formLogin()
	    .loginPage("/login")
	    .loginProcessingUrl("/perform_login")

	    .defaultSuccessUrl("/list")
	    .failureUrl("/login?error=true")
	    .failureHandler(authenticationFailureHandler())
	    .and()
	    .logout()
	    .logoutUrl("/perform_logout")
	    .deleteCookies("JSESSIONID")
	    .logoutSuccessHandler(logoutSuccessHandler())
	    ;
	}
	//@formatter:on

	@Bean
	public LogoutSuccessHandler logoutSuccessHandler() {
		SimpleUrlLogoutSuccessHandler handler = new SimpleUrlLogoutSuccessHandler();
		handler.setDefaultTargetUrl("/");
		return handler;
	}


	@Bean
	public AuthenticationFailureHandler authenticationFailureHandler() {
		return new SimpleUrlAuthenticationFailureHandler();
	}
}
*/
