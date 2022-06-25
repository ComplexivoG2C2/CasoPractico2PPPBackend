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
@Table(name = "anexo9")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Anexo9 implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre_proyecto")
    private String nombreProyecto;

    @Column(name = "nombre_empresa")
    private String nombreEmpresa;

    @Column(name = "nombre_estudiante")
    private String nombreEstudiante;

    @Column(name = "cedula_estudiante")
    private String cedulaEstudiante;

    @Column(name = "cedula_tutoremp")
    private String cedulaTutoremp;

    @Column(name = "nombre_representanteemp")
    private String nombreRepresentanteemp;

    @Column(name = "nombre_tutoraca")
    private String nombreTutorAcademico;

    @Column(name = "nombre_tutoremp")
    private String nombreTutoremp;

    @Column(name = "total_horas")
    private String totalHoras;

    @Column(length = 10485760)
    private String documento;

    @OneToMany(targetEntity = ActividadesAnexo9.class, mappedBy = "anexo9")
    private List<ActividadesAnexo9> actividadesAnexo9s;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "proyecto_id", referencedColumnName = "id")
    private ProyectoPPP proyectoPPP;

}