package com.tecazuay.complexivog2c2.dto.anexos.anexo1214y15;

import lombok.Data;

import javax.persistence.Column;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
public class Anexo14Response implements Serializable {

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

        private String resultadoAnexo14;

        private String nombretutoracademico;
        private String cedulatutoracademico;
        private String empresa;
        private String siglascarrera;
        private int tutoracademicoPuntaje;


        private String documento;

        private List<Anexo14TutorAcaRequest> tutoraca;

}
