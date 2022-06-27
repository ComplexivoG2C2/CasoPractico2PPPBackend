package com.tecazuay.complexivog2c2.service.Anexos;

import com.tecazuay.complexivog2c2.dto.anexos.*;
import com.tecazuay.complexivog2c2.exception.BadRequestException;
import com.tecazuay.complexivog2c2.exception.ResponseNotFoundException;
import com.tecazuay.complexivog2c2.model.Primary.Anexos.*;
import com.tecazuay.complexivog2c2.model.Primary.empresa.Empresa;
import com.tecazuay.complexivog2c2.model.Primary.solicitudproyecto.ProyectoPPP;
import com.tecazuay.complexivog2c2.repository.Primary.Anexos.ActividadEconomicaRucRepository;
import com.tecazuay.complexivog2c2.repository.Primary.Anexos.ActividadesRealizarRepository;
import com.tecazuay.complexivog2c2.repository.Primary.Anexos.RegistroConveniosRepository;
import com.tecazuay.complexivog2c2.repository.Primary.designaciones.CoordinadorVinculacionRepository;
import com.tecazuay.complexivog2c2.repository.Primary.empresa.EmpresaRepository;
import com.tecazuay.complexivog2c2.service.email.EmailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
public class RegistroConveniosService {
    @Autowired
    private RegistroConveniosRepository registroConveniosRepository;

    @Autowired
    private EmpresaRepository empresaRepository;

    @Autowired
    private ActividadEconomicaRucRepository actividadEconomicaRucRepository;

    @Autowired
    private ActividadesRealizarRepository actividadesRealizarRepository;

    @Autowired
    private CoordinadorVinculacionRepository coordinadorVinculacionRepository;

    @Autowired
    private EmailService emailService;

    public boolean save(RegistroConveniosRequest registroConveniosRequest){
        Optional<Empresa> optional = empresaRepository.findById(registroConveniosRequest.getEmpresa_id());
        if (optional.isPresent()) {
        RegistroConvenios reCon=new RegistroConvenios();
        reCon.setCodigoInforme(registroConveniosRequest.getCodigoInforme());
        reCon.setAnioInforme(registroConveniosRequest.getAnioInforme());
        reCon.setFechaConvenio(registroConveniosRequest.getFechaConvenio());
        reCon.setNombreEmpresa(registroConveniosRequest.getNombreEmpresa());
        reCon.setNaturalezaEntidad(registroConveniosRequest.getNaturalezaEntidad());
        reCon.setNombreRepreEmpresa(registroConveniosRequest.getNombreRepreEmpresa());
        reCon.setRucEmpresa(registroConveniosRequest.getRucEmpresa());

        List<ActividadEconomicaRuc> list = new ArrayList<>();
        registroConveniosRequest.getActividadEconomicaRuc().stream().forEach(a -> {
            ActividadEconomicaRuc actividadEconomicaRuc = new ActividadEconomicaRuc();
            actividadEconomicaRuc.setCodActividad(a.getCodActividad());
            actividadEconomicaRuc.setActividades(a.getActividades());
            list.add(actividadEconomicaRuc);
        });

        reCon.setAnioConvenio(registroConveniosRequest.getAnioConvenio());
        reCon.setNroEstudiantes(registroConveniosRequest.getNroEstudiantes());
        reCon.setTotalEstudiantes(registroConveniosRequest.getTotalEstudiantes());
        reCon.setNombreTutorAcademico(registroConveniosRequest.getNombreTutorAcademico());
        reCon.setTlfTutorA(registroConveniosRequest.getTlfTutorA());
        reCon.setNombreTutorEmpresa(registroConveniosRequest.getNombreTutorEmpresa());
        reCon.setCargoTutorEmpresa(registroConveniosRequest.getCargoTutorEmpresa());
        reCon.setTlfTutorEmpresa(registroConveniosRequest.getTlfTutorEmpresa());
        reCon.setEmailEmpresa(registroConveniosRequest.getEmailEmpresa());
        reCon.setTlfContactoEmpresa(registroConveniosRequest.getTlfContactoEmpresa());
        reCon.setProvinciaMatrizEmpresa(registroConveniosRequest.getProvinciaMatrizEmpresa());
        reCon.setCantonMatrizEmpresa(registroConveniosRequest.getCantonMatrizEmpresa());
        reCon.setCallePrincipalEmpresa(registroConveniosRequest.getCallePrincipalEmpresa());
        reCon.setNumIdetificacionEdificio(registroConveniosRequest.getNumIdetificacionEdificio());
        reCon.setCalleSecundariaMatrizEmpresa(registroConveniosRequest.getCalleSecundariaMatrizEmpresa());
        reCon.setProvinciaSucursalEmpresa(registroConveniosRequest.getProvinciaSucursalEmpresa());
        reCon.setCantonSucursalEmpresa(registroConveniosRequest.getCantonSucursalEmpresa());
        reCon.setDireccionSucursalEmpresa(registroConveniosRequest.getDireccionSucursalEmpresa());
        reCon.setCarrera(registroConveniosRequest.getCarrera());
        reCon.setCargoAdminConvenioIsta(registroConveniosRequest.getCargoAdminConvenioIsta());
        reCon.setCargoRepreEmpresa(registroConveniosRequest.getCargoRepreEmpresa());
        reCon.setJustificacionEmpresa(registroConveniosRequest.getJustificacionEmpresa());
        reCon.setNombreRectorIsta(registroConveniosRequest.getNombreRectorIsta());
        reCon.setFechaNombramiento(registroConveniosRequest.getFechaNombramiento());
        reCon.setEmpresa(optional.get());

        List<ActividadesRealizar> list2 = new ArrayList<>();
        registroConveniosRequest.getActividadesRealizars().stream().forEach(a -> {
            ActividadesRealizar actividadesRealizar = new ActividadesRealizar();
            actividadesRealizar.setActividadesRealizar(a.getActividadesRealizar());
            list2.add(actividadesRealizar);
        });

        reCon.setNroTutoresEmpresa(registroConveniosRequest.getNroTutoresEmpresa());
        reCon.setConclusionesConvenio(registroConveniosRequest.getConclusionesConvenio());
        reCon.setRecomendacionesConvenio(registroConveniosRequest.getRecomendacionesConvenio());
        reCon.setNombreAdminConvenio(registroConveniosRequest.getNombreAdminConvenio());

        reCon.setDocumento(registroConveniosRequest.getDocumento());
        reCon.setNum_proceso(registroConveniosRequest.getNum_proceso());

        try {
            registroConveniosRepository.save(reCon);
            return true;
        } catch (Exception ex) {
            throw new BadRequestException("No se guardó el Registro del Convenio" + ex);
        }

        }
        throw new BadRequestException("No existe la empresa con el id:" + registroConveniosRequest.getEmpresa_id());
    }

    @Transactional
    public List<RegistroConveniosResponse> listAll() {
        return registroConveniosRepository.findAll().stream().map(a -> {
            RegistroConveniosResponse response = new RegistroConveniosResponse();
            response.setId(a.getId());
            response.setCodigoInforme(a.getCodigoInforme());
            response.setAnioInforme(a.getAnioInforme());
            response.setFechaConvenio(a.getFechaConvenio());
            response.setNombreEmpresa(a.getNombreEmpresa());
            response.setNaturalezaEntidad(a.getNaturalezaEntidad());
            response.setNombreRepreEmpresa(a.getNombreRepreEmpresa());
            response.setRucEmpresa(a.getRucEmpresa());
            response.setEmpresa_id(a.getEmpresa().getId());

            List<ActividadEconomicaRucRequest> list = a.getActividadEconomicaRuc().stream().map(ac -> {
                ActividadEconomicaRucRequest request = new ActividadEconomicaRucRequest();
                request.setId(ac.getId());
                request.setCodActividad(ac.getCodActividad());
                request.setActividades(ac.getActividades());
                return request;
            }).collect(Collectors.toList());

            response.setAnioConvenio(a.getAnioConvenio());
            response.setNroEstudiantes(a.getNroEstudiantes());
            response.setTotalEstudiantes(a.getTotalEstudiantes());
            response.setNombreTutorAcademico(a.getNombreTutorAcademico());
            response.setTlfTutorA(a.getTlfTutorA());
            response.setNombreTutorEmpresa(a.getNombreTutorEmpresa());
            response.setCargoTutorEmpresa(a.getCargoTutorEmpresa());
            response.setTlfTutorEmpresa(a.getTlfTutorEmpresa());
            response.setEmailEmpresa(a.getEmailEmpresa());
            response.setTlfContactoEmpresa(a.getTlfContactoEmpresa());
            response.setProvinciaMatrizEmpresa(a.getProvinciaMatrizEmpresa());
            response.setCantonMatrizEmpresa(a.getCantonMatrizEmpresa());
            response.setCallePrincipalEmpresa(a.getCallePrincipalEmpresa());
            response.setNumIdetificacionEdificio(a.getNumIdetificacionEdificio());
            response.setCalleSecundariaMatrizEmpresa(a.getCalleSecundariaMatrizEmpresa());
            response.setProvinciaSucursalEmpresa(a.getProvinciaSucursalEmpresa());
            response.setCantonSucursalEmpresa(a.getCantonSucursalEmpresa());
            response.setDireccionSucursalEmpresa(a.getDireccionSucursalEmpresa());
            response.setCarrera(a.getCarrera());
            response.setCargoAdminConvenioIsta(a.getCargoAdminConvenioIsta());
            response.setCargoRepreEmpresa(a.getCargoRepreEmpresa());
            response.setJustificacionEmpresa(a.getJustificacionEmpresa());
            response.setNombreRectorIsta(a.getNombreRectorIsta());
            response.setFechaNombramiento(a.getFechaNombramiento());

            List<ActividadesRealizarRequest> list2 = a.getActividadesRealizars().stream().map(ac -> {
                ActividadesRealizarRequest request = new ActividadesRealizarRequest();
                request.setId(ac.getId());
                request.setActividadesRealizar(ac.getActividadesRealizar());
                return request;
            }).collect(Collectors.toList());

            response.setNroTutoresEmpresa(a.getNroTutoresEmpresa());
            response.setConclusionesConvenio(a.getConclusionesConvenio());
            response.setRecomendacionesConvenio(a.getRecomendacionesConvenio());
            response.setNombreAdminConvenio(a.getNombreAdminConvenio());

            response.setDocumento(a.getDocumento());
            response.setNum_proceso(a.getNum_proceso());

            response.setActividadEconomicaRuc(list);
            response.setActividadesRealizars(list2);
            return response;
        }).collect(Collectors.toList());
    }

    @Transactional
    public void deleteById(Long id){
        Optional<RegistroConvenios> optional = registroConveniosRepository.findById(id);
        if(optional.isEmpty()){
            throw new BadRequestException("El registro de convenio con el id " + id + ", no existe");
        }
        registroConveniosRepository.deleteById(id);
    }

    @Transactional
    public RegistroConveniosResponse ByidRegistro(Long id) {
        Optional<RegistroConvenios> a = registroConveniosRepository.findById(id);
        if (a.isPresent()) {
            RegistroConveniosResponse response = new RegistroConveniosResponse();
            response.setId(a.get().getId());
            response.setCodigoInforme(a.get().getCodigoInforme());
            response.setAnioInforme(a.get().getAnioInforme());
            response.setFechaConvenio(a.get().getFechaConvenio());
            response.setNombreEmpresa(a.get().getNombreEmpresa());
            response.setNaturalezaEntidad(a.get().getNaturalezaEntidad());
            response.setNombreRepreEmpresa(a.get().getNombreRepreEmpresa());
            response.setRucEmpresa(a.get().getRucEmpresa());
            response.setEmpresa_id(a.get().getEmpresa().getId());

            List<ActividadEconomicaRucRequest> list = a.get().getActividadEconomicaRuc().stream().map(ac -> {
                ActividadEconomicaRucRequest request = new ActividadEconomicaRucRequest();
                request.setId(ac.getId());
                request.setCodActividad(ac.getCodActividad());
                request.setActividades(ac.getActividades());
                return request;
            }).collect(Collectors.toList());

            response.setAnioConvenio(a.get().getAnioConvenio());
            response.setNroEstudiantes(a.get().getNroEstudiantes());
            response.setTotalEstudiantes(a.get().getTotalEstudiantes());
            response.setNombreTutorAcademico(a.get().getNombreTutorAcademico());
            response.setTlfTutorA(a.get().getTlfTutorA());
            response.setNombreTutorEmpresa(a.get().getNombreTutorEmpresa());
            response.setCargoTutorEmpresa(a.get().getCargoTutorEmpresa());
            response.setTlfTutorEmpresa(a.get().getTlfTutorEmpresa());
            response.setEmailEmpresa(a.get().getEmailEmpresa());
            response.setTlfContactoEmpresa(a.get().getTlfContactoEmpresa());
            response.setProvinciaMatrizEmpresa(a.get().getProvinciaMatrizEmpresa());
            response.setCantonMatrizEmpresa(a.get().getCantonMatrizEmpresa());
            response.setCallePrincipalEmpresa(a.get().getCallePrincipalEmpresa());
            response.setNumIdetificacionEdificio(a.get().getNumIdetificacionEdificio());
            response.setCalleSecundariaMatrizEmpresa(a.get().getCalleSecundariaMatrizEmpresa());
            response.setProvinciaSucursalEmpresa(a.get().getProvinciaSucursalEmpresa());
            response.setCantonSucursalEmpresa(a.get().getCantonSucursalEmpresa());
            response.setDireccionSucursalEmpresa(a.get().getDireccionSucursalEmpresa());
            response.setCarrera(a.get().getCarrera());
            response.setCargoAdminConvenioIsta(a.get().getCargoAdminConvenioIsta());
            response.setCargoRepreEmpresa(a.get().getCargoRepreEmpresa());
            response.setJustificacionEmpresa(a.get().getJustificacionEmpresa());
            response.setNombreRectorIsta(a.get().getNombreRectorIsta());
            response.setFechaNombramiento(a.get().getFechaNombramiento());

            List<ActividadesRealizarRequest> list2 = a.get().getActividadesRealizars().stream().map(ac -> {
                ActividadesRealizarRequest request = new ActividadesRealizarRequest();
                request.setId(ac.getId());
                request.setActividadesRealizar(ac.getActividadesRealizar());
                return request;
            }).collect(Collectors.toList());

            response.setNroTutoresEmpresa(a.get().getNroTutoresEmpresa());
            response.setConclusionesConvenio(a.get().getConclusionesConvenio());
            response.setRecomendacionesConvenio(a.get().getRecomendacionesConvenio());
            response.setNombreAdminConvenio(a.get().getNombreAdminConvenio());

            response.setDocumento(a.get().getDocumento());
            response.setNum_proceso(a.get().getNum_proceso());

            response.setActividadEconomicaRuc(list);
            response.setActividadesRealizars(list2);
            return response;
        }
        throw new BadRequestException("No existe la empresa con el id:");
        }




    @Transactional
    public void updateregistro(RegistroConveniosRequest registroConveniosRequest) {
        Optional<RegistroConvenios> optionalAnexo = registroConveniosRepository.findById(registroConveniosRequest.getId());
        if (optionalAnexo.isPresent()) {

            Optional<Empresa> optional = empresaRepository.findById(registroConveniosRequest.getEmpresa_id());
            if (optional.isPresent()) {

            RegistroConvenios reCon  = optionalAnexo.get();
            reCon.setCodigoInforme(registroConveniosRequest.getCodigoInforme());
            reCon.setAnioInforme(registroConveniosRequest.getAnioInforme());
            reCon.setFechaConvenio(registroConveniosRequest.getFechaConvenio());
            reCon.setNombreEmpresa(registroConveniosRequest.getNombreEmpresa());
            reCon.setNaturalezaEntidad(registroConveniosRequest.getNaturalezaEntidad());
            reCon.setNombreRepreEmpresa(registroConveniosRequest.getNombreRepreEmpresa());
            reCon.setRucEmpresa(registroConveniosRequest.getRucEmpresa());

            List<ActividadEconomicaRuc> list = new ArrayList<>();
            registroConveniosRequest.getActividadEconomicaRuc().stream().forEach(a -> {
                ActividadEconomicaRuc actividadEconomicaRuc = new ActividadEconomicaRuc();
                actividadEconomicaRuc.setCodActividad(a.getCodActividad());
                actividadEconomicaRuc.setActividades(a.getActividades());
                list.add(actividadEconomicaRuc);
            });

            reCon.setAnioConvenio(registroConveniosRequest.getAnioConvenio());
            reCon.setNroEstudiantes(registroConveniosRequest.getNroEstudiantes());
            reCon.setTotalEstudiantes(registroConveniosRequest.getTotalEstudiantes());
            reCon.setNombreTutorAcademico(registroConveniosRequest.getNombreTutorAcademico());
            reCon.setTlfTutorA(registroConveniosRequest.getTlfTutorA());
            reCon.setNombreTutorEmpresa(registroConveniosRequest.getNombreTutorEmpresa());
            reCon.setCargoTutorEmpresa(registroConveniosRequest.getCargoTutorEmpresa());
            reCon.setTlfTutorEmpresa(registroConveniosRequest.getTlfTutorEmpresa());
            reCon.setEmailEmpresa(registroConveniosRequest.getEmailEmpresa());
            reCon.setTlfContactoEmpresa(registroConveniosRequest.getTlfContactoEmpresa());
            reCon.setProvinciaMatrizEmpresa(registroConveniosRequest.getProvinciaMatrizEmpresa());
            reCon.setCantonMatrizEmpresa(registroConveniosRequest.getCantonMatrizEmpresa());
            reCon.setCallePrincipalEmpresa(registroConveniosRequest.getCallePrincipalEmpresa());
            reCon.setNumIdetificacionEdificio(registroConveniosRequest.getNumIdetificacionEdificio());
            reCon.setCalleSecundariaMatrizEmpresa(registroConveniosRequest.getCalleSecundariaMatrizEmpresa());
            reCon.setProvinciaSucursalEmpresa(registroConveniosRequest.getProvinciaSucursalEmpresa());
            reCon.setCantonSucursalEmpresa(registroConveniosRequest.getCantonSucursalEmpresa());
            reCon.setDireccionSucursalEmpresa(registroConveniosRequest.getDireccionSucursalEmpresa());
            reCon.setCarrera(registroConveniosRequest.getCarrera());
            reCon.setCargoAdminConvenioIsta(registroConveniosRequest.getCargoAdminConvenioIsta());
            reCon.setCargoRepreEmpresa(registroConveniosRequest.getCargoRepreEmpresa());
            reCon.setJustificacionEmpresa(registroConveniosRequest.getJustificacionEmpresa());
            reCon.setNombreRectorIsta(registroConveniosRequest.getNombreRectorIsta());
            reCon.setFechaNombramiento(registroConveniosRequest.getFechaNombramiento());
            reCon.setEmpresa(optional.get());

            List<ActividadesRealizar> list2 = new ArrayList<>();
            registroConveniosRequest.getActividadesRealizars().stream().forEach(a -> {
                ActividadesRealizar actividadesRealizar = new ActividadesRealizar();
                actividadesRealizar.setActividadesRealizar(a.getActividadesRealizar());
                list2.add(actividadesRealizar);
            });

            reCon.setNroTutoresEmpresa(registroConveniosRequest.getNroTutoresEmpresa());
            reCon.setConclusionesConvenio(registroConveniosRequest.getConclusionesConvenio());
            reCon.setRecomendacionesConvenio(registroConveniosRequest.getRecomendacionesConvenio());
            reCon.setNombreAdminConvenio(registroConveniosRequest.getNombreAdminConvenio());

            reCon.setDocumento(registroConveniosRequest.getDocumento());
            reCon.setNum_proceso(registroConveniosRequest.getNum_proceso());

            try{
                registroConveniosRepository.save(reCon);
            } catch (Exception ex) {
                throw new BadRequestException("Error al actualizar Anexo 7");
            }
        }
    } else
            throw new ResponseNotFoundException("registro no actualizado", "id", registroConveniosRequest.getId() + "");
}



}
