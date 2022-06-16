package com.tecazuay.complexivog2c2.service.Anexos;

import com.tecazuay.complexivog2c2.dto.anexos.*;
import com.tecazuay.complexivog2c2.exception.BadRequestException;
import com.tecazuay.complexivog2c2.model.Primary.Anexos.Anexo31;
import com.tecazuay.complexivog2c2.model.Primary.solicitudproyecto.ProyectoPPP;
import com.tecazuay.complexivog2c2.repository.Primary.Anexos.Anexo31Repository;
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
public class Anexo31Service {


    @Autowired
    private Anexo31Repository anexo31Repository;


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


    public boolean save(Anexo31Request request) {
        Optional<ProyectoPPP> optional = proyectoRepository.findById(request.getIdProyectoPPP());
        if (optional.isPresent()) {
            if (!optional.get().isEstado())
                throw new BadRequestException("El proceso a finalizado");
            if (!anexo31Repository.existsByProyectoPPP(optional.get())) {
                Anexo31 anexo = new Anexo31();
                anexo.setFechaRespuesta(request.getFechaRespuesta());
                anexo.setNombreRepresentanteEmp(request.getNombreRepresentanteEmp());
                anexo.setCargoEmpresa(request.getCargoEmpresa());
                anexo.setNombreEmpresa(request.getNombreEmpresa());
                anexo.setFechaSolicitudEmp(request.getFechaSolicitudEmp());
                anexo.setNombreResponsable(request.getNombreResponsable());
                anexo.setCarrera(request.getCarrera());
                anexo.setDocumento(request.getDocumento());
                anexo.setProyectoPPP(optional.get());
                anexo.setNum_proceso(request.getNum_proceso());

                try {
                    anexo31Repository.save(anexo);
                    return true;
                } catch (Exception ex) {
                    throw new BadRequestException("No se guardó el anexo 1" + ex);
                }

            } else {
                throw new BadRequestException("Ya existe el anexo con ese id de solicitud");
            }
        }
        throw new BadRequestException("No existe el proyecto con id: " + request.getIdProyectoPPP());
    }


    @Transactional
    public List<Anexo31Response> listAll() {
        return anexo31Repository.findAll().stream().map(a -> {
            Anexo31Response response = new Anexo31Response();
            response.setId(a.getId());
            response.setFechaRespuesta(a.getFechaRespuesta());
            response.setNombreRepresentanteEmp(a.getNombreRepresentanteEmp());
            response.setCargoEmpresa(a.getCargoEmpresa());
            response.setNombreEmpresa(a.getNombreEmpresa());
            response.setFechaSolicitudEmp(a.getFechaSolicitudEmp());
            response.setNombreResponsable(a.getNombreResponsable());
            response.setCarrera(a.getCarrera());
            response.setDocumento(a.getDocumento());
            response.setNum_proceso(a.getNum_proceso());

            response.setIdProyectoPPP(a.getProyectoPPP().getId());
            return response;
        }).collect(Collectors.toList());
    }
}


