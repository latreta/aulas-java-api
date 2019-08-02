package com.poli.policlass.model.entity.common;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class TelefonePrimario {
	@Column(name = "ddd_1")
	private String ddd1;
	@Column(name = "tel_1")
	private String numeroPrincipal;

	public TelefonePrimario() {
	}

	public TelefonePrimario(String ddd, String telefone) {
		this.ddd1 = ddd;
		this.numeroPrincipal = telefone;
	}

	public String getDdd1() {
		return ddd1;
	}

	public void setDdd1(String ddd1) {
		this.ddd1 = ddd1;
	}

	public String getNumeroPrincipal() {
		return numeroPrincipal;
	}

	public void setNumeroPrincipal(String numeroPrincipal) {
		this.numeroPrincipal = numeroPrincipal;
	}

}
