package com.tecazuay.complexivog2c2.service.Anexos;


import com.tecazuay.complexivog2c2.dto.anexos.*;
import com.tecazuay.complexivog2c2.exception.BadRequestException;
import com.tecazuay.complexivog2c2.model.Primary.Anexos.*;
import com.tecazuay.complexivog2c2.model.Primary.solicitudproyecto.ProyectoPPP;
import com.tecazuay.complexivog2c2.model.Secondary.alumnos.VAlumnosppp;
import com.tecazuay.complexivog2c2.repository.Primary.Anexos.Anexo3_1Repository;
import com.tecazuay.complexivog2c2.repository.Primary.designaciones.ResponsablePPPRepository;
import com.tecazuay.complexivog2c2.repository.Primary.solicitudproyecto.ProyectoRepository;
import com.tecazuay.complexivog2c2.repository.Primary.solicitudproyecto.RequisitosRepository;
import com.tecazuay.complexivog2c2.repository.Primary.usuario.UsuarioRepository;
import com.tecazuay.complexivog2c2.repository.Secondary.alumnos.VAlumnosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class Anexo3_1Service {

    private Anexo3_1Repository anexo3_1Repository;

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


    public boolean save(Anexo3_1Request request) {
        Optional<ProyectoPPP> optional = proyectoRepository.findById(request.getIdProyectoPPP());

        if (optional.isPresent()) {
            if (!optional.get().isEstado())
                throw new BadRequestException("El proceso a finalizado");

            if (!anexo3_1Repository.existsByProyectoPPP(optional.get())) {
                Anexo3_1 anexo3_1 = new Anexo3_1();
                anexo3_1.setFechaRespuesta(request.getFechaRespuesta());
                anexo3_1.setTituloRepresentanteEmp(request.getTituloRepresentanteEmp());
                anexo3_1.setNombreRepresentanteEmp(request.getNombreRepresentanteEmp());
                anexo3_1.setCargoEmpresa(request.getCargoEmpresa());
                anexo3_1.setNombreEmpresa(request.getNombreEmpresa());
                anexo3_1.setFechaSolicitudEmp(request.getFechaSolicitudEmp());
                anexo3_1.setNombreResponsable(request.getNombreResponsable());
                anexo3_1.setCarrera(request.getCarrera());
                anexo3_1.setProyectoPPP(optional.get());
                anexo3_1.setDocumento(request.getDocumento());
                anexo3_1.setNum_proceso(request.getNum_proceso());

                try {
                    anexo3_1Repository.save(anexo3_1);
                    return true;
                } catch (Exception ex) {
                    throw new BadRequestException("No se guardó el anexo 3.1" + ex);
                }
            } else {
                throw new BadRequestException("Ya existe el anexo con ese id de proyecto");
            }
        }
        throw new BadRequestException("No existe el proyecto con id: " + request.getIdProyectoPPP());
    }

    @Transactional
    public boolean update(Anexo3_1Request request) {
        Optional<Anexo3_1> optional = anexo3_1Repository.findById(request.getId());
        if (optional.isPresent()) {
            if (!optional.get().getProyectoPPP().isEstado())
                throw new BadRequestException("El proyecto ha finalizado, no es posible modificar sus datos");

            Anexo3_1 anexo3_1 = optional.get();
            //anexo3_1.setFechaRespuesta(request.getFechaRespuesta());
            anexo3_1.setTituloRepresentanteEmp(request.getTituloRepresentanteEmp());
            anexo3_1.setNombreRepresentanteEmp(request.getNombreRepresentanteEmp());
            anexo3_1.setCargoEmpresa(request.getCargoEmpresa());
            anexo3_1.setNombreEmpresa(request.getNombreEmpresa());
            anexo3_1.setFechaSolicitudEmp(request.getFechaSolicitudEmp());
            anexo3_1.setNombreResponsable(request.getNombreResponsable());
            anexo3_1.setCarrera(request.getCarrera());
            anexo3_1.setProyectoPPP(proyectoRepository.findById(request.getIdProyectoPPP()).orElseThrow(() -> new BadRequestException("No existe proyecto con id: " + request.getIdProyectoPPP())));
            anexo3_1.setNum_proceso(request.getNum_proceso());

            try {
                anexo3_1Repository.save(anexo3_1);
                return true;
            } catch (Exception ex) {
                throw new BadRequestException("No se guardó el anexo 3.1" + ex);
            }
        }
        return false;
    }

    public Anexo3_1Response anexoIdProyecto(Long proyectoId) {
        Optional<ProyectoPPP> optional = proyectoRepository.findById(proyectoId);
        if (optional.isPresent()) {
            Optional<Anexo3_1> a = anexo3_1Repository.findByProyectoPPP(optional.get());
            if (a.isPresent()) {
                Anexo3_1Response response = new Anexo3_1Response();
                response.setId(a.get().getId());
                response.setFechaRespuesta(a.get().getFechaRespuesta());
                response.setTituloRepresentanteEmp(a.get().getTituloRepresentanteEmp());
                response.setNombreRepresentanteEmp(a.get().getNombreRepresentanteEmp());
                response.setCargoEmpresa(a.get().getCargoEmpresa());
                response.setNombreEmpresa(a.get().getNombreEmpresa());
                response.setFechaSolicitudEmp(a.get().getFechaSolicitudEmp());
                response.setNombreResponsable(a.get().getNombreResponsable());
                response.setCarrera(a.get().getCarrera());
                response.setDocumento(a.get().getDocumento());
                response.setNum_proceso(a.get().getNum_proceso());
                response.setIdProyectoPPP(a.get().getProyectoPPP().getId());
                return response;
            }
            throw new BadRequestException("No existe el anexo con id de la solicitud: " + proyectoId);
        }

        throw new BadRequestException("No existe la solicitud: " + proyectoId);
    }


    //Listar todos los anexos3.1
    @Transactional
    public List<Anexo3_1Response> listAnexoProyecto(Long id) {
        Optional<ProyectoPPP> op = proyectoRepository.findById(id);
        if (op.isPresent()) {
            List<Anexo3_1> lista = anexo3_1Repository.findAllByProyectoPPP(op.get());
            return lista.stream().map(anexo3_1 -> {
                Anexo3_1Response an = new Anexo3_1Response();
                an.setId(anexo3_1.getId());
                an.setFechaRespuesta(anexo3_1.getFechaRespuesta());
                an.setTituloRepresentanteEmp(anexo3_1.getTituloRepresentanteEmp());
                an.setNombreRepresentanteEmp(anexo3_1.getNombreRepresentanteEmp());
                an.setCargoEmpresa(anexo3_1.getCargoEmpresa());
                an.setNombreEmpresa(anexo3_1.getNombreEmpresa());
                an.setFechaSolicitudEmp(anexo3_1.getFechaSolicitudEmp());
                an.setNombreResponsable(anexo3_1.getNombreResponsable());
                an.setCarrera(anexo3_1.getCarrera());
                an.setIdProyectoPPP(anexo3_1.getProyectoPPP().getId());
                an.setNum_proceso(anexo3_1.getNum_proceso());
                an.setDocumento(anexo3_1.getDocumento());
                return an;
            }).collect(Collectors.toList());

        }
        throw new BadRequestException("No existe el solicitudproyecto");
    }

    @Transactional
    public List<Anexo3_1Response> listAll() {
        return anexo3_1Repository.findAll().stream().map(a -> {
            Anexo3_1Response response = new Anexo3_1Response();
            response.setId(a.getId());
            response.setFechaRespuesta(a.getFechaRespuesta());
            response.setTituloRepresentanteEmp(a.getTituloRepresentanteEmp());
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


    @Transactional
    public void deleteAnexo3_1ById(Long id) {
        Optional<Anexo3_1> optional = anexo3_1Repository.findById(id);
        if (optional.isEmpty()) {
            throw new BadRequestException("El anexo3.1 con id: " + id + ", no existe");
        }
        anexo3_1Repository.deleteById(id);
    }



    public List<VAlumnosppp> lista(String codigoCarrera) {
        List<VAlumnosppp> lista = vAlumnosRepository.findAllByCodigoCarrera(codigoCarrera);
        System.out.println("-----------------------------------------------------------------RECUPERADOS");
        System.out.println(lista.size());
        return lista;

    }


}
