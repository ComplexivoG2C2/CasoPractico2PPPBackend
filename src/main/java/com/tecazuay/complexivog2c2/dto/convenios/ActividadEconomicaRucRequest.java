package com.tecazuay.complexivog2c2.dto.convenios;

import com.tecazuay.complexivog2c2.model.Primary.convenios.RegistroConvenios;
import lombok.Data;

import java.io.Serializable;

@Data
public class ActividadEconomicaRucRequest implements Serializable {
    private Long id;

    private int codActividad;

    private String actividades;

}
