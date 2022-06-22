package com.tecazuay.complexivog2c2.dto.anexos;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class Anexo11Request implements Serializable {
    private Long id;
    private String nombreDirectorDocenteApoyo;
    private String cedulaDirectorDocenteApoyo;
    private String periodoAcademicon;
    private String empresa;
    private String representanteLegal;
    private String ciclo;
    private String observaciones;
    private String documento;
    private List<EstudiantesVisitaRequest> estudiantesVisitas;
    private List<ListVisitaRequest> informes;
    private long proyectoId;
    private int num_proceso;
}
