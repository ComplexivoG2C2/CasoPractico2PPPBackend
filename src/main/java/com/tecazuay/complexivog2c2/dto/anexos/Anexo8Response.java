package com.tecazuay.complexivog2c2.dto.anexos;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class Anexo8Response implements Serializable {

        private Long id;
        private Date fechaRespuesta;
        private String estudianteTitulo;
        private String nombreEstudiante;
        private String siglasCarrera;
        private String nombreProyecto;
        private String nombreTutoremp;
        private String nombreRepresentante;
        private String numeroHoras;
        private String nombreResponsable;
        private String fechaRecepcionEst;
        private Long idProyectoPPP;
        private String documento;
        private String cedulaEstudiante;
        private String observaciones;
        private int num_proceso;
    }
