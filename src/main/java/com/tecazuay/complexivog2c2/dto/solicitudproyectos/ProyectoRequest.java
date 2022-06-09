package com.tecazuay.complexivog2c2.dto.solicitudproyectos;

import com.tecazuay.complexivog2c2.dto.docentes.DocenteRequest;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
public class ProyectoRequest implements Serializable {

    private Long id;

    private int participantes;

    private String codigo;

    private String nombre;

    private String lineaaccion;

    private String codigocarrera;

    private boolean estado;

    private Date fechaat;

    private Long empresa;

    private String tutorempProyecto;

    private Long responsablePPP;

    private String documento;

    private String programaVinculacion;

    private List<ActividadeslistProyecto> actividadeslistProyectos;

    private List<RequisitoslistProyecto> requisitoslistProyectos;

    //FALTA COORDINADOR DE CARERRA EL ID
   // private List<TutorAcademicoDelegados> docentesApoyoDelegados;

    private List<ActividadesEmpresalistProyecto> actividadesEmpresaProyecto;

    private List<DocenteRequest> docentesDelegados;

    private String coordinadorCedula;

    private String plazoEjecucion;

    private Date fechaInicio;

    private Date fechaFin;

    private int horas;
}

