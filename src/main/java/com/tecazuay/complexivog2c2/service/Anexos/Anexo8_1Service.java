package com.tecazuay.complexivog2c2.service.Anexos;

import com.tecazuay.complexivog2c2.dto.anexos.*;
import com.tecazuay.complexivog2c2.exception.BadRequestException;
import com.tecazuay.complexivog2c2.model.Primary.Anexos.Anexo8_1;
import com.tecazuay.complexivog2c2.model.Primary.solicitudproyecto.ProyectoPPP;
import com.tecazuay.complexivog2c2.repository.Primary.Anexos.Anexo8_1Repository;
import com.tecazuay.complexivog2c2.repository.Primary.designaciones.ResponsablePPPRepository;
import com.tecazuay.complexivog2c2.repository.Primary.solicitudproyecto.ProyectoRepository;
import com.tecazuay.complexivog2c2.repository.Primary.solicitudproyecto.RequisitosRepository;
import com.tecazuay.complexivog2c2.repository.Primary.usuario.UsuarioRepository;
import com.tecazuay.complexivog2c2.repository.Secondary.alumnos.VAlumnosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class Anexo8_1Service {
    @Autowired
    private Anexo8_1Repository anexo8_1Repository;

    @Autowired
    private ProyectoRepository proyectoRepository;

    @Autowired
    private VAlumnosRepository vAlumnosRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private RequisitosRepository requisitosRepository;

    @Autowired
    private ResponsablePPPRepository responsablePPPRepository;

    public boolean save(Anexo8_1Request request) {
        Optional<ProyectoPPP> optional = proyectoRepository.findById(request.getIdProyectoPPP());

        if (optional.isPresent()) {
            if (!optional.get().isEstado())
                throw new BadRequestException("El proceso a finalizado");

            if (!anexo8_1Repository.existsByProyectoPPP(optional.get())) {
                Anexo8_1 anexo8_1 = new Anexo8_1();
                anexo8_1.setFechaNotificacion(request.getFechaNotificacion());
                anexo8_1.setTutorAcademico(request.getTutorAcademico());
                anexo8_1.setCortesia(request.getCortesia());
                anexo8_1.setNombreEstudiante(request.getNombreEstudiante());
                anexo8_1.setNombreEmpresa(request.getNombreEmpresa());
                anexo8_1.setNombreResponsable(request.getNombreResponsable());
                anexo8_1.setCarrera(request.getCarrera());
                anexo8_1.setProyectoPPP(optional.get());
                anexo8_1.setDocumento(request.getDocumento());
                anexo8_1.setNum_proceso(request.getNum_proceso());

                try {
                    anexo8_1Repository.save(anexo8_1);
                } catch (Exception ex) {
                    throw new BadRequestException("No se guardó el anexo 8.1" + ex);
                }try {
                    return true;
                } catch (Exception ex) {
                    throw new BadRequestException("No se envió el email");
                }
            } else {
                throw new BadRequestException("Ya existe el anexo con ese id de proyecto");
            }
        }
        throw new BadRequestException("No existe el proyecto con id: " + request.getIdProyectoPPP());
    }

    @Transactional
    public boolean update(Anexo8_1Request request) {
        Optional<Anexo8_1> optional = anexo8_1Repository.findById(request.getId());
        if (optional.isPresent()) {
            if (!optional.get().getProyectoPPP().isEstado())
                throw new BadRequestException("El proyecto ha finalizado, no es posible modificar sus datos");

            Anexo8_1 anexo8_1 = optional.get();
            //anexo8_1.setFechaRespuesta(request.getFechaRespuesta());
            anexo8_1.setTutorAcademico(request.getTutorAcademico());
            anexo8_1.setCortesia(request.getCortesia());
            anexo8_1.setNombreEstudiante(request.getNombreEstudiante());
            anexo8_1.setNombreEmpresa(request.getNombreEmpresa());
            anexo8_1.setNombreResponsable(request.getNombreResponsable());
            anexo8_1.setCarrera(request.getCarrera());
            anexo8_1.setProyectoPPP(proyectoRepository.findById(request.getIdProyectoPPP()).orElseThrow(() -> new BadRequestException("No existe proyecto con id: " + request.getIdProyectoPPP())));
            anexo8_1.setNum_proceso(request.getNum_proceso());

            try {
                anexo8_1Repository.save(anexo8_1);
                return true;
            } catch (Exception ex) {
                throw new BadRequestException("No se guardó el anexo 8.1" + ex);
            }
        }
        return false;
    }

    public Anexo8_1Response anexoIdProyecto(Long proyectoId) {
        Optional<ProyectoPPP> optional = proyectoRepository.findById(proyectoId);
        if (optional.isPresent()) {
            Optional<Anexo8_1> a = anexo8_1Repository.findByProyectoPPP(optional.get());
            if (a.isPresent()) {
                Anexo8_1Response response = new Anexo8_1Response();
                response.setId(a.get().getId());
                response.setFechaNotificacion(a.get().getFechaNotificacion());
                response.setTutorAcademico(a.get().getTutorAcademico());
                response.setCortesia(a.get().getCortesia());
                response.setNombreEstudiante(a.get().getNombreEstudiante());
                response.setNombreEmpresa(a.get().getNombreEmpresa());
                response.setNombreResponsable(a.get().getNombreResponsable());
                response.setCarrera(a.get().getCarrera());
                response.setDocumento(a.get().getDocumento());
                response.setNum_proceso(a.get().getNum_proceso());
                response.setIdProyectoPPP(a.get().getProyectoPPP().getId());
                return response;
            }
            throw new BadRequestException("No existe el anexo con id de la solicitud: " + proyectoId);
        }

        throw new BadRequestException("No existe la Anexo 8.1: " + proyectoId);
    }

    //Listar todos los anexos8.1
    @Transactional
    public List<Anexo8_1Response> listAnexoProyecto(Long id) {
        Optional<ProyectoPPP> op = proyectoRepository.findById(id);
        if (op.isPresent()) {
            List<Anexo8_1> lista = anexo8_1Repository.findAllByProyectoPPP(op.get());
            return lista.stream().map(anexo8_1 -> {
                Anexo8_1Response an = new Anexo8_1Response();
                an.setId(anexo8_1.getId());
                an.setFechaNotificacion(anexo8_1.getFechaNotificacion());
                an.setTutorAcademico(anexo8_1.getTutorAcademico());
                an.setCortesia(anexo8_1.getCortesia());
                an.setNombreEstudiante(anexo8_1.getNombreEstudiante());
                an.setNombreEmpresa(anexo8_1.getNombreEmpresa());
                an.setNombreResponsable(anexo8_1.getNombreResponsable());
                an.setCarrera(anexo8_1.getCarrera());
                an.setIdProyectoPPP(anexo8_1.getProyectoPPP().getId());
                an.setNum_proceso(anexo8_1.getNum_proceso());
                an.setDocumento(anexo8_1.getDocumento());
                return an;
            }).collect(Collectors.toList());

        }
        throw new BadRequestException("No existe el Anexo 8.1");
    }

    @Transactional
    public List<Anexo8_1Response> listAll() {
        return anexo8_1Repository.findAll().stream().map(a -> {
            Anexo8_1Response response = new Anexo8_1Response();
            response.setId(a.getId());
            response.setFechaNotificacion(a.getFechaNotificacion());
            response.setTutorAcademico(a.getTutorAcademico());
            response.setCortesia(a.getCortesia());
            response.setNombreEstudiante(a.getNombreEstudiante());
            response.setNombreEmpresa(a.getNombreEmpresa());
            response.setNombreResponsable(a.getNombreResponsable());
            response.setCarrera(a.getCarrera());
            response.setDocumento(a.getDocumento());
            response.setNum_proceso(a.getNum_proceso());
            response.setIdProyectoPPP(a.getProyectoPPP().getId());
            return response;
        }).collect(Collectors.toList());
    }

    @Transactional
    public void deleteAnexo8_1ById(Long id) {
        Optional<Anexo8_1> optional = anexo8_1Repository.findById(id);
        if (optional.isEmpty()) {
            throw new BadRequestException("El anexo8.1 con id: " + id + ", no existe");
        }
        anexo8_1Repository.deleteById(id);
    }

}
