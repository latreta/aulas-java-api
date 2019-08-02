package com.poli.policlass.model.entity.common;

public enum SEXO {
	MASCULINO("MASCULINO"), FEMININO("FEMININO");

	private String valor;

	SEXO(String valor) {
		this.setValor(valor);
	}

	public String getValor() {
		return valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}
}
