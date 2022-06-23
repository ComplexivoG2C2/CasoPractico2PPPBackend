package com.tecazuay.complexivog2c2.service.Anexos.anexo1214y15;

import com.tecazuay.complexivog2c2.dto.anexos.anexo1214y15.Anexo14Request;
import com.tecazuay.complexivog2c2.dto.anexos.anexo1214y15.Anexo14Response;
import com.tecazuay.complexivog2c2.dto.anexos.anexo1214y15.Anexo14TutorAcaRequest;
import com.tecazuay.complexivog2c2.exception.BadRequestException;
import com.tecazuay.complexivog2c2.exception.ResponseNotFoundException;
import com.tecazuay.complexivog2c2.model.Primary.Anexos.anexo1214y15.Anexo14;
import com.tecazuay.complexivog2c2.model.Primary.Anexos.anexo1214y15.ItemsAnexo14Tutoracademcio;
import com.tecazuay.complexivog2c2.model.Primary.empresa.Empresa;
import com.tecazuay.complexivog2c2.model.Primary.solicitudproyecto.ProyectoPPP;
import com.tecazuay.complexivog2c2.repository.Primary.Anexos.anexo1214y15.Anexo14Repository;
import com.tecazuay.complexivog2c2.repository.Primary.Anexos.anexo1214y15.TutorAcademico14Repository;
import com.tecazuay.complexivog2c2.repository.Primary.empresa.EmpresaRepository;
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
public class Anexo14Service {

    @Autowired
    private ProyectoRepository proyectoRepository;
    @Autowired
    private Anexo14Repository anexo14Repository;
    @Autowired
    private EmpresaRepository empresaRepository;
    @Autowired
    private TutorAcademico14Repository tutorAcademico14Repository;

    public boolean save(Anexo14Request request) {

        Anexo14 a = new Anexo14();
        Optional<ProyectoPPP> optional = proyectoRepository.findById(request.getIdProyecto());
        if (optional.isPresent()) {
            if (!optional.get().isEstado())
                throw new BadRequestException("proceso de solicitud terminado");

            Optional<Empresa> entidad = empresaRepository.findById(optional.get().getEmpresa());
            //entidad?
            if (entidad.isPresent()) {
                a.setProyectoPPP(proyectoRepository.findById(request.getIdProyecto()).orElseThrow(() ->
                        new BadRequestException("No existe la solicitud de empresa con id:" + request.getIdProyecto())
                ));
                a.setProyectoPPP(optional.get());
                a.setNombresEstudiante(request.getNombresEstudiante());
                a.setCedulaEstudiante(request.getCedulaEstudiante());
                a.setCarrera(request.getCarrera());
                a.setFechaInicio(request.getFechaInicio());
                a.setFechaFinaliza(request.getFechaFinaliza());
                a.setFechaEvaluacion(request.getFechaEvaluacion());
                a.setTotalHoras(request.getTotalHoras());
                a.setResultadoAnexo14(request.getResultadoAnexo14());
                a.setPromedio(request.getPromedio());
                a.setDocumento(request.getDocumento());

                a.setNombretutoracademico(request.getNombretutoracademico());
                a.setCedulatutoracademico(request.getCedulatutoracademico());
                a.setSiglascarrera(request.getSiglascarrera());
                a.setEmpresa(request.getEmpresa());
                a.setTutoracademicoPuntaje(request.getTutoracademicoPuntaje());

                List<ItemsAnexo14Tutoracademcio> list = new ArrayList<>();
                request.getTutoraca().stream().forEach(da -> {
                    ItemsAnexo14Tutoracademcio a14 = new ItemsAnexo14Tutoracademcio ();
                    a14.setTutoracaItem1(da.getTutoracaItem1());
                    a14.setTutoracaItem2(da.getTutoracaItem2());
                    list.add(a14);
                });

                try {
                    saveTutorA(list, anexo14Repository.save(a));
                    return true;
                } catch (Exception e) {
                    throw new BadRequestException("No se guardo el anexo 14" + e);

                }

            } else {
                throw new BadRequestException("No existe una empresa con ID:" + optional.get().getEmpresa());
            }


        } else {
            throw new BadRequestException("No existe un proyecto con ID:" + request.getIdProyecto());
        }
    }


    private void saveTutorA(List<ItemsAnexo14Tutoracademcio> list, Anexo14 anexo14) {
        list.stream().forEach(a -> {
            a.setAnexo14(anexo14);
            tutorAcademico14Repository.save(a);
        });
    }

    // Listar todos, listar por id anexo y listar por id proyecto,

    @Transactional
    public List<Anexo14Response> listAll() {
        return anexo14Repository.findAll()
                .stream().map(a -> {
                    Anexo14Response response = new Anexo14Response();
                    response.setId(a.getId());
                    response.setNombresEstudiante(a.getNombresEstudiante());
                    response.setCedulaEstudiante(a.getCedulaEstudiante());
                    response.setCarrera(a.getCarrera());
                    response.setFechaInicio(a.getFechaInicio());
                    response.setFechaFinaliza(a.getFechaFinaliza());
                    response.setFechaEvaluacion(a.getFechaEvaluacion());
                    response.setTotalHoras(a.getTotalHoras());
                    response.setResultadoAnexo14(a.getResultadoAnexo14());
                    response.setPromedio(a.getPromedio());

                    response.setNombretutoracademico(a.getNombretutoracademico());
                    response.setCedulatutoracademico(a.getCedulatutoracademico());
                    response.setSiglascarrera(a.getSiglascarrera());
                    response.setEmpresa(a.getEmpresa());
                    response.setTutoracademicoPuntaje(a.getTutoracademicoPuntaje());

                    response.setIdProyecto(a.getProyectoPPP().getId());
                    response.setDocumento(a.getDocumento());

                    List<Anexo14TutorAcaRequest> list = a.getItemsTutorAcademico().stream().map(ap -> {
                        Anexo14TutorAcaRequest request = new Anexo14TutorAcaRequest();
                        request.setId(ap.getId());
                        request.setTutoracaItem1(ap.getTutoracaItem1());
                        request.setTutoracaItem2(ap.getTutoracaItem2());
                        return request;
                    }).collect(Collectors.toList());
                    response.setTutoraca(list);

                    return response;
                }).collect(Collectors.toList());
    }

    public Anexo14Response listAnexo14ById(Long id) {
        Optional<Anexo14> a = anexo14Repository.findById(id);
        if (a.isPresent()) {
            Anexo14Response response = new Anexo14Response();
            response.setId(a.get().getId());
            response.setNombresEstudiante(a.get().getNombresEstudiante());
            response.setCedulaEstudiante(a.get().getCedulaEstudiante());
            response.setCarrera(a.get().getCarrera());
            response.setFechaInicio(a.get().getFechaInicio());
            response.setFechaFinaliza(a.get().getFechaFinaliza());
            response.setFechaEvaluacion(a.get().getFechaEvaluacion());
            response.setTotalHoras(a.get().getTotalHoras());
            response.setResultadoAnexo14(a.get().getResultadoAnexo14());
            response.setPromedio(a.get().getPromedio());

            response.setNombretutoracademico(a.get().getNombretutoracademico());
            response.setCedulatutoracademico(a.get().getCedulatutoracademico());
            response.setSiglascarrera(a.get().getSiglascarrera());
            response.setEmpresa(a.get().getEmpresa());
            response.setTutoracademicoPuntaje(a.get().getTutoracademicoPuntaje());

            response.setIdProyecto(a.get().getProyectoPPP().getId());
            response.setDocumento(a.get().getDocumento());
            List<Anexo14TutorAcaRequest> list = a.get().getItemsTutorAcademico().stream().map(ap -> {
                Anexo14TutorAcaRequest request = new Anexo14TutorAcaRequest();
                request.setId(ap.getId());
                request.setTutoracaItem1(ap.getTutoracaItem1());
                request.setTutoracaItem2(ap.getTutoracaItem2());

                return request;
            }).collect(Collectors.toList());
            response.setTutoraca(list);


            return response;
        }
        throw new BadRequestException("No existe el anexo con id: " + id);
    }

    public List<Anexo14Response> listAnexo14ByProyectoPPP(Long pryectoId) {
        Optional<ProyectoPPP> optional = proyectoRepository.findById(pryectoId);
        if (optional.isPresent()) {
            List<Anexo14> b = anexo14Repository.findByProyectoPPP(optional.get());
            return b.stream().map(a -> {
                Anexo14Response response = new Anexo14Response();
                response.setId(a.getId());
                response.setNombresEstudiante(a.getNombresEstudiante());
                response.setCedulaEstudiante(a.getCedulaEstudiante());
                response.setCarrera(a.getCarrera());
                response.setFechaInicio(a.getFechaInicio());
                response.setFechaFinaliza(a.getFechaFinaliza());
                response.setFechaEvaluacion(a.getFechaEvaluacion());
                response.setTotalHoras(a.getTotalHoras());
                response.setPromedio(a.getPromedio());



                response.setNombretutoracademico(a.getNombretutoracademico());
                response.setCedulatutoracademico(a.getCedulatutoracademico());
                response.setSiglascarrera(a.getSiglascarrera());
                response.setEmpresa(a.getEmpresa());
                response.setTutoracademicoPuntaje(a.getTutoracademicoPuntaje());

                response.setIdProyecto(a.getProyectoPPP().getId());
                response.setDocumento(a.getDocumento());


                List<Anexo14TutorAcaRequest> list = a.getItemsTutorAcademico().stream().map(ap -> {
                    Anexo14TutorAcaRequest request = new Anexo14TutorAcaRequest();
                    request.setId(ap.getId());

                    return request;
                }).collect(Collectors.toList());
                response.setTutoraca(list);
                return response;
            }).collect(Collectors.toList());

        }
        throw new BadRequestException("No existe el proyecto " + pryectoId);
    }


    //Actualizar

    public boolean update(Anexo14Request anexo14Request) {
        Optional<Anexo14> a = anexo14Repository.findById(anexo14Request.getId());
        if (a.isPresent()) {
            Optional<ProyectoPPP> optionalp = proyectoRepository.findById(anexo14Request.getIdProyecto());
            if (optionalp.isPresent()) {
                if (!optionalp.get().isEstado())
                    throw new BadRequestException("El proyecto ha finalizado, no es posible modificar sus datos");

                a.get().setNombresEstudiante(anexo14Request.getNombresEstudiante());
                a.get().setCedulaEstudiante(anexo14Request.getCedulaEstudiante());
                a.get().setCarrera(anexo14Request.getCarrera());
                a.get().setFechaInicio(anexo14Request.getFechaInicio());
                a.get().setFechaFinaliza(anexo14Request.getFechaFinaliza());
                a.get().setFechaEvaluacion(anexo14Request.getFechaEvaluacion());
                a.get().setTotalHoras(anexo14Request.getTotalHoras());
                a.get().setResultadoAnexo14(anexo14Request.getResultadoAnexo14());
                a.get().setPromedio(anexo14Request.getPromedio());

                a.get().setNombretutoracademico(anexo14Request.getNombretutoracademico());
                a.get().setCedulatutoracademico(anexo14Request.getCedulatutoracademico());
                a.get().setSiglascarrera(anexo14Request.getSiglascarrera());
                a.get().setEmpresa(anexo14Request.getEmpresa());
                a.get().setTutoracademicoPuntaje(anexo14Request.getTutoracademicoPuntaje());

                a.get().setDocumento(anexo14Request.getDocumento());


                updateTutorAca(anexo14Request.getId(), anexo14Request.getTutoraca());

                try {
                    Anexo14 a11 = anexo14Repository.save(a.get());

                    return true;
                } catch (Exception ex) {
                    throw new BadRequestException("No se actualizo el anexo 11" + ex);
                }
            } else {
                throw new BadRequestException("No existe un proyecto con ID:" + anexo14Request.getIdProyecto());

            }
        }
        throw new ResponseNotFoundException("Anexo14", "ID:", "" + anexo14Request.getId());
    }

    //Eliminar
    @Transactional
    public void deleteById(Long id) {
        Optional<Anexo14> optional = anexo14Repository.findById(id);
        if (optional.isEmpty()) {
            throw new BadRequestException("El anexo 14 con el id " + id + ", no existe");
        }
        if (!optional.get().getProyectoPPP().isEstado())
            throw new BadRequestException("El proceso a finalizado");

        optional.get().getItemsTutorAcademico()
                .forEach(d -> deleteTutoracaById(d.getId()));


        anexo14Repository.deleteById(id);
    }


    public void deleteTutoracaById(Long id) {
        Optional<ItemsAnexo14Tutoracademcio> optional = tutorAcademico14Repository.findById(id);
        if (optional.isEmpty()) {
            throw new BadRequestException("Las actividades del tutor academico con: " + id + ", no existen");
        }
        tutorAcademico14Repository.deleteById(id);
    }

    @Transactional
    public void updateTutorAca(Long id, List<Anexo14TutorAcaRequest> request) {
        Optional<Anexo14> a = anexo14Repository.findById(id);
        if (a.isPresent()) {

            List<ItemsAnexo14Tutoracademcio> itemsBase = a.get().getItemsTutorAcademico();
            request.forEach(i -> {
                ItemsAnexo14Tutoracademcio req;
                if (i.getId() == null) {
                    req = new ItemsAnexo14Tutoracademcio();
                } else {
                    Optional<ItemsAnexo14Tutoracademcio> op = itemsBase.stream().filter(item -> Objects.equals(item.getId(), i.getId())).findFirst();
                    if (op.isEmpty()) throw new BadRequestException("El item con id: " + i.getId() + ", no exiset");
                    req = op.get();
                }
                req.setTutoracaItem1(i.getTutoracaItem1());
                req.setTutoracaItem2(i.getTutoracaItem2());
                req.setAnexo14(a.get());
                tutorAcademico14Repository.save(req);
            });

        } else {
            throw new BadRequestException("El anexo con id: " + id + ", no existe");
        }
    }


}
