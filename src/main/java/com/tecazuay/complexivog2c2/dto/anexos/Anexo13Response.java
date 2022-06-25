package com.tecazuay.complexivog2c2.dto.anexos;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@Data
@NoArgsConstructor
public class Anexo13Response implements Serializable {

    private Long id;
    private Date fechaEmision;
    private Long idProyectoPPP;
    private Long idEmpresa;
    private String documento;
    private Long idTutorEmp;
}
