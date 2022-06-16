package com.tecazuay.complexivog2c2.dto.anexos;

import lombok.Data;

import java.io.Serializable;

@Data
public class CronogramaActividadesAnexo7Request implements Serializable {
    private Long id;

    private String actividadRealizar;

    private int semanas;

    private int nrohoras;

    private int horasTotales;

}
