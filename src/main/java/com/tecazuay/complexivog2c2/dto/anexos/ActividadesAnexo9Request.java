package com.tecazuay.complexivog2c2.dto.anexos;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class ActividadesAnexo9Request implements Serializable {
    private Long id;
    private Date fecha;
    private String descripcionActividad;
    private String lugar;
    private int numHoras;
}
