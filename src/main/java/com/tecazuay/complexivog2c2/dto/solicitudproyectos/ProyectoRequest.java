package com.tecazuay.complexivog2c2.dto.solicitudproyectos;

import com.tecazuay.complexivog2c2.dto.docentes.DocenteRequest;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
public class ProyectoRequest implements Serializable {

    private Long id;
    private String codigo;

    private String nombre;
    private int participantes;


    private String cargosolicitante;
    private String nombresolicitante;
    private String nombreempresa;
    private Long empresa;

    private Long tutoremp;

    private String nombreTutoremp;
    private String cedulaTutoremp;
    private String tituloTutoremp;

    private String carrera;
    private String codigocarrera;

    private boolean estado;

    private Date fechaat;

    private String tutorempProyecto;

    private Long responsablePPP;

    private String documento;

    private List<ActividadeslistProyecto> actividadeslistProyectos;

    private List<RequisitoslistProyecto> requisitoslistProyectos;


    private List<ActividadesEmpresalistProyecto> actividadesEmpresaProyecto;

    private List<DocenteRequest> docentesDelegados;

    private String coordinadorCedula;

    private String plazoEjecucion;

    private Date fechaInicio;

    private Date fechaFin;

    private int horas;
}

