package com.campussocialmedia.authenticationservice.Security;

import java.io.IOException;
import java.util.Date;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.SignatureException;

import com.campussocialmedia.authenticationservice.Exceptions.ExceptionResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/*
The request sent to any API endpoint is first intercepted by a Filter.
There are various types of filters.
To create own custom filter, we need to create our own filter class and extend it to an existing filter class
OncePerRequestFilter is used to intercept the incoming request only once.

The job of this class is to intercept the incoming API request, extract the JWT token from the
Authorization header, and check if the token is valid.
If the token is valid, this request is passed to the next filter in the chain.
If this is the last filter in the chain of filters, then the request now will be sent to the actual API
it was meant for.

IF the authentication fails, 
*/

/*
The following exceptions need to be handled:
If token is absent.
If token is invalid.

Since doFilterInternal method returns void, we cannot return the ResponseEntity directly from here.
Throw the above exceptions in this method and handle them separately in another class.
*/
@Component
public class JwtRequestFilter extends OncePerRequestFilter {

	@Autowired
	private CustomUserDetailsService customDetailsService;

	@Autowired
	private JwtUtil jwtUtil;

	// The doFilterInternal is the method that actually does the filter job.
	// We override this method and do that custom tasks that we want to do.
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws ServletException, IOException {

		final String authorizationHeader = request.getHeader("Authorization");

		String username = null;
		String jwt = null;
		if (request.getRequestURI().equals("/login") || request.getRequestURI().equals("/signUp")
				|| request.getRequestURI().equals("/jwt")) {
			chain.doFilter(request, response);
			// return;
		} else {
			try {
				/*
				 * Check if the token is present in the request header
				 */
				if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
					jwt = authorizationHeader.substring(7);

					// If the token is invalid in any case, this line will throw SignatureException
					// which is handled below.
					username = jwtUtil.extractUsername(jwt);

				} else {
					throw new Exception("Token Not Present");
				}

				if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {

					UserDetails userDetails = this.customDetailsService.loadUserByUsername(username);

					if (jwtUtil.validateToken(jwt, userDetails)) {

						UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
								userDetails, null, userDetails.getAuthorities());

						usernamePasswordAuthenticationToken
								.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

						SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
					}
				} else {
					// Throw some Error
				}

				/*
				 * After successful authentication of jwt token, chain.doFilter(req,res) is used
				 * to advance to the next filter in the chain
				 */
				chain.doFilter(request, response);

			} catch (SignatureException ex) {
				ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), "The token is invalid",
						request.getRequestURI());
				response.setStatus(HttpStatus.SC_UNAUTHORIZED);
				response.setHeader("Content-Type", "application/json");
				response.getWriter().write(convertObjectToJson(exceptionResponse));
				return;
			} catch (Exception ex) {
				ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), ex.getMessage(),
						request.getRequestURI());
				response.setStatus(HttpStatus.SC_UNAUTHORIZED);
				response.setHeader("Content-Type", "application/json");
				response.getWriter().write(convertObjectToJson(exceptionResponse));
				return;
			}

		}

	}

	private String convertObjectToJson(Object object) throws JsonProcessingException {
		if (object == null) {
			return null;
		}
		ObjectMapper mapper = new ObjectMapper();
		return mapper.writeValueAsString(object);
	}

}