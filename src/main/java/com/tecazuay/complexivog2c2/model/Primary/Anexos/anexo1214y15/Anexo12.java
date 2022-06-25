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
@Table(name="anexo12")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Anexo12 implements Serializable {

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

    @Column(name = "resultado_anexo12")
    private String resultadoAnexo12;

    @Column(name = "nombre_tutoremp")
    private String nombretutoremp;
    @Column(name = "cedulatutoremp")
    private String cedulatutoremp;
    @Column(name = "empresa")
    private String empresa;
    @Column(name = "siglascarrera")
    private String siglascarrera;
    @Column(name = "tutoremppuntaje")
    private int tutorempPuntaje;

    @Column(length = 10485760)
    private String documento;

    //
    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "proyecto_id", referencedColumnName = "id")
    private ProyectoPPP proyectoPPP;

    @OneToMany(targetEntity = ItemsAnexo12Tutoremp.class, mappedBy = "anexo12")
    private List<ItemsAnexo12Tutoremp> itemsTutor;
}