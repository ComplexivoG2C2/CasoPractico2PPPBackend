package com.tecazuay.complexivog2c2.dto.anexos;

import lombok.Data;

import java.io.Serializable;

@Data
public class ActividadEconomicaRucRequest implements Serializable {
    private Long id;

    private String codActividad;

    private String actividades;

}
