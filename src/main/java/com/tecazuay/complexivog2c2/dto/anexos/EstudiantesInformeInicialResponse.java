package com.tecazuay.complexivog2c2.dto.anexos;

import lombok.Data;

import javax.persistence.Column;
import java.io.Serializable;
@Data
public class EstudiantesInformeInicialResponse implements Serializable {
    private Long id;
    private String nombreEstudiante;
    private String estado;
    private String observaciones;

    @Column(length = 10485760)
    private String documento;
}
