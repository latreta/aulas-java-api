package com.poli.policlass.model.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.poli.policlass.model.entity.User;

public class UserDTO {
	private Long id;
	private String name;
	private String email;
	@JsonIgnore
	private String password;
	@JsonFormat(pattern = "dd/MM/yyyy", shape = Shape.STRING)
	private Date birthdate;

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

	public User generateUserDTO() {
		User user = new User(this.id, this.name, this.birthdate);
		return user;
	}

}
