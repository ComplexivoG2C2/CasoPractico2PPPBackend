package com.tecazuay.complexivog2c2.dto.anexos;

import lombok.Data;

import java.util.List;

@Data
public class Anexo9Response {
    private Long id;
    private String nombreProyecto;
    private String nombreEmpresa;
    private String nombreEstudiante;
    private String cedulaEstudiante;
    private String nombreRepresentanteemp;
    private String siglascarrera;
    private String nombreTutorAcademico;
    private String nombreTutoremp;
    private Long idProyectoPPP;
    private String documento;
    private String totalHoras;
    private String cedulaTutoremp;
    private List<ActividadesAnexo9Request> actividades;
}

