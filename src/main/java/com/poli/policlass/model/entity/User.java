package com.poli.policlass.model.entity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;
import com.poli.policlass.model.dto.UserDTO;
import com.poli.policlass.model.entity.common.Endereco;
import com.poli.policlass.model.entity.common.SEXO;

@Entity
@Table(name = "users")
public class User implements UserDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotNull
	private String name;
	@Enumerated(value = EnumType.ORDINAL)
	private SEXO sexo;
	private String email;
	@NotNull
	private String password;
	@JsonFormat(pattern = "dd/MM/yyyy", shape = Shape.STRING)
	private Date birthdate;
	@Column(name = "activated")
	private boolean isActivated;
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "users_profiles", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "profile_id"))
	private List<Profile> profiles = new ArrayList<>();
	@Embedded
	private Endereco endereco;

	public User() {

	}

	public User(Long id, String name, String email, String password, Date birthdate, boolean status) {
		this.id = id;
		this.name = name;
		this.birthdate = birthdate;
		this.email = email;
		this.password = password;
		this.isActivated = status;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int isActivated() {
		return isActivated ? 1 : 0;
	}

	public void setActivated(boolean isActivated) {
		this.isActivated = isActivated;
	}

	public SEXO getSexo() {
		return sexo;
	}

	public void setSexo(SEXO sexo) {
		this.sexo = sexo;
	}

	public List<Profile> getProfiles() {
		return profiles;
	}

	public void setProfiles(List<Profile> profiles) {
		this.profiles = profiles;
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

	@Override
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = new BCryptPasswordEncoder().encode(password);
	}

	public Date getBirthdate() {
		return birthdate;
	}

	public void setBirthdate(Date birthdate) {
		this.birthdate = birthdate;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return this.profiles;
	}

	@Override
	public String getUsername() {
		return this.email;
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
		return this.isActivated;
	}

	public UserDTO generateDTO() {
		UserDTO user = new UserDTO();
		user.setId(id);
		user.setEmail(email);
		user.setName(name);
		user.setBirthdate(birthdate);
		user.setPassword(password);
		user.setEndereco(endereco);
		return user;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

}
