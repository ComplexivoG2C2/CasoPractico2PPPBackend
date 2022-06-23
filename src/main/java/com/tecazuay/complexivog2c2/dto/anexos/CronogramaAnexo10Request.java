package com.tecazuay.complexivog2c2.dto.anexos;

import com.tecazuay.complexivog2c2.model.Primary.Anexos.Anexo10;
import lombok.Data;

import javax.persistence.*;
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
