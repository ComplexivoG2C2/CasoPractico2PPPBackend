package com.tecazuay.complexivog2c2.service.Anexos.anexo121;

import com.tecazuay.complexivog2c2.dto.anexos.Anexo7Request;
import com.tecazuay.complexivog2c2.dto.anexos.anexo121.ActividadesAnexo121Request;
import com.tecazuay.complexivog2c2.dto.anexos.anexo121.Anexo121certificadoRequest;
import com.tecazuay.complexivog2c2.dto.anexos.anexo121.Anexo121certificadoResponse;
import com.tecazuay.complexivog2c2.exception.BadRequestException;
import com.tecazuay.complexivog2c2.model.Primary.Anexos.ActividadesAnexo7;
import com.tecazuay.complexivog2c2.model.Primary.Anexos.ActividadesCumplirAnexo7;
import com.tecazuay.complexivog2c2.model.Primary.Anexos.Anexo7;
import com.tecazuay.complexivog2c2.model.Primary.Anexos.CronogramaActividadesAnexo7;
import com.tecazuay.complexivog2c2.model.Primary.Anexos.anexo121.ActividadesAnexo121;
import com.tecazuay.complexivog2c2.model.Primary.Anexos.anexo121.Anexo121certificado;
import com.tecazuay.complexivog2c2.model.Primary.solicitudproyecto.ProyectoPPP;
import com.tecazuay.complexivog2c2.repository.Primary.Anexos.anexo121.Actividadesanexo121Repository;
import com.tecazuay.complexivog2c2.repository.Primary.Anexos.anexo121.Anexo121certificadoRepository;
import com.tecazuay.complexivog2c2.repository.Primary.empresa.EmpresaRepository;
import com.tecazuay.complexivog2c2.repository.Primary.solicitudproyecto.ProyectoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class Anexo121Service {
    @Autowired
    private ProyectoRepository proyectoRepository;
    @Autowired
    private Anexo121certificadoRepository anexo121certificadoRepository;
    @Autowired
    private Actividadesanexo121Repository actividadesanexo121Repository ;




    public boolean save(Anexo121certificadoRequest request) {
        Optional<ProyectoPPP> optional = proyectoRepository.findById(request.getIdProyecto());
        if (optional.isPresent()) {
            if (!optional.get().isEstado())
                throw new BadRequestException("El proceso a finalizado");
            Anexo121certificado an = new Anexo121certificado();

            an.setProyectoPPP(optional.get());
            an.setNombresEstudiante(request.getNombresEstudiante());
            an.setCedulaEstudiante(request.getCedulaEstudiante());
            an.setCarrera(request.getCarrera());
            an.setFechaInicio(request.getFechaInicio());
            an.setFechaFinaliza(request.getFechaFinaliza());
            an.setFechaEvaluacion(request.getFechaEvaluacion());
            an.setTotalHoras(request.getTotalHoras());
            an.setPromedio(request.getPromedio());
            an.setDocumento(request.getDocumento());

            an.setNombretutoremp(request.getNombretutoremp());
            an.setCedulatutoremp(request.getCedulatutoremp());
            an.setSiglascarrera(request.getSiglascarrera());
            an.setEmpresa(request.getEmpresa());
            an.setTutorempPuntaje(request.getTutorempPuntaje());;

            List<ActividadesAnexo121> list = new ArrayList<>();

            request.getActividades().stream().forEach(a -> {
                ActividadesAnexo121 actividadesAnexo2 = new ActividadesAnexo121();
                actividadesAnexo2.setDescripcion(a.getDescripcion());
                list.add(actividadesAnexo2);
            });
            try {
                saveActividades(list, anexo121certificadoRepository.save(an));
            } catch (Exception e) {
                throw new BadRequestException("No se guardÃ³ el anexo 12.1" + e);
            }
            try {
                return true;
            } catch (Exception ex) {
                throw new BadRequestException("No se guardo");
            }


        }
        throw new BadRequestException("No existe el proyecto con id: " + request.getIdProyecto());
    }

    @Transactional
    public boolean update(Anexo121certificadoRequest request) {
        Optional<Anexo121certificado> optional = anexo121certificadoRepository.findById(request.getId());
        if (optional.isPresent()) {
            if (!optional.get().getProyectoPPP().isEstado())
                throw new BadRequestException("El proceso afinalizado");

            Anexo121certificado an = optional.get();
            an.setNombresEstudiante(request.getNombresEstudiante());
            an.setCedulaEstudiante(request.getCedulaEstudiante());
            an.setCarrera(request.getCarrera());
            an.setFechaInicio(request.getFechaInicio());
            an.setFechaFinaliza(request.getFechaFinaliza());
            an.setFechaEvaluacion(request.getFechaEvaluacion());
            an.setTotalHoras(request.getTotalHoras());
            an.setPromedio(request.getPromedio());
            an.setDocumento(request.getDocumento());

            an.setNombretutoremp(request.getNombretutoremp());
            an.setCedulatutoremp(request.getCedulatutoremp());
            an.setSiglascarrera(request.getSiglascarrera());
            an.setEmpresa(request.getEmpresa());
            an.setTutorempPuntaje(request.getTutorempPuntaje());
            an.setProyectoPPP(proyectoRepository.findById(request.getIdProyecto()).orElseThrow(() -> new BadRequestException("No existe proyecto con id: " + request.getIdProyecto())));


            try {

                anexo121certificadoRepository.save(an);
                updateActividades(request.getId(), request.getActividades());

                return true;
            } catch (Exception ex) {
                throw new BadRequestException("No se guardó el anexo 2" + ex);
            }
        }
        return false;
    }

    @Transactional
    public void updateActividades(Long id, List<ActividadesAnexo121Request> requisitos) {

        Optional<Anexo121certificado> optional = anexo121certificadoRepository.findById(id);

        if (optional.isPresent()) {
            List<ActividadesAnexo121> proyecto = optional.get().getActividades();
            proyecto.forEach(r -> {
                Optional<ActividadesAnexo121Request> exists = requisitos
                        .stream()
                        .filter(req -> req.getDescripcion().equalsIgnoreCase(r.getDescripcion()))
                        .findAny();
                if (exists.isPresent()) {
                    actividadesanexo121Repository.delete(r);
                }
            });

            requisitos.forEach(request -> {
                String descripcion = request.getDescripcion();
                Optional<ActividadesAnexo121> exists = proyecto
                        .stream()
                        .filter(r -> r.getDescripcion().equalsIgnoreCase(descripcion))
                        .findAny();
                if (!exists.isPresent()) {
                    ActividadesAnexo121 save = new ActividadesAnexo121();
                    save.setAnexo121certificados(optional.get());
                    save.setDescripcion(request.getDescripcion());
                    actividadesanexo121Repository.save(save);
                }
            });
        } else {
            throw new BadRequestException("El proyecto con id: " + id + ", no existe");
        }
    }

    public void deleteActividadesById(Long id) {
        Optional<ActividadesAnexo121> optional = actividadesanexo121Repository.findById(id);
        if (!optional.isPresent()) {
            throw new BadRequestException("Las actividades con id: " + id + ", no existen");
        }
        actividadesanexo121Repository.deleteById(id);
    }

    @Transactional
    public void deleteAnexo121ById(Long id) {
        Optional<Anexo121certificado> optional = anexo121certificadoRepository.findById(id);
        if (!optional.isPresent()) {
            throw new BadRequestException("El anexo2 con id: " + id + ", no existe");
        }
        if (!optional.get().getProyectoPPP().isEstado())
            throw new BadRequestException("El proyecto ha finalizado, no es posible modificar sus datos");

        optional.get().getActividades()
                .forEach(d -> deleteActividadesById(d.getId()));


        anexo121certificadoRepository.delete(optional.get());
    }






    private void saveActividades(List<ActividadesAnexo121> list, Anexo121certificado anexo121certificado) {
        list.stream().forEach(a -> {
            a.setAnexo121certificados(anexo121certificado);
            actividadesanexo121Repository.save(a);
        });
    }


    @Transactional
    public List<Anexo121certificadoResponse> listAll() {
        return anexo121certificadoRepository.findAll().stream().map(a -> {
            Anexo121certificadoResponse response = new  Anexo121certificadoResponse ();
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

            List<ActividadesAnexo121Request> list = a.getActividades().stream().map(ap -> {
                ActividadesAnexo121Request request = new ActividadesAnexo121Request();
                request.setId(ap.getId());
                request.setDescripcion(ap.getDescripcion());
                return request;
            }).collect(Collectors.toList());
            response.setActividades(list);

            return response;
        }).collect(Collectors.toList());
    }

//
//    public Anexo121certificadoResponse listanexoIdProyecto(Long proyectoId) {
//        Optional<ProyectoPPP> optional = proyectoRepository.findById(proyectoId);
//        if (optional.isPresent()) {
//            Optional<Anexo121certificado> a = anexo121certificadoRepository.findByProyectoPPP(optional.get());
//            if (a.isPresent()) {
//                Anexo121certificadoResponse response = new Anexo121certificadoResponse();
//                response.setId(a.get().getId());
//                response.setNombresEstudiante(a.get().getNombresEstudiante());
//                response.setCedulaEstudiante(a.get().getCedulaEstudiante());
//                response.setCarrera(a.get().getCarrera());
//                response.setFechaInicio(a.get().getFechaInicio());
//                response.setFechaFinaliza(a.get().getFechaFinaliza());
//                response.setFechaEvaluacion(a.get().getFechaEvaluacion());
//                response.setTotalHoras(a.get().getTotalHoras());
//                response.setPromedio(a.get().getPromedio());
//
//                response.setNombretutoremp(a.get().getNombretutoremp());
//                response.setCedulatutoremp(a.get().getCedulatutoremp());
//                response.setSiglascarrera(a.get().getSiglascarrera());
//                response.setEmpresa(a.get().getEmpresa());
//                response.setTutorempPuntaje(a.get().getTutorempPuntaje());
//
//                response.setIdProyecto(a.get().getProyectoPPP().getId());
//                response.setDocumento(a.get().getDocumento());
//
//                List<ActividadesAnexo121Request> list = a.get().getActividades().stream().map(ap -> {
//                    ActividadesAnexo121Request request = new ActividadesAnexo121Request();
//                    request.setId(ap.getId());
//                    request.setDescripcion(ap.getDescripcion());
//                    return request;
//                }).collect(Collectors.toList());
//                response.setActividades(list);
//                return response;
//            }
//            throw new BadRequestException("No existe el anexo con id de la solicitud: " + proyectoId);
//        }
//
//        throw new BadRequestException("No existe la solicitud: " + proyectoId);
//    }



    public List<Anexo121certificadoResponse> listAnexo12ByProyectoPPP(Long pryectoId) {
        Optional<ProyectoPPP> optional = proyectoRepository.findById(pryectoId);
        if (optional.isPresent()) {
            List<Anexo121certificado> b = anexo121certificadoRepository.findByProyectoPPP(optional.get());
            return b.stream().map(a -> {
                Anexo121certificadoResponse response = new Anexo121certificadoResponse();
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

                List<ActividadesAnexo121Request> list = a.getActividades().stream().map(ap -> {
                    ActividadesAnexo121Request request = new ActividadesAnexo121Request();
                    request.setId(ap.getId());
                    request.setDescripcion(ap.getDescripcion());
                    return request;
                }).collect(Collectors.toList());
                response.setActividades(list);
                return response;}).collect(Collectors.toList());

        }
        throw new BadRequestException("No existe el proyecto " + pryectoId);
    }

}
