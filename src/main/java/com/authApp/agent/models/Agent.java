package com.authApp.agent.models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

@Entity
@Table(name="agents")
public class Agent {
	
	@Id
    @Column(name = "agent_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idagent;

	@Column(name = "agent_register", unique=true)
    private Integer register;
    
    @Column(name = "agent_alias", unique=true)
    private String alias;
    
    @Column(name = "agent_mail", unique=true)
    private String mail;
    
    @Column(name = "agent_password", unique=true)
    private String password;
    
    @Column(name = "agent_state", unique=true)
    private ArrayList<String>  state = new ArrayList<String>(List.of("users", "admin", "guest", "inactive"));

	public Integer getIdagent() {
		return idagent;
	}

	public void setIdagent(Integer idagent) {
		this.idagent = idagent;
	}

	public Integer getRegister() {
		return register;
	}

	public void setRegister(Integer register) {
		this.register = register;
	}

	public String getAlias() {
		return alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public ArrayList<String> getState() {
		return state;
	}

	public void setState(ArrayList<String> state) {
		this.state = state;
	}
}
    
    
    