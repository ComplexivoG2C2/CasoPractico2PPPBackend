package com.tecazuay.complexivog2c2.dto.solicitudproyectos;

import com.tecazuay.complexivog2c2.dto.docentes.designaciones.TutorAcademicoResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProyectoResponse implements Serializable {

    private Long id;
    private String codigo;
    private String nombre;
    private int participantes;

    private String cargosolicitante;
    private String nombresolicitante;
    private String nombreempresa;
    private Long empresa;
    private Long tutoremp;
    private String carrera;
    private String codigocarrera;
    private boolean estado;
    private Date fechaat;

    private String nombreTutoremp;
    private String cedulaTutoremp;
    private String tituloTutoremp;

    private String nombreresponsable;
    private String documento;
    private String plazoEjecucion;
    private Date fechaInicio;
    private Date fechaFin;
    private int horas;

    private List<ActividadeslistProyecto> actividadeslistProyectos;

    private List<RequisitoslistProyecto> requisitoslistProyectos;

    private List<TutorAcademicoResponse> tutorAcademicoResponse;

    private List<ActividadesEmpresalistProyecto> actividadesEmpresaProyecto;



}
