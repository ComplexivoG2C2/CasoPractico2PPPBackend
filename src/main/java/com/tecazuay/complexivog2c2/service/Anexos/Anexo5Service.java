package com.tecazuay.complexivog2c2.service.Anexos;

import com.tecazuay.complexivog2c2.dto.anexos.*;
import com.tecazuay.complexivog2c2.exception.BadRequestException;
import com.tecazuay.complexivog2c2.model.Primary.Anexos.AlumnosAnexo5;
import com.tecazuay.complexivog2c2.model.Primary.Anexos.AlumnosAnexo6;
import com.tecazuay.complexivog2c2.model.Primary.Anexos.Anexo5;
import com.tecazuay.complexivog2c2.model.Primary.Anexos.Anexo6;
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
                throw new BadRequestException("El proyecto ha finalizado, no es posible modificar sus datos");

            if(isAlumnoAsignado(request.getAlumnos(), optional.get())){
                Anexo5 anexo = new Anexo5();

                anexo.setFechaEmision(request.getFechaEmision());
                anexo.setTituloTutor(request.getTituloTutor());
                anexo.setProyectoPPP(optional.get());
                anexo.setEmpresa(optionalEmpresa.get());
                anexo.setDocumento(request.getDocumento());

                List<AlumnosAnexo5> list = new ArrayList<>();
                request.getAlumnos().stream().forEach(a->{
                    AlumnosAnexo5 alumnosAnexo5= new AlumnosAnexo5();
                    alumnosAnexo5.setNombreEstudiante(a.getNombreEstudiante());
                    alumnosAnexo5.setCedulaEstudiante(a.getCedulaEstudiante());
                    list.add(alumnosAnexo5);
                });

                try{
                    saveAlumnos(list, anexo5Repository.save(anexo));
                    return true;
                }catch(Exception e){
                    throw new BadRequestException("No se guardó el anexo 5" + e);
                }
            }

        }
        throw new BadRequestException("No existe el proyecto con id: " + request.getIdProyectoPPP());
    }

    boolean isAlumnoAsignado(List<AlumnosAnexo5Request> alumnos, ProyectoPPP proyecto) {
        List<String> cedulas = alumnos.stream()
                .map(AlumnosAnexo5Request::getCedulaEstudiante)
                .collect(Collectors.toList());

        List<String> cedulasAsignadas = new ArrayList<>();

        cedulas.forEach(cedula -> {
            List<AlumnosAnexo5> listAlumno = alumnosAnexo5Repository.findAllByCedulaEstudiante(cedula);
            listAlumno.stream()
                    .filter(a -> Objects.equals(a.getAnexo5().getProyectoPPP().getId(), proyecto.getId()))
                    .findAny()
                    .ifPresent(alumno -> cedulasAsignadas.add(cedula));
        });

        if (cedulasAsignadas.size() == 0) return true;
        String msg = cedulasAsignadas.stream().reduce((s, s2) -> s.concat(", " + s2)).get();
        throw new BadRequestException("Los alumnos con las cédulas ya están asignados a un docente de apoyo en este proyecto: " + msg);
    }

    private void saveAlumnos(List<AlumnosAnexo5> list, Anexo5 anexo5) {
        list.stream().forEach(a -> {
            a.setAnexo5(anexo5);
            alumnosAnexo5Repository.save(a);
        });
    }

    @Transactional
    public List<Anexo5Response> listAll(){
        return anexo5Repository.findAll().stream().map(a->{
            Anexo5Response response = new Anexo5Response();
            response.setId(a.getId());
            response.setTituloTutor(a.getTituloTutor());
            response.setDocumento(a.getDocumento());
            response.setFechaEmision(a.getFechaEmision());
            List<AlumnosAnexo5Request> list = a.getAlumnosAnexo5().stream().map(ac ->{
                AlumnosAnexo5Request request = new AlumnosAnexo5Request();
                request.setId(ac.getId());
                request.setCedulaEstudiante(ac.getCedulaEstudiante());
                request.setNombreEstudiante(ac.getNombreEstudiante());
                return request;
            }).collect(Collectors.toList());
            response.setAlumnos(list);
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
                List<AlumnosAnexo5Request> list = a.getAlumnosAnexo5().stream().map(ac->{
                    AlumnosAnexo5Request request = new AlumnosAnexo5Request();
                    request.setId(ac.getId());
                    request.setNombreEstudiante(ac.getNombreEstudiante());
                    request.setCedulaEstudiante(ac.getCedulaEstudiante());
                    return request;
                }).collect(Collectors.toList());
                response.setAlumnos(list);
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
}
