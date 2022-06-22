package com.tecazuay.complexivog2c2.model.Primary.Anexos;

import com.tecazuay.complexivog2c2.model.Primary.proyecto.ProyectoPPP;

import javax.persistence.*;
import java.util.Date;

public class Anexo14 {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "cedula_estudiante")
    private String cedulaEstudiante;

    @Column(name = "nombre_estudiante")
    private String nombreEstudiante;

    @Column(name = "nombre_empresa")
    private String nombreEmpresa;

    @Column(name = "carrera")
    private String carrera;

    @Column(name = "inicio_practicas")
    private Date inicioPracticas;

    @Column(name = "fin_practicas")
    private Date finPracticas;

    @Column(name = "puntaje_total")
    private int puntajeTotal;

    @Column(name = "numero_horas")
    private int numeroHoras;

    @Column(name = "nombre_tutor_academico")
    private String nombreTutorAcademico;

    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "proyecto_id", referencedColumnName = "id")
    private ProyectoPPP proyectoPPP;

}
