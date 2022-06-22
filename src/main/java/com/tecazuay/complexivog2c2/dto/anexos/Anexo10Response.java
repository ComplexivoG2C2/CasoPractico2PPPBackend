package com.tecazuay.complexivog2c2.dto.anexos;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class Anexo10Response implements Serializable {
    private Long id;

    private String carrera;
    private String siglascarrera;
    private String nombreEstudiante;
    private String cedulaTutorAcademico;
    private String nombreEmpresa;

    private List<CronogramaAnexo10Request> cronogramaAnexo10s;

    private String tutorAcademico;

    private Long idProyectoPPP;

    private String codigoAnexo;

    private String documento;

    private int num_proceso;
}
