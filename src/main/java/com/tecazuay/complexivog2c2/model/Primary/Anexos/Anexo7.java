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
@Table(name = "anexo7")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Anexo7 implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "fecha_reunion")
    @Temporal(TemporalType.DATE)
    private Date fechaReunion;

    @Column(name = "nombre_responsable")
    private String nombreResponsable;

    private String carrera;

    @Column(name = "titulo_tutor_emp")
    private String tituloTutorEmp;

    @Column(name = "nombre_tutor_emp")
    private String nombreTutorEmp;

    @Column(name = "nombre_empresa")
    private String nombreEmpresa;

    @OneToMany(targetEntity = ActividadesAnexo7.class, mappedBy = "anexo7")
    private List<ActividadesAnexo7> actividadesAnexo7s;

    @Column(name = "nombre_reunion")
    private String lugarReunion;

    private String cortesia;

    @Column(name = "nombre_estudiante")
    private String nombreEstudiante;
    @Column(name = "cedula_estudiante")
    private String cedulaEstudiante;
    @Column(name = "cedula_tutoracademico")
    private String cedulaTutoracademico;
    @Column(name = "nombre_tutoracademico")
    private String nombreTutoracademico;
    private String ciclo;

    @Column(name = "horas_cumplidas")
    private int horasCumplidas;

    @OneToMany(targetEntity = ActividadesCumplirAnexo7.class, mappedBy = "anexo7")
    private List<ActividadesCumplirAnexo7> actividadesCumplirAnexo7s;

    @Column(name = "fecha_inicio")
    private String Fechainicio;

    @Column(name = "fecha_fin")
    @Temporal(TemporalType.DATE)
    private Date Fechafin;

    @Column(name = "horas_inicio")
    private String horasInicio;

    @Column(name = "horas_fin")
    private String horasFin;
    @Column(name = "siglascarrera")
    private String siglascarrera;

    @OneToMany(targetEntity = CronogramaActividadesAnexo7.class, mappedBy = "anexo7")
    private List<CronogramaActividadesAnexo7> cronogramaActividadesAnexo7s;

    @Column(name = "horas_totales")
    private int horasTotales;

    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "proyecto_id", referencedColumnName = "id")
    private ProyectoPPP proyectoPPP;

    private String codigoAnexo;

    @Column(length = 10485760)
    private String documento;

    private int num_proceso;

}
