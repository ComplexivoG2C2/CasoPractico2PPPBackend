package com.tecazuay.complexivog2c2.model.Primary.Anexos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "anexo8_1")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Anexo8_1 implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "fecha_notificacion")
    @Temporal(TemporalType.DATE)
    private Date fechaNotificacion;

    @Column(name = "tutor_academico")
    private String tutorAcademico;

    private String cortesia;

    @Column(name = "nombre_estudiante")
    private String nombreEstudiante;

    @Column(name = "nombre_empresa")
    private String nombreEmpresa;

    @Column(name = "nombre_responsable")
    private String nombreResponsable;

    private String carrera;

    @Column(length = 10485760)
    private String documento;

    private int num_proceso;
}
