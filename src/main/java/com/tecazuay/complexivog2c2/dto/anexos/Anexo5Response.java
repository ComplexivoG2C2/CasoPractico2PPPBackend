package com.tecazuay.complexivog2c2.dto.anexos;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
public class Anexo5Response implements Serializable {
    private Long id;
    private Date fechaEmision;
    private String tituloTutor;
    private String documento;
    private Long idProyectoPPP;
    private Long idEmpresa;
    private List<AlumnosAnexo5Request> alumnos ;
}
