package org.example.dto;

import java.io.Serializable;

public class ProductoDTO implements Serializable {
    private Producto entidad;

    public ProductoDTO(Producto entidad) {
        this.entidad = entidad;
    }

    public Producto getEntidad() {
        return entidad;
    }

    public void setEntidad(Producto entidad) {
        this.entidad = entidad;
    }
}