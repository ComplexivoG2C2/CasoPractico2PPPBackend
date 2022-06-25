package com.tecazuay.complexivog2c2.service.Anexos;


import com.tecazuay.complexivog2c2.dto.anexos.*;
import com.tecazuay.complexivog2c2.exception.BadRequestException;
import com.tecazuay.complexivog2c2.exception.ResponseNotFoundException;
import com.tecazuay.complexivog2c2.model.Primary.Anexos.Anexo11;
import com.tecazuay.complexivog2c2.model.Primary.Anexos.EstudiantesVisita;
import com.tecazuay.complexivog2c2.model.Primary.Anexos.ListVisitaAnexo11;
import com.tecazuay.complexivog2c2.model.Primary.desigaciones.TutorAcademicoDelegados;
import com.tecazuay.complexivog2c2.model.Primary.solicitudproyecto.ProyectoPPP;
import com.tecazuay.complexivog2c2.repository.Primary.Anexos.Anexo11Repository;
import com.tecazuay.complexivog2c2.repository.Primary.Anexos.EstudiantesVisitaRepository;
import com.tecazuay.complexivog2c2.repository.Primary.Anexos.ListVisitaAnexo11Repository;
import com.tecazuay.complexivog2c2.repository.Primary.designaciones.TutorAcademicoRepository;
import com.tecazuay.complexivog2c2.repository.Primary.solicitudproyecto.ProyectoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
    @Service
    @Slf4j
    public class Anexo11Service {

    private final Anexo11Repository anexo11Repository;
    private final ProyectoRepository proyectoRepository;
    private final ListVisitaAnexo11Repository informeRepository;
    private final EstudiantesVisitaRepository estudianteA11Repository;
    private final TutorAcademicoRepository tutorAcademicoRepository;

        @Transactional
        public void save(Anexo11Request request) {
            Optional<ProyectoPPP> optionalProyecto = proyectoRepository.findById(request.getProyectoId());
            if (optionalProyecto.isPresent()) {
                if (!optionalProyecto.get().isEstado())
                    throw new BadRequestException("El solicitud a fonalizado");

                Optional<TutorAcademicoDelegados> optionalDocente = tutorAcademicoRepository.findByCedula(request.getCedulaDirectorDocenteApoyo());
                  if (optionalDocente.isPresent()) {
                    Anexo11 anexo11;
                    if (request.getId() == null) {
                        anexo11 = new Anexo11();
                    } else {
                        anexo11 = anexo11Repository
                                .findById(request.getId())
                                .orElseThrow(() -> new BadRequestException("El anexo11 con id: " + request.getId() + ", no existe"));
                    }
                    anexo11.setNombreDirectorDocenteApoyo(request.getNombreDirectorDocenteApoyo());
                    anexo11.setCedulaDirectorDocenteApoyo(request.getCedulaDirectorDocenteApoyo());
                    anexo11.setPeriodoAcademicon(request.getPeriodoAcademicon());
                    anexo11.setEmpresa(request.getEmpresa());
                      anexo11.setNombreest(request.getNombreest());
                      anexo11.setCedulaest(request.getCedulaest());
                      anexo11.setCarrera(request.getCarrera());
                      anexo11.setSiglascarrera(request.getSiglascarrera());
                      anexo11.setNombretutoremp(request.getNombretutoremp());
                      anexo11.setCedulaetutoremp(request.getCedulaetutoremp());
                    anexo11.setRepresentanteLegal(request.getRepresentanteLegal());
                    anexo11.setCiclo(request.getCiclo());
                    anexo11.setObservaciones(request.getObservaciones());
                    anexo11.setProyectoPPP(optionalProyecto.get());
                    anexo11.setNum_proceso(request.getNum_proceso());
                    try {
                        Anexo11 saved = anexo11Repository.save(anexo11);
                        //saveInformesVisitas(request.getInformes(), saved);
                        updateInformeVisitas(request.getInformes(), saved.getId());
                        //saveEstudiantes(request.getEstudiantesVisitas(), saved);
                        updateEstudiantes(request.getEstudiantesVisitas(), saved.getId());
                        return;
                    } catch (Exception e) {
                        log.error("Error al guardar anexo13");
                        e.printStackTrace();
                        throw new BadRequestException("Error al guardar el anexo 13: " + e.getMessage());
                    }
                }
                throw new ResponseNotFoundException("Tutor academico", "cédula", request.getCedulaDirectorDocenteApoyo());
            }
        }

        private void updateEstudiantes(List<EstudiantesVisitaRequest> list, Long idanexo13) {
            Optional<Anexo11> optional = anexo11Repository.findById(idanexo13);
            if (optional.isEmpty()) {
                throw new BadRequestException("Anexo 11 con id: " + idanexo13 + ", no existe");
            }
            List<EstudiantesVisita> proyecto = optional.get().getEstudiantesVisita();
            if (proyecto != null) {
                proyecto.forEach(r -> {
                    Optional<EstudiantesVisitaRequest> exists = list
                            .stream()
                            .filter(req -> req.getCedula().equalsIgnoreCase(r.getCedula()))
                            .findAny();
                    if (exists.isEmpty()) {
                        try {
                            estudianteA11Repository.delete(r);
                            log.info("delete: ESTUDIANTE VISITA");
                        } catch (Exception e) {
                            throw new BadRequestException("Error eliminar estudiantes anexo 13, cédula: " + r.getCedula());
                        }
                    }
                });
                list.forEach(request -> {
                    String descripcion = request.getCedula();
                    Optional<EstudiantesVisita> exists = proyecto
                            .stream()
                            .filter(r -> r.getCedula().equalsIgnoreCase(descripcion))
                            .findAny();
                    if (exists.isEmpty()) {
                        EstudiantesVisita save = new EstudiantesVisita();
                        save.setAnexo11(optional.get());
                        save.setCedula(request.getCedula());
                        save.setNombre(request.getNombre());
                        try {
                            estudianteA11Repository.save(save);
                        } catch (Exception e) {
                            throw new BadRequestException("Error save estudiantes anexo 11, cédula: " + request.getCedula());
                        }
                    }
                });
            } else {
                list.forEach(request -> {
                    EstudiantesVisita save = new EstudiantesVisita();
                    save.setAnexo11(optional.get());
                    save.setCedula(request.getCedula());
                    save.setNombre(request.getNombre());
                    try {
                        estudianteA11Repository.save(save);
                    } catch (Exception e) {
                        throw new BadRequestException("Error save estudiantes anexo 13, cédula: " + request.getCedula());
                    }
                });
            }


        }

        private void updateInformeVisitas(List<ListVisitaRequest> list, Long  idanexo11) {
            Optional<Anexo11> optional = anexo11Repository.findById(idanexo11);
            if (optional.isEmpty()) {
                throw new BadRequestException("Anexo 11 con id: " + idanexo11 + ", no existe");
            }
            try {
                if (optional.get().getInformes() != null)
                    informeRepository.deleteAll(optional.get().getInformes());
            } catch (Exception e) {
                e.printStackTrace();
                throw new BadRequestException("Error deleteAll informe visitas anexo11");
            }
            list.stream().forEach(r -> {
                ListVisitaAnexo11 informe = new ListVisitaAnexo11();
                informe.setObservaciones(r.getObservaciones());
                informe.setActividades(r.getActividades());
                informe.setAsunto(r.getAsunto());
                informe.setFecha(r.getFecha());
                informe.setHoraInicio(r.getHoraInicio());
                informe.setHoraFin(r.getHoraFin());
                informe.setAnexo11(optional.get());
                try {
                    informeRepository.save(informe);
                } catch (Exception e) {
                    log.error("Error al guardar informe de visitas");
                }
            });
        }

        private void saveInformesVisitas(List<ListVisitaRequest> list, Anexo11 anexo11) {
            list.stream().forEach(r -> {
                ListVisitaAnexo11 informe = new ListVisitaAnexo11();
                informe.setObservaciones(r.getObservaciones());
                informe.setActividades(r.getActividades());
                informe.setAsunto(r.getAsunto());
                informe.setFecha(r.getFecha());
                informe.setHoraInicio(r.getHoraInicio());
                informe.setHoraFin(r.getHoraFin());
                informe.setAnexo11(anexo11);

                try {
                    informeRepository.save(informe);
                } catch (Exception e) {
                    log.error("Error al guardar informe de visitas");
                }
            });
        }

        private void saveEstudiantes(List<EstudiantesVisitaRequest> list, Anexo11 anexo11) {
            list.stream().forEach(r -> {
                EstudiantesVisita e = new EstudiantesVisita();
                e.setCedula(r.getCedula());
                e.setNombre(r.getNombre());
                e.setAnexo11(anexo11);
                try {
                    estudianteA11Repository.save(e);
                } catch (Exception ex) {
                    log.error("Error al guardar estudiante anexo11");
                }
            });
        }

        public void update(Anexo11Request request) {
            Optional<Anexo11> optionalAnexo = anexo11Repository.findById(request.getId());
            if (optionalAnexo.isPresent()) {
                if (!optionalAnexo.get().getProyectoPPP().isEstado())
                    throw new BadRequestException("El proyecto ha finalizado, no es posible modificar sus datos");

                Anexo11 anexo11 = optionalAnexo.get();
                anexo11.setObservaciones(request.getObservaciones());
                anexo11.setDocumento(request.getDocumento());
                anexo11.setNombreDirectorDocenteApoyo(request.getNombreDirectorDocenteApoyo());
                anexo11.setCedulaDirectorDocenteApoyo(request.getCedulaDirectorDocenteApoyo());
                anexo11.setNombreest(request.getNombreest());
                anexo11.setCedulaest(request.getCedulaest());
                anexo11.setCarrera(request.getCarrera());
                anexo11.setNombretutoremp(request.getNombretutoremp());
                anexo11.setCedulaetutoremp(request.getCedulaetutoremp());
                anexo11.setSiglascarrera(request.getSiglascarrera());
                anexo11.setNum_proceso(request.getNum_proceso());
                try {
                    Anexo11 updated = anexo11Repository.save(anexo11);
                    saveEstudiantes(request.getEstudiantesVisitas(), updated);
                    saveInformesVisitas(request.getInformes(), updated);
                } catch (Exception e) {
                    log.error("Error al actualizar anexo11 con id: {}, ex: {}", request.getId(), e.getMessage());
                    throw new BadRequestException("Error al actualizar anexo11 con id: " + request.getId());
                }
            } else {
                throw new BadRequestException("Anexo 11 con id:" + request.getId() + ", no existe");
            }
        }

        @Transactional
        public void updateSave(Anexo11Request request) {
            Optional<Anexo11> anexo11Recuperar = anexo11Repository.findById(request.getId());
            if (anexo11Recuperar.isPresent()) {
                if (!anexo11Recuperar.get().getProyectoPPP().isEstado())
                    throw new BadRequestException("El proyecto ha finalizado, no es posible modificar sus datos");

                Anexo11 anexo11 = new Anexo11();
                anexo11.setNombreDirectorDocenteApoyo(request.getNombreDirectorDocenteApoyo());
                anexo11.setCedulaDirectorDocenteApoyo(request.getCedulaDirectorDocenteApoyo());
                anexo11.setNombreest(request.getNombreest());
                anexo11.setCedulaest(request.getCedulaest());
                anexo11.setCarrera(request.getCarrera());
                anexo11.setSiglascarrera(request.getSiglascarrera());
                anexo11.setNombretutoremp(request.getNombretutoremp());
                anexo11.setCedulaetutoremp(request.getCedulaetutoremp());
                anexo11.setPeriodoAcademicon(anexo11Recuperar.get().getPeriodoAcademicon());
                anexo11.setEmpresa(anexo11Recuperar.get().getEmpresa());
                anexo11.setRepresentanteLegal(anexo11Recuperar.get().getRepresentanteLegal());
                anexo11.setCiclo(anexo11Recuperar.get().getCiclo());
                anexo11.setObservaciones(request.getObservaciones());
                anexo11.setProyectoPPP(anexo11Recuperar.get().getProyectoPPP());
                anexo11.setNum_proceso(anexo11Recuperar.get().getNum_proceso());

                try {
                    Anexo11 saved = anexo11Repository.save(anexo11);
                    saveInformesVisitas(request.getInformes(), saved);
                    System.out.println("ANEXO:" + anexo11.getId());

                    System.out.println("NUMERO: " + listEstudiantesByIdPro(anexo11.getId()).size());
                    saveEstudiantes(listEstudiantesByIdPro(request.getId()), saved);
                    return;
                } catch (Exception e) {
                    log.error("Error al guardar anexo13");
                }
            }
            throw new ResponseNotFoundException("Anexo11", "id", request.getId() + "");
        }


        @Transactional
        public List<EstudiantesVisitaRequest> listEstudiantesByIdPro(Long id) {
            Optional<Anexo11> optional = anexo11Repository.findById(id);
            if (optional.isPresent()) {
                return estudianteA11Repository.findAllByAnexo11(optional.get())
                        .stream()
                        .map(a -> {
                            EstudiantesVisitaRequest response = new EstudiantesVisitaRequest();
                            response.setId(a.getId());
                            response.setCedula(a.getCedula());
                            response.setNombre(a.getNombre());
                            return response;
                        }).collect(Collectors.toList());
            }
            throw new ResponseNotFoundException("Anexo13", "id", id + "");
        }

        public List<Anexo11Response> listByProyecto(Long proyectoId) {
            Optional<ProyectoPPP> optional = proyectoRepository.findById(proyectoId);
            if (optional.isPresent()) {
                return anexo11Repository.findAllByProyectoPPP(optional.get())
                        .stream()
                        .map(a -> {
                            Anexo11Response response = new Anexo11Response();
                            response.setId(a.getId());
                            response.setNombreDirectorDocenteApoyo(a.getNombreDirectorDocenteApoyo());
                            response.setCedulaDirectorDocenteApoyo(a.getCedulaDirectorDocenteApoyo());
                            response.setCiclo(a.getCiclo());
                            response.setEmpresa(a.getEmpresa());
                            response.setNombreest(a.getNombreest());
                            response.setCedulaest(a.getCedulaest());
                            response.setNombretutoremp(a.getNombretutoremp());
                          response.setCedulaetutoremp(a.getCedulaetutoremp());
                            response.setCarrera(a.getCarrera());
                            response.setSiglascarrera(a.getSiglascarrera());
                            response.setRepresentanteLegal(a.getRepresentanteLegal());
                            response.setObservaciones(a.getObservaciones());
                            response.setPeriodoAcademicon(a.getPeriodoAcademicon());
                            response.setProyectoId(a.getProyectoPPP().getId());
                            response.setNum_proceso(a.getNum_proceso());
                            List<EstudiantesVisitaResponse> estudiantes = a.getEstudiantesVisita().stream().map(e -> {
                                EstudiantesVisitaResponse er = new EstudiantesVisitaResponse();
                                er.setCedula(e.getCedula());
                                er.setNombre(e.getNombre());
                                er.setId(e.getId());
                                return er;
                            }).collect(Collectors.toList());

                            response.setEstudiantesVisitas(estudiantes);
                            List<ListVisitaResponse> informes = a.getInformes().stream().map(i -> {
                               ListVisitaResponse ir = new ListVisitaResponse();
                                ir.setActividades(i.getActividades());
                                ir.setAsunto(i.getAsunto());
                                ir.setId(i.getId());
                                ir.setObservaciones(i.getObservaciones());
                                ir.setFecha(i.getFecha());
                                ir.setHoraInicio(i.getHoraInicio());
                                ir.setHoraFin(i.getHoraFin());
                                return ir;
                            }).collect(Collectors.toList());
                            response.setInformes(informes);
                            response.setDocumento(a.getDocumento());
                            return response;
                        }).collect(Collectors.toList());
            }
            throw new ResponseNotFoundException("ProyectoPPP", "id", proyectoId + "");
        }

        public void deleteById(Long id) {
            Optional<Anexo11> optional = anexo11Repository.findById(id);
            if (optional.isEmpty()) {
                throw new BadRequestException("El anexo 11 con el id " + id + ", no existe");
            }
            if (!optional.get().getProyectoPPP().isEstado())
                throw new BadRequestException("El proyecto ha finalizado, no es posible modificar sus datos");

            anexo11Repository.deleteById(id);
        }




    @Transactional
    public List<Anexo11Response> listAll() {
        return anexo11Repository.findAll().stream().map(a -> {
            Anexo11Response response = new Anexo11Response();

            response.setId(a.getId());
            response.setNombreDirectorDocenteApoyo(a.getNombreDirectorDocenteApoyo());
            response.setCedulaDirectorDocenteApoyo(a.getCedulaDirectorDocenteApoyo());
            response.setCiclo(a.getCiclo());
            response.setEmpresa(a.getEmpresa());
            response.setNombreest(a.getNombreest());
            response.setCedulaest(a.getCedulaest());
            response.setNombretutoremp(a.getNombretutoremp());
            response.setCedulaetutoremp(a.getCedulaetutoremp());
            response.setCarrera(a.getCarrera());
            response.setSiglascarrera(a.getSiglascarrera());
            response.setRepresentanteLegal(a.getRepresentanteLegal());
            response.setObservaciones(a.getObservaciones());
            response.setPeriodoAcademicon(a.getPeriodoAcademicon());
            response.setProyectoId(a.getProyectoPPP().getId());
            response.setNum_proceso(a.getNum_proceso());

            List<EstudiantesVisitaResponse> estudiantes = a.getEstudiantesVisita().stream().map(e -> {
                EstudiantesVisitaResponse request = new EstudiantesVisitaResponse();
                request.setCedula(e.getCedula());
                request.setNombre(e.getNombre());
                request.setId(e.getId());
                return request;
            }).collect(Collectors.toList());
            response.setEstudiantesVisitas(estudiantes);


            List<ListVisitaResponse> informes = a.getInformes().stream().map(i -> {
                ListVisitaResponse ir = new ListVisitaResponse();
                ir.setActividades(i.getActividades());
                ir.setAsunto(i.getAsunto());
                ir.setId(i.getId());
                ir.setObservaciones(i.getObservaciones());
                ir.setFecha(i.getFecha());
                ir.setHoraInicio(i.getHoraInicio());
                ir.setHoraFin(i.getHoraFin());
                return ir;
            }).collect(Collectors.toList());
            response.setInformes(informes);

            response.setProyectoId(a.getProyectoPPP().getId());
            return response;
        }).collect(Collectors.toList());
    }


}
