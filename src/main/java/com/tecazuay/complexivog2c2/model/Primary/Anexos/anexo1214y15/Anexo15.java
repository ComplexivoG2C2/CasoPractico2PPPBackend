package com.tecazuay.complexivog2c2.model.Primary.Anexos.anexo1214y15;

import com.tecazuay.complexivog2c2.model.Primary.solicitudproyecto.ProyectoPPP;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name="anexo15")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Anexo15 implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombres_completos_estu")
    private String nombresEstudiante;

    @Column(name = "cedula_estudiante")
    private String cedulaEstudiante;

    @Column(name = "carrera")
    private String carrera;

    @Column(name = "fecha_evaluacion")
    private Date fechaEvaluacion;

    @Column(name = "total_horas")
    private int totalHoras;

    @Column(name = "nombre_tutoracademico")
    private String nombretutoracademico;
    @Column(name = "cedulatutoracademico")
    private String cedulatutoracademico;
    @Column(name = "empresa")
    private String empresa;
    @Column(name = "siglascarrera")
    private String siglascarrera;
    @Column(name = "periodoacademico")
    private String periodoacademico;

    @Column(name = "notatutorA")
    private int notaTutorA;
    @Column(name = "notatutorE")
    private int notaTutorE;
    @Column(name = "porcentajetutorA")
    private int porcentajeTutorA;
    @Column(name = "porcentajetutorE")
    private int porcentajeTutorE;
    @Column(name = "promedio")
    private int promediofinal;

    @Column(name = "ciclo")
    private String ciclo;

    @Column(length = 10485760)
    private String documento;

    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "proyecto_id", referencedColumnName = "id")
    private ProyectoPPP proyectoPPP;
}
