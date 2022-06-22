package com.tecazuay.complexivog2c2.service.Anexos.anexo1214y15;

import com.tecazuay.complexivog2c2.dto.anexos.anexo1214y15.Anexo12Request;
import com.tecazuay.complexivog2c2.dto.anexos.anexo1214y15.Anexo12Response;
import com.tecazuay.complexivog2c2.dto.anexos.anexo1214y15.Anexo12TutorempRequest;
import com.tecazuay.complexivog2c2.exception.BadRequestException;
import com.tecazuay.complexivog2c2.exception.ResponseNotFoundException;
import com.tecazuay.complexivog2c2.model.Primary.Anexos.anexo1214y15.Anexo12;
import com.tecazuay.complexivog2c2.model.Primary.Anexos.anexo1214y15.ItemsAnexo12Tutoremp;
import com.tecazuay.complexivog2c2.model.Primary.empresa.Empresa;
import com.tecazuay.complexivog2c2.model.Primary.solicitudproyecto.ProyectoPPP;
import com.tecazuay.complexivog2c2.repository.Primary.Anexos.anexo1214y15.Anexo12Repository;
import com.tecazuay.complexivog2c2.repository.Primary.Anexos.anexo1214y15.TutorEmp12Repository;
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
public class Anexo12Service {

    @Autowired
    private ProyectoRepository proyectoRepository;
    @Autowired
    private Anexo12Repository anexo12Repository;
    @Autowired
    private EmpresaRepository empresaRepository;
    @Autowired
    private TutorEmp12Repository tutorEmp12Repository;

    public boolean save(Anexo12Request request) {

        Anexo12 a = new Anexo12();
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
                a.setResultadoAnexo12(request.getResultadoAnexo12());
                a.setPromedio(request.getPromedio());
                a.setDocumento(request.getDocumento());

                a.setNombretutoremp(request.getNombretutoremp());
                a.setCedulatutoremp(request.getCedulatutoremp());
                a.setSiglascarrera(request.getSiglascarrera());
                a.setEmpresa(request.getEmpresa());
                a.setTutorempPuntaje(request.getTutorempPuntaje());

                List<ItemsAnexo12Tutoremp> list = new ArrayList<>();
                request.getTutoremp().stream().forEach(da -> {
                    ItemsAnexo12Tutoremp a12 = new  ItemsAnexo12Tutoremp ();
                    a12.setTutorempItem1(da.getTutorempItem1());
                    a12.setTutorempItem2(da.getTutorempItem2());
                    list.add(a12);
                });

                try {
                    saveTutorA(list, anexo12Repository.save(a));
                    return true;
                } catch (Exception e) {
                    throw new BadRequestException("No se guardo el anexo 12" + e);

                }

            } else {
                throw new BadRequestException("No existe una empresa con ID:" + optional.get().getEmpresa());
            }


        } else {
            throw new BadRequestException("No existe un proyecto con ID:" + request.getIdProyecto());
        }
    }


    private void saveTutorA(List<ItemsAnexo12Tutoremp> list, Anexo12 anexo12) {
        list.stream().forEach(a -> {
            a.setAnexo12(anexo12);
            tutorEmp12Repository.save(a);
        });
    }

    // Listar todos, listar por id anexo y listar por id proyecto,

    @Transactional
    public List<Anexo12Response> listAll() {
        return anexo12Repository.findAll()
                .stream().map(a -> {
                    Anexo12Response response = new Anexo12Response();
                    response.setId(a.getId());
                    response.setNombresEstudiante(a.getNombresEstudiante());
                    response.setCedulaEstudiante(a.getCedulaEstudiante());
                    response.setCarrera(a.getCarrera());
                    response.setFechaInicio(a.getFechaInicio());
                    response.setFechaFinaliza(a.getFechaFinaliza());
                    response.setFechaEvaluacion(a.getFechaEvaluacion());
                    response.setTotalHoras(a.getTotalHoras());
                    response.setResultadoAnexo12(a.getResultadoAnexo12());
                    response.setPromedio(a.getPromedio());

                    response.setNombretutoremp(a.getNombretutoremp());
                    response.setCedulatutoremp(a.getCedulatutoremp());
                    response.setSiglascarrera(a.getSiglascarrera());
                    response.setEmpresa(a.getEmpresa());
                    response.setTutorempPuntaje(a.getTutorempPuntaje());

                    response.setIdProyecto(a.getProyectoPPP().getId());
                    response.setDocumento(a.getDocumento());

                    List<Anexo12TutorempRequest> list = a.getItemsTutor().stream().map(ap -> {
                        Anexo12TutorempRequest request = new Anexo12TutorempRequest();
                        request.setId(ap.getId());
                        request.setTutorempItem1(ap.getTutorempItem1());
                        request.setTutorempItem2(ap.getTutorempItem2());
                        return request;
                    }).collect(Collectors.toList());
                    response.setTutoremp(list);

                    return response;
                }).collect(Collectors.toList());
    }

    public Anexo12Response listAnexo12ById(Long id) {
        Optional<Anexo12> a = anexo12Repository.findById(id);
        if (a.isPresent()) {
            Anexo12Response response = new Anexo12Response();
            response.setId(a.get().getId());
            response.setNombresEstudiante(a.get().getNombresEstudiante());
            response.setCedulaEstudiante(a.get().getCedulaEstudiante());
            response.setCarrera(a.get().getCarrera());
            response.setFechaInicio(a.get().getFechaInicio());
            response.setFechaFinaliza(a.get().getFechaFinaliza());
            response.setFechaEvaluacion(a.get().getFechaEvaluacion());
            response.setTotalHoras(a.get().getTotalHoras());
            response.setResultadoAnexo12(a.get().getResultadoAnexo12());
            response.setPromedio(a.get().getPromedio());

            response.setNombretutoremp(a.get().getNombretutoremp());
            response.setCedulatutoremp(a.get().getCedulatutoremp());
            response.setSiglascarrera(a.get().getSiglascarrera());
            response.setEmpresa(a.get().getEmpresa());
            response.setTutorempPuntaje(a.get().getTutorempPuntaje());

            response.setIdProyecto(a.get().getProyectoPPP().getId());
            response.setDocumento(a.get().getDocumento());
            List<Anexo12TutorempRequest> list = a.get().getItemsTutor().stream().map(ap -> {
                Anexo12TutorempRequest request = new Anexo12TutorempRequest();
                request.setId(ap.getId());
                request.setTutorempItem1(ap.getTutorempItem1());
                request.setTutorempItem2(ap.getTutorempItem2());

                return request;
            }).collect(Collectors.toList());
            response.setTutoremp(list);


            return response;
        }
        throw new BadRequestException("No existe el anexo con id: " + id);
    }

    public List<Anexo12Response> listAnexo12ByProyectoPPP(Long pryectoId) {
        Optional<ProyectoPPP> optional = proyectoRepository.findById(pryectoId);
        if (optional.isPresent()) {
            List<Anexo12> b = anexo12Repository.findByProyectoPPP(optional.get());
            return b.stream().map(a -> {
                Anexo12Response response = new Anexo12Response();
                response.setId(a.getId());
                response.setNombresEstudiante(a.getNombresEstudiante());
                response.setCedulaEstudiante(a.getCedulaEstudiante());
                response.setCarrera(a.getCarrera());
                response.setFechaInicio(a.getFechaInicio());
                response.setFechaFinaliza(a.getFechaFinaliza());
                response.setFechaEvaluacion(a.getFechaEvaluacion());
                response.setTotalHoras(a.getTotalHoras());
                response.setPromedio(a.getPromedio());


                response.setNombretutoremp(a.getNombretutoremp());
                response.setCedulatutoremp(a.getCedulatutoremp());
                response.setSiglascarrera(a.getSiglascarrera());
                response.setEmpresa(a.getEmpresa());
                response.setTutorempPuntaje(a.getTutorempPuntaje());

                response.setIdProyecto(a.getProyectoPPP().getId());
                response.setDocumento(a.getDocumento());


                List<Anexo12TutorempRequest> list = a.getItemsTutor().stream().map(ap -> {
                    Anexo12TutorempRequest request = new Anexo12TutorempRequest();
                    request.setId(ap.getId());

                    return request;
                }).collect(Collectors.toList());
                response.setTutoremp(list);
                return response;
            }).collect(Collectors.toList());

        }
        throw new BadRequestException("No existe el proyecto " + pryectoId);
    }


    //Actualizar

    public boolean update(Anexo12Request anexo12Request) {
        Optional<Anexo12> a = anexo12Repository.findById(anexo12Request.getId());
        if (a.isPresent()) {
            Optional<ProyectoPPP> optionalp = proyectoRepository.findById(anexo12Request.getIdProyecto());
            if (optionalp.isPresent()) {
                if (!optionalp.get().isEstado())
                    throw new BadRequestException("El proyecto ha finalizado, no es posible modificar sus datos");

                a.get().setNombresEstudiante(anexo12Request.getNombresEstudiante());
                a.get().setCedulaEstudiante(anexo12Request.getCedulaEstudiante());
                a.get().setCarrera(anexo12Request.getCarrera());
                a.get().setFechaInicio(anexo12Request.getFechaInicio());
                a.get().setFechaFinaliza(anexo12Request.getFechaFinaliza());
                a.get().setFechaEvaluacion(anexo12Request.getFechaEvaluacion());
                a.get().setTotalHoras(anexo12Request.getTotalHoras());
                a.get().setResultadoAnexo12(anexo12Request.getResultadoAnexo12());
                a.get().setPromedio(anexo12Request.getPromedio());

                a.get().setNombretutoremp(anexo12Request.getNombretutoremp());
                a.get().setCedulatutoremp(anexo12Request.getCedulatutoremp());
                a.get().setSiglascarrera(anexo12Request.getSiglascarrera());
                a.get().setEmpresa(anexo12Request.getEmpresa());
                a.get().setTutorempPuntaje(anexo12Request.getTutorempPuntaje());

                a.get().setDocumento(anexo12Request.getDocumento());


                updateTutorEmp(anexo12Request.getId(), anexo12Request.getTutoremp());

                try {
                    Anexo12 a11 = anexo12Repository.save(a.get());

                    return true;
                } catch (Exception ex) {
                    throw new BadRequestException("No se actualizo el anexo 11" + ex);
                }
            } else {
                throw new BadRequestException("No existe un proyecto con ID:" + anexo12Request.getIdProyecto());

            }
        }
        throw new ResponseNotFoundException("Anexo12", "ID:", "" + anexo12Request.getId());
    }

    //Eliminar
    @Transactional
    public void deleteById(Long id) {
        Optional<Anexo12> optional = anexo12Repository.findById(id);
        if (optional.isEmpty()) {
            throw new BadRequestException("El anexo 12 con el id " + id + ", no existe");
        }
        if (!optional.get().getProyectoPPP().isEstado())
            throw new BadRequestException("El proceso a finalizado");

        optional.get().getItemsTutor()
                .forEach(d -> deleteTutorempById(d.getId()));


        anexo12Repository.deleteById(id);
    }


    public void deleteTutorempById(Long id) {
        Optional<ItemsAnexo12Tutoremp> optional = tutorEmp12Repository.findById(id);
        if (optional.isEmpty()) {
            throw new BadRequestException("Las actividades del docente de apoyo con: " + id + ", no existen");
        }
        tutorEmp12Repository.deleteById(id);
    }

    @Transactional
    public void updateTutorEmp(Long id, List<Anexo12TutorempRequest> request) {
        Optional<Anexo12> a = anexo12Repository.findById(id);
        if (a.isPresent()) {

            List<ItemsAnexo12Tutoremp> itemsBase = a.get().getItemsTutor();
            request.forEach(i -> {
                ItemsAnexo12Tutoremp req;
                if (i.getId() == null) {
                    req = new ItemsAnexo12Tutoremp();
                } else {
                    Optional<ItemsAnexo12Tutoremp> op = itemsBase.stream().filter(item -> Objects.equals(item.getId(), i.getId())).findFirst();
                    if (op.isEmpty()) throw new BadRequestException("El item con id: " + i.getId() + ", no exiset");
                    req = op.get();
                }
                req.setTutorempItem1(i.getTutorempItem1());
                req.setTutorempItem2(i.getTutorempItem2());
                req.setAnexo12(a.get());
               tutorEmp12Repository.save(req);
            });

        } else {
            throw new BadRequestException("El anexo con id: " + id + ", no existe");
        }
    }


}