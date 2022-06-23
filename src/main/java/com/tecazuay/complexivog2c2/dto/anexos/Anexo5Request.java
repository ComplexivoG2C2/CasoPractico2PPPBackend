package com.tecazuay.complexivog2c2.dto.anexos;

import lombok.Data;

import javax.persistence.Column;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
public class Anexo5Request implements Serializable {
    private Long id;
    private Date fechaEmision;
    private String tituloTutor;
    private String documento;
    private Long idProyectoPPP;
    private String nombreTutor;
    private Long idEmpresa;
    private String nombreEst;
    private String cedulaEst;
    private String carrera;
    private String siglascarrera;
    private List<AlumnosAnexo5Request> alumnos ;
}
