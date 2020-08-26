package com.todo.api.security;

public class JWTProperties {
	
	public static final String  SECRET ="SANKETDE";
	public static final int  EXPIRATON_TIME = 864000000; //10Days
	public static final String  TOKEN_PREFIX ="Bearer ";
	public static final String  HEADER_STRING = "Authorization";
	

}
