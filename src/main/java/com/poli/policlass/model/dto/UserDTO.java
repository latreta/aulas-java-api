package com.poli.policlass.model.dto;

import java.util.Date;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import com.poli.policlass.model.entity.User;

public class UserDTO {
	private Long id;
	private String name;
	private String email;
	@JsonProperty(access = Access.WRITE_ONLY)
	private String password;
	@JsonFormat(pattern = "dd/MM/yyyy", shape = Shape.STRING)
	private Date birthdate;
	@JsonProperty(access = Access.WRITE_ONLY)
	private boolean activated;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getBirthdate() {
		return birthdate;
	}

	public void setBirthdate(Date birthdate) {
		this.birthdate = birthdate;
	}

	public User generateUser() {
		User user = new User(this.id, this.name, this.email, this.password, this.birthdate, this.activated);
		return user;
	}

	@JsonIgnore
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = new BCryptPasswordEncoder().encode(password);
	}

	@JsonIgnore
	public boolean isActivated() {
		return activated;
	}

	public void setActivated(boolean activated) {
		this.activated = activated;
	}

}