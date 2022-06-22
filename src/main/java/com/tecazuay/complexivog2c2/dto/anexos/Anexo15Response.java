package com.tecazuay.complexivog2c2.dto.anexos;

import lombok.Data;

import java.io.Serializable;

@Data
public class Anexo15Response implements Serializable {

    private Long id;
    private int dia;
    private String mes;
    private int año;
    private String nombreResponsablePPP;
    private String carrera;
    private String nombreEstudiante;
    private String ciclo;
    private int horas;
    private String periodoAcademico;
    private String nombreEmpresa;
    private Double notaObtenidaTutorAcademico;
    private Double notaObtenidaTutorEmpresarial;
    private Double notaAcreditadaTutorAcademico;
    private Double notaAcreditadaTutorEmpresarial;
    private Double puntajeFinal;
    private String nombreTutorAcademico;
}
