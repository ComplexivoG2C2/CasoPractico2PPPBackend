package com.tecazuay.complexivog2c2.service.Anexos;

import com.tecazuay.complexivog2c2.dto.anexos.*;
import com.tecazuay.complexivog2c2.dto.anexos.anexo1214y15.Anexo15Request;
import com.tecazuay.complexivog2c2.dto.anexos.anexo1214y15.Anexo15Response;
import com.tecazuay.complexivog2c2.exception.BadRequestException;
import com.tecazuay.complexivog2c2.model.Primary.Anexos.AlumnosAnexo5;
import com.tecazuay.complexivog2c2.model.Primary.Anexos.AlumnosAnexo6;
import com.tecazuay.complexivog2c2.model.Primary.Anexos.Anexo5;
import com.tecazuay.complexivog2c2.model.Primary.Anexos.Anexo6;
import com.tecazuay.complexivog2c2.model.Primary.Anexos.anexo1214y15.Anexo15;
import com.tecazuay.complexivog2c2.model.Primary.empresa.Empresa;
import com.tecazuay.complexivog2c2.model.Primary.solicitudproyecto.ProyectoPPP;
import com.tecazuay.complexivog2c2.repository.Primary.Anexos.AlumnosAnexo5Repository;
import com.tecazuay.complexivog2c2.repository.Primary.Anexos.Anexo5Repository;
import com.tecazuay.complexivog2c2.repository.Primary.empresa.EmpresaRepository;
import com.tecazuay.complexivog2c2.repository.Primary.solicitudproyecto.ProyectoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class Anexo5Service {

    @Autowired
    private Anexo5Repository anexo5Repository;

    @Autowired
    private AlumnosAnexo5Repository alumnosAnexo5Repository;

    @Autowired
    private ProyectoRepository proyectoRepository;

    @Autowired
    private EmpresaRepository empresaRepository;


    public boolean save(Anexo5Request request){
        Optional<ProyectoPPP> optional= proyectoRepository.findById(request.getIdProyectoPPP());
        Optional<Empresa> optionalEmpresa= empresaRepository.findById(request.getIdEmpresa());
        if(optional.isPresent()){
            if (!optional.get().isEstado())
                throw new BadRequestException("El proceso a finalizado");
                Anexo5 anexo = new Anexo5();

                anexo.setFechaEmision(request.getFechaEmision());
                anexo.setTituloTutor(request.getTituloTutor());
                anexo.setNombreTutor(request.getNombreTutor());
                anexo.setDocumento(request.getDocumento());
                anexo.setNombreEst(request.getNombreEst());
                anexo.setCedulaEst(request.getCedulaEst());
                anexo.setCarrera(request.getCarrera());
                anexo.setSiglascarrera(request.getSiglascarrera());
            anexo.setProyectoPPP(optional.get());
            anexo.setEmpresa(optionalEmpresa.get());

            try {
                anexo5Repository.save(anexo);
                return true;
            } catch (Exception ex) {
                throw new BadRequestException("No se guardó el anexo 8" + ex);
            }
        }
        throw new BadRequestException("No existe solicitud con id: " + request.getIdProyectoPPP());
    }







    @Transactional
    public List<Anexo5Response> listAll(){
        return anexo5Repository.findAll().stream().map(a->{
            Anexo5Response response = new Anexo5Response();
            response.setId(a.getId());
            response.setTituloTutor(a.getTituloTutor());
            response.setDocumento(a.getDocumento());
            response.setFechaEmision(a.getFechaEmision());
            response.setNombreTutor(a.getNombreTutor());
            response.setNombreEst(a.getNombreEst());
            response.setCedulaEst(a.getCedulaEst());
            response.setCarrera(a.getCarrera());
            response.setSiglascarrera(a.getSiglascarrera());

            response.setIdProyectoPPP(a.getProyectoPPP().getId());
            return response;
        }).collect(Collectors.toList());
    }

    public List<Anexo5Response> anexoIdProyecto(Long proyectoId){
        Optional<ProyectoPPP> optional= proyectoRepository.findById(proyectoId);
        Optional<Empresa> optionalEmpresa= empresaRepository.findById(proyectoId);
        if(optional.isPresent()){
            List<Anexo5> c=anexo5Repository.findByProyectoPPP(optional.get());
            return c.stream().map(a->{
                Anexo5Response response = new Anexo5Response();
                response.setId(a.getId());
                response.setFechaEmision(a.getFechaEmision());
                response.setDocumento(a.getDocumento());
                response.setNombreTutor(a.getNombreTutor());
                response.setTituloTutor(a.getTituloTutor());
                response.setNombreEst(a.getNombreEst());
                response.setCedulaEst(a.getCedulaEst());
                response.setCarrera(a.getCarrera());
                response.setSiglascarrera(a.getSiglascarrera());

                response.setIdProyectoPPP(a.getProyectoPPP().getId());
                response.setIdEmpresa(a.getEmpresa().getId());
                return response;
            }).collect(Collectors.toList());
        }
        throw new BadRequestException("No existe la solicitud: " + proyectoId);
    }

    public void deleteById(Long id){
        Optional<Anexo5> optional=anexo5Repository.findById(id);
        if(optional.isEmpty()){
            throw new BadRequestException("El anexo 5 con el id " + id + ", no existe");
        }
        if(!optional.get().getProyectoPPP().isEstado())
            throw new BadRequestException("El proyecto ha finalizado, no es posible modificar sus datos");

        anexo5Repository.deleteById(id);
    }




    @Transactional
    public List<Anexo5Response> findAllByNombre(String nombreTutor) {
        return anexo5Repository.findAllByNombreTutor(nombreTutor).stream().map(a -> {
            Anexo5Response response = new Anexo5Response();
            response.setId(a.getId());
            response.setFechaEmision(a.getFechaEmision());
            response.setDocumento(a.getDocumento());
            response.setNombreTutor(a.getNombreTutor());
            response.setTituloTutor(a.getTituloTutor());
            response.setNombreEst(a.getNombreEst());
            response.setCedulaEst(a.getCedulaEst());
            response.setCarrera(a.getCarrera());
            response.setSiglascarrera(a.getSiglascarrera());

            response.setIdProyectoPPP(a.getProyectoPPP().getId());
            response.setIdEmpresa(a.getEmpresa().getId());
            return response;
        }).collect(Collectors.toList());
    }


    @Transactional
    public List<Anexo5Response> findAllByidempresa(Long idEmpresa) {
        Optional<Empresa> optional= empresaRepository.findById(idEmpresa);
        return anexo5Repository.findAllByEmpresa(optional.get()).stream().map(a -> {
            Anexo5Response response = new Anexo5Response();
            response.setId(a.getId());
            response.setFechaEmision(a.getFechaEmision());
            response.setDocumento(a.getDocumento());
            response.setNombreTutor(a.getNombreTutor());
            response.setTituloTutor(a.getTituloTutor());
            response.setNombreEst(a.getNombreEst());
            response.setCedulaEst(a.getCedulaEst());
            response.setCarrera(a.getCarrera());
            response.setSiglascarrera(a.getSiglascarrera());

            response.setIdProyectoPPP(a.getProyectoPPP().getId());
            response.setIdEmpresa(a.getEmpresa().getId());
            return response;
        }).collect(Collectors.toList());
    }

}
