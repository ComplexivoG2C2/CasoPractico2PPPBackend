package com.tecazuay.complexivog2c2.dto.anexos;

import lombok.Data;

import java.io.Serializable;

@Data
public class AlumnosAnexo6Request implements Serializable {
    private Long id;
    private String nombreEstudiante;
    private String cedulaEstudiante;

}
