package com.tecazuay.complexivog2c2.model.Primary.convenios;

import com.tecazuay.complexivog2c2.model.Primary.Anexos.ActividadesAnexo2;
import com.tecazuay.complexivog2c2.model.Primary.empresa.Empresa;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "registro_convenios")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RegistroConvenios implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "codigo_informe")
    private String codigoInforme;

    // esta variable se repite 3 veces en el documento
    @Column(name = "anio_informe")
    private String anioInforme;

    @Column(name = "fecha_convenio")
    @Temporal(TemporalType.DATE)
    private Date fechaConvenio;

    @Column(name = "nombre_empresa")
    private String nombreEmpresa;

    ///Naturaleza gestion Entidad Receptora Publica/Privada/Mixta
    @Column(name = "naturaleza_entidad")
    private String naturalezaEntidad;

    // esta variable se repite 2 veces en el documento
    @Column(name = "nombre_repre_empresa")
    private String nombreRepreEmpresa;

    @Column(name = "ruc_empresa")
    private String rucEmpresa;

    //Actividad económica que consta en el RUC
    @OneToMany(targetEntity = ActividadEconomicaRuc.class, mappedBy = "registroConvenios")
    private List<ActividadEconomicaRuc> actividadEconomicaRuc;

    @Column(name = "anio_convenio")
    private int anioConvenio;

    @Column(name = "nro_estudiantes")
    private int nroEstudiantes;

    @Column(name = "total_Estudiantes")
    private int totalEstudiantes;

    @Column(name = "nombre_tutor_academico")
    private String nombreTutorAcademico;

    @Column(name = "tlf_tutor_a")
    private String tlfTutorA;

    @Column(name = "nombre_tutor_empresa")
    private String nombreTutorEmpresa;

    @Column(name = "cargo_tutor_empresa")
    private String cargoTutorEmpresa;

    @Column(name = "tlf_tutor_empresa")
    private String tlfTutorEmpresa;

    @Column(name = "email_empresa")
    private String emailEmpresa;

    @Column(name = "tlf_contacto_empresa")
    private String tlfContactoEmpresa;

    @Column(name = "provincia_matriz_empresa")
    private String provinciaMatrizEmpresa;

    @Column(name = "canton_matriz_empresa")
    private String cantonMatrizEmpresa;

    @Column(name = "calle_principal_empresa")
    private String callePrincipalEmpresa;

    @Column(name = "num_identificacion_edificio")
    private String numIdetificacionEdificio;

    @Column(name = "calle_secundaria_matriz_empresa")
    private String calleSecundariaMatrizEmpresa;

    @Column(name = "referencia_empresa")
    private String referenciaEmpresa;

    @Column(name = "provincia_sucursal_empresa")
    private String provinciaSucursalEmpresa;

    @Column(name = "canton_sucursal_empresa")
    private String cantonSucursalEmpresa;

    @Column(name = "direccion_sucursal_empresa")
    private String direccionSucursalEmpresa;

    // esta variable se repite 6 veces en el documento
    private String carrera;

    @Column(name = "cargo_administrador_convenio_ista")
    private String cargoAdminConvenioIsta;

    @Column(name = "cargo_representante_empresa")
    private String cargoRepreEmpresa;

    @Column(name = "justificacion_empresa")
    private String justificacionEmpresa;

    @Column(name = "nombre_rector_ista")
    private String nombreRectorIsta;

    @Temporal(TemporalType.DATE)
    @Column(name = "fecha_nombramiento")
    private Date fechaNombramiento;

    //Actividades que realizará el o los estudiantes
    @OneToMany(targetEntity = ActividadesRealizar.class, mappedBy = "registroConvenios")
    private List<ActividadesRealizar> actividadesRealizars;

    @Column(name = "nro_tutores_empresa")
    private int nroTutoresEmpresa;

    @Column(name = "conclusiones_convenio")
    private String conclusionesConvenio;

    @Column(name = "recomendaciones_convenio")
    private String recomendacionesConvenio;

    @Column(name = "nombre_admin_convenio")
    private String nombreAdminConvenio;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="id_registroConvenio", referencedColumnName = "id")
    private Empresa empresa;

    @Column(length = 10485760)
    private String documento;

    private int num_proceso;
}
