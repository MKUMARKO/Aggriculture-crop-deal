package com.cropdeal.userservice.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.cropdeal.userservice.jwtfilter.JwtRequestFilter;

@Configuration
public class WebsecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	UserDetailsService userDetailsService;
	
	@Autowired
	private JwtRequestFilter jwtRequestFilter;

	@Override
	@Bean
	public AuthenticationManager authenticationManagerBean() throws Exception {

		return super.authenticationManagerBean();
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {

		auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
	}

	/*
	 * @Bean public PasswordEncoder passwordEncoder() { return
	 * PasswordEncoderFactories.createDelegatingPasswordEncoder();
	 * 
	 * 
	 * }
	 */

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		
		http.csrf().disable();
		
		http.authorizeRequests().antMatchers("/home/**").permitAll()
		
		                        .antMatchers("/admin/**").hasRole("ADMIN")
		                        
		                        .antMatchers("/farmer/**").hasRole("FARMER")
		                        
		                        .antMatchers("/dealer/**").hasRole("DEALER")
		                    
		                        .and()
								.exceptionHandling()
								.and().sessionManagement()
						        .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		
		
	    http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
		                      
		
		/*
		 * http.cors().disable();
		 * 
		 * http.headers().frameOptions().disable();
		 * 
		 * super.configure(http);
		 */
	

	}

}
