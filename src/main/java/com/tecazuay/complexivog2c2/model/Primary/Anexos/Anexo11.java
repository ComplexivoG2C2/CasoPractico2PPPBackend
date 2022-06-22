package com.tecazuay.complexivog2c2.model.Primary.Anexos;
import com.tecazuay.complexivog2c2.model.Primary.solicitudproyecto.ProyectoPPP;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "anexo11")
public class Anexo11 implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre_director_docente_apoyo")
    private String nombreDirectorDocenteApoyo;

    @Column(name = "cedula_director_docente_apoyo")
    private String cedulaDirectorDocenteApoyo;

    @Column(name = "periodo_academico")
    private String periodoAcademicon;

    private String empresa;

    @Column(name = "representante_legal")
    private String representanteLegal;

    private String ciclo;

    private String observaciones;

    @Column(length = 10485760)
    private String documento;

    @OneToMany(targetEntity = EstudiantesVisita.class, mappedBy = "anexo11")
    private List<EstudiantesVisita> estudiantesVisita;

    @OneToMany(targetEntity = ListVisitaAnexo11.class, mappedBy = "anexo11")
    private List<ListVisitaAnexo11> informes;

    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "proyecto_id", referencedColumnName = "id")
    private ProyectoPPP proyectoPPP;

    private int num_proceso;
}