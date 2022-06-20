package com.tecazuay.complexivog2c2.dto.anexos;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;
@Data
public class TutorEmpRequest implements Serializable {

        private Long id;

        private String cedula;

        private String apellidos;

        private String nombres;

        private boolean estado;

        private Date fecha_designacion;

        private Long empresa_id;

        private String correo;

        private String clave;

        private Long idProyectoPPP;
        public TutorEmpRequest() {
        }

        public TutorEmpRequest(Long id, String cedula, String apellidos, String nombres, boolean estado, Date fecha_designacion, Long empresa_id, String correo, String clave) {
            this.id = id;
            this.cedula = cedula;
            this.apellidos = apellidos;
            this.nombres = nombres;
            this.estado = estado;
            this.fecha_designacion = fecha_designacion;
            this.empresa_id = empresa_id;
            this.correo = correo;
            this.clave = clave;
        }

        public TutorEmpRequest(String correo, String clave) {
            this.correo = correo;
            this.clave = clave;
        }

}