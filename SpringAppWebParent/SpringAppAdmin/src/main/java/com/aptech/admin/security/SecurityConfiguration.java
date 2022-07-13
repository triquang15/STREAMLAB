package com.aptech.admin.security;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfiguration {

	@Bean
	public UserDetailsService userDetailsService() {
		return new StreamLabUserDetailsService();
	}

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

		http.authorizeRequests()
		.antMatchers("/states/list_by_country/**").hasAnyAuthority("Admin", "Salesperson")
		.antMatchers("/users/**", "/settings/**", "/countries/**", "/states/**").hasAuthority("Admin")
		.antMatchers("/categories/**").hasAnyAuthority("Admin", "Editor")
		
		.antMatchers("/movies/new", "/movies/delete/**").hasAnyAuthority("Admin", "Editor")
		
		.antMatchers("/movies/edit/**", "/movies/save", "/movies/check_unique")
			.hasAnyAuthority("Admin", "Editor", "Salesperson")
			
		.antMatchers("/movies", "/movies/", "/movies/detail/**", "/movies/page/**")
			.hasAnyAuthority("Admin", "Editor", "Salesperson", "Shipper")
			
		.antMatchers("/movies/**").hasAnyAuthority("Admin", "Editor")
		
		.antMatchers("/orders", "/orders/", "/orders/page/**", "/orders/detail/**").hasAnyAuthority("Admin", "Salesperson", "Shipper")
		
		.antMatchers("/movies/detail/**", "/customers/detail/**").hasAnyAuthority("Admin", "Editor", "Salesperson", "Assistant")

		.antMatchers("/customers/**", "/orders/**", "/get_shipping_cost", "/reports/**").hasAnyAuthority("Admin", "Salesperson")
		
		.antMatchers("/orders_shipper/update/**").hasAuthority("Shipper")
		
		.antMatchers("/reviews/**").hasAnyAuthority("Admin", "Assistant")
		
		.anyRequest().authenticated()
		.and()
		.formLogin()			
			.loginPage("/login")
			.usernameParameter("email")
			.permitAll()
		.and().logout().permitAll()
		.and()
			.rememberMe()
				.key("AbcDefgHijKlmnOpqrs_1234567890")
				.tokenValiditySeconds(7 * 24 * 60 * 60);
				;
		http.headers().frameOptions().sameOrigin();

		return http.build();
	}

	@Bean
	public WebSecurityCustomizer webSecurityCustomizer() {
		return (web) -> web.ignoring().antMatchers("/images/**", "/vendors/**", "/css/**", "/js/**", "/webjars/**");
	}

}
