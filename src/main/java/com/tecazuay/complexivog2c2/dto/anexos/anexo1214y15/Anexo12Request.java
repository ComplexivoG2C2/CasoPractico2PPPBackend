package com.tecazuay.complexivog2c2.dto.anexos.anexo1214y15;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
public class Anexo12Request  implements Serializable {

        private Long id;
        private Long idProyecto;
        private String nombresEstudiante;
        private String cedulaEstudiante;
        private String carrera;
        private Date fechaInicio;
        private Date fechaFinaliza;
        private Date fechaEvaluacion;
        private int totalHoras;
        private String resultadoAnexo12;
        private int promedio;

        private String nombretutoremp;
        private String cedulatutoremp;
        private String empresa;
        private String siglascarrera;
        private int tutorempPuntaje;

        private String documento;

        private List<Anexo12TutorempRequest> tutoremp;
    }