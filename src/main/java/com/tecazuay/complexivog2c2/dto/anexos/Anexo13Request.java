package com.tecazuay.complexivog2c2.dto.anexos;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class Anexo13Request implements Serializable {
    private Long id;
    private Date fechaEmision;
    private Long idProyectoPPP;
    private Long idEmpresa;
    private String documento;
    private Long idTutorEmp;
}
