package com.tecazuay.complexivog2c2.dto.convenios;

import com.tecazuay.complexivog2c2.dto.empresa.EmpresaRequest;
import com.tecazuay.complexivog2c2.dto.empresa.EmpresaResponse;
import com.tecazuay.complexivog2c2.model.Primary.empresa.Empresa;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
public class RegistroConveniosResponse implements Serializable {
    private Long id;

    private String codigoInforme;

    private String anioInforme;

    private Date fechaConvenio;

    private String nombreEmpresa;

    ///Naturaleza gestion Entidad Receptora Publica/Privada/Mixta
    private String naturalezaEntidad;

    private String nombreRepreEmpresa;

    private String nombreCordinadorVinculacion;

    private String codigoRucEmpresa;

//Actividad económica que consta en el RUC

    private int plazoVigenciaConvenio;

    private int cantidadEstudiantesInicial;

    private int cantidadEstudiantesTotal;

    private String nombreTutorAcademico;

    private String telefonoTutorAcademico;

    private String nombreTutorEmpresa;

    private String cargoTutorEmpresa;

    private String telefonoTutorEmpresa;

    private String emailEmpresa;

    private String telefonoContactoEmpresa;

    private String provinciaMatrizEmpresa;

    private String cantonMatrizEmpresa;

    private String callePrincipalMatrizEmpresa;

    private String calleSecundariaMatrizEmpresa;

    private String numIdetificacionMatrizEdificio;

    private String provinciaSucursalEmpresa;

    private String cantonSucursalEmpresa;

    private String direccionSucursalEmpresa;

    private String nombreCarrera;

    private String cargoAdminConvenioIsta;

    private String cargoRepreEmpresa;

    private String justificacionEmpresa;

    private String numAcuerdo;

    private String nombreRectorIsta;

//No. De acción de personal del Rector/a del IST

    private String actividadesEstudiante;

    private int cantidadTutoresEmpresa;

    private String conclusionesConvenio;

    private String recomendacionesConvenio;

    private List<Empresa> registroConvenioEmpresa;

}
