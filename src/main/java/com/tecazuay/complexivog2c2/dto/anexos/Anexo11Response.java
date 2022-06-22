package com.tecazuay.complexivog2c2.dto.anexos;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class Anexo11Response implements Serializable {
    private Long id;
    private String nombreDirectorDocenteApoyo;
    private String cedulaDirectorDocenteApoyo;
    private String nombreest;
    private String cedulaest;
    private String nombretutoremp;
    private String cedulaetutoremp;
    private String carrera;
    private String siglascarrera;
    private String periodoAcademicon;
    private String empresa;
    private String representanteLegal;
    private String ciclo;
    private String observaciones;
    private String documento;
    private List<EstudiantesVisitaResponse> estudiantesVisitas;
    private List<ListVisitaResponse> informes;
    private Long proyectoId;
    private int num_proceso;
}
