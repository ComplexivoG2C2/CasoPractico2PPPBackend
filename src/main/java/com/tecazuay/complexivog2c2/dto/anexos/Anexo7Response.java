package com.tecazuay.complexivog2c2.dto.anexos;


import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
public class Anexo7Response implements Serializable {
    private Long id;

    private Date fechaReunion;

    private String nombreResponsable;

    private String carrera;

    private String tituloTutorEmp;

    private String nombreTutorEmp;

    private String nombreEmpresa;

    private List<ActividadesAnexo7Request> actividadesAnexo7s;

    private String lugarReunion;

    private String cortesia;

    private String nombreEstudiante;

    private String ciclo;

    private int horasCumplidas;

    private List<ActividadesCumplirAnexo7Request> actividadesCumplirAnexo7s;

    private Date Fechainicio;

    private Date Fechafin;

    private int horasInicio;

    private int horasFin;

    private List<CronogramaActividadesAnexo7Request> cronogramaActividadesAnexo7s;

    private int horasTotales;

    private Long idProyectoPPP;

    private String codigoAnexo;

    private String documento;

    private int num_proceso;
}
