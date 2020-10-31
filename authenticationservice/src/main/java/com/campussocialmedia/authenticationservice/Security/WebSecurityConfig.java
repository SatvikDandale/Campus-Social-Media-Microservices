package com.campussocialmedia.authenticationservice.Security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/*
This class is responsible for the configuration of spring security.
Here we have made the following configurations:
1: Configured Spring Security to use our own version of UserDetailsService
2: Specified the password encoder to be used
3: Removed the "/login" and "/signUp" endpoints from JWT Security
*/

@EnableWebSecurity
class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	@Autowired
	private UserDetailsService customDetailsService;
	@Autowired
	private JwtRequestFilter jwtRequestFilter;

	/*
	 * This method is used to tell spring security to use our own version of
	 * UserDetailsService instead of the default UserDetailsService provided by
	 * Spring Security
	 */
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(customDetailsService);
	}

	/*
	 * We want the Spring Security to treat the incoming password as it as and not
	 * do any hashing. Hence we have used to NoOpPasswordEncoder
	 */
	@Bean
	public PasswordEncoder passwordEncoder() {
		return NoOpPasswordEncoder.getInstance();
	}

	// We need to manually define the bean for AuthenticationManager for @Autowired
	// AuthenticationManger
	// to work
	@Override
	@Bean
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	@Override
	protected void configure(HttpSecurity httpSecurity) throws Exception {
		httpSecurity.cors().and().csrf().disable().authorizeRequests().antMatchers("/**/login/**/", "/**/signUp/**/")
				.permitAll().anyRequest().authenticated().and().exceptionHandling().and().sessionManagement()
				.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		// Asking spring security not to create and manage sessions because we want to
		// use JWT as authentication.

		// Adding the JWTRequestFilter in the filter chain:
		httpSecurity.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);

	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		// Allow eureka client to be accessed without authentication
		web.ignoring().antMatchers("/*/")//
				.antMatchers("/eureka/**")//
				.antMatchers(HttpMethod.OPTIONS, "/**"); // Request type options should be allowed.
	}

}
