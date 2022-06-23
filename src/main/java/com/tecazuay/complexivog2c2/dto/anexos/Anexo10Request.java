package com.tecazuay.complexivog2c2.dto.anexos;

import com.tecazuay.complexivog2c2.model.Primary.Anexos.CronogramaAnexo10;
import com.tecazuay.complexivog2c2.model.Primary.solicitudproyecto.ProyectoPPP;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Data
public class Anexo10Request implements Serializable {
    private Long id;

    private String carrera;

    private String nombreEstudiante;

    private String nombreEmpresa;

    private List<CronogramaAnexo10Request> cronogramaAnexo10s;

    private String tutorAcademico;

    private Long idProyectoPPP;

    private String codigoAnexo;

    private String documento;

    private int num_proceso;
}
