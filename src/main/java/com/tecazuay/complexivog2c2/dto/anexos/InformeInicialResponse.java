package com.tecazuay.complexivog2c2.dto.anexos;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
@Data
public class InformeInicialResponse implements Serializable {
    private Long id;
    private String nombreProyecto;
    private String nombreDirector;
    private String nombreCarrera;
    private String antecedentes;
    private String objetivoGeneral;
    private String evidencias;
    private String nombreElaborado;
    private String cargoElaborado;
    private Date fechaElaborado;
    private String nombreRevisado;
    private String cargoRevisado;
    private Date fechaRevisado;
    private Long idProyectoPPP;
    private List<EstudiantesInformeInicialRequest> estudianteInformeInicial;
}
