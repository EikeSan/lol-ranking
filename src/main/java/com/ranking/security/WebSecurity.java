package com.ranking.security;

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


import com.ranking.model.UserDetailsServiceImpl;

import static com.ranking.security.SecurityConstants.NO_AUTH_URL;;;

@EnableWebSecurity
public class WebSecurity extends WebSecurityConfigurerAdapter{

	private UserDetailsServiceImpl userDetailsService;
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	public WebSecurity(UserDetailsServiceImpl userDetailsService, BCryptPasswordEncoder bCryptPasswordEncoder) {
		this.userDetailsService = userDetailsService;
		this.bCryptPasswordEncoder = bCryptPasswordEncoder;
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		   http.cors().and().csrf().disable().authorizeRequests()
           .antMatchers(NO_AUTH_URL).permitAll()
           .anyRequest().authenticated()
           .and()
           .addFilter(new JWTAuthenticationFilter(authenticationManager()))
           .addFilter(new JWTAuthorizationFilter(authenticationManager()))
           // this disables session creation on Spring Security
           .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder);
	}
	

}
