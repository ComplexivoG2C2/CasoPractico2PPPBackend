package com.tecazuay.complexivog2c2.model.Primary.solicitudproyecto;


import com.tecazuay.complexivog2c2.model.Primary.Anexos.*;
import com.tecazuay.complexivog2c2.model.Primary.Anexos.anexo121.Anexo121certificado;
import com.tecazuay.complexivog2c2.model.Primary.Anexos.anexo1214y15.Anexo12;
import com.tecazuay.complexivog2c2.model.Primary.Anexos.anexo1214y15.Anexo14;
import com.tecazuay.complexivog2c2.model.Primary.Anexos.anexo1214y15.Anexo15;
import com.tecazuay.complexivog2c2.model.Primary.desigaciones.TutorAcademicoDelegados;
import com.tecazuay.complexivog2c2.model.Primary.desigaciones.ResponsablePPP;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "solicitudproyecto")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProyectoPPP implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String codigo;
    private String nombre;
    private int participantes;

    private String cargosolicitante;
    private String nombresolicitante;
    private String nombreempresa;
    private Long empresa;


    private String nombreTutoremp;
    private String cedulaTutoremp;
    private String tituloTutoremp;


    private String carrera;
    private String codigocarrera;

    private boolean estado;

    @Temporal(TemporalType.DATE)
    private Date fechaat;

    @Column(length = 10485760)
    private String documento;


    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "id_responsable", referencedColumnName = "id")
    private ResponsablePPP responsablePPP;

    @OneToMany(targetEntity = Anexo1.class, mappedBy = "proyectoPPP")
    private List<Anexo1> anexo1;

    @OneToMany(targetEntity = Anexo2.class, mappedBy = "proyectoPPP")
    private List<Anexo2> anexo2;

    @OneToMany(targetEntity = Anexo3.class, mappedBy = "proyectoPPP")
    private List<Anexo3> anexo3;

    @OneToMany(targetEntity = Anexo31.class, mappedBy = "proyectoPPP")
    private List<Anexo31> anexo31;

    @OneToMany(targetEntity = Anexo4.class, mappedBy = "proyectoPPP")
    private List<Anexo4> anexo4;

    @OneToMany(targetEntity = Anexo5.class, mappedBy = "proyectoPPP")
    private List<Anexo5> anexo5;

    @OneToMany(targetEntity = Anexo6.class, mappedBy = "proyectoPPP")
    private List<Anexo6> anexo6;

    @OneToMany(targetEntity = Anexo7.class, mappedBy = "proyectoPPP")
    private List<Anexo7> anexo7;

    @OneToMany(targetEntity = Anexo8.class, mappedBy = "proyectoPPP")
    private List<Anexo8> anexo8;

    @OneToMany(targetEntity = Anexo81.class, mappedBy = "proyectoPPP")
    private List<Anexo81> anexo81;

    @OneToMany(targetEntity = Anexo9.class, mappedBy = "proyectoPPP")
    private List<Anexo9> anexo9;

    @OneToMany(targetEntity = Anexo10.class, mappedBy = "proyectoPPP")
    private List<Anexo10> anexo10;

    @OneToMany(targetEntity = Anexo11.class, mappedBy = "proyectoPPP")
    private List<Anexo11> anexo11;

    @OneToMany(targetEntity = Anexo12.class, mappedBy = "proyectoPPP")
    private List<Anexo12> anexo12;

    @OneToMany(targetEntity = Anexo13.class, mappedBy = "proyectoPPP")
    private List<Anexo13> anexo13;

    @OneToMany(targetEntity = Anexo121certificado.class, mappedBy = "proyectoPPP")
    private List<Anexo121certificado> anexo121certificados;

    @OneToMany(targetEntity = Anexo14.class, mappedBy = "proyectoPPP")
    private List<Anexo14> anexo14;

    @OneToMany(targetEntity = Anexo15.class, mappedBy = "proyectoPPP")
    private List<Anexo15> anexo15;

    @OneToMany(targetEntity = TutorEmp.class, mappedBy = "proyectoPPP")
    private List<TutorEmp> tutoremp;


    @OneToMany(targetEntity = ActividadesProyecto.class, mappedBy = "proyectoPPP", cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    private List<ActividadesProyecto> actividadesProyecto;

    @OneToMany(targetEntity = RequisitosProyecto.class, mappedBy = "proyectoPPP")
    private List<RequisitosProyecto> requisitosProyecto;

    @OneToMany(targetEntity = ActividadesempresaProyecto.class, mappedBy = "proyectoPPP", cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    private List<ActividadesempresaProyecto> actividadesempresaProyecto;

    @OneToMany(mappedBy = "proyectoPPP")
    private List<TutorAcademicoDelegados> tutorAcademicoDelegados = new ArrayList<>();



    @Column(name = "plazo_ejecucion")
    private String plazoEjecucion;

    @Column(name = "fecha_inicio")
    @Temporal(TemporalType.DATE)
    private Date fechaInicio;

    @Column(name = "fecha_fin")
    @Temporal(TemporalType.DATE)
    private Date fechaFin;

    private int horas;

    @PostLoad
    private void verifyEndDate() {
        if (this.fechaFin.before(new Date())) {
            this.estado = false;
            this.tutorAcademicoDelegados.forEach(d -> d.setEstado(false));
        }
    }
}
