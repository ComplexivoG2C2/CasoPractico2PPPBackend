package com.tecazuay.complexivog2c2.dto.anexos;
import lombok.Data;

import javax.persistence.Column;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
public class InformeInicialRequest implements Serializable  {
    private Long id;
    private Long idProyectoPPP;
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
    //private String cedulaEstudiante;
    
    private List<EstudiantesInformeInicialRequest> estudianteInformeInicial;
    @Column(length = 10485760)
    private String documento;



}
