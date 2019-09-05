package com.poli.policlass.model.dto;

import com.poli.policlass.model.entity.Sala;

public class RoomDTO implements DTO<Sala> {
	private String name;
	private BlocoDTO bloco;

	@Override
	public Sala convert() {
		Sala sala = new Sala();
		sala.setName(name);
		sala.setBloco(bloco.convert());
		return sala;
	}

}
