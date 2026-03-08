package org.example.dto;

import java.io.Serializable;

public class CategoriaDTO implements Serializable {
    private Categoria entidad;

    public CategoriaDTO(Categoria entidad) {
        this.entidad = entidad;
    }

    public Categoria getEntidad() {
        return entidad;
    }

    public void setEntidad(Categoria entidad) {
        this.entidad = entidad;
    }
}