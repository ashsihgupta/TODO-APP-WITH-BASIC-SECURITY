package com.example.demo.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SuppressWarnings("deprecation")
@Configuration
public class Security extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {

		auth.inMemoryAuthentication().withUser("user").password(passwordEncoder().encode("user")).roles("USER").and()
				.withUser("admin").password(passwordEncoder().encode("admin")).roles("ADMIN");

	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.httpBasic().and().authorizeRequests().antMatchers(HttpMethod.GET, "/todos/**").hasAnyRole("USER", "ADMIN")
				.antMatchers(HttpMethod.POST, "/todos").hasRole("ADMIN").antMatchers(HttpMethod.PUT, "/todos/**")
				.hasRole("ADMIN").antMatchers(HttpMethod.DELETE, "/todos/**").hasRole("ADMIN").and().csrf().disable()
				.formLogin().disable();

	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	

}
