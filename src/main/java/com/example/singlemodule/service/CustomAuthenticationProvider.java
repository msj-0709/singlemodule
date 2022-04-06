/*
package com.example.singlemodule.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.example.singlemodule.model.GetUserResponse;

@Component //개발자가 직접 작성한 class를 bean으로 만드는 것이다. 싱글톤 클래스 빈을 생성하는 어노테이션이다.
public class CustomAuthenticationProvider implements AuthenticationProvider {

	@Autowired
	private RestTemplate restTemplate;
	@Autowired
	private PasswordEncoder passwordEncoder;


	@Override //오버라이드 할 때 쓰인다. //상속시 부모클래스에 있는 메소드를 자식 메소드에서 재정의 하는 것을 말한다.
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {

		String name = (String)authentication.getName();
		String password = authentication.getCredentials().toString();

		try {
			ResponseEntity<GetUserResponse> responeEntity = restTemplate
					.getForEntity(String.format("http://localhost:6050/user?username=%s", name), GetUserResponse.class);

			HttpStatus status = responeEntity.getStatusCode();
			GetUserResponse response = responeEntity.getBody();

			*/
/*String expected = passwordEncoder.encode(password); // test*//*

			if (!passwordEncoder.matches(password, response.getPassword())) {
				throw new BadCredentialsException("invalid password");
			}

			List<GrantedAuthority> authrities = new ArrayList<>();
			if ("admin".equals(name)) {
				authrities.add(new SimpleGrantedAuthority("ADMIN"));
			} else {
				authrities.add(new SimpleGrantedAuthority("USER"));
			}
			return new UsernamePasswordAuthenticationToken(name, password, authrities);
		} catch (Exception e) {
			throw e;
		}
		// if (shouldAuthenticateAgainstThirdPartySystem()) {

		// use the credentials
		// and authenticate against the third-party system
		// return new UsernamePasswordAuthenticationToken(name, password, new
		// ArrayList<>());
		// } else {
		// return null;
		// }
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}
}*/
