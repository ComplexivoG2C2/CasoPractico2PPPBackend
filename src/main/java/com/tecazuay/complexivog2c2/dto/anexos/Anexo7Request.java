package com.tecazuay.complexivog2c2.dto.anexos;

import lombok.Data;

import javax.persistence.Column;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
public class Anexo7Request implements Serializable {
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
    private String cedulaEstudiante;
    private String cedulaTutoracademico;
    private String nombreTutoracademico;

    private String ciclo;

    private int horasCumplidas;

    private List<ActividadesCumplirAnexo7Request> actividadesCumplirAnexo7s;

    private String Fechainicio;

    private Date Fechafin;

    private String horasInicio;
    private String siglascarrera;
    private String horasFin;

    private List<CronogramaActividadesAnexo7Request> cronogramaActividadesAnexo7s;

    private int horasTotales;

    private Long idProyectoPPP;

    private String codigoAnexo;

    private String documento;

    private int num_proceso;

}
