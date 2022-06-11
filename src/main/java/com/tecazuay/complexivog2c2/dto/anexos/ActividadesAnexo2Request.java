package com.tecazuay.complexivog2c2.dto.anexos;

import lombok.*;

import java.io.Serializable;
import java.util.Date;
@Data
public class ActividadesAnexo2Request implements Serializable {
    private Long id;
    private String descripcion;
    private Date inicio;
    private Date fin;
}
