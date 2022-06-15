package com.tecazuay.complexivog2c2.service.Anexos;

import com.tecazuay.complexivog2c2.dto.anexos.Anexo4Request;
import com.tecazuay.complexivog2c2.dto.anexos.Anexo4Response;
import com.tecazuay.complexivog2c2.dto.anexos.ListaEstudiantesAnexo4Request;
import com.tecazuay.complexivog2c2.exception.BadRequestException;
import com.tecazuay.complexivog2c2.exception.ResponseNotFoundException;
import com.tecazuay.complexivog2c2.model.Primary.Anexos.ActividadesAnexo2;
import com.tecazuay.complexivog2c2.model.Primary.Anexos.Anexo2;
import com.tecazuay.complexivog2c2.model.Primary.Anexos.Anexo4;
import com.tecazuay.complexivog2c2.model.Primary.Anexos.ListaEstudiantesAnexo4;
import com.tecazuay.complexivog2c2.model.Primary.solicitudproyecto.ProyectoPPP;
import com.tecazuay.complexivog2c2.model.Secondary.alumnos.VAlumnosppp;
import com.tecazuay.complexivog2c2.repository.Primary.Anexos.Anexo4Repository;
import com.tecazuay.complexivog2c2.repository.Primary.Anexos.ListaEstudiantesAnexo4Repository;
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
public class Anexo4Service {

    @Autowired
    private Anexo4Repository anexo4Repository;

    @Autowired
    private ListaEstudiantesAnexo4Repository listaEstudiantesAnexo4Repository;

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


    public boolean save(Anexo4Request request){
        Optional<ProyectoPPP> optional = proyectoRepository.findById(request.getIdProyectoPPP());
        if (optional.isPresent()) {
            if (!optional.get().isEstado())
                throw new BadRequestException("El proceso a finalizado");
            if (!anexo4Repository.existsByProyectoPPP(optional.get())) {
                Anexo4 anexo4=new Anexo4();
                anexo4.setFechaRespuesta(request.getFechaRespuesta());
                anexo4.setNombreRepresentanteEmp(request.getNombreRepresentanteEmp());
                anexo4.setCargoEmpresa(request.getCargoEmpresa());
                anexo4.setNombreEmpresa(request.getNombreEmpresa());
                anexo4.setFechaSolicitudEmp(request.getFechaSolicitudEmp());
                anexo4.setNombreResponsable(request.getNombreResponsable());
                anexo4.setCarrera(request.getCarrera());
                anexo4.setDocumento(request.getDocumento());
                anexo4.setProyectoPPP(optional.get());
                anexo4.setNum_proceso(request.getNum_proceso());


                    List<ListaEstudiantesAnexo4> list = new ArrayList<>();

                    request.getListaEstudiantes().stream().forEach(a -> {
                        ListaEstudiantesAnexo4 listaEstudiantesAnexo4 = new ListaEstudiantesAnexo4();
                        listaEstudiantesAnexo4.setCedula(a.getCedula());
                        listaEstudiantesAnexo4.setNombre(a.getNombre());
                        list.add(listaEstudiantesAnexo4);
                    });

                    try {
                        saveListaEstudiantes(list, anexo4Repository.save(anexo4));
                    } catch (Exception e) {
                        throw new BadRequestException("No se guardó el anexo 4" + e);
                    }
                    try {

                        return true;
                    } catch (Exception ex) {
                        throw new BadRequestException(".......");
                    }
                } else {
                    throw new BadRequestException("Ya existe el anexo con ese id de solicitud");
                }
            }
            throw new BadRequestException("No existe el proyecto con id: " + request.getIdProyectoPPP());
        }

    @Transactional
    public boolean update(Anexo4Request request) {
        Optional<Anexo4> optional = anexo4Repository.findById(request.getId());
        if (optional.isPresent()) {
            if (!optional.get().getProyectoPPP().isEstado())
                throw new BadRequestException("El proyecto ha finalizado, no es posible modificar sus datos");

            Anexo4 anexo4 = optional.get();
            anexo4.setFechaRespuesta(request.getFechaRespuesta());
            anexo4.setNombreRepresentanteEmp(request.getNombreRepresentanteEmp());
            anexo4.setCargoEmpresa(request.getCargoEmpresa());
            anexo4.setNombreEmpresa(request.getNombreEmpresa());
            anexo4.setFechaSolicitudEmp(request.getFechaSolicitudEmp());
            anexo4.setNombreResponsable(request.getNombreResponsable());
            anexo4.setCarrera(request.getCarrera());
            anexo4.setDocumento(request.getDocumento());
            anexo4.setNum_proceso(request.getNum_proceso());
            anexo4.setProyectoPPP(proyectoRepository.findById(request.getIdProyectoPPP()).orElseThrow(() -> new BadRequestException("No existe proyecto con id: " + request.getIdProyectoPPP())));

            try {

                anexo4Repository.save(anexo4);
                updateListaEstudiantes(request.getId(), request.getListaEstudiantes());

                return true;
            } catch (Exception ex) {
                throw new BadRequestException("No se guardÃ³ el anexo 4" + ex);
            }
        }
        return false;
    }

    @Transactional
    public List<Anexo4Response> listAll() {
        return anexo4Repository.findAll().stream().map(a -> {
            Anexo4Response response = new Anexo4Response();
            response.setId(a.getId());
            response.setFechaRespuesta(a.getFechaRespuesta());
            response.setNombreRepresentanteEmp(a.getNombreRepresentanteEmp());
            response.setCargoEmpresa(a.getCargoEmpresa());
            response.setNombreEmpresa(a.getNombreEmpresa());
            response.setFechaSolicitudEmp(a.getFechaSolicitudEmp());
            response.setNombreResponsable(a.getNombreResponsable());
            response.setCarrera(a.getCarrera());
            response.setNombreResponsable(a.getNombreResponsable());
            response.setDocumento(a.getDocumento());
            response.setNum_proceso(a.getNum_proceso());

            List<ListaEstudiantesAnexo4Request> list = a.getListaEstudiantesAnexo4().stream().map(ac -> {
                ListaEstudiantesAnexo4Request request = new ListaEstudiantesAnexo4Request();
                request.setNombre(ac.getNombre());
                request.setNombre(ac.getNombre());
                request.setId(ac.getId());
                return request;
            }).collect(Collectors.toList());
            response.setListaEstudiantes(list);
            response.setIdProyectoPPP(a.getProyectoPPP().getId());
            return response;
        }).collect(Collectors.toList());
    }

    @Transactional
    public void deleteAnexo4ById(Long id) {
        Optional<Anexo4> optional = anexo4Repository.findById(id);
        if (optional.isEmpty()) {
            throw new BadRequestException("El anexo4 con id: " + id + ", no existe");
        }
        if (!optional.get().getProyectoPPP().isEstado())
            throw new BadRequestException("El proyecto ha finalizado, no es posible modificar sus datos");

        optional.get().getListaEstudiantesAnexo4()
                .forEach(d -> deleteListaEstudiantesById(d.getId()));


        anexo4Repository.delete(optional.get());
    }


    public Anexo4Response anexoIdProyecto(Long proyectoId) {
        Optional<ProyectoPPP> optional = proyectoRepository.findById(proyectoId);
        if (optional.isPresent()) {
            Optional<Anexo4> a = anexo4Repository.findAllByProyectoPPP(optional.get());
            if (a.isPresent()) {
                Anexo4Response response = new Anexo4Response();
                response.setId(a.get().getId());
                response.setFechaRespuesta(a.get().getFechaRespuesta());
                response.setNombreRepresentanteEmp(a.get().getNombreRepresentanteEmp());
                response.setCargoEmpresa(a.get().getCargoEmpresa());
                response.setNombreEmpresa(a.get().getNombreEmpresa());
                response.setFechaSolicitudEmp(a.get().getFechaSolicitudEmp());
                response.setNombreResponsable(a.get().getNombreResponsable());
                response.setCarrera(a.get().getCarrera());
                response.setNombreResponsable(a.get().getNombreResponsable());
                response.setDocumento(a.get().getDocumento());
                response.setNum_proceso(a.get().getNum_proceso());
                List<ListaEstudiantesAnexo4Request> list = a.get().getListaEstudiantesAnexo4().stream().map(ac -> {
                    ListaEstudiantesAnexo4Request request = new ListaEstudiantesAnexo4Request();
                    request.setCedula(ac.getCedula());
                    request.setNombre(ac.getNombre());
                    request.setId(ac.getId());
                    return request;
                }).collect(Collectors.toList());
                response.setListaEstudiantes(list);
                response.setIdProyectoPPP(a.get().getProyectoPPP().getId());
                return response;
            }
            throw new BadRequestException("No existe el anexo con id de la solicitud: " + proyectoId);
        }

        throw new BadRequestException("No existe la solicitud: " + proyectoId);
    }

    private void saveListaEstudiantes(List<ListaEstudiantesAnexo4> list, Anexo4 anexo4) {
        list.stream().forEach(a -> {
            a.setAnexo4(anexo4);
            listaEstudiantesAnexo4Repository.save(a);
        });
    }




    @Transactional
    public void updateListaEstudiantes(Long id, List<ListaEstudiantesAnexo4Request> requisitos) {

        Optional<Anexo4> optional = anexo4Repository.findById(id);

        if (optional.isPresent()) {
            List<ListaEstudiantesAnexo4> proyecto = optional.get().getListaEstudiantesAnexo4();
            proyecto.forEach(r -> {
                Optional<ListaEstudiantesAnexo4Request> exists = requisitos
                        .stream()
                        .filter(req -> req.getCedula().equalsIgnoreCase(r.getCedula()))
                        .findAny();
                if (exists.isEmpty()) {
                    listaEstudiantesAnexo4Repository.delete(r);
                }
            });

            requisitos.forEach(request -> {
                String cedula = request.getCedula();
                Optional<ListaEstudiantesAnexo4> exists = proyecto
                        .stream()
                        .filter(r -> r.getCedula().equalsIgnoreCase(cedula))
                        .findAny();
                if (exists.isEmpty()) {
                    ListaEstudiantesAnexo4 save = new ListaEstudiantesAnexo4();
                    save.setAnexo4(optional.get());
                    save.setCedula(request.getCedula());
                    save.setNombre(request.getNombre());
                    listaEstudiantesAnexo4Repository.save(save);
                }
            });
        } else {
            throw new BadRequestException("El proyecto con id: " + id + ", no existe");
        }
    }

    public void deleteListaEstudiantesById(Long id) {
        Optional<ListaEstudiantesAnexo4> optional = listaEstudiantesAnexo4Repository.findById(id);
        if (optional.isEmpty()) {
            throw new BadRequestException("Las actividades con id: " + id + ", no existen");
        }
        listaEstudiantesAnexo4Repository.deleteById(id);
    }


}


//
//import java.util.List;
//import java.util.Optional;
//import java.util.stream.Collectors;
//
//@Service
//public class Anexo4Service {
//
//    @Autowired
//    private Anexo4Repository anexo4Repository;
//
//    @Autowired
//    private ProyectoRepository proyectoRepository;
//
//    @Autowired
//    private VAlumnosRepository vAlumnosRepository;
//
//    @Autowired
//    private UsuarioRepository usuarioRepository;
//
//    @Autowired
//    private EmailService emailService;
//
//    @Autowired
//    private RequisitosRepository requisitosRepository;
//
//    public boolean save(Anexo4Request request){
//        Optional<ProyectoPPP> optional = proyectoRepository.findById(request.getIdProyectoPPP());
//        if (optional.isPresent()) {
//            if (!optional.get().isEstado())
//                throw new BadRequestException("El proceso a finalizado");
//            if (!anexo4Repository.existsByProyectoPPP(optional.get())) {
//                Anexo4 anexo4=new Anexo4();
//                anexo4.setFechaRespuesta(request.getFechaRespuesta());
//                anexo4.setNombreRepresentanteEmp(request.getNombreRepresentanteEmp());
//                anexo4.setCargoEmpresa(request.getCargoEmpresa());
//                anexo4.setNombreEmpresa(request.getNombreEmpresa());
//                anexo4.setFechaSolicitudEmp(request.getFechaSolicitudEmp());
//                anexo4.setNombreResponsable(request.getNombreResponsable());
//                anexo4.setCarrera(request.getCarrera());
//                anexo4.setDocumento(request.getDocumento());
//                anexo4.setProyectoPPP(optional.get());
//                anexo4.setNum_proceso(request.getNum_proceso());
//                try {
//                    anexo4Repository.save(anexo4);
//                } catch (Exception e) {
//                    throw new BadRequestException("No se guardó el anexo 4" + e);
//                }
//            }else {
//                throw new ResponseNotFoundException("Estudiante", "CEDULA:", "" + request.getId());
//            }
//        }
//        throw new BadRequestException("No existe el proyecto con id: " + request.getIdProyectoPPP());
//    }
//
//    @Transactional
//    public List<Anexo4Response> listAll() {
//        return anexo4Repository.findAll().stream().map(a -> {
//            Anexo4Response response = new Anexo4Response();
//            response.setId(a.getId());
//            response.setFechaRespuesta(a.getFechaRespuesta());
//            response.setNombreRepresentanteEmp(a.getNombreRepresentanteEmp());
//            response.setCargoEmpresa(a.getCargoEmpresa());
//            response.setNombreEmpresa(a.getNombreEmpresa());
//            response.setFechaSolicitudEmp(a.getFechaSolicitudEmp());
//            response.setNombreResponsable(a.getNombreResponsable());
//            response.setCarrera(a.getCarrera());
//            response.setNombreResponsable(a.getNombreResponsable());
//            response.setDocumento(a.getDocumento());
//            response.setNum_proceso(a.getNum_proceso());
//            response.setIdProyectoPPP(a.getProyectoPPP().getId());
//            return response;
//        }).collect(Collectors.toList());
//    }
//
//    public Anexo4Response anexoIdProyecto(Long proyectoId) {
//        Optional<ProyectoPPP> optional = proyectoRepository.findById(proyectoId);
//        if (optional.isPresent()) {
//            Optional<Anexo4> a = anexo4Repository.findByProyectoPPP(optional.get());
//            if (a.isPresent()) {
//                Anexo4Response response = new Anexo4Response();
//                response.setId(a.get().getId());
//                response.setFechaRespuesta(a.get().getFechaRespuesta());
//                response.setNombreRepresentanteEmp(a.get().getNombreRepresentanteEmp());
//                response.setCargoEmpresa(a.get().getCargoEmpresa());
//                response.setNombreEmpresa(a.get().getNombreEmpresa());
//                response.setFechaSolicitudEmp(a.get().getFechaSolicitudEmp());
//                response.setNombreResponsable(a.get().getNombreResponsable());
//                response.setCarrera(a.get().getCarrera());
//                response.setNombreResponsable(a.get().getNombreResponsable());
//                response.setDocumento(a.get().getDocumento());
//                response.setNum_proceso(a.get().getNum_proceso());
//                response.setIdProyectoPPP(a.get().getProyectoPPP().getId());
//                response.setNum_proceso(a.get().getNum_proceso());
//                response.setIdProyectoPPP(a.get().getProyectoPPP().getId());
//                return response;
//            }
//            throw new BadRequestException("No existe el anexo con id de la solicitud: " + proyectoId);
//        }
//
//        throw new BadRequestException("No existe la solicitud: " + proyectoId);
//    }
//
//}
