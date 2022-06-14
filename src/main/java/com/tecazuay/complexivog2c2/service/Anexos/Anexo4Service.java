package com.tecazuay.complexivog2c2.service.Anexos;

import com.tecazuay.complexivog2c2.dto.anexos.Anexo4Request;
import com.tecazuay.complexivog2c2.dto.anexos.Anexo4Response;
import com.tecazuay.complexivog2c2.exception.BadRequestException;
import com.tecazuay.complexivog2c2.exception.ResponseNotFoundException;
import com.tecazuay.complexivog2c2.model.Primary.Anexos.Anexo4;
import com.tecazuay.complexivog2c2.model.Primary.solicitudproyecto.ProyectoPPP;
import com.tecazuay.complexivog2c2.repository.Primary.Anexos.Anexo4Repository;
import com.tecazuay.complexivog2c2.repository.Primary.solicitudproyecto.ProyectoRepository;
import com.tecazuay.complexivog2c2.repository.Primary.solicitudproyecto.RequisitosRepository;
import com.tecazuay.complexivog2c2.repository.Primary.usuario.UsuarioRepository;
import com.tecazuay.complexivog2c2.repository.Secondary.alumnos.VAlumnosRepository;
import com.tecazuay.complexivog2c2.service.email.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class Anexo4Service {

    @Autowired
    private Anexo4Repository anexo4Repository;

    @Autowired
    private ProyectoRepository proyectoRepository;

    @Autowired
    private VAlumnosRepository vAlumnosRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private EmailService emailService;

    @Autowired
    private RequisitosRepository requisitosRepository;

    public boolean save(Anexo4Request request){
        Optional<ProyectoPPP> optional = proyectoRepository.findById(request.getIdProyectoPPP());
        if (optional.isPresent()) {
            if (!optional.get().isEstado())
                throw new BadRequestException("El proceso a finalizado");
            if (!anexo4Repository.existsByProyectoPPP(optional.get())) {
                Anexo4 anexo4=new Anexo4();
                anexo4.setFechaRespuesta(request.getFechaRespuesta());
                anexo4.setNombreRepresentanteEmp(request.getNombreRepresentanteEmp());
                anexo4.setCargoEmpresa(request.getCargoEmpresa());
                anexo4.setNombreEmpresa(request.getNombreEmpresa());
                anexo4.setFechaSolicitudEmp(request.getFechaSolicitudEmp());
                anexo4.setNombreResponsable(request.getNombreResponsable());
                anexo4.setCarrera(request.getCarrera());
                anexo4.setDocumento(request.getDocumento());
                anexo4.setProyectoPPP(optional.get());
                anexo4.setNum_proceso(request.getNum_proceso());
                try {
                    anexo4Repository.save(anexo4);
                } catch (Exception e) {
                    throw new BadRequestException("No se guardó el anexo 4" + e);
                }
            }else {
                throw new ResponseNotFoundException("Estudiante", "CEDULA:", "" + request.getId());
            }
        }
        throw new BadRequestException("No existe el proyecto con id: " + request.getIdProyectoPPP());
    }

    @Transactional
    public List<Anexo4Response> listAll() {
        return anexo4Repository.findAll().stream().map(a -> {
            Anexo4Response response = new Anexo4Response();
            response.setId(a.getId());
            response.setFechaRespuesta(a.getFechaRespuesta());
            response.setNombreRepresentanteEmp(a.getNombreRepresentanteEmp());
            response.setCargoEmpresa(a.getCargoEmpresa());
            response.setNombreEmpresa(a.getNombreEmpresa());
            response.setFechaSolicitudEmp(a.getFechaSolicitudEmp());
            response.setNombreResponsable(a.getNombreResponsable());
            response.setCarrera(a.getCarrera());
            response.setNombreResponsable(a.getNombreResponsable());
            response.setDocumento(a.getDocumento());
            response.setNum_proceso(a.getNum_proceso());
            response.setIdProyectoPPP(a.getProyectoPPP().getId());
            return response;
        }).collect(Collectors.toList());
    }

    public Anexo4Response anexoIdProyecto(Long proyectoId) {
        Optional<ProyectoPPP> optional = proyectoRepository.findById(proyectoId);
        if (optional.isPresent()) {
            Optional<Anexo4> a = anexo4Repository.findByProyectoPPP(optional.get());
            if (a.isPresent()) {
                Anexo4Response response = new Anexo4Response();
                response.setId(a.get().getId());
                response.setFechaRespuesta(a.get().getFechaRespuesta());
                response.setNombreRepresentanteEmp(a.get().getNombreRepresentanteEmp());
                response.setCargoEmpresa(a.get().getCargoEmpresa());
                response.setNombreEmpresa(a.get().getNombreEmpresa());
                response.setFechaSolicitudEmp(a.get().getFechaSolicitudEmp());
                response.setNombreResponsable(a.get().getNombreResponsable());
                response.setCarrera(a.get().getCarrera());
                response.setNombreResponsable(a.get().getNombreResponsable());
                response.setDocumento(a.get().getDocumento());
                response.setNum_proceso(a.get().getNum_proceso());
                response.setIdProyectoPPP(a.get().getProyectoPPP().getId());
                response.setNum_proceso(a.get().getNum_proceso());
                response.setIdProyectoPPP(a.get().getProyectoPPP().getId());
                return response;
            }
            throw new BadRequestException("No existe el anexo con id de la solicitud: " + proyectoId);
        }

        throw new BadRequestException("No existe la solicitud: " + proyectoId);
    }

}
