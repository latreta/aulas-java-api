package com.poli.policlass.model.dto;

public class TokenDTO {
	private String token;
	private String prefix;

	public TokenDTO(String token, String prefix) {
		this.token = token;
		this.prefix = prefix;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getPrefix() {
		return prefix;
	}

	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}

}
