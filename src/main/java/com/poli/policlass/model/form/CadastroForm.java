package com.poli.policlass.model.form;

import java.util.Date;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;
import com.poli.policlass.model.dto.DTO;
import com.poli.policlass.model.entity.User;
import com.poli.policlass.model.entity.common.Endereco;
import com.poli.policlass.model.entity.common.SEXO;
import com.poli.policlass.model.entity.common.TelefonePrimario;
import com.poli.policlass.model.entity.common.TelefoneSecundario;

public class CadastroForm implements DTO<User> {
	private String firstName;
	private String lastName;
	private String email;
	private String password;
	@JsonFormat(pattern = "yyyy-MM-dd", shape = Shape.STRING)
	private Date birthdate;
	@Enumerated(value = EnumType.STRING)
	private SEXO sexo;
	private String ddd_1;
	private String tel_1;
	private String ddd_2;
	private String tel_2;
	// Endereços
	private String cep;
	private String street;
	private String number;
	private String neighborhood;
	private String city;
	private String state;
	private String complement;

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String name) {
		this.firstName = name;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Date getBirthdate() {
		return birthdate;
	}

	public void setBirthdate(Date birthdate) {
		this.birthdate = birthdate;
	}

	public SEXO getSexo() {
		return sexo;
	}

	public void setSexo(SEXO sexo) {
		this.sexo = sexo;
	}

	public String getDdd_1() {
		return ddd_1;
	}

	public void setDdd_1(String ddd_1) {
		this.ddd_1 = ddd_1;
	}

	public String getTel_1() {
		return tel_1;
	}

	public void setTel_1(String tel_1) {
		this.tel_1 = tel_1;
	}

	public String getDdd_2() {
		return ddd_2;
	}

	public void setDdd_2(String ddd_2) {
		this.ddd_2 = ddd_2;
	}

	public String getTel_2() {
		return tel_2;
	}

	public void setTel_2(String tel_2) {
		this.tel_2 = tel_2;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getNeighborhood() {
		return neighborhood;
	}

	public void setNeighborhood(String neighborhood) {
		this.neighborhood = neighborhood;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getComplement() {
		return complement;
	}

	public void setComplement(String complement) {
		this.complement = complement;
	}

	@Override
	public User convert() {
		User user = new User();
		user.setEmail(email);
		user.setName(firstName);
		user.setLastName(lastName);
		user.setPassword(password);
		user.setBirthdate(birthdate);
		user.setPrimario(new TelefonePrimario(ddd_1, tel_1));
		user.setSecundario(new TelefoneSecundario(ddd_2, tel_2));
		user.setSexo(sexo);
		user.setEndereco(new Endereco(street, number, neighborhood, city, cep, state, complement));
		return user;
	}

}
