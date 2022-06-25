package com.tecazuay.complexivog2c2.dto.anexos;

import lombok.Data;

import java.util.Date;

@Data
public class ListVisitaResponse {
    private Long id;
    private String asunto;
    private String actividades;
    private String observaciones;
    private String horaInicio;
    private String horaFin;
    private Date fecha;
}
