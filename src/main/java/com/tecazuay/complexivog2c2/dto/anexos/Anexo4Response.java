package com.tecazuay.complexivog2c2.dto.anexos;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
public class Anexo4Response implements Serializable {
    private Long id;

    private Date fechaRespuesta;

    private String nombreRepresentanteEmp;

    private String cargoEmpresa;

    private String nombreEmpresa;

    private Date fechaSolicitudEmp;

    private String documento; //Byte[] a String

    private Long idProyectoPPP;

    private int num_proceso;

    private String carrera;

    private String nombreResponsable;

    private String codigoAnexo;

    private List<ListaEstudiantesAnexo4Request> listaEstudiantes;
}
