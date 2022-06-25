package com.tecazuay.complexivog2c2.dto.anexos.anexo121;

import com.tecazuay.complexivog2c2.dto.anexos.anexo1214y15.Anexo12TutorempRequest;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
@Data
public class Anexo121certificadoRequest implements Serializable {

    private Long id;
    private Long idProyecto;
    private String nombresEstudiante;
    private String cedulaEstudiante;
    private String carrera;
    private Date fechaInicio;
    private Date fechaFinaliza;
    private Date fechaEvaluacion;
    private int totalHoras;
    private int promedio;

    private String nombretutoremp;
    private String cedulatutoremp;
    private String empresa;
    private String siglascarrera;
    private int tutorempPuntaje;

    private String documento;

    private List<ActividadesAnexo121Request> actividades;
}