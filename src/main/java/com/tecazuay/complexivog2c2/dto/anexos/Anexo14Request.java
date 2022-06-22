package com.tecazuay.complexivog2c2.dto.anexos;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class Anexo14Request implements Serializable {
    private Long id;
    private String cedulaEstudiante;
    private String nombreEstudiante;
    private String nombreEmpresa;
    private String carrera;
    private Date inicioPracticas;
    private Date finPracticas;
    private int puntajeTotal;
    private int numeroHoras;
    private String nombreTutorAcademico;

}
