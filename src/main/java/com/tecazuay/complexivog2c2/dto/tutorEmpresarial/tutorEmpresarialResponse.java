package com.tecazuay.complexivog2c2.dto.tutorEmpresarial;

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
public class tutorEmpresarialResponse implements Serializable {
    private Long id;

    private String cedula;

    private String apellidos;

    private String nombres;

    private boolean estado;

    private String coordinador_id;

    private Date fecha_designacion;

    private Long empresa_id;

    private String correo;

    private String clave;

    private String token;

    public tutorEmpresarialResponse(Long id, String cedula, String apellidos, String nombres, boolean estado, Date fecha_designacion, String correo, String clave, String token) {
        this.id = id;
        this.cedula = cedula;
        this.apellidos = apellidos;
        this.nombres = nombres;
        this.estado = estado;
        this.fecha_designacion = fecha_designacion;
        this.correo = correo;
        this.clave = clave;
        this.token = token;
    }

    public tutorEmpresarialResponse(Long id, String correo, String clave) {
        this.id = id;
        this.correo = correo;
        this.clave = clave;
    }
}
