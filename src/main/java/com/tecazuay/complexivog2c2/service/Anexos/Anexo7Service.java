package com.tecazuay.complexivog2c2.service.Anexos;

import com.tecazuay.complexivog2c2.dto.anexos.*;
import com.tecazuay.complexivog2c2.dto.solicitudproyectos.ProyectoResponse;
import com.tecazuay.complexivog2c2.exception.BadRequestException;
import com.tecazuay.complexivog2c2.exception.ResponseNotFoundException;
import com.tecazuay.complexivog2c2.model.Primary.Anexos.ActividadesAnexo7;
import com.tecazuay.complexivog2c2.model.Primary.Anexos.ActividadesCumplirAnexo7;
import com.tecazuay.complexivog2c2.model.Primary.Anexos.Anexo7;
import com.tecazuay.complexivog2c2.model.Primary.Anexos.CronogramaActividadesAnexo7;
import com.tecazuay.complexivog2c2.model.Primary.solicitudproyecto.ProyectoPPP;
import com.tecazuay.complexivog2c2.repository.Primary.Anexos.ActividadesAnexo7Repository;
import com.tecazuay.complexivog2c2.repository.Primary.Anexos.ActividadesCumplirAnexo7Repository;
import com.tecazuay.complexivog2c2.repository.Primary.Anexos.Anexo7Repository;
import com.tecazuay.complexivog2c2.repository.Primary.Anexos.CronogramaActividadesAnexo7Repository;
import com.tecazuay.complexivog2c2.repository.Primary.designaciones.ResponsablePPPRepository;
import com.tecazuay.complexivog2c2.repository.Primary.solicitudproyecto.ProyectoRepository;
import com.tecazuay.complexivog2c2.repository.Primary.solicitudproyecto.RequisitosRepository;
import com.tecazuay.complexivog2c2.repository.Primary.usuario.UsuarioRepository;
import com.tecazuay.complexivog2c2.repository.Secondary.alumnos.VAlumnosRepository;
import com.tecazuay.complexivog2c2.service.email.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class Anexo7Service {

    @Autowired
    private Anexo7Repository anexo7Repository;

    @Autowired
    private ActividadesAnexo7Repository actividadesAnexo7Repository;

    @Autowired
    private ActividadesCumplirAnexo7Repository actividadesCumplirAnexo7Repository;

    @Autowired
    private CronogramaActividadesAnexo7Repository cronogramaActividadesAnexo7Repository;

    @Autowired
    private ProyectoRepository proyectoRepository;

    @Autowired
    private VAlumnosRepository vAlumnosRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private EmailService emailService;

    @Autowired
    private RequisitosRepository requisitosRepository;

    @Autowired
    private ResponsablePPPRepository responsablePPPRepository;

    public boolean save(Anexo7Request request){
        Optional<ProyectoPPP> optional = proyectoRepository.findById(request.getIdProyectoPPP());
        if (optional.isPresent()) {
            if (!optional.get().isEstado())
                throw new BadRequestException("El proceso a finalizado");
                Anexo7 anexo7=new Anexo7();
                anexo7.setFechaReunion(request.getFechaReunion());
                anexo7.setNombreResponsable(request.getNombreResponsable());
                anexo7.setCarrera(request.getCarrera());
                anexo7.setTituloTutorEmp(request.getTituloTutorEmp());
                anexo7.setNombreTutorEmp(request.getNombreTutorEmp());
                anexo7.setNombreEmpresa(request.getNombreEmpresa());

                anexo7.setCortesia(request.getCortesia());
                anexo7.setLugarReunion(request.getLugarReunion());
                anexo7.setNombreEstudiante(request.getNombreEstudiante());
                anexo7.setCedulaEstudiante(request.getCedulaEstudiante());
                anexo7.setCedulaTutoracademico(request.getCedulaTutoracademico());
                anexo7.setNombreTutoracademico(request.getNombreTutoracademico());
                anexo7.setCiclo(request.getCiclo());
                anexo7.setHorasCumplidas(request.getHorasCumplidas());
                anexo7.setSiglascarrera(request.getSiglascarrera());

                anexo7.setFechainicio(request.getFechainicio());
                anexo7.setFechafin(request.getFechafin());
                anexo7.setHorasInicio(request.getHorasInicio());
                anexo7.setHorasFin(request.getHorasFin());

                anexo7.setHorasTotales(request.getHorasTotales());
                anexo7.setNombreResponsable(request.getNombreResponsable());
                anexo7.setDocumento(request.getDocumento());
                anexo7.setProyectoPPP(optional.get());
                anexo7.setNum_proceso(request.getNum_proceso());

                List<ActividadesAnexo7> list = new ArrayList<>();
                request.getActividadesAnexo7s().stream().forEach(a -> {
                    ActividadesAnexo7 actividadesAnexo7 = new ActividadesAnexo7();
                    actividadesAnexo7.setDescripcion(a.getDescripcion());
                    list.add(actividadesAnexo7);
                });

                List<ActividadesCumplirAnexo7> list2 = new ArrayList<>();
                request.getActividadesCumplirAnexo7s().stream().forEach(a -> {
                    ActividadesCumplirAnexo7 actividadesCumplirAnexo7 = new ActividadesCumplirAnexo7();
                    actividadesCumplirAnexo7.setArea(a.getArea());
                    actividadesCumplirAnexo7.setActividadRealizar(a.getActividadRealizar());
                    actividadesCumplirAnexo7.setAsignaturaRelacionada(a.getAsignaturaRelacionada());
                    list2.add(actividadesCumplirAnexo7);
                });

                List<CronogramaActividadesAnexo7> list3 = new ArrayList<>();
                request.getCronogramaActividadesAnexo7s().stream().forEach(a -> {
                    CronogramaActividadesAnexo7 cronogramaActividadesAnexo7 = new CronogramaActividadesAnexo7();
                    cronogramaActividadesAnexo7.setActividadRealizar(a.getActividadRealizar());
                    cronogramaActividadesAnexo7.setSemanas(a.getSemanas());
                    cronogramaActividadesAnexo7.setNrohoras(a.getNrohoras());
                    cronogramaActividadesAnexo7.setHorasTotales(a.getHorasTotales());
                    list3.add(cronogramaActividadesAnexo7);
                });

                try {
                    saveActividades(list, anexo7Repository.save(anexo7));
                    saveActividadesCumplir(list2,anexo7Repository.save(anexo7));
                    saveCronogramaActividades(list3,anexo7Repository.save(anexo7));
                } catch (Exception e) {
                    throw new BadRequestException("No se guardÃ³ el anexo 7" + e);
                }
                try {
                    return true;
                } catch (Exception ex) {
                    throw new BadRequestException("No se enviÃ³ el email");
                }


        }
        throw new BadRequestException("No existe el proyecto con id: " + request.getIdProyectoPPP());
    }

    //Metodos para guardar las actividades, ActividadesCumplir, CronogramaActividades
    private void saveActividades(List<ActividadesAnexo7> list, Anexo7 anexo7) {
        list.stream().forEach(a -> {
            a.setAnexo7(anexo7);
            actividadesAnexo7Repository.save(a);
        });
    }

    private void saveActividadesCumplir(List<ActividadesCumplirAnexo7> list, Anexo7 anexo7) {
        list.stream().forEach(a -> {
            a.setAnexo7(anexo7);
            actividadesCumplirAnexo7Repository.save(a);
        });
    }

    private void saveCronogramaActividades(List<CronogramaActividadesAnexo7> list, Anexo7 anexo7) {
        list.stream().forEach(a -> {
            a.setAnexo7(anexo7);
            cronogramaActividadesAnexo7Repository.save(a);
        });
    }

    //Metodos para eliminar las actividades, ActividadesCumplir, CronogramaActividades

    public void deleteActividadesById(Long id) {
        Optional<ActividadesAnexo7> optional = actividadesAnexo7Repository.findById(id);
        if (!optional.isPresent()) {
            throw new BadRequestException("Las actividades con id: " + id + ", no existen");
        }
        actividadesAnexo7Repository.deleteById(id);
    }

    public void deleteActividadesCumplirById(Long id) {
        Optional<ActividadesCumplirAnexo7> optional = actividadesCumplirAnexo7Repository.findById(id);
        if (!optional.isPresent()) {
            throw new BadRequestException("Las actividades con id: " + id + ", no existen");
        }
        actividadesCumplirAnexo7Repository.deleteById(id);
    }

    public void deleteCronogramaActividadesById(Long id) {
        Optional<CronogramaActividadesAnexo7> optional = cronogramaActividadesAnexo7Repository.findById(id);
        if (!optional.isPresent()) {
            throw new BadRequestException("Las actividades con id: " + id + ", no existen");
        }
        cronogramaActividadesAnexo7Repository.deleteById(id);
    }

    @Transactional
    public void deleteAnexo7ById(Long id) {
        Optional<Anexo7> optional = anexo7Repository.findById(id);
        if (!optional.isPresent()) {
            throw new BadRequestException("El anexo7 con id: " + id + ", no existe");
        }
        if (!optional.get().getProyectoPPP().isEstado())
            throw new BadRequestException("El proyecto ha finalizado, no es posible modificar sus datos");

        optional.get().getActividadesAnexo7s()
                .forEach(d -> deleteActividadesById(d.getId()));

        optional.get().getActividadesCumplirAnexo7s()
                .forEach(d -> deleteActividadesCumplirById(d.getId()));

        optional.get().getCronogramaActividadesAnexo7s()
                .forEach(d -> deleteCronogramaActividadesById(d.getId()));

        anexo7Repository.delete(optional.get());
    }

    @Transactional
    public List<Anexo7Response> listAll() {
        return anexo7Repository.findAll().stream().map(a -> {
            Anexo7Response response = new Anexo7Response();

            response.setId(a.getId());
            response.setFechaReunion(a.getFechaReunion());
            response.setNombreResponsable(a.getNombreResponsable());
            response.setCarrera(a.getCarrera());
            response.setTituloTutorEmp(a.getTituloTutorEmp());
            response.setNombreTutorEmp(a.getNombreTutorEmp());
            response.setNombreEmpresa(a.getNombreEmpresa());
            response.setSiglascarrera(a.getSiglascarrera());

            response.setCortesia(a.getCortesia());
            response.setLugarReunion(a.getLugarReunion());
            response.setNombreEstudiante(a.getNombreEstudiante());
            response.setCedulaEstudiante(a.getCedulaEstudiante());
            response.setCedulaTutoracademico(a.getCedulaTutoracademico());
            response.setNombreTutoracademico(a.getNombreTutoracademico());

            response.setCiclo(a.getCiclo());
            response.setHorasCumplidas(a.getHorasCumplidas());

            response.setFechainicio(a.getFechainicio());
            response.setFechafin(a.getFechafin());
            response.setHorasInicio(a.getHorasInicio());
            response.setHorasFin(a.getHorasFin());

            response.setHorasTotales(a.getHorasTotales());
            response.setNombreResponsable(a.getNombreResponsable());
            response.setHorasFin(a.getHorasFin());
            response.setDocumento(a.getDocumento());
            response.setNum_proceso(a.getNum_proceso());

            List<ActividadesAnexo7Request> list = a.getActividadesAnexo7s().stream().map(ac -> {
                ActividadesAnexo7Request request = new ActividadesAnexo7Request();
                request.setDescripcion(ac.getDescripcion());
                request.setId(ac.getId());
                return request;
            }).collect(Collectors.toList());

            List<ActividadesCumplirAnexo7Request> list2 = a.getActividadesCumplirAnexo7s().stream().map(ac -> {
                ActividadesCumplirAnexo7Request request = new ActividadesCumplirAnexo7Request();
                request.setArea(ac.getArea());
                request.setActividadRealizar(ac.getActividadRealizar());
                request.setAsignaturaRelacionada(ac.getAsignaturaRelacionada());
                request.setId(ac.getId());
                return request;
            }).collect(Collectors.toList());

            List<CronogramaActividadesAnexo7Request> list3 = a.getCronogramaActividadesAnexo7s().stream().map(ac -> {
                CronogramaActividadesAnexo7Request request = new CronogramaActividadesAnexo7Request();
                request.setActividadRealizar(ac.getActividadRealizar());
                request.setSemanas(ac.getSemanas());
                request.setNrohoras(ac.getNrohoras());
                request.setHorasTotales(ac.getHorasTotales());
                request.setId(ac.getId());
                return request;
            }).collect(Collectors.toList());

            response.setActividadesAnexo7s(list);
            response.setActividadesCumplirAnexo7s(list2);
            response.setCronogramaActividadesAnexo7s(list3);
            response.setIdProyectoPPP(a.getProyectoPPP().getId());
            return response;
        }).collect(Collectors.toList());
    }

    public Anexo7Response anexoIdProyecto(Long proyectoId) {
        Optional<ProyectoPPP> optional = proyectoRepository.findById(proyectoId);
        if (optional.isPresent()) {
            Optional<Anexo7> a = anexo7Repository.findByProyectoPPP(optional.get());
            if (a.isPresent()) {
                Anexo7Response response = new Anexo7Response();

                response.setId(a.get().getId());
                response.setFechaReunion(a.get().getFechaReunion());
                response.setNombreResponsable(a.get().getNombreResponsable());
                response.setCarrera(a.get().getCarrera());
                response.setTituloTutorEmp(a.get().getTituloTutorEmp());
                response.setNombreTutorEmp(a.get().getNombreTutorEmp());
                response.setNombreEmpresa(a.get().getNombreEmpresa());
                response.setSiglascarrera(a.get().getSiglascarrera());

                response.setCedulaTutoracademico(a.get().getCedulaTutoracademico());
                response.setNombreTutoracademico(a.get().getNombreTutoracademico());

                response.setCortesia(a.get().getCortesia());
                response.setLugarReunion(a.get().getLugarReunion());
                response.setNombreEstudiante(a.get().getNombreEstudiante());
                response.setCedulaEstudiante(a.get().getCedulaEstudiante());

                response.setCiclo(a.get().getCiclo());
                response.setHorasCumplidas(a.get().getHorasCumplidas());

                response.setFechainicio(a.get().getFechainicio());
                response.setFechafin(a.get().getFechafin());
                response.setHorasInicio(a.get().getHorasInicio());
                response.setHorasFin(a.get().getHorasFin());

                response.setHorasTotales(a.get().getHorasTotales());
                response.setNombreResponsable(a.get().getNombreResponsable());
                response.setHorasFin(a.get().getHorasFin());
                response.setDocumento(a.get().getDocumento());
                response.setNum_proceso(a.get().getNum_proceso());

                List<ActividadesAnexo7Request> list = a.get().getActividadesAnexo7s().stream().map(ac -> {
                    ActividadesAnexo7Request request = new ActividadesAnexo7Request();
                    request.setDescripcion(ac.getDescripcion());
                    request.setId(ac.getId());
                    return request;
                }).collect(Collectors.toList());

                List<ActividadesCumplirAnexo7Request> list2 = a.get().getActividadesCumplirAnexo7s().stream().map(ac -> {
                    ActividadesCumplirAnexo7Request request = new ActividadesCumplirAnexo7Request();
                    request.setArea(ac.getArea());
                    request.setActividadRealizar(ac.getActividadRealizar());
                    request.setAsignaturaRelacionada(ac.getAsignaturaRelacionada());
                    request.setId(ac.getId());
                    return request;
                }).collect(Collectors.toList());

                List<CronogramaActividadesAnexo7Request> list3 = a.get().getCronogramaActividadesAnexo7s().stream().map(ac -> {
                    CronogramaActividadesAnexo7Request request = new CronogramaActividadesAnexo7Request();
                    request.setActividadRealizar(ac.getActividadRealizar());
                    request.setSemanas(ac.getSemanas());
                    request.setNrohoras(ac.getNrohoras());
                    request.setHorasTotales(ac.getHorasTotales());
                    request.setId(ac.getId());
                    return request;
                }).collect(Collectors.toList());

                response.setActividadesAnexo7s(list);
                response.setActividadesCumplirAnexo7s(list2);
                response.setCronogramaActividadesAnexo7s(list3);
                response.setIdProyectoPPP(a.get().getProyectoPPP().getId());
                return response;
            }
            throw new BadRequestException("No existe el anexo con id de la solicitud: " + proyectoId);
        }

        throw new BadRequestException("No existe la solicitud: " + proyectoId);
    }


    public Anexo7Response anexoBYIdan7(Long id) {

        Optional<Anexo7> a = anexo7Repository.findById(id);
        if (a.isPresent()) {
            Anexo7Response response = new Anexo7Response();

            response.setId(a.get().getId());
            response.setFechaReunion(a.get().getFechaReunion());
            response.setNombreResponsable(a.get().getNombreResponsable());
            response.setCarrera(a.get().getCarrera());
            response.setTituloTutorEmp(a.get().getTituloTutorEmp());
            response.setNombreTutorEmp(a.get().getNombreTutorEmp());
            response.setNombreEmpresa(a.get().getNombreEmpresa());
            response.setSiglascarrera(a.get().getSiglascarrera());

            response.setCedulaTutoracademico(a.get().getCedulaTutoracademico());
            response.setNombreTutoracademico(a.get().getNombreTutoracademico());

            response.setCortesia(a.get().getCortesia());
            response.setLugarReunion(a.get().getLugarReunion());
            response.setNombreEstudiante(a.get().getNombreEstudiante());
            response.setCedulaEstudiante(a.get().getCedulaEstudiante());

            response.setCiclo(a.get().getCiclo());
            response.setHorasCumplidas(a.get().getHorasCumplidas());

            response.setFechainicio(a.get().getFechainicio());
            response.setFechafin(a.get().getFechafin());
            response.setHorasInicio(a.get().getHorasInicio());
            response.setHorasFin(a.get().getHorasFin());

            response.setHorasTotales(a.get().getHorasTotales());
            response.setNombreResponsable(a.get().getNombreResponsable());
            response.setHorasFin(a.get().getHorasFin());
            response.setDocumento(a.get().getDocumento());
            response.setNum_proceso(a.get().getNum_proceso());

            List<ActividadesAnexo7Request> list = a.get().getActividadesAnexo7s().stream().map(ac -> {
                ActividadesAnexo7Request request = new ActividadesAnexo7Request();
                request.setDescripcion(ac.getDescripcion());
                request.setId(ac.getId());
                return request;
            }).collect(Collectors.toList());

            List<ActividadesCumplirAnexo7Request> list2 = a.get().getActividadesCumplirAnexo7s().stream().map(ac -> {
                ActividadesCumplirAnexo7Request request = new ActividadesCumplirAnexo7Request();
                request.setArea(ac.getArea());
                request.setActividadRealizar(ac.getActividadRealizar());
                request.setAsignaturaRelacionada(ac.getAsignaturaRelacionada());
                request.setId(ac.getId());
                return request;
            }).collect(Collectors.toList());

            List<CronogramaActividadesAnexo7Request> list3 = a.get().getCronogramaActividadesAnexo7s().stream().map(ac -> {
                CronogramaActividadesAnexo7Request request = new CronogramaActividadesAnexo7Request();
                request.setActividadRealizar(ac.getActividadRealizar());
                request.setSemanas(ac.getSemanas());
                request.setNrohoras(ac.getNrohoras());
                request.setHorasTotales(ac.getHorasTotales());
                request.setId(ac.getId());
                return request;
            }).collect(Collectors.toList());

            response.setActividadesAnexo7s(list);
            response.setActividadesCumplirAnexo7s(list2);
            response.setCronogramaActividadesAnexo7s(list3);
            response.setIdProyectoPPP(a.get().getProyectoPPP().getId());
            return response;
        }
        throw new BadRequestException("No existe el anexo con id de la solicitud: " + id);

    }






    @Transactional
    public void updatean7(Anexo7Request request) {
        Optional<Anexo7> optionalAnexo7 = anexo7Repository.findById(request.getId());
        if (optionalAnexo7.isPresent()) {
            Optional<ProyectoPPP> optionalProyectoPPP = proyectoRepository.findById(request.getIdProyectoPPP());
            if (optionalProyectoPPP.isPresent()) {
                if (!optionalProyectoPPP.get().isEstado())
                    throw new BadRequestException("El proyecto finalizos");
                Anexo7 a7 = optionalAnexo7.get();

                a7.setFechaReunion(request.getFechaReunion());
                a7.setNombreResponsable(request.getNombreResponsable());
                a7.setCarrera(request.getCarrera());
                a7.setTituloTutorEmp(request.getTituloTutorEmp());
                a7.setNombreTutorEmp(request.getNombreTutorEmp());
                a7.setNombreEmpresa(request.getNombreEmpresa());
                a7.setSiglascarrera(request.getSiglascarrera());

                a7.setCedulaTutoracademico(request.getCedulaTutoracademico());
                a7.setNombreTutoracademico(request.getNombreTutoracademico());

                a7.setCortesia(request.getCortesia());
                a7.setLugarReunion(request.getLugarReunion());
                a7.setNombreEstudiante(request.getNombreEstudiante());
                a7.setCedulaEstudiante(request.getCedulaEstudiante());

                a7.setCiclo(request.getCiclo());
                a7.setHorasCumplidas(request.getHorasCumplidas());

                a7.setFechainicio(request.getFechainicio());
                a7.setFechafin(request.getFechafin());
                a7.setHorasInicio(request.getHorasInicio());
                a7.setHorasFin(request.getHorasFin());

                a7.setHorasTotales(request.getHorasTotales());
                a7.setNombreResponsable(request.getNombreResponsable());
                a7.setHorasFin(request.getHorasFin());
                a7.setDocumento(request.getDocumento());
                a7.setNum_proceso(request.getNum_proceso());



                a7.setProyectoPPP(optionalProyectoPPP.get());


                List<ActividadesAnexo7Request> list = new ArrayList<>();

                request.getActividadesAnexo7s().stream().forEach(a -> {
                    ActividadesAnexo7 actividad = new ActividadesAnexo7();
                   actividad.setDescripcion(a.getDescripcion());
                    actividad.setId(a.getId());
                    //actividad.setAnexo6();
                });

                List<ActividadesCumplirAnexo7Request> list2 = new ArrayList<>();

                request.getActividadesCumplirAnexo7s().stream().forEach(a -> {
                    ActividadesCumplirAnexo7 actividad = new ActividadesCumplirAnexo7();
                    actividad.setId(a.getId());
                    actividad.setArea(a.getArea());
                    actividad.setActividadRealizar(a.getActividadRealizar());
                    actividad.setAsignaturaRelacionada(a.getAsignaturaRelacionada());
                });
                List<CronogramaActividadesAnexo7Request> list3 = new ArrayList<>();

                request.getCronogramaActividadesAnexo7s().stream().forEach(a -> {
                    CronogramaActividadesAnexo7 actividad = new CronogramaActividadesAnexo7();
                    actividad.setId(a.getId());
                    actividad.setActividadRealizar(a.getActividadRealizar());
                    actividad.setSemanas(a.getSemanas());
                    actividad.setNrohoras(a.getNrohoras());
                    actividad.setHorasTotales(a.getHorasTotales());

                    //actividad.setAnexo6();
                });


                try {
                    anexo7Repository.save(a7);
                } catch (Exception ex) {
                    throw new BadRequestException("Error al actualizar Anexo 7");
                }
            }
        } else
            throw new ResponseNotFoundException("Anexo 7", "id", request.getId() + "");
    }


}
