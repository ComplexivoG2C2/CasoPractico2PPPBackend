package com.tecazuay.complexivog2c2.model.Primary.Anexos;

import com.tecazuay.complexivog2c2.model.Primary.solicitudproyecto.ProyectoPPP;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

    @Entity
    @Table(name = "anexo8")
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public class Anexo8 {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @Column(name = "fecha_respuesta")
        private Date fechaRespuesta;

        @Column(name="estudiante_titulo")
        private String estudianteTitulo;

        @Column(name="nombre_estudiante")
        private String nombreEstudiante;

        @Column(name="cedula_estudiante")
        private String cedulaEstudiante;

        @Column(name = "siglas_carrera")
        private String siglasCarrera;

        @Column(name="nombre_proyecto")
        private String nombreProyecto;

        @Column(name="nombre_tutoremp")
        private String nombreTutoremp;




        @Column(name="nombre_representante")
        private String nombreRepresentante;



        @Column(name="numero_horas")
        private String numeroHoras;

        @Column(name="nombre_responsable")
        private String nombreResponsable;



        @Column(name="fecha_recepcion_est")
        private String fechaRecepcionEst;

        @ManyToOne(cascade = CascadeType.ALL)
        @JoinColumn(name="proyecto_id", referencedColumnName = "id")
        private ProyectoPPP proyectoPPP;

        @Column(length = 10485760)
        private String documento;

        @Column(length = 10485760)
        private String observaciones;

        private int num_proceso;

    }
