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
@Table(name = "informeInicial")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class InformeInicial implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre_proyecto")
    private String nombreProyecto;

    @Column(name = "nombre_director")
    private String nombreDirector;

    @Column(name = "nombre_carrera")
    private String nombreCarrera;

    @Column(name = "antecedentes")
    private String antecedentes;

    @Column(name = "objetivo_general")
    private String objetivoGeneral;

    @Column(name = "evidencias")
    private String evidencias;

    @Column(name = "nombre_elaborado")
    private String nombreElaborado;

    @Column(name = "cargo_elaborado")
    private String cargoElaborado;

    @Column(name = "fecha_elaborado")
    private Date fechaElaborado;

    @Column(name = "nombre_revisado")
    private String nombreRevisado;

    @Column(name = "cargo_revisado")
    private String cargoRevisado;

    @Column(name = "fecha_revisado")
    private Date fechaRevisado;

    @OneToMany(targetEntity = EstudiantesInformeInicial.class, mappedBy = "informeInicial")
    private List<EstudiantesInformeInicial> estudianteInformeInicial ;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "proyecto_id", referencedColumnName = "id")
    private ProyectoPPP proyectoPPP;

    @Column(length = 10485760)
    private String documento;


}
