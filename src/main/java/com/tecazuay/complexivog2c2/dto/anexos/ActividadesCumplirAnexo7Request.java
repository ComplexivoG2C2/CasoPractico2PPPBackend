package com.tecazuay.complexivog2c2.dto.anexos;

import lombok.Data;

import java.io.Serializable;

@Data
public class ActividadesCumplirAnexo7Request implements Serializable {

    private Long id;

    private String area;

    private String actividadRealizar;

    private String asignaturaRelacionada;

}
