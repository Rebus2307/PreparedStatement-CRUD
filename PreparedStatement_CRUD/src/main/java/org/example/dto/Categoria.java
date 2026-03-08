package org.example.dto;

import lombok.*;
import java.io.Serializable;
import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Categoria implements Serializable {
    private int idCategoria;
    private String nombreCategoria;
    private String descripcionCategoria;
    private Date create_at;
}