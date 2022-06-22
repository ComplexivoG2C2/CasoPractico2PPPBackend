package com.tecazuay.complexivog2c2.service.Anexos;

import com.tecazuay.complexivog2c2.dto.anexos.AlumnosAnexo6Request;
import com.tecazuay.complexivog2c2.dto.anexos.Anexo6Request;
import com.tecazuay.complexivog2c2.dto.anexos.Anexo6Response;
import com.tecazuay.complexivog2c2.dto.anexos.TutorAcademicoAnexo6Response;
import com.tecazuay.complexivog2c2.exception.BadRequestException;
import com.tecazuay.complexivog2c2.exception.ResponseNotFoundException;
import com.tecazuay.complexivog2c2.model.Primary.Anexos.AlumnosAnexo6;
import com.tecazuay.complexivog2c2.model.Primary.Anexos.Anexo6;
import com.tecazuay.complexivog2c2.model.Primary.solicitudproyecto.ProyectoPPP;
import com.tecazuay.complexivog2c2.model.Primary.usuario.Usuario;
import com.tecazuay.complexivog2c2.repository.Primary.Anexos.AlumnosAnexo6Repository;
import com.tecazuay.complexivog2c2.repository.Primary.Anexos.Anexo6Repository;
import com.tecazuay.complexivog2c2.repository.Primary.solicitudproyecto.ProyectoRepository;
import com.tecazuay.complexivog2c2.repository.Primary.usuario.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class Anexo6Service {

    @Autowired
    private Anexo6Repository anexo6Repository;

    @Autowired
    private AlumnosAnexo6Repository alumnosAnexo6Repository;

    @Autowired
    private ProyectoRepository proyectoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;


//    public boolean save(Anexo5Request request) {
//        Optional<ProyectoPPP> optional = proyectoRepository.findById(request.getIdProyectoPPP());
//
//        if (optional.isPresent()) {
//           // if (!anexo5Repository.existsByProyectoPPP(optional.get())) {
//                Anexo5 anexo5 = new Anexo5();
//
//                anexo5.setFechaEmision(request.getFechaEmision());
//                anexo5.setTituloTercerN(request.getTituloTercerN());
//                anexo5.setTituloCuartoN(request.getTituloCuartoN());
//                anexo5.setNombreDocenteReceptor(request.getNombreDocenteReceptor());
//                anexo5.setDirectorD(request.getDirectorD());
//                anexo5.setSiglasCarrera(request.getSiglasCarrera());
//                anexo5.setNonbreDocenteEmisor(request.getNonbreDocenteEmisor());
//                anexo5.setFechaRecepcion(request.getFechaRecepcion());
//                anexo5.setDocumento(request.getDocumento());
//                anexo5.setCedulaDocenteApoyo(request.getCedulaDocenteApoyo());
//                anexo5.setProyectoPPP(optional.get());
//
//                List<AlumnosAnexo5> list = new ArrayList<>();
//
//                request.getAlumnos().stream().forEach(a -> {
//                    AlumnosAnexo5 alumnosAnexo5 = new AlumnosAnexo5();
//                    alumnosAnexo5.setNombreEstudiante(a.getNombreEstudiante());
//                    alumnosAnexo5.setCedulaEstudiante(a.getCedulaEstudiante());
//                    list.add(alumnosAnexo5);
//                });
//
//                try {
//                    saveAlumnos(list, anexo5Repository.save(anexo5));
//                    return true;
//                } catch (Exception e) {
//                    throw new BadRequestException("No se guardó el anexo 5" + e);
//                }
////            } else {
////                throw new BadRequestException("Ya existe el anexo con ese id de proyecto");
////            }
//        }
//        throw new BadRequestException("No existe el proyecto con id: " + request.getIdProyectoPPP());
//    }

    public boolean save(Anexo6Request request) {
        Optional<ProyectoPPP> optional = proyectoRepository.findById(request.getIdProyectoPPP());

        if (optional.isPresent()) {
            if (!optional.get().isEstado())
                throw new BadRequestException("El proyecto ha finalizado, no es posible modificar sus datos");
//            if (!anexo5Repository.existsByProyectoPPP(optional.get())) {

            if (isAlumnoAsignado(request.getAlumnos(), optional.get())) {

                Anexo6 anexo = new Anexo6();

                anexo.setFechaEmision(request.getFechaEmision());
                anexo.setTituloTercerN(request.getTituloTercerN());
                anexo.setTituloCuartoN(request.getTituloCuartoN());
                anexo.setNombreDocenteReceptor(request.getNombreDocenteReceptor());
                anexo.setSiglasCarrera(request.getSiglasCarrera());
                anexo.setNonbreDocenteEmisor(request.getNonbreDocenteEmisor());
                anexo.setFechaRecepcion(request.getFechaRecepcion());
                anexo.setDocumento(request.getDocumento());
                anexo.setCedulaDocenteApoyo(request.getCedulaDocenteApoyo());
                anexo.setProyectoPPP(optional.get());
                anexo.setNombreProyecto(optional.get().getNombre());
                anexo.setNum_proceso(request.getNum_proceso());

                List<AlumnosAnexo6> list = new ArrayList<>();
                request.getAlumnos().stream().forEach(a -> {
                    AlumnosAnexo6 alumnosAnexo6 = new AlumnosAnexo6();
                    alumnosAnexo6.setNombreEstudiante(a.getNombreEstudiante());
                    alumnosAnexo6.setCedulaEstudiante(a.getCedulaEstudiante());
                    list.add(alumnosAnexo6);
                });

                try {
                    saveAlumnos(list, anexo6Repository.save(anexo));
                    return true;
                } catch (Exception e) {
                    throw new BadRequestException("No se guardó el anexo 5" + e);
                }
            }
        }
        throw new BadRequestException("No existe el proyecto con id: " + request.getIdProyectoPPP());
    }

    @Transactional(readOnly = true)
    boolean isAlumnoAsignado(List<AlumnosAnexo6Request> alumnos, ProyectoPPP proyecto) {
        List<String> cedulas = alumnos.stream()
                .map(AlumnosAnexo6Request::getCedulaEstudiante)
                .collect(Collectors.toList());

        List<String> cedulasAsignadas = new ArrayList<>();

        cedulas.forEach(cedula -> {
            List<AlumnosAnexo6> listAlumno = alumnosAnexo6Repository.findAllByCedulaEstudiante(cedula);
            listAlumno.stream()
                    .filter(a -> Objects.equals(a.getAnexo6().getProyectoPPP().getId(), proyecto.getId()))
                    .findAny()
                    .ifPresent(alumno -> cedulasAsignadas.add(cedula));
        });

        if (cedulasAsignadas.size() == 0) return true;
        String msg = cedulasAsignadas.stream().reduce((s, s2) -> s.concat(", " + s2)).get();
        throw new BadRequestException("Los alumnos con las cédulas ya están asignados a un docente de apoyo en este proyecto: " + msg);
    }

    private void saveAlumnos(List<AlumnosAnexo6> list, Anexo6 anexo6) {
        list.stream().forEach(a -> {
            a.setAnexo6(anexo6);
            alumnosAnexo6Repository.save(a);
        });
    }

    public void update(Anexo6Request request) {
        Optional<Anexo6> optional = anexo6Repository.findById(request.getId());
        if (optional.isPresent()) {
            if (!optional.get().getProyectoPPP().isEstado())
                throw new BadRequestException("El proyecto ha finalizado, no es posible modificar sus datos");

            Anexo6 anexo = optional.get();
            anexo.setFechaEmision(request.getFechaEmision());
            anexo.setTituloTercerN(request.getTituloTercerN());
            anexo.setTituloCuartoN(request.getTituloCuartoN());
            anexo.setNombreDocenteReceptor(request.getNombreDocenteReceptor());
            anexo.setSiglasCarrera(request.getSiglasCarrera());
            anexo.setNonbreDocenteEmisor(request.getNonbreDocenteEmisor());
            anexo.setFechaRecepcion(request.getFechaRecepcion());
            anexo.setCedulaDocenteApoyo(request.getCedulaDocenteApoyo());
            anexo.setDocumento(request.getDocumento());
            anexo.setNum_proceso(request.getNum_proceso());
            //anexo5.setProyectoPPP(proyectoRepository.findById(request.getIdProyectoPPP()).orElse(new ProyectoPPP()));
            List<AlumnosAnexo6> alumnosAnexo6s = new ArrayList<>();
            request.getAlumnos().stream().forEach(a -> {
                AlumnosAnexo6 alumnos = new AlumnosAnexo6();
                alumnos.setCedulaEstudiante(a.getCedulaEstudiante());
                alumnos.setNombreEstudiante(a.getNombreEstudiante());
                alumnosAnexo6s.add(alumnos);
            });

            try {
                Anexo6 a = anexo6Repository.save(anexo);
                saveAlumnos(alumnosAnexo6s, a);
            } catch (Exception ex) {
                throw new BadRequestException("No se guardó el anexo 5" + ex);
            }
        } else
            throw new ResponseNotFoundException("Anexo 5", "Id:", request.getId() + "");
    }

    public void updateMS(Anexo6Request request) {
        Optional<Anexo6> optional = anexo6Repository.findById(request.getId());
        if (optional.isPresent()) {
            if (!optional.get().getProyectoPPP().isEstado())
                throw new BadRequestException("El proyecto ha finalizado, no es posible modificar sus datos");

            Anexo6 anexo = optional.get();
            anexo.setFechaEmision(request.getFechaEmision());
            anexo.setTituloTercerN(request.getTituloTercerN());
            anexo.setTituloCuartoN(request.getTituloCuartoN());
            anexo.setNombreDocenteReceptor(request.getNombreDocenteReceptor());
            anexo.setSiglasCarrera(request.getSiglasCarrera());
            anexo.setNonbreDocenteEmisor(request.getNonbreDocenteEmisor());
            anexo.setFechaRecepcion(request.getFechaRecepcion());
            anexo.setCedulaDocenteApoyo(request.getCedulaDocenteApoyo());
            anexo.setDocumento(request.getDocumento());
            anexo.setNum_proceso(request.getNum_proceso());


            try {
                Anexo6 a = anexo6Repository.save(anexo);
                updateAlumnos(request.getId(), request.getAlumnos());

            } catch (Exception ex) {
                throw new BadRequestException("No se guardó el anexo 5" + ex);
            }
        } else
            throw new ResponseNotFoundException("Anexo 5", "Id:", request.getId() + "");
    }

    @Transactional
    public void updateAlumnos(Long id, List<AlumnosAnexo6Request> requisitos) {

        Optional<Anexo6> optional = anexo6Repository.findById(id);

        if (optional.isPresent()) {
            List<AlumnosAnexo6> proyecto = optional.get().getAlumnosAnexo6s();
            proyecto.forEach(r -> {
                Optional<AlumnosAnexo6Request> exists = requisitos
                        .stream()
                        .filter(req -> req.getCedulaEstudiante().equalsIgnoreCase(r.getCedulaEstudiante()))
                        .findAny();
                if (exists.isEmpty()) {
                    alumnosAnexo6Repository.delete(r);
                }
            });

            requisitos.forEach(request -> {
                String descripcion = request.getCedulaEstudiante();
                Optional<AlumnosAnexo6> exists = proyecto
                        .stream()
                        .filter(r -> r.getCedulaEstudiante().equalsIgnoreCase(descripcion))
                        .findAny();
                if (exists.isEmpty()) {
                    AlumnosAnexo6 save = new AlumnosAnexo6();
                    save.setAnexo6(optional.get());
                    save.setCedulaEstudiante(request.getCedulaEstudiante());
                    save.setNombreEstudiante(request.getNombreEstudiante());

                    alumnosAnexo6Repository.save(save);
                }
            });
        } else {
            throw new BadRequestException("El anexo con id: " + id + ", no existe");
        }
    }

    @Transactional
    public List<Anexo6Response> listAll() {
        return anexo6Repository.findAll().stream().map(a -> {
            Anexo6Response response = new Anexo6Response();
            response.setId(a.getId());
            response.setFechaEmision(a.getFechaEmision());
            response.setTituloTercerN(a.getTituloTercerN());
            response.setTituloCuartoN(a.getTituloCuartoN());
            response.setNombreDocenteReceptor(a.getNombreDocenteReceptor());
            response.setSiglasCarrera(a.getSiglasCarrera());
            response.setNonbreDocenteEmisor(a.getNonbreDocenteEmisor());
            response.setFechaRecepcion(a.getFechaRecepcion());
            response.setCedulaDocenteApoyo(a.getCedulaDocenteApoyo());
            response.setNombreProyecto(a.getNombreProyecto());
            response.setDocumento(a.getDocumento());
            response.setNum_proceso(a.getNum_proceso());
            List<AlumnosAnexo6Request> list = a.getAlumnosAnexo6s().stream().map(ac -> {
                AlumnosAnexo6Request request = new AlumnosAnexo6Request();
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

    public List<Anexo6Response> anexoIdProyecto(Long proyectoId) {
        Optional<ProyectoPPP> optional = proyectoRepository.findById(proyectoId);
        if (optional.isPresent()) {
            List<Anexo6> c = anexo6Repository.findByProyectoPPP(optional.get());
            return c.stream().map(a -> {
                Anexo6Response response = new Anexo6Response();
                response.setId(a.getId());
                response.setSiglasCarrera(a.getSiglasCarrera());
                response.setFechaEmision(a.getFechaEmision());
                response.setTituloTercerN(a.getTituloTercerN());
                response.setTituloCuartoN(a.getTituloCuartoN());
                response.setNombreDocenteReceptor(a.getNombreDocenteReceptor());
                response.setSiglasCarrera(a.getSiglasCarrera());
                response.setNonbreDocenteEmisor(a.getNonbreDocenteEmisor());
                response.setFechaRecepcion(a.getFechaRecepcion());
                response.setCedulaDocenteApoyo(a.getCedulaDocenteApoyo());
                response.setNum_proceso(a.getNum_proceso());
                List<AlumnosAnexo6Request> list = a.getAlumnosAnexo6s().stream().map(ac -> {
                    AlumnosAnexo6Request request = new AlumnosAnexo6Request();
                    request.setNombreEstudiante(ac.getNombreEstudiante());
                    request.setCedulaEstudiante(ac.getCedulaEstudiante());
                    request.setId(ac.getId());
                    return request;
                }).collect(Collectors.toList());
                response.setAlumnos(list);
                response.setIdProyectoPPP(a.getProyectoPPP().getId());
                return response;
            }).collect(Collectors.toList());
        }
        throw new BadRequestException("No existe la solicitud: " + proyectoId);
    }

    //Listar por id de Anexo
    public Anexo6Response listaAnexo5ById(Long id) {
        Optional<Anexo6> a = anexo6Repository.findById(id);
        if (a.isPresent()) {
            Anexo6Response response = new Anexo6Response();
            response.setId(a.get().getId());
            response.setSiglasCarrera(a.get().getSiglasCarrera());
            response.setFechaEmision(a.get().getFechaEmision());
            response.setTituloTercerN(a.get().getTituloTercerN());
            response.setTituloCuartoN(a.get().getTituloCuartoN());
            response.setNombreDocenteReceptor(a.get().getNombreDocenteReceptor());
            response.setSiglasCarrera(a.get().getSiglasCarrera());
            response.setNonbreDocenteEmisor(a.get().getNonbreDocenteEmisor());
            response.setFechaRecepcion(a.get().getFechaRecepcion());
            response.setCedulaDocenteApoyo(a.get().getCedulaDocenteApoyo());
            response.setNombreProyecto(a.get().getNombreProyecto());
            response.setDocumento(a.get().getDocumento());
            response.setNum_proceso(a.get().getNum_proceso());
            List<AlumnosAnexo6Request> list = a.get().getAlumnosAnexo6s().stream().map(ac -> {
                AlumnosAnexo6Request request = new AlumnosAnexo6Request();
                request.setNombreEstudiante(ac.getNombreEstudiante());
                request.setCedulaEstudiante(ac.getCedulaEstudiante());
                request.setId(ac.getId());
                return request;
            }).collect(Collectors.toList());
            response.setAlumnos(list);
            response.setIdProyectoPPP(a.get().getProyectoPPP().getId());
            return response;
        }
        throw new BadRequestException("No existe el anexo con id: " + id);
    }

    @Transactional
    public List<Anexo6Response> findAllByCedula(String cedula) {
        return anexo6Repository.findAllByCedulaDocenteApoyo(cedula).stream().map(a -> {
            Anexo6Response response = new Anexo6Response();
            response.setId(a.getId());
            response.setFechaEmision(a.getFechaEmision());
            response.setTituloTercerN(a.getTituloTercerN());
            response.setTituloCuartoN(a.getTituloCuartoN());
            response.setNombreDocenteReceptor(a.getNombreDocenteReceptor());
            response.setSiglasCarrera(a.getSiglasCarrera());
            response.setNonbreDocenteEmisor(a.getNonbreDocenteEmisor());
            response.setFechaRecepcion(a.getFechaRecepcion());
            response.setCedulaDocenteApoyo(a.getCedulaDocenteApoyo());
            response.setDocumento(a.getDocumento());
            response.setNombreProyecto(a.getNombreProyecto());
            response.setNum_proceso(a.getNum_proceso());
            List<AlumnosAnexo6Request> list = a.getAlumnosAnexo6s().stream().map(ac -> {
                AlumnosAnexo6Request request = new AlumnosAnexo6Request();
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


    public TutorAcademicoAnexo6Response docenteApoyoByEstudiante(String cedula, Long idProyectoPPP) {
        List<AlumnosAnexo6> alumnos = alumnosAnexo6Repository.findAllByCedulaEstudiante(cedula);
        Optional<ProyectoPPP> optional = proyectoRepository.findById(idProyectoPPP);
        if (optional.isPresent()) {
            TutorAcademicoAnexo6Response  daResponse = new TutorAcademicoAnexo6Response ();
            alumnos.stream().forEach(alumnosAnexo5 -> {
                if (Objects.equals(alumnosAnexo5.getAnexo6().getProyectoPPP().getId(), idProyectoPPP)) {
                    daResponse.setNombreDApoyo(alumnosAnexo5.getAnexo6().getNombreDocenteReceptor());
                    daResponse.setCedulaDAapoyo(alumnosAnexo5.getAnexo6().getCedulaDocenteApoyo());
                    daResponse.setCorreoDApoyo(correoPorUsuario(alumnosAnexo5.getAnexo6().getCedulaDocenteApoyo()));
                }
            });
            return daResponse;
        }
        throw new BadRequestException("No se encontró el Proyecto con id: " + idProyectoPPP);
    }

    private String correoPorUsuario(String cedula) {
        Optional<Usuario> optional = usuarioRepository.findByCedula(cedula);
        if (optional.isPresent()) {
            return optional.get().getEmail();
        }
        return "";
    }

    public void deleteById(Long id) {
        Optional<Anexo6> optional = anexo6Repository.findById(id);
        if (optional.isEmpty()) {
            throw new BadRequestException("El anexo 5 con el id " + id + ", no existe");

        }

        if (!optional.get().getProyectoPPP().isEstado())
            throw new BadRequestException("El proyecto ha finalizado, no es posible modificar sus datos");

        anexo6Repository.deleteById(id);
    }

}