package com.todo.api.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.todo.api.model.User;
import com.todo.api.repository.UserRepository;

public class JWTAuthorizationFilter extends BasicAuthenticationFilter{

	@Autowired
	UserRepository userRepository;
	
	public JWTAuthorizationFilter(AuthenticationManager authenticationManager, UserRepository userRepository) {
		super(authenticationManager); 
		this.userRepository = userRepository;
	}
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		// Read Header where the token should be
		String header =  request.getHeader(JWTProperties.HEADER_STRING);
		
		if(header == null || !header.startsWith(JWTProperties.TOKEN_PREFIX)) {
			chain.doFilter(request, response);
			return;
		}
		
		//If header present, try to grab principal from database and perform authorization
		Authentication authentication = getUsernamePaswwordAuthentication(request);
		SecurityContextHolder.getContext().setAuthentication(authentication);
		
		//Continue filter execution
		chain.doFilter(request, response);
		
		
	}

	private Authentication getUsernamePaswwordAuthentication(HttpServletRequest request) {
		
		String token = request.getHeader(JWTProperties.HEADER_STRING).replace(JWTProperties.TOKEN_PREFIX, "");
		if(token != null) {
			String userName =  JWT.require(Algorithm.HMAC512(JWTProperties.SECRET))
								.build()
								.verify(token)
								.getSubject();
			if(userName != null) {
				User user = userRepository.findByUsername(userName);
				UserPrincipal userPrincipal =  new UserPrincipal(user);
				UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userName, null, userPrincipal.getAuthorities());
				return authenticationToken;
			}
		}
		return null;
	}
	
	

}
