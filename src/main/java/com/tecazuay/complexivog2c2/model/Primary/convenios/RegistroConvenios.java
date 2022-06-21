package com.tecazuay.complexivog2c2.model.Primary.convenios;

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

    @Column(name = "nombre_representate_empresa")
    private String nombreRepreEmpresa;

    @Column(name = "nombre_cordinador_vinculacion")
    private String nombreCordinadorVinculacion;

    @Column(name = "codigo_ruc_empresa")
    private String codigoRucEmpresa;

//Actividad económica que consta en el RUC

    @Column(name = "plazo_vigencia_Convenio")
    private int plazoVigenciaConvenio;

    @Column(name = "cantidad_estudiante_inicial")
    private int cantidadEstudiantesInicial;

    @Column(name = "cantida_estudiante_total")
    private int cantidadEstudiantesTotal;

    @Column(name = "nombre_tutor_academico")
    private String nombreTutorAcademico;

    @Column(name = "telefono_tutor_academico")
    private String telefonoTutorAcademico;

    @Column(name = "nombre_tutor_empresa")
    private String nombreTutorEmpresa;

    @Column(name = "cargo_tutor_empresa")
    private String cargoTutorEmpresa;

    @Column(name = "telefono_tutor_empresa")
    private String telefonoTutorEmpresa;

    @Column(name = "email_empresa")
    private String emailEmpresa;

    @Column(name = "telefono_contacto_empresa")
    private String telefonoContactoEmpresa;

    @Column(name = "provincia_matriz_empresa")
    private String provinciaMatrizEmpresa;

    @Column(name = "canton_matriz_empresa")
    private String cantonMatrizEmpresa;

    @Column(name = "calle_principal_matriz_empresa")
    private String callePrincipalMatrizEmpresa;

    @Column(name = "calle_secundaria_matriz_empresa")
    private String calleSecundariaMatrizEmpresa;

    @Column(name = "numero_identificacion_matriz_edificio")
    private String numIdetificacionMatrizEdificio;

    @Column(name = "provincia_sucursal_empresa")
    private String provinciaSucursalEmpresa;

    @Column(name = "canton_sucursal_empresa")
    private String cantonSucursalEmpresa;

    @Column(name = "direccion_sucursal_empresa")
    private String direccionSucursalEmpresa;

    @Column(name = "nombre_carrera")
    private String nombreCarrera;

    @Column(name = "cargo_administrador_convenio_ista")
    private String cargoAdminConvenioIsta;

    @Column(name = "cargo_representante_empresa")
    private String cargoRepreEmpresa;

    @Column(name = "justificacion_empresa")
    private String justificacionEmpresa;

    @Column(name = "numero_acuerdo")
    private String numAcuerdo;

    @Column(name = "nombre_rector_ista")
    private String nombreRectorIsta;

//No. De acción de personal del Rector/a del IST

    @Column(name = "actividades_estudiante")
    private String actividadesEstudiante;

    @Column(name = "cantidad_tutores_empresa")
    private int cantidadTutoresEmpresa;

    @Column(name = "conclusiones_convenio")
    private String conclusionesConvenio;
    @Column(name = "recomendaciones_convenio")
    private String recomendacionesConvenio;

    @OneToMany(targetEntity = Empresa.class, mappedBy = "registroConvenios")
    private List<Empresa> registroConvenioEmpresa;


}
