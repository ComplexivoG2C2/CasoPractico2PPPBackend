package com.tecazuay.complexivog2c2.model.Primary.Anexos;

import com.tecazuay.complexivog2c2.model.Primary.desigaciones.ResponsablePPP;
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
@Table(name = "Anexo4")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Anexo4 implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "fecha_respuesta")
    @Temporal(TemporalType.DATE)
    private Date fechaRespuesta;

    @Column(name = "nombre_representante_emp")
    private String nombreRepresentanteEmp;

    private String carrera;

    @Column(name = "cargo_empresa")
    private String cargoEmpresa;

    @Column(name = "nombre_empresa")
    private String nombreEmpresa;

    @Column(name = "fecha_solicitud_emp")
    @Temporal(TemporalType.DATE)
    private Date fechaSolicitudEmp;

    @Column(length = 10485760)
    private String documento;
//
//    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
//    @JoinColumn(name = "id_responsable", referencedColumnName = "id")
//    private ResponsablePPP responsablePPP;

    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "proyecto_id", referencedColumnName = "id")
    private ProyectoPPP proyectoPPP;

    @OneToMany(targetEntity = ListaEstudiantesAnexo4.class, mappedBy = "anexo4")
    private List<ListaEstudiantesAnexo4> estudiantesAnexo4s;

    private String codigoAnexo;

    @Column(name = "nombre_responsable")
    private String nombreResponsable;

    private int num_proceso;
}
