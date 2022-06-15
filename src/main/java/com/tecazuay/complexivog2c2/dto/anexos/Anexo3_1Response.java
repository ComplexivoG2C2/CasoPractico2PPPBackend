package com.tecazuay.complexivog2c2.dto.anexos;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class Anexo3_1Response implements Serializable {
    private Long id;

    private Date fechaRespuesta;

    private String tituloRepresentanteEmp;

    private String nombreRepresentanteEmp;

    private String cargoEmpresa;

    private String nombreEmpresa;

    private Date fechaSolicitudEmp;

    private String nombreResponsable;

    private String carrera;

    private Long idProyectoPPP;

    private String documento;

    // private String docx;
    private String codigoAnexo;
    private int num_proceso;
}

