package com.tecazuay.complexivog2c2.model.Primary.Anexos;

import com.tecazuay.complexivog2c2.model.Primary.solicitudproyecto.ProyectoPPP;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "anexo81")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Anexo81 implements Serializable {
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
    @Column(name = "cedula_estudiante")
    private String cedulaEstudiante;
    @Column(name = "cedula_tutoracademico")
    private String cedulaTutoracademico;
    @Column(name = "nombre_tutoracademico")
    private String nombreTutoracademico;

    @Column(name = "nombre_empresa")
    private String nombreEmpresa;

    @Column(name = "nombre_responsable")
    private String nombreResponsable;

    private String carrera;

    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "proyecto_id", referencedColumnName = "id")
    private ProyectoPPP proyectoPPP;

    private String codigoAnexo;

    @Column(length = 10485760)
    private String documento;

    private String siglascarrera;
}
