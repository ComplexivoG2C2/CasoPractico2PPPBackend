package com.tecazuay.complexivog2c2.model.Primary.Anexos.anexo1214y15;

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
@Table(name="anexo14")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Anexo14 implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombres_completos_estu")
    private String nombresEstudiante;

    @Column(name = "cedula_estudiante")
    private String cedulaEstudiante;

    @Column(name = "carrera")
    private String carrera;

    @Column(name = "fecha_inicio")
    private Date fechaInicio;

    @Column(name = "fecha_finaliza")
    private Date fechaFinaliza;

    @Column(name = "fecha_evaluacion")
    private Date fechaEvaluacion;

    @Column(name = "total_horas")
    private int totalHoras;

    @Column(name = "promedio")
    private int promedio;

    @Column(name = "resultado_anexo14")
    private String resultadoAnexo14;

    @Column(name = "nombre_tutoracademico")
    private String nombretutoracademico;
    @Column(name = "cedulatutoracademico")
    private String cedulatutoracademico;
    @Column(name = "empresa")
    private String empresa;
    @Column(name = "siglascarrera")
    private String siglascarrera;
    @Column(name = "tutoracademicopuntaje")
    private int tutoracademicoPuntaje;

    @Column(length = 10485760)
    private String documento;

    //
    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "proyecto_id", referencedColumnName = "id")
    private ProyectoPPP proyectoPPP;

    @OneToMany(targetEntity = ItemsAnexo14Tutoracademcio.class, mappedBy = "anexo14")
    private List<ItemsAnexo14Tutoracademcio> itemsTutorAcademico;
}