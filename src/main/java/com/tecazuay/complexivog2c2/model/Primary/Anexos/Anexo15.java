package com.tecazuay.complexivog2c2.model.Primary.Anexos;

import com.tecazuay.complexivog2c2.model.Primary.proyecto.ProyectoPPP;

import javax.persistence.*;

public class Anexo15 {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "dia")
    private int dia;

    @Column(name = "mes")
    private String mes;

    @Column(name = "año")
    private int año;

    @Column(name = "nombre_responsablePPP")
    private String nombreResponsablePPP;

    @Column(name = "carrera")
    private String carrera;

    @Column(name = "nombre_estudiante")
    private String nombreEstudiante;

    @Column(name = "ciclo")
    private String ciclo;

    @Column(name = "horas")
    private int horas;

    @Column(name = "periodo_academico")
    private String periodoAcademico;

    @Column(name = "nombre_empresa")
    private String nombreEmpresa;

    @Column(name = "nota_obteida_tutor_academico")
    private Double notaObtenidaTutorAcademico;

    @Column(name = "nota_obtenida_tutor_empreasrial")
    private Double notaObtenidaTutorEmpresarial;

    @Column(name = "nota_acreditada_tutor_academico")
    private Double notaAcreditadaTutorAcademico;

    @Column(name = "nota_acreditada_tutor_empresarial")
    private Double notaAcreditadaTutorEmpresarial;

    @Column(name = "puntaje_final")
    private Double puntajeFinal;

    @Column(name = "nombre_tutor_academico")
    private String nombreTutorAcademico;

    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "proyecto_id", referencedColumnName = "id")
    private ProyectoPPP proyectoPPP;

}
