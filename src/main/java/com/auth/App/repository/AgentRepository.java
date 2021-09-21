package com.auth.App.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.authApp.models.Agent;

public interface AgentRepository extends JpaRepository<Agent, Integer>{
	public Optional<Agent> findByLogin(String register);
}
