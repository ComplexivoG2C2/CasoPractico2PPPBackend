package com.tecazuay.complexivog2c2.dto.anexos;


import lombok.Data;

import java.io.Serializable;

@Data
public class ListaEstudiantesAnexo4Request implements Serializable {
    private Long id;

    private String cedula;

    private String nombre;
}
