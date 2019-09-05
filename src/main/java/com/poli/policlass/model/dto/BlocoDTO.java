package com.poli.policlass.model.dto;

import com.poli.policlass.model.entity.Bloco;

public class BlocoDTO implements DTO<Bloco> {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public Bloco convert() {
        Bloco bloco = new Bloco();
        bloco.setName(name);
        return bloco;
    }
}
