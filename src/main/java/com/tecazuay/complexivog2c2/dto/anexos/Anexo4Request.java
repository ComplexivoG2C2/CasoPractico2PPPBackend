package com.tecazuay.complexivog2c2.dto.anexos;


import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.io.Serializable;
import java.util.Date;

@Data
public class Anexo4Request implements Serializable {
    private Long id;

    private Date fechaRespuesta;

    private String nombreRepresentanteEmp;

    private String cargoEmpresa;

    private String nombreEmpresa;

    private Date fechaSolicitudEmp;

    private String documento; //Byte[] a String

    private Long idProyectoPPP;

    private int num_proceso;

    private String carrera;

    private String nombreResponsable;
}
