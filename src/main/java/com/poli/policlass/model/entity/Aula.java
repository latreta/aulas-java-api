package com.poli.policlass.model.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "aulas")
public class Aula {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@OneToOne
	@JoinColumn(name = "cadeira_id")
	private Cadeira cadeira;
	@OneToOne
	@JoinColumn(name = "professor_id")
	private Discente professor;
	@OneToOne
	@JoinColumn(name = "sala_id")
	private Sala sala;
	@NotNull
	private String turma;
	private String inicio;
	private String fim;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Cadeira getCadeira() {
		return cadeira;
	}

	public void setCadeira(Cadeira cadeira) {
		this.cadeira = cadeira;
	}

	public Discente getProfessor() {
		return professor;
	}

	public void setProfessor(Discente professor) {
		this.professor = professor;
	}

	public Sala getSala() {
		return sala;
	}

	public void setSala(Sala sala) {
		this.sala = sala;
	}

	public String getInicio() {
		return inicio;
	}

	public void setInicio(String inicio) {
		this.inicio = inicio;
	}

	public String getFim() {
		return fim;
	}

	public void setFim(String fim) {
		this.fim = fim;
	}

	public String getTurma() {
		return turma;
	}

	public void setTurma(String turma) {
		this.turma = turma;
	}

}
