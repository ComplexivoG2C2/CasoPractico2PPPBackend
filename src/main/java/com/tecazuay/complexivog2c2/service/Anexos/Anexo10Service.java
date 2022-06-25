package com.tecazuay.complexivog2c2.service.Anexos;

import com.tecazuay.complexivog2c2.dto.anexos.Anexo10Request;
import com.tecazuay.complexivog2c2.dto.anexos.Anexo10Response;
import com.tecazuay.complexivog2c2.dto.anexos.CronogramaAnexo10Request;
import com.tecazuay.complexivog2c2.exception.BadRequestException;
import com.tecazuay.complexivog2c2.model.Primary.Anexos.Anexo10;
import com.tecazuay.complexivog2c2.model.Primary.Anexos.CronogramaAnexo10;
import com.tecazuay.complexivog2c2.model.Primary.solicitudproyecto.ProyectoPPP;
import com.tecazuay.complexivog2c2.repository.Primary.Anexos.Anexo10Repository;
import com.tecazuay.complexivog2c2.repository.Primary.Anexos.CronogrmaAnexo10Repository;
import com.tecazuay.complexivog2c2.repository.Primary.solicitudproyecto.ProyectoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class Anexo10Service {
    @Autowired
    private Anexo10Repository anexo10Repository;

    @Autowired
    private CronogrmaAnexo10Repository cronogramaAnexo10Repository;

    @Autowired
    private ProyectoRepository proyectoRepository;




    public boolean save(Anexo10Request request) {
        Optional<ProyectoPPP> optional = proyectoRepository.findById(request.getIdProyectoPPP());

        if (optional.isPresent()) {
            if (!optional.get().isEstado())
                throw new BadRequestException("El proceso a finalizado");

            if (!anexo10Repository.existsByProyectoPPP(optional.get())) {
                Anexo10 anexo10 = new Anexo10();
                anexo10.setCarrera(request.getCarrera());
                anexo10.setSiglascarrera(request.getSiglascarrera());
                anexo10.setNombreEstudiante(request.getNombreEstudiante());
                anexo10.setNombreEmpresa(request.getNombreEmpresa());
                anexo10.setTutorAcademico(request.getTutorAcademico());
                anexo10.setCedulaTutorAcademico(request.getCedulaTutorAcademico());
                anexo10.setDocumento(request.getDocumento());
                anexo10.setProyectoPPP(optional.get());
                List<CronogramaAnexo10> list = new ArrayList<>();

                request.getCronogramaAnexo10s().stream().forEach(a -> {
                    CronogramaAnexo10 cronogramaAnexo10 = new CronogramaAnexo10();
                    cronogramaAnexo10.setNro(a.getNro());
                    cronogramaAnexo10.setFechaSeguimiento(a.getFechaSeguimiento());
                    cronogramaAnexo10.setActividades(a.getActividades());
                    cronogramaAnexo10.setFechaFinPrevista(a.getFechaFinPrevista());
                    cronogramaAnexo10.setPorcentajeAvance(a.getPorcentajeAvance());
                    cronogramaAnexo10.setObservaciones(a.getObservaciones());
                    list.add(cronogramaAnexo10);
                });

                try {
                    saveCronograma(list, anexo10Repository.save(anexo10));
                } catch (Exception e) {
                    throw new BadRequestException("No se guardo el anexo 10" + e);
                }
                try {
                    return true;
                } catch (Exception ex) {
                    throw new BadRequestException("No se enviÃ³ el email");
                }
            } else {
                throw new BadRequestException("Ya existe el anexo con ese id de proyecto");
            }
        }
        throw new BadRequestException("No existe la solicitud con id: " + request.getIdProyectoPPP());
    }

    private void saveCronograma(List<CronogramaAnexo10> list, Anexo10 anexo10) {
        list.stream().forEach(a -> {
            a.setAnexo10(anexo10);
            cronogramaAnexo10Repository.save(a);
        });
    }

    public void deleteCronogramaById(Long id) {
        Optional<CronogramaAnexo10> optional = cronogramaAnexo10Repository.findById(id);
        if (!optional.isPresent()) {
            throw new BadRequestException("Las actividades con id: " + id + ", no existen");
        }
        cronogramaAnexo10Repository.deleteById(id);
    }

    @Transactional
    public void deleteAnexo10ById(Long id) {
        Optional<Anexo10> optional = anexo10Repository.findById(id);
        if (!optional.isPresent()) {
            throw new BadRequestException("El anexo10 con id: " + id + ", no existe");
        }
        if (!optional.get().getProyectoPPP().isEstado())
            throw new BadRequestException("La solictiud ha finalizado");

        optional.get().getCronogramaAnexo10s()
                .forEach(d -> deleteCronogramaById(d.getId()));

        anexo10Repository.delete(optional.get());
    }

    @Transactional
    public List<Anexo10Response> listAll() {
        return anexo10Repository.findAll().stream().map(a -> {
            Anexo10Response response = new Anexo10Response();

            response.setId(a.getId());
            response.setCarrera(a.getCarrera());
            response.setSiglascarrera(a.getSiglascarrera());
            response.setNombreEstudiante(a.getNombreEstudiante());
            response.setNombreEmpresa(a.getNombreEmpresa());
            response.setTutorAcademico(a.getTutorAcademico());
           response.setCedulaTutorAcademico(a.getCedulaTutorAcademico());
            response.setDocumento(a.getDocumento());

            List<CronogramaAnexo10Request> list = a.getCronogramaAnexo10s().stream().map(ac -> {
                CronogramaAnexo10Request request = new CronogramaAnexo10Request();
                request.setNro(ac.getNro());
                request.setFechaSeguimiento(ac.getFechaSeguimiento());
                request.setActividades(ac.getActividades());
                request.setFechaFinPrevista(ac.getFechaFinPrevista());
                request.setPorcentajeAvance(ac.getPorcentajeAvance());
                request.setObservaciones(ac.getObservaciones());
                request.setId(ac.getId());
                return request;
            }).collect(Collectors.toList());

            response.setCronogramaAnexo10s(list);
            response.setIdProyectoPPP(a.getProyectoPPP().getId());
            return response;
        }).collect(Collectors.toList());
    }






    public Anexo10Response anexo10IdAnexo10 (Long id){

        Optional<Anexo10> a = anexo10Repository.findById(id);
        if(a.isPresent()){
            Anexo10Response response = new Anexo10Response();
            response.setId(a.get().getId());
            response.setCarrera(a.get().getCarrera());
            response.setSiglascarrera(a.get().getSiglascarrera());
            response.setNombreEstudiante(a.get().getNombreEstudiante());
            response.setNombreEmpresa(a.get().getNombreEmpresa());
            response.setTutorAcademico(a.get().getTutorAcademico());
            response.setCedulaTutorAcademico(a.get().getCedulaTutorAcademico());
            response.setDocumento(a.get().getDocumento());

            List<CronogramaAnexo10Request> list = a.get().getCronogramaAnexo10s().stream().map(ac -> {
                CronogramaAnexo10Request request = new CronogramaAnexo10Request();
                request.setNro(ac.getNro());
                request.setFechaSeguimiento(ac.getFechaSeguimiento());
                request.setActividades(ac.getActividades());
                request.setFechaFinPrevista(ac.getFechaFinPrevista());
                request.setPorcentajeAvance(ac.getPorcentajeAvance());
                request.setObservaciones(ac.getObservaciones());
                request.setId(ac.getId());
                return request;
            }).collect(Collectors.toList());

            response.setCronogramaAnexo10s(list);
            response.setIdProyectoPPP(a.get().getProyectoPPP().getId());
            return response;
        }
        throw new BadRequestException("No existe el anexo 10 con id: " +id);

    }




    public Anexo10Response anexoIdProyecto(Long proyectoId) {
        Optional<ProyectoPPP> optional = proyectoRepository.findById(proyectoId);
        if (optional.isPresent()) {
            Optional<Anexo10> a = anexo10Repository.findByProyectoPPP(optional.get());
            if (a.isPresent()) {
                Anexo10Response response = new Anexo10Response();

                response.setId(a.get().getId());
                response.setCarrera(a.get().getCarrera());
                response.setSiglascarrera(a.get().getSiglascarrera());
                response.setNombreEstudiante(a.get().getNombreEstudiante());
                response.setNombreEmpresa(a.get().getNombreEmpresa());
                response.setTutorAcademico(a.get().getTutorAcademico());
                response.setCedulaTutorAcademico(a.get().getCedulaTutorAcademico());
                response.setDocumento(a.get().getDocumento());

                List<CronogramaAnexo10Request> list = a.get().getCronogramaAnexo10s().stream().map(ac -> {
                    CronogramaAnexo10Request request = new CronogramaAnexo10Request();
                    request.setNro(ac.getNro());
                    request.setFechaSeguimiento(ac.getFechaSeguimiento());
                    request.setActividades(ac.getActividades());
                    request.setFechaFinPrevista(ac.getFechaFinPrevista());
                    request.setPorcentajeAvance(ac.getPorcentajeAvance());
                    request.setObservaciones(ac.getObservaciones());
                    request.setId(ac.getId());
                    return request;
                }).collect(Collectors.toList());

                response.setCronogramaAnexo10s(list);
                response.setIdProyectoPPP(a.get().getProyectoPPP().getId());
                return response;
            }
            throw new BadRequestException("No existe el anexo con id de la solicitud: " + proyectoId);
        }

        throw new BadRequestException("No existe la solicitud: " + proyectoId);
    }

}
