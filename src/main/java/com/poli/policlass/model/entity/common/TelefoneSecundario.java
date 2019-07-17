package com.poli.policlass.model.entity.common;

import javax.persistence.Column;

public class TelefoneSecundario {
	@Column(name = "ddd_2")
	private String ddd2;
	@Column(name = "tel_2")
	private String numeroSecundario;

	public String getDdd2() {
		return ddd2;
	}

	public void setDdd2(String ddd2) {
		this.ddd2 = ddd2;
	}

	public String getNumeroSecundario() {
		return numeroSecundario;
	}

	public void setNumeroSecundario(String numeroSecundario) {
		this.numeroSecundario = numeroSecundario;
	}

}
