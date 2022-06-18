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

    @Column(name = "nombre_Empresa")
    private String nombreEmpresa;

    @Column(name = "nombre_estudiante")
    private String nombreEstudiante;

    @Column(name = "cedula_estudiante")
    private String cedulaEstudiante;

    @Column(name = "cedula_director_proyecto")
    private String cedulaDirector;

    @Column(name = "nombre_administrador_EB")
    private String nombreAdminEB;

    @Column(name = "nombre_docente_apoyo")
    private String nombreDocenteApoyo;

    @Column(name = "nombre_director_proyecto")
    private String nombreDirectorProyecto;

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
