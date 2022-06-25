package com.tecazuay.complexivog2c2.dto.anexos.anexo1214y15;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class Anexo15Request implements Serializable {
    private Long id;
    private Long idProyecto;
    private String nombresEstudiante;
    private String cedulaEstudiante;
    private String carrera;
    private Date fechaEvaluacion;
    private int totalHoras;
    private String nombretutoracademico;
    private String cedulatutoracademico;
    private String nombretutoremp;
    private String cedulatutoremp;
    private String empresa;
    private String siglascarrera;
    private String periodoacademico;
    private int notaTutorA;
    private int notaTutorE;
    private int porcentajeTutorA;
    private int porcentajeTutorE;
    private int promediofinal;
    private double promediofinaldecimal;
    private String ciclo;
    private String documento;

}
