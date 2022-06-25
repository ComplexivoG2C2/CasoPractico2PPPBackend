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
@Table(name = "anexo2")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Anexo2 implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "siglas_carrera")
    private String siglasCarrera;

    @Column(name = "anio")
    private String anio;

    @Column(name = "numero_convocatoria")
    private String numeroConvocatoria;

    @Temporal(TemporalType.DATE)
    private Date fecha;

    private String ciclo;
    private String carrera;

    @Column(name = "nombre_proyecto")
    private String nombreProyecto;

    @Column(name = "empresa")
    private String empresa;
    //private String actividades;
    //private String materias;

    @Temporal(TemporalType.DATE)
    @Column(name = "fecha_max_recepcion")
    private Date fechaMaxRecepcion;

    @Column(name = "nombre_responsable")
    private String nombreResponsable;

    @Column(name = "email_docente")
    private String emailDocente;

    @OneToMany(targetEntity = ActividadesAnexo2.class, mappedBy = "anexo2")
    private List<ActividadesAnexo2> actividadesAnexo2s;

    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "proyecto_id", referencedColumnName = "id")
    private ProyectoPPP proyectoPPP;

    private String codigoAnexo;

    @Column(length = 10485760)
    private String documento;

    private int num_proceso;
}
