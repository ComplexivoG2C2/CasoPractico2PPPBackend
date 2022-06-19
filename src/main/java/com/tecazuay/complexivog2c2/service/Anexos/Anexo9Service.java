package com.tecazuay.complexivog2c2.service.Anexos;


import com.tecazuay.complexivog2c2.dto.anexos.ActividadesAnexo9Request;
import com.tecazuay.complexivog2c2.dto.anexos.Anexo9Request;
import com.tecazuay.complexivog2c2.dto.anexos.Anexo9Response;
import com.tecazuay.complexivog2c2.exception.BadRequestException;
import com.tecazuay.complexivog2c2.exception.ResponseNotFoundException;
import com.tecazuay.complexivog2c2.model.Primary.Anexos.ActividadesAnexo9;
import com.tecazuay.complexivog2c2.model.Primary.Anexos.Anexo9;
import com.tecazuay.complexivog2c2.model.Primary.solicitudproyecto.ProyectoPPP;
import com.tecazuay.complexivog2c2.repository.Primary.Anexos.ActividadesAnexo9Repository;
import com.tecazuay.complexivog2c2.repository.Primary.Anexos.Anexo9Repository;
import com.tecazuay.complexivog2c2.repository.Primary.solicitudproyecto.ProyectoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class Anexo9Service {
    @Autowired
    private Anexo9Repository anexo9Repository;

    @Autowired
    private ActividadesAnexo9Repository actividadesAnexo9Repository;

    @Autowired
    private ProyectoRepository proyectoRepository;

    private void saveActividades(List<ActividadesAnexo9> list, Anexo9 anexo9) {
        list.stream().forEach(a -> {
            a.setAnexo9(anexo9);
            actividadesAnexo9Repository.save(a);
        });
    }

    public boolean save(Anexo9Request request) {
        Optional<ProyectoPPP> optional = proyectoRepository.findById(request.getIdProyectoPPP());

        if (optional.isPresent()) {
            if (!anexo9Repository.existsByProyectoPPPAndCedulaEstudiante(optional.get(), request.getCedulaEstudiante())) {
                Anexo9 anexo9 = new Anexo9();

                anexo9.setNombreProyecto(request.getNombreProyecto());
                anexo9.setNombreEmpresa(request.getNombreEmpresa());
                anexo9.setNombreEstudiante(request.getNombreEstudiante());
                anexo9.setCedulaEstudiante(request.getCedulaEstudiante());
                anexo9.setNombreRepresentanteemp(request.getNombreRepresentanteemp());
                anexo9.setNombreTutorAcademico(request.getNombreTutoracademico());
                anexo9.setNombreTutoremp(request.getNombreTutoremp());
                anexo9.setTotalHoras(request.getTotalHoras());
                anexo9.setDocumento(request.getDocumento());
                anexo9.setCedulaTutoremp(request.getCedulaTutoremp());
                anexo9.setProyectoPPP(optional.get());

                List<ActividadesAnexo9> list = new ArrayList<>();

                request.getActividades().stream().forEach(a -> {
                    ActividadesAnexo9 actividadesAnexo9 = new ActividadesAnexo9();
                    actividadesAnexo9.setFecha(a.getFecha());
                    actividadesAnexo9.setDescripcionActividad(a.getDescripcionActividad());
                    actividadesAnexo9.setLugar(a.getLugar());
                    actividadesAnexo9.setHorallegada(a.getHorallegada());
                    actividadesAnexo9.setHorasalida(a.getHorasalida());
                    actividadesAnexo9.setNumHoras(a.getNumHoras());
                    list.add(actividadesAnexo9);
                });

                try {
                    saveActividades(list, anexo9Repository.save(anexo9));
                    return true;
                } catch (Exception e) {
                    throw new BadRequestException("No se guardó el anexo 9" + e);
                }
            } else {
                throw new BadRequestException("El estudiante de cédula: " + request.getCedulaEstudiante() + ", ya tiene un anexo9 en este proyecto con id: " + request.getIdProyectoPPP());
            }
        }
        throw new BadRequestException("No existe el proyecto con id: " + request.getIdProyectoPPP());
    }

   /* public void update(Anexo9Request request) {
        Optional<Anexo9> optional = anexo9Repository.findById(request.getId());
        if (optional.isPresent()) {
            Anexo9 anexo9 = optional.get();
            anexo9.setNombreProyecto(request.getNombreProyecto());
            anexo9.setNombreEntidadBeneficiaria(request.getNombreEntidadBeneficiaria());
            anexo9.setNombreEstudiante(request.getNombreEstudiante());
            anexo9.setCedulaEstudiante(request.getCedulaEstudiante());
            anexo9.setNombreAdminEB(request.getNombreAdminEB());
            anexo9.setNombreDocenteApoyo(request.getNombreDocenteApoyo());
            anexo9.setNombreDirectorProyecto(request.getNombreDirectorProyecto());
            anexo9.setTotalHoras(request.getTotalHoras());
            anexo9.setCedulaDirector(request.getCedulaDirector());
            anexo9.setDocumento(request.getDocumento());
            List<ActividadesAnexo9> actividadesAnexo9s = new ArrayList<>();
            request.getActividades().stream().forEach(a -> {
                ActividadesAnexo9 actividades = new ActividadesAnexo9();
                actividades.setFecha(a.getFecha());
                actividades.setDescripcionActividad(a.getDescripcionActividad());
                actividades.setLugar(a.getLugar());
                actividades.setNumHoras(a.getNumHoras());
                actividadesAnexo9s.add(actividades);
            });
            try {
                Anexo9 a = anexo9Repository.save(anexo9);
                saveActividades(actividadesAnexo9s, a);
            } catch (Exception ex) {
                throw new BadRequestException("No se guardó el anexo 9" + ex);
            }
        } else
            throw new ResponseNotFoundException("Anexo 9", "Id:", request.getId() + "");
    }*/


    public void update(Anexo9Request request) {
        Optional<Anexo9> optional = anexo9Repository.findById(request.getId());
        if (optional.isPresent()) {
            Anexo9 anexo9 = optional.get();
            anexo9.setNombreProyecto(request.getNombreProyecto());
            anexo9.setNombreEmpresa(request.getNombreEmpresa());
            anexo9.setNombreEstudiante(request.getNombreEstudiante());
            anexo9.setCedulaEstudiante(request.getCedulaEstudiante());
            anexo9.setNombreRepresentanteemp(request.getNombreRepresentanteemp());
            anexo9.setNombreTutorAcademico(request.getNombreTutoracademico());
            anexo9.setNombreTutoremp(request.getNombreTutoremp());
            anexo9.setTotalHoras(request.getTotalHoras());
            anexo9.setCedulaTutoremp(request.getCedulaTutoremp());
            anexo9.setDocumento(request.getDocumento());

            try {
                Anexo9 a = anexo9Repository.save(anexo9);
                actualizarCrearActividades(request.getActividades(), a);
            } catch (Exception ex) {
                throw new BadRequestException("No se guardó el anexo 9" + ex);
            }
        } else
            throw new ResponseNotFoundException("Anexo 9", "Id:", request.getId() + "");
    }

    private void actualizarCrearActividades(List<ActividadesAnexo9Request> requestList, Anexo9 anexo9) {
        requestList.stream().forEach(request -> {
            if (request.getId() == null) {
                ActividadesAnexo9 newActividad = dtoToActividad9(request, anexo9);
                try {
                    actividadesAnexo9Repository.save(newActividad);
                } catch (Exception e) {
                    throw new BadRequestException("Error al guardar actividad anexo9: " + e.getMessage());
                }
            } else
                updateActividad(anexo9.getId(), request);
        });
    }

    private ActividadesAnexo9 dtoToActividad9(ActividadesAnexo9Request request, Anexo9 anexo9) {
        ActividadesAnexo9 actividad = new ActividadesAnexo9();
        actividad.setFecha(request.getFecha());
        actividad.setDescripcionActividad(request.getDescripcionActividad());
        actividad.setLugar(request.getLugar());
        actividad.setHorallegada(request.getHorallegada());
        actividad.setHorasalida(request.getHorasalida());
        actividad.setNumHoras(request.getNumHoras());
        actividad.setAnexo9(anexo9);
        return actividad;
    }

    @Transactional
    public List<Anexo9Response> listAll() {
        return anexo9Repository.findAll().stream().map(a -> {
            Anexo9Response response = new Anexo9Response();
            response.setId(a.getId());
            response.setNombreProyecto(a.getNombreProyecto());
            response.setNombreEmpresa(a.getNombreEmpresa());
            response.setNombreEstudiante(a.getNombreEstudiante());
            response.setCedulaEstudiante(a.getCedulaEstudiante());
            response.setNombreRepresentanteemp(a.getNombreRepresentanteemp());
            response.setNombreTutoracademico(a.getNombreTutorAcademico());
            response.setNombreTutoremp(a.getNombreTutoremp());
            response.setTotalHoras(a.getTotalHoras());
            response.setCedulaTutoremp(a.getCedulaTutoremp());
            response.setDocumento(a.getDocumento());

            List<ActividadesAnexo9Request> list = a.getActividadesAnexo9s().stream().map(ac -> {
                ActividadesAnexo9Request request = new ActividadesAnexo9Request();
                request.setId(ac.getId());
                request.setFecha(ac.getFecha());
                request.setDescripcionActividad(ac.getDescripcionActividad());
                request.setLugar(ac.getLugar());
                request.setHorallegada(ac.getHorallegada());
                request.setHorasalida(ac.getHorasalida());
                request.setNumHoras(ac.getNumHoras());
                return request;
            }).collect(Collectors.toList());
            response.setActividades(list);
            response.setIdProyectoPPP(a.getProyectoPPP().getId());
            return response;
        }).collect(Collectors.toList());
    }

    public List<Anexo9Response> anexoIdProyecto(Long proyectoId) {
        Optional<ProyectoPPP> optional = proyectoRepository.findById(proyectoId);
        if (optional.isPresent()) {
            List<Anexo9> anexosList = anexo9Repository.findAllByProyectoPPP(optional.get());
            return anexosList.stream().map(a -> {
                Anexo9Response response = new Anexo9Response();
                response.setId(a.getId());
                response.setNombreProyecto(a.getNombreProyecto());
                response.setNombreEmpresa(a.getNombreEmpresa());
                response.setNombreEstudiante(a.getNombreEstudiante());
                response.setCedulaEstudiante(a.getCedulaEstudiante());
                response.setNombreRepresentanteemp(a.getNombreRepresentanteemp());
                response.setNombreTutoracademico(a.getNombreTutorAcademico());
                response.setNombreTutoremp(a.getNombreTutorAcademico());
                response.setCedulaTutoremp(a.getCedulaTutoremp());
                response.setTotalHoras(a.getTotalHoras());
                response.setDocumento(a.getDocumento());

                List<ActividadesAnexo9Request> list = a.getActividadesAnexo9s().stream().map(ac -> {
                    ActividadesAnexo9Request request = new ActividadesAnexo9Request();
                    request.setId(ac.getId());
                    request.setFecha(ac.getFecha());
                    request.setDescripcionActividad(ac.getDescripcionActividad());
                    request.setLugar(ac.getLugar());
                    request.setHorallegada(ac.getHorallegada());
                    request.setHorasalida(ac.getHorasalida());
                    request.setNumHoras(ac.getNumHoras());
                    return request;
                }).collect(Collectors.toList());
                response.setActividades(list);
                response.setIdProyectoPPP(a.getProyectoPPP().getId());
                return response;
            }).collect(Collectors.toList());
        }

        throw new BadRequestException("No existe el proyecto: " + proyectoId);
    }

    public Anexo9Response listaAnexo9ById(Long id) {
        Optional<Anexo9> a = anexo9Repository.findById(id);
        if (a.isPresent()) {
            Anexo9Response response = new Anexo9Response();
            response.setId(a.get().getId());
            response.setNombreProyecto(a.get().getNombreProyecto());
            response.setNombreEmpresa(a.get().getNombreEmpresa());
            response.setNombreEstudiante(a.get().getNombreEstudiante());
            response.setCedulaEstudiante(a.get().getCedulaEstudiante());
            response.setNombreRepresentanteemp(a.get().getNombreRepresentanteemp());
            response.setNombreTutoracademico(a.get().getNombreTutorAcademico());
            response.setNombreTutoremp(a.get().getNombreTutoremp());
            response.setTotalHoras(a.get().getTotalHoras());
            response.setCedulaTutoremp(a.get().getCedulaTutoremp());
            response.setDocumento(a.get().getDocumento());

            List<ActividadesAnexo9Request> list = a.get().getActividadesAnexo9s().stream().map(ac -> {
                ActividadesAnexo9Request request = new ActividadesAnexo9Request();
                request.setId(ac.getId());
                request.setFecha(ac.getFecha());
                request.setDescripcionActividad(ac.getDescripcionActividad());
                request.setLugar(ac.getLugar());
                request.setHorallegada(ac.getHorallegada());
                request.setHorasalida(ac.getHorasalida());
                request.setNumHoras(ac.getNumHoras());
                return request;
            }).collect(Collectors.toList());
            response.setActividades(list);
            response.setIdProyectoPPP(a.get().getProyectoPPP().getId());
            return response;
        }
        throw new BadRequestException("No existe el anexo con id: " + id);
    }

    public List<Anexo9Response> allByAlumnoCedula(String cedula) {
        List<Anexo9> anexos9 = anexo9Repository.findAllByCedulaEstudiante(cedula);
        return anexos9.stream().map(a -> {
            Anexo9Response response = new Anexo9Response();
            response.setId(a.getId());
            response.setNombreProyecto(a.getNombreProyecto());
            response.setNombreEmpresa(a.getNombreEmpresa());
            response.setNombreEstudiante(a.getNombreEstudiante());
            response.setCedulaEstudiante(a.getCedulaEstudiante());
            response.setNombreRepresentanteemp(a.getNombreRepresentanteemp());
            response.setNombreTutoracademico(a.getNombreTutorAcademico());
            response.setNombreTutoremp(a.getNombreTutoremp());
            response.setTotalHoras(a.getTotalHoras());
            response.setCedulaTutoremp(a.getCedulaTutoremp());
            response.setDocumento(a.getDocumento());

            List<ActividadesAnexo9Request> list = a.getActividadesAnexo9s().stream().map(ac -> {
                ActividadesAnexo9Request request = new ActividadesAnexo9Request();
                request.setId(ac.getId());
                request.setFecha(ac.getFecha());
                request.setDescripcionActividad(ac.getDescripcionActividad());
                request.setLugar(ac.getLugar());
                request.setHorallegada(ac.getHorallegada());
                request.setHorasalida(ac.getHorasalida());
                request.setNumHoras(ac.getNumHoras());
                return request;
            }).collect(Collectors.toList());
            response.setActividades(list);
            response.setIdProyectoPPP(a.getProyectoPPP().getId());
            return response;
        }).collect(Collectors.toList());
    }

    public void deleteActividad(Long idAnexo, Long idActividad) {
        Optional<Anexo9> optionalAnexo9 = anexo9Repository.findById(idAnexo);
        if (optionalAnexo9.isPresent()) {
            ActividadesAnexo9 actividadesAnexo9 = optionalAnexo9.get().getActividadesAnexo9s()
                    .stream()
                    .filter(a -> Objects.equals(a.getId(), idActividad))
                    .findFirst()
                    .orElseThrow(() -> new ResponseNotFoundException("Actividad Anexo9", "id", idActividad + ""));
            try {
                actividadesAnexo9Repository.delete(actividadesAnexo9);
                return;
            } catch (Exception e) {
                throw new BadRequestException("Error al eliminar actividad anexo 9 con id: " + idActividad);
            }
        }
        throw new ResponseNotFoundException("Anexo9", "id", idAnexo + "");
    }

    public void updateActividad(Long idAnexo, ActividadesAnexo9Request request) {
        Optional<Anexo9> optionalAnexo9 = anexo9Repository.findById(idAnexo);
        if (optionalAnexo9.isPresent()) {
            ActividadesAnexo9 actividadesAnexo9 = optionalAnexo9.get().getActividadesAnexo9s()
                    .stream()
                    .filter(a -> Objects.equals(a.getId(), request.getId()))
                    .findFirst()
                    .orElseThrow(() -> new ResponseNotFoundException("Actividad Anexo9", "id", request.getId() + ""));
            try {
                actividadesAnexo9.setDescripcionActividad(request.getDescripcionActividad());
                actividadesAnexo9.setFecha(request.getFecha());
                actividadesAnexo9.setLugar(request.getLugar());
                actividadesAnexo9.setHorallegada(request.getHorallegada());
                actividadesAnexo9.setHorasalida(request.getHorasalida());
                actividadesAnexo9.setNumHoras(request.getNumHoras());
                actividadesAnexo9Repository.save(actividadesAnexo9);
                return;
            } catch (Exception e) {
                throw new BadRequestException("Error al actualizar actividad anexo 9 con id: " + request.getId());
            }
        }
        throw new ResponseNotFoundException("Anexo9", "id", idAnexo + "");
    }
}