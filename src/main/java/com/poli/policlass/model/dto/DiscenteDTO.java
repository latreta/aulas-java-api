package com.poli.policlass.model.dto;

import com.poli.policlass.model.entity.Discente;

public class DiscenteDTO implements DTO<Discente> {
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public Discente convert() {
		Discente discente = new Discente();
		discente.setName(name);
		return discente;
	}

}
