package com.tecazuay.complexivog2c2.dto.anexos;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class CronogramaAnexo10Request implements Serializable {
    private Long id;

    private int nro;

    private Date fechaSeguimiento;

    private String actividades;

    private Date fechaFinPrevista;

    private int porcentajeAvance;

    private String observaciones;

}
