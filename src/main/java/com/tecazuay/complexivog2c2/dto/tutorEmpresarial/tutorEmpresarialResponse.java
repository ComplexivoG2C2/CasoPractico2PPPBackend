package com.tecazuay.complexivog2c2.dto.tutorEmpresarial;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Data
public class tutorEmpresarialResponse {
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

    public tutorEmpresarialResponse() {
    }

    public tutorEmpresarialResponse(Long id, String cedula, String apellidos, String nombres, boolean estado, String coordinador_id, Date fecha_designacion, Long empresa_id, String correo, String clave) {
        this.id = id;
        this.cedula = cedula;
        this.apellidos = apellidos;
        this.nombres = nombres;
        this.estado = estado;
        this.coordinador_id = coordinador_id;
        this.fecha_designacion = fecha_designacion;
        this.empresa_id = empresa_id;
        this.correo = correo;
        this.clave = clave;
    }
}
