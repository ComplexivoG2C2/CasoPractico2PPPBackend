package com.tecazuay.complexivog2c2.model.Primary.Anexos;

import com.tecazuay.complexivog2c2.model.Primary.solicitudproyecto.ProyectoPPP;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "anexo31")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Anexo31 implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "fecha_respuesta")
    @Temporal(TemporalType.DATE)
    private Date fechaRespuesta;

    @Column(name = "titulo_representante_emp")
    private String tituloRepresentanteEmp;

    @Column(name = "nombre_representante_emp")
    private String nombreRepresentanteEmp;

    @Column(name = "cargo_empresa")
    private String cargoEmpresa;

    @Column(name = "nombre_empresa")
    private String nombreEmpresa;

    @Column(name = "fecha_solicitud_emp")
    @Temporal(TemporalType.DATE)
    private Date fechaSolicitudEmp;

    @Column(name = "nombre_responsable")
    private String nombreResponsable;

    private String carrera;

    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "proyecto_id", referencedColumnName = "id")
    private ProyectoPPP proyectoPPP;

    private String codigoAnexo;

    @Column(length = 10485760)
    private String documento;

    private int num_proceso;
}

