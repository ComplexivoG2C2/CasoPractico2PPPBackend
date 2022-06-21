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
@Table(name = "anexo10")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Anexo10 implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String cedulaTutorAcademico;
    private String carrera;
    private String siglascarrera;
    @Column(name = "nombre_estudiante")
    private String nombreEstudiante;

    @Column(name = "nombre_empresa")
    private String nombreEmpresa;

    @OneToMany(targetEntity = CronogramaAnexo10.class, mappedBy = "anexo10")
    private List<CronogramaAnexo10> cronogramaAnexo10s;

    @Column(name = "tutor_academico")
    private String tutorAcademico;

    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "proyecto_id", referencedColumnName = "id")
    private ProyectoPPP proyectoPPP;

    private String codigoAnexo;

    @Column(length = 10485760)
    private String documento;

    private int nro;
}
