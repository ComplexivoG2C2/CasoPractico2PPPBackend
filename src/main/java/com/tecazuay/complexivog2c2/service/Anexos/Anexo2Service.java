package com.tecazuay.complexivog2c2.service.Anexos;

import com.tecazuay.complexivog2c2.dto.anexos.ActividadesAnexo2Request;
import com.tecazuay.complexivog2c2.dto.anexos.Anexo2Request;
import com.tecazuay.complexivog2c2.dto.anexos.Anexo2Response;
import com.tecazuay.complexivog2c2.dto.email.EmailBody;
import com.tecazuay.complexivog2c2.dto.materias.MateriaNombre;
import com.tecazuay.complexivog2c2.exception.BadRequestException;
import com.tecazuay.complexivog2c2.model.Primary.Anexos.ActividadesAnexo2;
import com.tecazuay.complexivog2c2.model.Primary.Anexos.Anexo2;
import com.tecazuay.complexivog2c2.model.Primary.solicitudproyecto.ProyectoPPP;
import com.tecazuay.complexivog2c2.model.Primary.solicitudproyecto.RequisitosProyecto;
import com.tecazuay.complexivog2c2.model.Primary.usuario.Usuario;
import com.tecazuay.complexivog2c2.model.Secondary.alumnos.VAlumnosppp;
import com.tecazuay.complexivog2c2.repository.Primary.Anexos.ActividadesAnexo2Repository;
import com.tecazuay.complexivog2c2.repository.Primary.Anexos.Anexo2Repository;
import com.tecazuay.complexivog2c2.repository.Primary.solicitudproyecto.ProyectoRepository;
import com.tecazuay.complexivog2c2.repository.Primary.solicitudproyecto.RequisitosRepository;
import com.tecazuay.complexivog2c2.repository.Primary.usuario.UsuarioRepository;
import com.tecazuay.complexivog2c2.repository.Secondary.alumnos.VAlumnosRepository;
import com.tecazuay.complexivog2c2.service.email.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class Anexo2Service {
    @Autowired
    private Anexo2Repository anexo2Repository;

    @Autowired
    private ActividadesAnexo2Repository actividadesAnexo2Repository;

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


    public boolean save(Anexo2Request request) {
        Optional<ProyectoPPP> optional = proyectoRepository.findById(request.getIdProyectoPPP());
        if (optional.isPresent()) {
//            if (!optional.get().isEstado())
//                throw new BadRequestException("El proceso a finalizado");
            if (!anexo2Repository.existsByProyectoPPP(optional.get())) {
                Anexo2 anexo2 = new Anexo2();
                anexo2.setSiglasCarrera(request.getSiglasCarrera());
                anexo2.setAnio(request.getAnio());
                anexo2.setNumeroConvocatoria(generateCode(request.getSiglasCarrera()) + "");
                anexo2.setFecha(request.getFecha());
                anexo2.setCarrera(request.getCarrera());
                anexo2.setCiclo(request.getCiclo());
                anexo2.setNombreProyecto(request.getNombreProyecto());
                anexo2.setEmpresa(request.getEmpresa());
                anexo2.setFechaMaxRecepcion(request.getFechaMaxRecepcion());
                anexo2.setNombreResponsable(request.getNombreResponsable());
                anexo2.setEmailDocente(request.getEmailDocente());
                anexo2.setDocumento(request.getDocumento());
                anexo2.setProyectoPPP(optional.get());
                anexo2.setNum_proceso(request.getNum_proceso());


                List<ActividadesAnexo2> list = new ArrayList<>();

                request.getActividades().stream().forEach(a -> {
                    ActividadesAnexo2 actividadesAnexo2 = new ActividadesAnexo2();
                    actividadesAnexo2.setDescripcion(a.getDescripcion());
                    list.add(actividadesAnexo2);
                });

                try {
                    saveActividades(list, anexo2Repository.save(anexo2));
                } catch (Exception e) {
                    throw new BadRequestException("No se guardó el anexo 2" + e);
                }
                try {
                    emailEstudiantes(request.getSiglasCarrera());
                    return true;
                } catch (Exception ex) {
                    throw new BadRequestException("No se envió el email");
                }
            } else {
                throw new BadRequestException("Ya existe el anexo con ese id de proyecto");
            }
        }
        throw new BadRequestException("No existe el proyecto con id: " + request.getIdProyectoPPP());
    }

    @Transactional
    public boolean update(Anexo2Request request) {
        Optional<Anexo2> optional = anexo2Repository.findById(request.getId());
        if (optional.isPresent()) {
            if (!optional.get().getProyectoPPP().isEstado())
                throw new BadRequestException("El proyecto ha finalizado, no es posible modificar sus datos");

            Anexo2 anexo2 = optional.get();
            anexo2.setSiglasCarrera(request.getSiglasCarrera());
            //anexo2.setAnio(request.getAnio());
            //anexo2.setNumeroConvocatoria(request.getNumeroConvocatoria());
            anexo2.setFecha(request.getFecha());
            anexo2.setCarrera(request.getCarrera());
            anexo2.setCiclo(request.getCiclo());
            anexo2.setNombreProyecto(request.getNombreProyecto());
            anexo2.setEmpresa(request.getEmpresa());
            anexo2.setFechaMaxRecepcion(request.getFechaMaxRecepcion());
            anexo2.setNombreResponsable(request.getNombreResponsable());
            anexo2.setEmailDocente(request.getEmailDocente());
            anexo2.setNum_proceso(request.getNum_proceso());
            anexo2.setAnio(request.getAnio());
            anexo2.setProyectoPPP(proyectoRepository.findById(request.getIdProyectoPPP()).orElseThrow(() -> new BadRequestException("No existe proyecto con id: " + request.getIdProyectoPPP())));


            try {

                anexo2Repository.save(anexo2);
                updateActividades(request.getId(), request.getActividades());

                return true;
            } catch (Exception ex) {
                throw new BadRequestException("No se guardó el anexo 2" + ex);
            }
        }
        return false;
    }

    @Transactional
    public void updateActividades(Long id, List<ActividadesAnexo2Request> requisitos) {

        Optional<Anexo2> optional = anexo2Repository.findById(id);

        if (optional.isPresent()) {
            List<ActividadesAnexo2> proyecto = optional.get().getActividadesAnexo2s();
            proyecto.forEach(r -> {
                Optional<ActividadesAnexo2Request> exists = requisitos
                        .stream()
                        .filter(req -> req.getDescripcion().equalsIgnoreCase(r.getDescripcion()))
                        .findAny();
                if (!exists.isPresent()) {
                    actividadesAnexo2Repository.delete(r);
                }
            });

            requisitos.forEach(request -> {
                String descripcion = request.getDescripcion();
                Optional<ActividadesAnexo2> exists = proyecto
                        .stream()
                        .filter(r -> r.getDescripcion().equalsIgnoreCase(descripcion))
                        .findAny();
                if (!exists.isPresent()) {
                    ActividadesAnexo2 save = new ActividadesAnexo2();
                    save.setAnexo2(optional.get());
                    save.setDescripcion(request.getDescripcion());
                    actividadesAnexo2Repository.save(save);
                }
            });
        } else {
            throw new BadRequestException("El proyecto con id: " + id + ", no existe");
        }
    }

    public void deleteActividadesById(Long id) {
        Optional<ActividadesAnexo2> optional = actividadesAnexo2Repository.findById(id);
        if (!optional.isPresent()) {
            throw new BadRequestException("Las actividades con id: " + id + ", no existen");
        }
        actividadesAnexo2Repository.deleteById(id);
    }

    @Transactional
    public void deleteAnexo2ById(Long id) {
        Optional<Anexo2> optional = anexo2Repository.findById(id);
        if (!optional.isPresent()) {
            throw new BadRequestException("El anexo2 con id: " + id + ", no existe");
        }
        if (!optional.get().getProyectoPPP().isEstado())
            throw new BadRequestException("El proyecto ha finalizado, no es posible modificar sus datos");

        optional.get().getActividadesAnexo2s()
                .forEach(d -> deleteActividadesById(d.getId()));


        anexo2Repository.delete(optional.get());
    }

    private void emailEstudiantes(String codigocarrera) {
        ArrayList<Usuario> users = new ArrayList<>();
        lista(codigocarrera).forEach(vAlumnosppp -> {

            Optional<Usuario> getEmail = usuarioRepository.findByCedula(vAlumnosppp.getCedula());
            if (getEmail.isPresent()) {
                System.out.println("ENTRANDO");
                users.add(getEmail.get());

            } else {
                System.out.println("NO HAY EL EMAIL");
            }

        });
        enviarCorreos(users);
        System.out.println("-------------------------------------------USERS");
        System.out.println(users.size());

    }

    public List<VAlumnosppp> lista(String codigoCarrera) {
        List<VAlumnosppp> lista = vAlumnosRepository.findAllByCodigoCarrera(codigoCarrera);
        System.out.println("-----------------------------------------------------------------RECUPERADOS");
        System.out.println(lista.size());
        return lista;

    }


    public void enviarCorreos(ArrayList<Usuario> arrayList) {
        List<String> correos = arrayList.stream().map(Usuario::getEmail).collect(Collectors.toList());

        EmailBody e = new EmailBody();
        e.setEmail(correos);
        e.setContent("Convocatoria PPP");
        e.setSubject("DETALLES: ");
        e.setText2("Ingrese a la plataforma dando clic en el siguiente botón: ");
        emailService.sendEmail(e);
    }


    private void saveActividades(List<ActividadesAnexo2> list, Anexo2 anexo2) {
        list.stream().forEach(a -> {
            a.setAnexo2(anexo2);
            actividadesAnexo2Repository.save(a);
        });
    }


    @Transactional
    public List<Anexo2Response> listAll() {
        return anexo2Repository.findAll().stream().map(a -> {
            Anexo2Response response = new Anexo2Response();
            response.setId(a.getId());
            response.setSiglasCarrera(a.getSiglasCarrera());
            response.setAnio(a.getAnio());
            response.setNumeroConvocatoria(a.getNumeroConvocatoria());
            response.setFecha(a.getFecha());
            response.setCarrera(a.getCarrera());
            response.setCiclo(a.getCiclo());
            response.setNombreProyecto(a.getNombreProyecto());
            response.setEmpresa(a.getEmpresa());
            response.setFechaMaxRecepcion(a.getFechaMaxRecepcion());
            response.setNombreResponsable(a.getNombreResponsable());
            response.setDocumento(a.getDocumento());
            response.setMaterias(materiasPorProyectoId(a.getProyectoPPP().getId()));
            response.setEmailDocente(a.getEmailDocente());
            response.setNum_proceso(a.getNum_proceso());
            List<ActividadesAnexo2Request> list = a.getActividadesAnexo2s().stream().map(ac -> {
                ActividadesAnexo2Request request = new ActividadesAnexo2Request();
                request.setDescripcion(ac.getDescripcion());
                request.setId(ac.getId());
                return request;
            }).collect(Collectors.toList());
            response.setActividades(list);
            response.setIdProyectoPPP(a.getProyectoPPP().getId());
            return response;
        }).collect(Collectors.toList());
    }


    public Anexo2Response anexoIdProyecto(Long proyectoId) {
        Optional<ProyectoPPP> optional = proyectoRepository.findById(proyectoId);
        if (optional.isPresent()) {
            Optional<Anexo2> a = anexo2Repository.findByProyectoPPP(optional.get());
            if (a.isPresent()) {
                Anexo2Response response = new Anexo2Response();
                response.setId(a.get().getId());
                response.setSiglasCarrera(a.get().getSiglasCarrera());
                response.setAnio(a.get().getAnio());
                response.setNumeroConvocatoria(a.get().getNumeroConvocatoria());
                response.setFecha(a.get().getFecha());
                response.setCarrera(a.get().getCarrera());
                response.setCiclo(a.get().getCiclo());
                response.setNombreProyecto(a.get().getNombreProyecto());
                response.setEmpresa(a.get().getEmpresa());
                response.setMaterias(materiasPorProyectoId(proyectoId));
                response.setFechaMaxRecepcion(a.get().getFechaMaxRecepcion());
                response.setNombreResponsable(a.get().getNombreResponsable());
                response.setDocumento(a.get().getDocumento());
                response.setEmailDocente(a.get().getEmailDocente());
                response.setNum_proceso(a.get().getNum_proceso());
                List<ActividadesAnexo2Request> list = a.get().getActividadesAnexo2s().stream().map(ac -> {
                    ActividadesAnexo2Request request = new ActividadesAnexo2Request();
                    request.setDescripcion(ac.getDescripcion());
                    request.setId(ac.getId());
                    return request;
                }).collect(Collectors.toList());
                response.setActividades(list);
                response.setIdProyectoPPP(a.get().getProyectoPPP().getId());
                return response;
            }
            throw new BadRequestException("No existe el anexo con id de la solicitud: " + proyectoId);
        }

        throw new BadRequestException("No existe la solicitud: " + proyectoId);
    }

    public List<MateriaNombre> materiasPorProyectoId(Long proyectoId) {
        Optional<ProyectoPPP> proyectoPPP = proyectoRepository.findById(proyectoId);
        if (proyectoPPP.isPresent()) {
            List<RequisitosProyecto> listRe = requisitosRepository.findAllByProyectoPPP(proyectoPPP.get());
            return listRe.stream().map(requisitosProyecto -> {
                MateriaNombre alp = new MateriaNombre();
                alp.setNombre(requisitosProyecto.getDescripcion());
                return alp;
            }).collect(Collectors.toList());
        }
        return new ArrayList<>();
    }

    public int generateCode(String codeCarrera) {
        LocalDate date = LocalDate.now();
        String year = date.getYear() + "";
        Optional<Anexo2> optional = anexo2Repository.findFirstBySiglasCarreraOrderByFechaDesc(codeCarrera);
        if (optional.isPresent()) {
            if (year.equals(optional.get().getAnio())) {
                return Integer.parseInt(optional.get().getNumeroConvocatoria()) + 1;
            } else {
                return 1;
            }
        } else {
            return 1;
        }
    }

}
