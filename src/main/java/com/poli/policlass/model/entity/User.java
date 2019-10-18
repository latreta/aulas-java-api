package com.poli.policlass.model.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;
import com.poli.policlass.model.entity.common.Endereco;
import com.poli.policlass.model.entity.common.GENDER;
import com.poli.policlass.model.entity.common.TelefonePrimario;
import com.poli.policlass.model.entity.common.TelefoneSecundario;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity
@Table(name="users")
@DiscriminatorValue(value="User")
public class User extends AuthenticableUser {

	@NotNull
	private String name;
	@NotNull
	private String lastName;
	@Enumerated(value = EnumType.STRING)
	private GENDER sexo;

	@Embedded
	private Endereco endereco;
	@Embedded
	private TelefonePrimario primario;
	@Embedded
	private TelefoneSecundario secundario;

	@JsonFormat(pattern = "dd/MM/yyyy", shape = Shape.STRING)
	private LocalDate birthdate;
	private LocalDate createdAt;
	private LocalDate updatedAt;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public GENDER getSexo() {
		return sexo;
	}

	public void setSexo(GENDER sexo) {
		this.sexo = sexo;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public TelefonePrimario getPrimario() {
		return primario;
	}

	public void setPrimario(TelefonePrimario primario) {
		this.primario = primario;
	}

	public TelefoneSecundario getSecundario() {
		return secundario;
	}

	public void setSecundario(TelefoneSecundario secundario) {
		this.secundario = secundario;
	}

	public LocalDate getBirthdate() {
		return birthdate;
	}

	public void setBirthdate(LocalDate birthdate) {
		this.birthdate = birthdate;
	}

	public LocalDate getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDate createdAt) {
		this.createdAt = createdAt;
	}

	public LocalDate getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(LocalDate updatedAt) {
		this.updatedAt = updatedAt;
	}
}
