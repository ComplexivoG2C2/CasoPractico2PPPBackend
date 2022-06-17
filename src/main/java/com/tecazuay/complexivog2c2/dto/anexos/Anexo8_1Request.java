package com.tecazuay.complexivog2c2.dto.anexos;

import com.tecazuay.complexivog2c2.model.Primary.solicitudproyecto.ProyectoPPP;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
public class Anexo8_1Request implements Serializable {
    private Long id;

    private Date fechaNotificacion;

    private String tutorAcademico;

    private String cortesia;

    private String nombreEstudiante;

    private String nombreEmpresa;

    private String nombreResponsable;

    private String carrera;

    private Long idProyectoPPP;

    private String codigoAnexo;

    private String documento;

    private int num_proceso;
}
