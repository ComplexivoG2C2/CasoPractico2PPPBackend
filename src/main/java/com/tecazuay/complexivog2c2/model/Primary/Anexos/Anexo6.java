package com.tecazuay.complexivog2c2.model.Primary.Anexos;

import com.tecazuay.complexivog2c2.model.Primary.solicitudproyecto.ProyectoPPP;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "anexo6")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Anexo6 implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Temporal(TemporalType.DATE)
    @Column(name = "fecha_emision")
    private Date fechaEmision;

    @Column(name = "titulo_tercer_nivel")
    private String tituloTercerN;

    @Column(name = "titulo_cuarto_nivel")
    private String tituloCuartoN;

    @Column(name = "nombre_docente_receptor")
    private String nombreDocenteReceptor;

    @Column(name = "siglas_carrera")
    private String siglasCarrera;

    @Column(name = "nombres_docente_emisor")
    private String nonbreDocenteEmisor;

    @Column(name = "cedula_docente_apoyo")
    private String cedulaDocenteApoyo;

    @Column(name = "nombre_proyecto")
    private String nombreProyecto;

    @Temporal(TemporalType.DATE)
    @Column(name = "fecha_recepcion")
    private Date fechaRecepcion;

    @Column(length = 10485760)
    private String documento;

    @OneToMany(targetEntity = AlumnosAnexo6.class, mappedBy = "anexo6")
    private List<AlumnosAnexo6> alumnosAnexo5s;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "proyecto_id", referencedColumnName = "id")
    private ProyectoPPP proyectoPPP;

    private int num_proceso;
}