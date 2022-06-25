package com.tecazuay.complexivog2c2.dto.anexos;

import lombok.*;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
public class Anexo2Request implements Serializable {
    private Long id;
    private String siglasCarrera;
    private String anio;
    private String numeroConvocatoria;
    private Date fecha;
    private String ciclo;
    private String carrera;
    private String nombreProyecto;
    private String empresa;
    //private String actividadesProyecto;
   // private String materias;
    private Date fechaMaxRecepcion;
    private String nombreResponsable;
    private String emailDocente;
    private List<ActividadesAnexo2Request> actividades;
    private Long idProyectoPPP;
    private String documento;
   // private String docx;
    private String codigoAnexo;
    private int num_proceso;
}
