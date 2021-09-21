package com.auth.App.services;

import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.auth.App.data.AgentDetailsData;
import com.auth.App.repository.AgentRepository;
import com.authApp.models.Agent;

@Component
public class AgentDetailsServiceImpl implements UserDetailsService {
	private final AgentRepository repository;

    public AgentDetailsServiceImpl(AgentRepository repository) {
        this.repository = repository;
    }
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<Agent> agent = repository.findByLogin(username);
		
		if (agent.isEmpty()) {
            throw new UsernameNotFoundException("Usuário [" + username + "] não encontrado");
        }
		
		return new AgentDetailsData(agent);
	}

}
