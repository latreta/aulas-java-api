package com.poli.policlass.model.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;

@Entity
public class Aluno extends User {
	@OneToMany
	private List<Aula> assinaturas = new ArrayList<>();

	public List<Aula> getAssinaturas() {
		return assinaturas;
	}

	public void setAssinaturas(List<Aula> assinaturas) {
		this.assinaturas = assinaturas;
	}
}
