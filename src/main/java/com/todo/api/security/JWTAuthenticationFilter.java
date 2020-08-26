package com.todo.api.security;

import java.io.IOException;
import java.util.Date;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.todo.api.model.LoginViewModel;

public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter{
	
	AuthenticationManager authenticationManager;
	
	public JWTAuthenticationFilter(AuthenticationManager authenticationManager) {
		this.authenticationManager = authenticationManager;
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {
		
		try {
			LoginViewModel credintials = new ObjectMapper().readValue(request.getInputStream(), LoginViewModel.class);
	
		
			//Create Login Token
			UsernamePasswordAuthenticationToken authenticationToken = 
					new UsernamePasswordAuthenticationToken(credintials.getUsername(), credintials.getPassword());
			
			//Authenticate Login Token
			Authentication auth =  this.authenticationManager.authenticate(authenticationToken);
		
			return auth;
		
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return super.attemptAuthentication(request, response);
		
	}
	
	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication authResult) throws IOException, ServletException {
		//grab principal
		UserPrincipal userPrincipal =  (UserPrincipal) authResult.getPrincipal();
		
		//Create token
		String token = JWT.create()
				.withSubject(userPrincipal.getUsername())
				.withExpiresAt(new Date(System.currentTimeMillis() +  JWTProperties.EXPIRATON_TIME))
				.sign(Algorithm.HMAC512(JWTProperties.SECRET));
		

		response.addHeader(JWTProperties.HEADER_STRING, JWTProperties.TOKEN_PREFIX + token);
		response.setContentType("application/json");
	    response.setCharacterEncoding("UTF-8");
	    //response.getWriter().print(token);
		response.getWriter().append("{\"token\":\"").append(JWTProperties.TOKEN_PREFIX + token).append("\"}");
		
	}
}
