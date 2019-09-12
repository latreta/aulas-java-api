package com.poli.policlass.model.entity.common;

public enum GENDER {
	MASCULINO("MASCULINO"), FEMININO("FEMININO");

	private String valor;

	GENDER(String valor) {
		this.setValor(valor);
	}

	public String getValor() {
		return valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}
}
