package com.auth.App.security;

import java.io.IOException;
import java.util.ArrayList;
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

import com.auth.App.data.AgentDetailsData;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.authApp.models.Agent;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JWTAuthFilter extends UsernamePasswordAuthenticationFilter {
	public static final int TOKEN_EXPIRACAO = 600_000;
    public static final String TOKEN_SENHA = "54237ebc-b203-4a50-b0a4-2c257739adb2";
	
	private final AuthenticationManager authenticationManager;
	
	public JWTAuthFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }
	
	@Override
    public Authentication attemptAuthentication(HttpServletRequest request,
                                                HttpServletResponse response) throws AuthenticationException {
       try {
    	   Agent agents = new ObjectMapper()
                   .readValue(request.getInputStream(), Agent.class);
    	   return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                   agents.getRegister(),
                   agents.getPassword(),
                   new ArrayList<>()
           ));
       } catch (IOException error) {
           throw new RuntimeException("Falha ao autenticar usuario", error);
       }

    }
	
	@Override
    protected void successfulAuthentication(HttpServletRequest request,
                                            HttpServletResponse response,
                                            FilterChain chain,
                                            Authentication authResult) throws IOException, ServletException {

        AgentDetailsData agentsData = (AgentDetailsData) authResult.getPrincipal();

        String token = JWT.create().
                withSubject(agentsData.getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis() + TOKEN_EXPIRACAO))
                .sign(Algorithm.HMAC512(TOKEN_SENHA));

        response.getWriter().write(token);
        response.getWriter().flush();
    }
}
