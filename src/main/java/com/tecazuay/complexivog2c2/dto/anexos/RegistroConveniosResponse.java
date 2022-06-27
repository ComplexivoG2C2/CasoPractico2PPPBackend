package com.tecazuay.complexivog2c2.dto.anexos;

import com.tecazuay.complexivog2c2.dto.empresa.EmpresaRequest;
import com.tecazuay.complexivog2c2.dto.empresa.EmpresaResponse;
import lombok.Data;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
public class RegistroConveniosResponse implements Serializable {
    private Long id;

    private String codigoInforme;

    // esta variable se repite 3 veces en el documento
    private String anioInforme;

    private Date fechaConvenio;

    private String nombreEmpresa;

    ///Naturaleza gestion Entidad Receptora Publica/Privada/Mixta
    private String naturalezaEntidad;

    // esta variable se repite 2 veces en el documento
    private String nombreRepreEmpresa;

    private String rucEmpresa;

    //Actividad económica que consta en el RUC
    private List<ActividadEconomicaRucRequest> actividadEconomicaRuc;

    private int anioConvenio;

    private int nroEstudiantes;

    private int totalEstudiantes;

    private String nombreTutorAcademico;

    private String tlfTutorA;

    private String nombreTutorEmpresa;

    private String cargoTutorEmpresa;

    private String tlfTutorEmpresa;

    private String emailEmpresa;

    private String tlfContactoEmpresa;

    private String provinciaMatrizEmpresa;

    private String cantonMatrizEmpresa;

    private String callePrincipalEmpresa;

    private String numIdetificacionEdificio;

    private String calleSecundariaMatrizEmpresa;

    private String referenciaEmpresa;

    private String provinciaSucursalEmpresa;

    private String cantonSucursalEmpresa;

    private String direccionSucursalEmpresa;

    // esta variable se repite 6 veces en el documento
    private String carrera;

    private String cargoAdminConvenioIsta;

    private String cargoRepreEmpresa;

    private String justificacionEmpresa;

    private String nombreRectorIsta;

    @Temporal(TemporalType.DATE)
    private Date fechaNombramiento;

    //Actividades que realizará el o los estudiantes
    private List<ActividadesRealizarRequest> actividadesRealizars;

    private int nroTutoresEmpresa;


    private String conclusionesConvenio;

    private String recomendacionesConvenio;

    private String nombreAdminConvenio;

    private Long empresa_id;

    private String documento;

    private int num_proceso;
}
