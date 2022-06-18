package com.tecazuay.complexivog2c2.dto.anexos;

import lombok.Data;

import java.util.List;
@Data
public class Anexo9Request {
    private Long id;
    private String nombreProyecto;
    private String nombreEmpresa;
    private String nombreEstudiante;
    private String cedulaEstudiante;
    private String nombreAdminEB;
    private String nombreDocenteApoyo;
    private String nombreDirectorProyecto;
    private Long idProyectoPPP;
    private String documento;
    private String totalHoras;
    private String cedulaDirector;
    private List<ActividadesAnexo9Request> actividades;
}

