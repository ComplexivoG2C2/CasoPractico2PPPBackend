package com.tecazuay.complexivog2c2.dto.anexos;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class Anexo81Response implements Serializable {
    private Long id;

    private Date fechaNotificacion;

    private String tutorAcademico;

    private String cortesia;

    private String nombreEstudiante;
    private String cedulaEstudiante;
    private String cedulaTutoracademico;
    private String nombreTutoracademico;
    private String nombreEmpresa;

    private String nombreResponsable;

    private String carrera;

    private Long idProyectoPPP;

    private String codigoAnexo;

    private String documento;

    private String siglascarrera;
}
