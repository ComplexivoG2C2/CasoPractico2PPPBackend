package com.tecazuay.complexivog2c2.dto.anexos;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
public class Anexo6Request implements Serializable {
    private Long id;
    private Date fechaEmision;
    private String tituloTercerN;
    private String tituloCuartoN;
    private String nombreDocenteReceptor;
    private String siglasCarrera;
    private String nonbreDocenteEmisor;
    private Date fechaRecepcion;
    private Long idProyectoPPP;
    private String cedulaDocenteApoyo;
    private String nombreProyecto;
    private String documento;
    private List<AlumnosAnexo6Request> alumnos ;
    private int num_proceso;
}
