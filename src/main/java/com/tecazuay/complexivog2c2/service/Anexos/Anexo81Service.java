package com.tecazuay.complexivog2c2.service.Anexos;

import com.tecazuay.complexivog2c2.dto.anexos.AlumnosAnexo6Request;
import com.tecazuay.complexivog2c2.dto.anexos.Anexo6Response;
import com.tecazuay.complexivog2c2.dto.anexos.Anexo81Request;
import com.tecazuay.complexivog2c2.dto.anexos.Anexo81Response;
import com.tecazuay.complexivog2c2.exception.BadRequestException;
import com.tecazuay.complexivog2c2.model.Primary.Anexos.Anexo81;
import com.tecazuay.complexivog2c2.model.Primary.solicitudproyecto.ProyectoPPP;
import com.tecazuay.complexivog2c2.repository.Primary.Anexos.Anexo81Repository;
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
public class Anexo81Service {
    @Autowired
    private Anexo81Repository anexo8_1Repository;

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

    public boolean save(Anexo81Request request) {
        Optional<ProyectoPPP> optional = proyectoRepository.findById(request.getIdProyectoPPP());

        if (optional.isPresent()) {
            if (!optional.get().isEstado())
                throw new BadRequestException("El proceso a finalizado");

                Anexo81 anexo8_1 = new Anexo81();
                anexo8_1.setFechaNotificacion(request.getFechaNotificacion());
                anexo8_1.setTutorAcademico(request.getTutorAcademico());
                anexo8_1.setCortesia(request.getCortesia());
                anexo8_1.setNombreEstudiante(request.getNombreEstudiante());
                anexo8_1.setNombreEmpresa(request.getNombreEmpresa());
                anexo8_1.setCedulaEstudiante(request.getCedulaEstudiante());
                anexo8_1.setCedulaTutoracademico(request.getCedulaTutoracademico());
                anexo8_1.setNombreTutoracademico(request.getNombreTutoracademico());
                anexo8_1.setNombreResponsable(request.getNombreResponsable());
                anexo8_1.setCarrera(request.getCarrera());
                anexo8_1.setProyectoPPP(optional.get());
                anexo8_1.setDocumento(request.getDocumento());
                anexo8_1.setSiglascarrera(request.getSiglascarrera());

                try {
                    anexo8_1Repository.save(anexo8_1);
                } catch (Exception ex) {
                    throw new BadRequestException("No se guardo el anexo 8.1" + ex);
                }try {
                    return true;
                } catch (Exception ex) {
                    throw new BadRequestException("No se envio el email");
                }

        }
        throw new BadRequestException("No existe la solicitud con id: " + request.getIdProyectoPPP());
    }

    @Transactional
    public boolean update(Anexo81Request request) {
        Optional<Anexo81> optional = anexo8_1Repository.findById(request.getId());
        if (optional.isPresent()) {
            if (!optional.get().getProyectoPPP().isEstado())
                throw new BadRequestException("El proyecto ha finalizado, no es posible modificar sus datos");

            Anexo81 anexo8_1 = optional.get();
            //anexo8_1.setFechaRespuesta(request.getFechaRespuesta());
            anexo8_1.setTutorAcademico(request.getTutorAcademico());
            anexo8_1.setCortesia(request.getCortesia());
            anexo8_1.setNombreEstudiante(request.getNombreEstudiante());
            anexo8_1.setCedulaEstudiante(request.getCedulaEstudiante());
            anexo8_1.setCedulaTutoracademico(request.getCedulaTutoracademico());
            anexo8_1.setNombreTutoracademico(request.getNombreTutoracademico());

            anexo8_1.setNombreEmpresa(request.getNombreEmpresa());
            anexo8_1.setNombreResponsable(request.getNombreResponsable());
            anexo8_1.setCarrera(request.getCarrera());
            anexo8_1.setProyectoPPP(proyectoRepository.findById(request.getIdProyectoPPP()).orElseThrow(() -> new BadRequestException("No existe proyecto con id: " + request.getIdProyectoPPP())));
            anexo8_1.setSiglascarrera(request.getSiglascarrera());

            try {
                anexo8_1Repository.save(anexo8_1);
                return true;
            } catch (Exception ex) {
                throw new BadRequestException("No se guardÃ³ el anexo 8.1" + ex);
            }
        }
        return false;
    }

    public Anexo81Response anexoIdProyecto(Long proyectoId) {
        Optional<ProyectoPPP> optional = proyectoRepository.findById(proyectoId);
        if (optional.isPresent()) {
            Optional<Anexo81> a = anexo8_1Repository.findByProyectoPPP(optional.get());
            if (a.isPresent()) {
                Anexo81Response response = new Anexo81Response();
                response.setId(a.get().getId());
                response.setFechaNotificacion(a.get().getFechaNotificacion());
                response.setTutorAcademico(a.get().getTutorAcademico());
                response.setCortesia(a.get().getCortesia());
                response.setNombreEstudiante(a.get().getNombreEstudiante());
                response.setCedulaTutoracademico(a.get().getCedulaTutoracademico());
                response.setNombreTutoracademico(a.get().getNombreTutoracademico());
                response.setCedulaEstudiante(a.get().getCedulaEstudiante());
                response.setNombreEmpresa(a.get().getNombreEmpresa());
                response.setNombreResponsable(a.get().getNombreResponsable());
                response.setCarrera(a.get().getCarrera());
                response.setDocumento(a.get().getDocumento());
                response.setSiglascarrera(a.get().getSiglascarrera());
                response.setIdProyectoPPP(a.get().getProyectoPPP().getId());
                return response;
            }
            throw new BadRequestException("No existe el anexo con id de la solicitud: " + proyectoId);
        }

        throw new BadRequestException("No existe la Anexo 8.1: " + proyectoId);
    }

    //Listar todos los anexos8.1
    @Transactional
    public List<Anexo81Response> listAnexoProyecto(Long id) {
        Optional<ProyectoPPP> op = proyectoRepository.findById(id);
        if (op.isPresent()) {
            List<Anexo81> lista = anexo8_1Repository.findAllByProyectoPPP(op.get());
            return lista.stream().map(anexo8_1 -> {
                Anexo81Response an = new Anexo81Response();
                an.setId(anexo8_1.getId());
                an.setFechaNotificacion(anexo8_1.getFechaNotificacion());
                an.setTutorAcademico(anexo8_1.getTutorAcademico());
                an.setCortesia(anexo8_1.getCortesia());
                an.setNombreEstudiante(anexo8_1.getNombreEstudiante());
                an.setCedulaEstudiante(anexo8_1.getCedulaEstudiante());
                an.setNombreTutoracademico(anexo8_1.getNombreEstudiante());
                an.setCedulaTutoracademico(anexo8_1.getCedulaTutoracademico());
                an.setNombreEmpresa(anexo8_1.getNombreEmpresa());
                an.setNombreResponsable(anexo8_1.getNombreResponsable());
                an.setCarrera(anexo8_1.getCarrera());
                an.setIdProyectoPPP(anexo8_1.getProyectoPPP().getId());
                an.setSiglascarrera(anexo8_1.getSiglascarrera());
                an.setDocumento(anexo8_1.getDocumento());
                return an;
            }).collect(Collectors.toList());

        }
        throw new BadRequestException("No existe el Anexo 8.1");
    }

    @Transactional
    public List<Anexo81Response> listAll() {
        return anexo8_1Repository.findAll().stream().map(a -> {
            Anexo81Response response = new Anexo81Response();
            response.setId(a.getId());
            response.setFechaNotificacion(a.getFechaNotificacion());
            response.setTutorAcademico(a.getTutorAcademico());
            response.setCortesia(a.getCortesia());
            response.setNombreEstudiante(a.getNombreEstudiante());
            response.setNombreTutoracademico(a.getNombreTutoracademico());
            response.setCedulaTutoracademico(a.getCedulaTutoracademico());
            response.setCedulaEstudiante(a.getCedulaEstudiante());
            response.setNombreEmpresa(a.getNombreEmpresa());
            response.setNombreResponsable(a.getNombreResponsable());
            response.setCarrera(a.getCarrera());
            response.setDocumento(a.getDocumento());
            response.setSiglascarrera(a.getSiglascarrera());
            response.setIdProyectoPPP(a.getProyectoPPP().getId());
            return response;
        }).collect(Collectors.toList());
    }

    @Transactional
    public void deleteAnexo81ById(Long id) {
        Optional<Anexo81> optional = anexo8_1Repository.findById(id);
        if (!optional.isPresent()) {
            throw new BadRequestException("El anexo8.1 con id: " + id + ", no existe");
        }
        anexo8_1Repository.deleteById(id);
    }
    @Transactional
    public List<Anexo81Response> findAllByCedula(String cedula) {
        return anexo8_1Repository.findAllByCedulaTutoracademico(cedula).stream().map(a -> {
            Anexo81Response response = new Anexo81Response();
            response.setId(a.getId());
            response.setFechaNotificacion(a.getFechaNotificacion());
            response.setTutorAcademico(a.getTutorAcademico());
            response.setCortesia(a.getCortesia());
            response.setNombreEstudiante(a.getNombreEstudiante());
            response.setNombreTutoracademico(a.getNombreTutoracademico());
            response.setCedulaTutoracademico(a.getCedulaTutoracademico());
            response.setCedulaEstudiante(a.getCedulaEstudiante());
            response.setNombreEmpresa(a.getNombreEmpresa());
            response.setNombreResponsable(a.getNombreResponsable());
            response.setCarrera(a.getCarrera());
            response.setDocumento(a.getDocumento());
            response.setSiglascarrera(a.getSiglascarrera());
            response.setIdProyectoPPP(a.getProyectoPPP().getId());
            return response;
        }).collect(Collectors.toList());
    }

}
