package com.auth.App.security;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

public class JWTValidateFilter extends BasicAuthenticationFilter {
	public static final String HEADER_ATRIBUTE = "Authorization";
	public static final String PREFIX_ATRIBUTE = "Bearer ";

	public JWTValidateFilter(AuthenticationManager authenticationManager) {
		super(authenticationManager);

	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		String atribute = request.getHeader(HEADER_ATRIBUTE);

		if (atribute == null) {
			chain.doFilter(request, response);
			return;
		}

		if (!atribute.startsWith(PREFIX_ATRIBUTE)) {
			chain.doFilter(request, response);
			return;
		}

		String token = atribute.replace(PREFIX_ATRIBUTE, "");
		UsernamePasswordAuthenticationToken authenticationToken = getAuthenticationToken(token);

		SecurityContextHolder.getContext().setAuthentication(authenticationToken);
		chain.doFilter(request, response);
	}

	private UsernamePasswordAuthenticationToken getAuthenticationToken(String token) {

		String agents = JWT.require(Algorithm.HMAC512(JWTAuthFilter.TOKEN_SENHA)).build().verify(token).getSubject();

		if (agents == null) {
			return null;
		}

		return new UsernamePasswordAuthenticationToken(agents, null, new ArrayList<>());
	}

}
