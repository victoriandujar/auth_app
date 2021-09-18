package com.auth.App.data;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.authApp.models.Agent;

public class AgentDetailsData implements UserDetails {
	private final Optional<Agent> agents;
	
	public AgentDetailsData(Optional<Agent> agents) {
        this.agents = agents;
    }
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return new ArrayList<>();
	}

	@Override
	public String getPassword() {
		return agents.orElse(new Agent()).getPassword();
	}

	@Override
	public String getUsername() {
		return agents.orElse(new Agent()).getRegister();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

}
