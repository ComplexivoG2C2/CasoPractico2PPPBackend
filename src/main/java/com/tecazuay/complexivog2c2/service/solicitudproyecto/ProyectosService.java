package com.tecazuay.complexivog2c2.service.solicitudproyecto;

import com.tecazuay.complexivog2c2.dto.anexos.TutorEmpRequest;
import com.tecazuay.complexivog2c2.dto.docentes.DocenteRequest;
import com.tecazuay.complexivog2c2.dto.docentes.DocenteRolesList;
import com.tecazuay.complexivog2c2.dto.docentes.designaciones.TutorAcademicoResponse;
import com.tecazuay.complexivog2c2.dto.email.EmailBody;
import com.tecazuay.complexivog2c2.dto.solicitudproyectos.*;
import com.tecazuay.complexivog2c2.exception.BadRequestException;
import com.tecazuay.complexivog2c2.exception.ResponseNotFoundException;
import com.tecazuay.complexivog2c2.model.Primary.Anexos.TutorEmp;
import com.tecazuay.complexivog2c2.model.Primary.coordinadores.CoordinadorCarrera;
import com.tecazuay.complexivog2c2.model.Primary.desigaciones.TutorAcademicoDelegados;
import com.tecazuay.complexivog2c2.model.Primary.desigaciones.ResponsablePPP;
import com.tecazuay.complexivog2c2.model.Primary.solicitudproyecto.ActividadesProyecto;
import com.tecazuay.complexivog2c2.model.Primary.solicitudproyecto.ActividadesempresaProyecto;
import com.tecazuay.complexivog2c2.model.Primary.solicitudproyecto.ProyectoPPP;
import com.tecazuay.complexivog2c2.model.Primary.solicitudproyecto.RequisitosProyecto;
import com.tecazuay.complexivog2c2.model.Primary.usuario.Usuario;
import com.tecazuay.complexivog2c2.model.Secondary.carreras.VCarrerasppp;
import com.tecazuay.complexivog2c2.model.Secondary.personas.VPersonasppp;
import com.tecazuay.complexivog2c2.repository.Primary.autoridades.AutoridadesRepository;
import com.tecazuay.complexivog2c2.repository.Primary.coordinadorCarrera.CoordinadorRepository;
import com.tecazuay.complexivog2c2.repository.Primary.designaciones.CoordinadorVinculacionRepository;
import com.tecazuay.complexivog2c2.repository.Primary.designaciones.TutorAcademicoRepository;
import com.tecazuay.complexivog2c2.repository.Primary.designaciones.ResponsablePPPRepository;
import com.tecazuay.complexivog2c2.repository.Primary.solicitudproyecto.*;
import com.tecazuay.complexivog2c2.repository.Primary.usuario.UsuarioRepository;
import com.tecazuay.complexivog2c2.repository.Secondary.carreras.CarrerasRepository;
import com.tecazuay.complexivog2c2.repository.Secondary.coordinadores.CoordinadorVRepository;
import com.tecazuay.complexivog2c2.repository.Secondary.docentes.DocentesAllRepository;
import com.tecazuay.complexivog2c2.repository.Secondary.materias.MateriasRepository;
import com.tecazuay.complexivog2c2.repository.Secondary.personas.PersonasRepository;
import com.tecazuay.complexivog2c2.service.Anexos.Anexo1Service;
import com.tecazuay.complexivog2c2.service.docentes.DocentesService;
import com.tecazuay.complexivog2c2.service.email.EmailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
@Slf4j
@Service
public class ProyectosService {

    @Autowired
    private ProyectoRepository proyectoRepository;

    @Autowired
    private ActividadesRepository actividadesRepository;

    @Autowired
    private RequisitosRepository requisitosRepository;


    @Autowired
    private ResponsablePPPRepository responsablePPPRepository;

    @Autowired
    private PersonasRepository personasRepository;

    @Autowired
    private CarrerasRepository carrerasRepository;

    @Autowired
    private TutorAcademicoRepository tutorAcademicoRepository;

    @Autowired
    private ObjetivosRepository objetivosRepository;

    @Autowired
    private ActividadesEmpresaRepository actividadesEmpresaRepository;

    @Autowired
    //private DocenteRepository docenteRepository;
    private DocentesAllRepository docenteAllRepository;

    @Autowired
    private MateriasRepository materiasRepository;

    @Autowired
    private CoordinadorRepository coordinadorRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private EmailService emailService;

    @Autowired
    private CoordinadorVRepository coordinadorVRepository;

    @Autowired
    private CoordinadorVinculacionRepository coordinadorVinculacionRepository;

    @Autowired
    private AutoridadesRepository autoridadesRepository;

    @Autowired
    private DocentesService docentesService;


    @Autowired
    private Anexo1Service anexo1Service;


    public void saveProyecto(ProyectoRequest proyectoRequest, ResponsablePPP responsablePPP) {



        ////
        ProyectoPPP proyectoPPP = new ProyectoPPP();
        proyectoPPP.setCodigo(proyectoRequest.getCodigo());
        proyectoPPP.setNombre(proyectoRequest.getNombre());
        proyectoPPP.setFechaat(proyectoRequest.getFechaat());
        proyectoPPP.setResponsablePPP(responsablePPP);
        proyectoPPP.setEstado(proyectoRequest.isEstado());
        proyectoPPP.setCodigocarrera(proyectoRequest.getCodigocarrera());
        proyectoPPP.setCarrera(proyectoRequest.getCarrera());
        proyectoPPP.setEmpresa(proyectoRequest.getEmpresa());
        proyectoPPP.setNombreempresa(proyectoRequest.getNombreempresa());
        proyectoPPP.setNombresolicitante(proyectoRequest.getNombresolicitante());
        proyectoPPP.setCargosolicitante(proyectoRequest.getCargosolicitante());
        proyectoPPP.setDocumento(proyectoRequest.getDocumento());
        proyectoPPP.setPlazoEjecucion(proyectoRequest.getPlazoEjecucion());
        proyectoPPP.setFechaInicio(proyectoRequest.getFechaInicio());
        proyectoPPP.setFechaFin(proyectoRequest.getFechaFin());
        proyectoPPP.setParticipantes(proyectoRequest.getParticipantes());
        proyectoPPP.setHoras(proyectoRequest.getHoras());
        try {
            ProyectoPPP saved = proyectoRepository.save(proyectoPPP);
            saveObjetivosEspecificos(proyectoRequest.getActividadesEmpresaProyecto().stream().map(objetivosEspeciicoslistProyecto ->
            {
                ActividadesempresaProyecto a = new ActividadesempresaProyecto();
                a.setDescripcion(objetivosEspeciicoslistProyecto.getDescripcion());
                a.setProyectoPPP(saved);
                return a;
            }).collect(Collectors.toList()), saved);
            String coordinadorCedula = findCordinador(proyectoRequest.getCodigocarrera());
            if (coordinadorCedula.isEmpty()) {
                throw new BadRequestException("No existe el coordinador de la carrera: " + proyectoRequest.getCodigocarrera());
            }

            // DocenteRolesList rolesList = delegadosToRolesList(proyectoRequest.getTutorAcademicoDelegados());
            //rolesList.setIdProyecto(saved.getId());

            Optional<CoordinadorCarrera> coordinadorCarrera = coordinadorRepository.findByCedula(proyectoRequest.getCoordinadorCedula());

            if (coordinadorCarrera.isEmpty()) {
                throw new BadRequestException("No se encontr?? al coordinador de carrera con c??dula: " + coordinadorCedula);
            }
//            saveRoles(proyectoRequest.getDocentesDelegados(), saved.getId(), coordinadorCarrera.get());
            // saveRolesApoyo(rolesList, coordinadorCarrera.get());
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new BadRequestException("No se guard?? el proyecto" + ex);
        }
    }

    private void saveRoles(List<DocenteRequest> docentes, long idProyecto, CoordinadorCarrera coordinadorCarrera) {
        DocenteRolesList rolesList = new DocenteRolesList();
        rolesList.setIdProyecto(idProyecto);
        rolesList.setDocentes(docentes);
        docentesService.saveRolesApoyo(rolesList, coordinadorCarrera);


    }

    private DocenteRolesList delegadosToRolesList(List<TutorAcademicoDelegados> apoyoDelegados) {
        DocenteRolesList rolesList = new DocenteRolesList();
        rolesList.setDocentes(apoyoDelegados.stream().map(docente -> {
            DocenteRequest request = new DocenteRequest();
            request.setEstado(docente.isEstado());
            request.setCedula(docente.getCedula());
            request.setCargo("apoyo");
            return request;
        }).collect(Collectors.toList()));
        return rolesList;
    }

    private String findCordinador(String carreraCod) {
        Optional<VCarrerasppp> optional = carrerasRepository.findByCarreraCodigo(carreraCod);
        if (optional.isPresent()) {
            return optional.get().getCedula();
        }
        return "";
    }

    @Transactional
    public boolean saveRolesApoyo(DocenteRolesList docenteRolesList, CoordinadorCarrera coordinadorCarrera) {

        List<TutorAcademicoDelegados> listDocentesApoyo =
                docenteRolesList.getDocentes()
                        .stream()
                        .filter(docenteRequest -> docenteRequest.getCargo().equalsIgnoreCase("apoyo"))
                        .map(docenteRequest -> {
                            if (coordinadorVinculacionRepository.existsByCedulaAndEstado(docenteRequest.getCedula(), true)) {
                                log.info("Ya existe un CV con la cedula y estado {}", docenteRequest.getCedula());
                                throw new BadRequestException("Ya se encuentra asignado como Coordinador de Vinculaci??n");
                            }
                            if (coordinadorVRepository.existsByCedula(docenteRequest.getCedula())) {
                                log.info("Ya existe un CC con esa cedula {}", docenteRequest.getCedula());
                                throw new BadRequestException("Ya se encuentra asignado como Coordinador de Carrera");
                            }
                            if (responsablePPPRepository.existsByCedulaAndEstado(docenteRequest.getCedula(), true)) {
                                log.info("Ya existe un RPPP con esa cedula {}", docenteRequest.getCedula());
                                throw new BadRequestException("Ya se encuentra asignado como Responsable de Practicas");
                            }
                            if (autoridadesRepository.existsByCedulaAndEstado(docenteRequest.getCedula(), true)) {
                                log.info("Ya existe un AUT con esa cedula {}", docenteRequest.getCedula());
                                throw new BadRequestException("Ya se encuentra asignado como Autoridad");
                            }
                            Optional<ProyectoPPP> optional = proyectoRepository.findById(docenteRolesList.getIdProyecto());
                            if (optional.isPresent()) {
                                if (tutorAcademicoRepository.existsByProyectoPPPAndCedula(optional.get(), docenteRequest.getCedula())) {
                                    throw new BadRequestException("Ya se encuentra asignado a este Proyecto");
                                }
                                if (docenteAllRepository.existsByCedula(docenteRequest.getCedula())) {
                                    TutorAcademicoDelegados da = new TutorAcademicoDelegados();
                                    da.setCedula(docenteRequest.getCedula());
                                    da.setCoordinadorCarrera(coordinadorCarrera);
                                    da.setEstado(docenteRequest.isEstado());
                                    optional.get().getTutorAcademicoDelegados().add(da);
                                    da.setProyectoPPP(optional.get());
                                    return da;
                                }
                            }
                            return null;
                        }).collect(Collectors.toList());

        listDocentesApoyo.removeIf(Objects::isNull);

        if (tutorAcademicoRepository.saveAll(listDocentesApoyo).size() > 0) {
            listDocentesApoyo.stream().forEach(docentesApoyoDelegados -> {
                Optional<Usuario> getEmail = usuarioRepository.findByCedula(docentesApoyoDelegados.getCedula());
                if (getEmail.isPresent()) {
                    EmailBody e = new EmailBody();
                    e.setEmail(List.of(getEmail.get().getEmail()));
                    e.setContent("Usted ha sido designado como docente de apoyo");
                    e.setText2(" Ingrese al sistema dando clic en el siguiente bot??n:");
                    e.setSubject("Designaci??n para proyectos de vinculaci??n");
                    emailService.sendEmail(e);
                } else {
                    System.out.println("NO HAY EL EMAIL");
                }
            });
            return true;
        }
        log.error("Error al guardar docentes de apoyo");
        return false;
    }


    private void saveObjetivosEspecificos(List<ActividadesempresaProyecto> request, ProyectoPPP proyectoPPP) {
        List<ActividadesempresaProyecto> objetivos = request.stream().map(o -> {
            ActividadesempresaProyecto objetivo = new ActividadesempresaProyecto();
            objetivo.setDescripcion(o.getDescripcion());
            objetivo.setProyectoPPP(proyectoPPP);
            return objetivo;
        }).collect(Collectors.toList());
        try {
            actividadesEmpresaRepository.saveAll(objetivos);
        } catch (Exception e) {
            log.error("Error");
            throw new BadRequestException("Error al guardar act");
        }
    }


    /**
     * Llamar desde controlador y enviar un proyecto al metodo de saveProyecto
     *
     * @param proyectoRequest
     * @return
     */
    public boolean saveProyectos(ProyectoRequest proyectoRequest) {
        ResponsablePPP responsablePPP = getIdResponsable(proyectoRequest.getResponsablePPP());
        saveProyecto(proyectoRequest, responsablePPP);
        return true;
    }

    /**
     * Obtener para obtener el repsonsable previamente asignado
     *
     * @param id
     * @return
     */
    public ResponsablePPP getIdResponsable(Long id) {
        Optional<ResponsablePPP> optional = responsablePPPRepository.findById(id);
        if (optional.isPresent()) {
            return optional.get();
        }
        log.error("No existe el id del responsable: {}", id);
        throw new BadRequestException("No existe el responsable");
    }

    /**
     * Metodo para actualizar proyectos
     *
     * @param proyectoRequest
     * @return
     */
    public boolean updateProyecto(ProyectoRequest proyectoRequest) {
        ProyectoPPP proyectoPPP = getProyecto(proyectoRequest.getId());
        proyectoPPP.setEstado(proyectoRequest.isEstado());

        ProyectoPPP saved = proyectoRepository.save(proyectoPPP);


        String coordinadorCedula = findCordinador(proyectoRequest.getCodigocarrera());
        if (coordinadorCedula.isEmpty()) {
            throw new BadRequestException("No existe el coordinador de la carrera: " + proyectoRequest.getCodigocarrera());
        }

        Optional<CoordinadorCarrera> coordinadorCarrera = coordinadorRepository.findByCedula(proyectoRequest.getCoordinadorCedula());
        if (coordinadorCarrera.isEmpty()) {
            throw new BadRequestException("No se encontr?? al coordinador de carrera con c??dula: " + coordinadorCedula);
        }

        saveRoles(proyectoRequest.getDocentesDelegados(), saved.getId(), coordinadorCarrera.get());

        return true;
    }

        public boolean updateestado(ProyectoRequest proyectoRequest) {
        try {
            ProyectoPPP proyectoPPP = getProyecto(proyectoRequest.getId());
            proyectoPPP.setEstado(proyectoRequest.isEstado());

            ProyectoPPP saved = proyectoRepository.save(proyectoPPP);
            return true;
        }catch (Exception e){
            throw new BadRequestException("no se actualizo estado: " + e);
        }
    }

    public boolean updateturoempresarial(ProyectoRequest proyectoRequest) {
        try {
            ProyectoPPP proyectoPPP = getProyecto(proyectoRequest.getId());
            proyectoPPP.setNombreTutoremp(proyectoRequest.getNombreTutoremp());
            proyectoPPP.setTituloTutoremp(proyectoRequest.getTituloTutoremp());
            proyectoPPP.setCedulaTutoremp(proyectoRequest.getCedulaTutoremp());


            ProyectoPPP saved = proyectoRepository.save(proyectoPPP);
            return true;
        }catch (Exception e){
            throw new BadRequestException("no se actualizo los datos del tutor empresariak: " + e);
        }
    }




    public boolean updateProyecto2MS(ProyectoRequest proyectoRequest) {
        ProyectoPPP proyectoPPP = getProyecto(proyectoRequest.getId());
        proyectoPPP.setEstado(proyectoRequest.isEstado());
        proyectoPPP.setCodigo(proyectoRequest.getCodigo());
        proyectoPPP.setNombre(proyectoRequest.getNombre());
        proyectoPPP.setFechaat(proyectoRequest.getFechaat());
        proyectoPPP.setCodigocarrera(proyectoRequest.getCodigocarrera());
        proyectoPPP.setCarrera(proyectoRequest.getCarrera());
        proyectoPPP.setEmpresa(proyectoRequest.getEmpresa());
        proyectoPPP.setNombreempresa(proyectoRequest.getNombreempresa());
        proyectoPPP.setNombresolicitante(proyectoRequest.getNombresolicitante());
        proyectoPPP.setCargosolicitante(proyectoRequest.getCargosolicitante());
        proyectoPPP.setDocumento(proyectoRequest.getDocumento());
        proyectoPPP.setPlazoEjecucion(proyectoRequest.getPlazoEjecucion());
        proyectoPPP.setFechaInicio(proyectoRequest.getFechaInicio());
        proyectoPPP.setFechaFin(proyectoRequest.getFechaFin());
        proyectoPPP.setParticipantes(proyectoRequest.getParticipantes());
        proyectoPPP.setHoras(proyectoRequest.getHoras());

        ProyectoPPP saved = proyectoRepository.save(proyectoPPP);
        updateObjetivosEspecificos(proyectoRequest.getId(), proyectoRequest.getActividadesEmpresaProyecto());

        String coordinadorCedula = findCordinador(proyectoRequest.getCodigocarrera());
        if (coordinadorCedula.isEmpty()) {
            throw new BadRequestException("No existe el coordinador de la carrera: " + proyectoRequest.getCodigocarrera());
        }

        Optional<CoordinadorCarrera> coordinadorCarrera = coordinadorRepository.findByCedula(proyectoRequest.getCoordinadorCedula());
        if (coordinadorCarrera.isEmpty()) {
            throw new BadRequestException("No se encontr?? al coordinador de carrera con c??dula: " + coordinadorCedula);
        }

        saveRoles(proyectoRequest.getDocentesDelegados(), saved.getId(), coordinadorCarrera.get());

        return true;
    }

    @Transactional
    public void updateObjetivosEspecificos(Long id, List<ActividadesEmpresalistProyecto> requisitos) {
        Optional<ProyectoPPP> optional = proyectoRepository.findById(id);
        if (optional.isPresent()) {
            List<ActividadesempresaProyecto> proyecto = optional.get().getActividadesempresaProyecto();
            proyecto.forEach(r -> {
                Optional<ActividadesEmpresalistProyecto> exists = requisitos
                        .stream()
                        .filter(req -> req.getDescripcion().equalsIgnoreCase(r.getDescripcion()))
                        .findAny();
                if (exists.isEmpty()) {
                  actividadesEmpresaRepository.delete(r);
                }
            });
            requisitos.forEach(request -> {
                String descripcion = request.getDescripcion();
                Optional<ActividadesempresaProyecto> exists = proyecto
                        .stream()
                        .filter(r -> r.getDescripcion().equalsIgnoreCase(descripcion))
                        .findAny();
                if (exists.isEmpty()) {
                    ActividadesempresaProyecto save = new ActividadesempresaProyecto();
                    save.setProyectoPPP(optional.get());
                    save.setDescripcion(request.getDescripcion());
                    actividadesEmpresaRepository.save(save);
                }
            });
        } else {
            throw new BadRequestException("La solicitud con id: " + id + ", no existe");
        }
    }

    /**
     * obtenemos el proyecto por id para actualizar
     *
     * @param id
     * @return
     */
    public ProyectoPPP getProyecto(Long id) {
        Optional<ProyectoPPP> optional = proyectoRepository.findById(id);
        if (optional.isPresent()) {
            return optional.get();
        }
        throw new BadRequestException("No existe un proyecto");
    }

    /**
     * Lista todos los proyectos
     *
     * @return
     */
    public List<ProyectoResponse> listaProyectos() {
        List<ProyectoPPP> listabase = proyectoRepository.findAll();
        return listabase.stream()
                .map(this::toProyectoResponse)
                .collect(Collectors.toList());
    }

    private List<TutorAcademicoResponse> getDocenteApoyo(ProyectoPPP proyectoPPP) {
        List<TutorAcademicoDelegados> lista = tutorAcademicoRepository.findAllByProyectoPPP(proyectoPPP);
        return lista.stream().map(docentesApoyoDelegados -> {
            TutorAcademicoResponse d = new TutorAcademicoResponse();
            d.setId(docentesApoyoDelegados.getId());
            d.setCedula(docentesApoyoDelegados.getCedula());
            d.setEstado(docentesApoyoDelegados.isEstado());
            d.setFechaDesignacion(docentesApoyoDelegados.getFecha_designacion());
            d.setNombres(getNombreDocente(docentesApoyoDelegados.getCedula()));
            return d;
        }).collect(Collectors.toList());
    }

    private String getNombreDocente(String cedula) {
        Optional<VPersonasppp> optional = personasRepository.findByCedula(cedula);
        String name = "";
        if (optional.isPresent()) {
            name = optional.get().getNombres() + " " + optional.get().getApellidos();
        }
        return name;
    }

    /**
     * Metodo para listar las actividades de un proyecto
     *
     * @param proyectoPPP
     * @return
     */
    private List<ActividadeslistProyecto> getActividadeByProject(ProyectoPPP proyectoPPP) {
        List<ActividadesProyecto> listAc = actividadesRepository.findAllByProyectoPPP(proyectoPPP);
        return listAc.stream().map(actividadesProyecto -> {
            ActividadeslistProyecto alp = new ActividadeslistProyecto();
            alp.setDescripcion(actividadesProyecto.getDescripcion());
            return alp;
        }).collect(Collectors.toList());
    }

    /**
     * Metodo para listar las requisitos de un proyecto
     *
     * @param proyectoPPP
     * @return
     */
    private List<RequisitoslistProyecto> getRequisitosByProject(ProyectoPPP proyectoPPP) {
        List<RequisitosProyecto> listRe = requisitosRepository.findAllByProyectoPPP(proyectoPPP);
        return listRe.stream().map(requisitosProyecto -> {
            RequisitoslistProyecto alp = new RequisitoslistProyecto();
            alp.setId(requisitosProyecto.getId());
            alp.setDescripcion(requisitosProyecto.getDescripcion());
            return alp;
        }).collect(Collectors.toList());
    }

    private List<ActividadesEmpresalistProyecto> getActividadesEmpresaByProyecto(ProyectoPPP proyectoPPP) {
        List<ActividadesempresaProyecto> listRe = objetivosRepository.findAllByProyectoPPP(proyectoPPP);
        return listRe.stream().map(one -> {
            ActividadesEmpresalistProyecto a = new ActividadesEmpresalistProyecto();
            a.setDescripcion(one.getDescripcion());
            return a;
        }).collect(Collectors.toList());
    }

    /**
     * Metodo para mostrar el nombre de Carrera al momento de listar proyectos
     *
     * @param codigo
     * @return
     */
    private String getNombreCarrea(String codigo) {
        Optional<VCarrerasppp> optional = carrerasRepository.findByCarreraCodigo(codigo);
        if (optional.isPresent()) {
            return optional.get().getNombre_carrera();
        }
        return " ";
    }

    /**
     * Metodo para obtener le nombre del responsable en el metodo listar
     *
     * @param id
     * @return
     */
    public String nombreResponsable(Long id) {
        Optional<ResponsablePPP> optional = responsablePPPRepository.findById(id);
        if (optional.isPresent()) {
            Optional<VPersonasppp> optionalP = personasRepository.findByCedula(optional.get().getCedula());
            if (optionalP.isPresent()) {
                return optionalP.get().getNombres() + " " + optionalP.get().getApellidos();
            }
        }
        throw new BadRequestException("No se encontr?? el nombre del responsable");
    }

    /**
     * Metodo para obtener le nombre del director en el metodo listar
     *
     * @param id
     * @return
     */


    public ProyectoResponse listaProyectoById(Long id) {
        Optional<ProyectoPPP> optional = proyectoRepository.findById(id);
        if (optional.isPresent()) {
            ProyectoResponse materiasList = new ProyectoResponse();

            materiasList.setParticipantes(optional.get().getParticipantes());
            materiasList.setId(optional.get().getId());
            materiasList.setCodigo(optional.get().getCodigo());
            materiasList.setNombre(optional.get().getNombre());
            materiasList.setCodigocarrera(optional.get().getCodigocarrera());
            materiasList.setCarrera(getNombreCarrea(optional.get().getCodigocarrera()));
            materiasList.setEstado(optional.get().isEstado());
            materiasList.setNombreempresa(optional.get().getEmpresa().toString());
            materiasList.setNombreresponsable(nombreResponsable(optional.get().getResponsablePPP().getId()));

            materiasList.setNombreTutoremp(optional.get().getNombreTutoremp());
            materiasList.setCedulaTutoremp(optional.get().getCedulaTutoremp());
            materiasList.setTituloTutoremp(optional.get().getTituloTutoremp());

            materiasList.setEmpresa(optional.get().getEmpresa());
            materiasList.setNombresolicitante(optional.get().getNombresolicitante());
            materiasList.setCargosolicitante(optional.get().getCargosolicitante());
            materiasList.setDocumento(optional.get().getDocumento());



            materiasList.setHoras(optional.get().getHoras());
            materiasList.setFechaFin(optional.get().getFechaFin());
            materiasList.setFechaInicio(optional.get().getFechaInicio());
            materiasList.setPlazoEjecucion(optional.get().getPlazoEjecucion());

            materiasList.setTutorAcademicoResponse(getDocenteApoyo(optional.get()));
            materiasList.setActividadeslistProyectos(getActividadeByProject(optional.get()));
            materiasList.setRequisitoslistProyectos(getRequisitosByProject(optional.get()));
            materiasList.setActividadesEmpresaProyecto(getActividadesEmpresaByProyecto(optional.get()));

            materiasList.setFechaat(optional.get().getFechaat());
            return materiasList;
        }
        throw new ResponseNotFoundException("Solicitud PPP", "id", id.toString());
    }

    public void deleteDocenteApoyo(Long id) {
        Optional<TutorAcademicoDelegados> optional = tutorAcademicoRepository.findById(id);
        if (optional.isPresent()) {
            tutorAcademicoRepository.deleteById(id);
            return;
        }
        throw new BadRequestException("El docente de apoyo con el id: " + id + ", no existe");
    }

    @Transactional
    public void deleteProyecoById(Long id) {
        Optional<ProyectoPPP> optional = proyectoRepository.findById(id);
        if (optional.isEmpty()) {
            throw new BadRequestException("El proyecto con id: " + id + ", no existe");
        }
        optional.get().getTutorAcademicoDelegados()
                .forEach(d -> docentesService.deleteDocenteApoyoById(d.getId()));

        optional.get().getActividadesempresaProyecto()
                .forEach(o -> actividadesEmpresaRepository.deleteById(o.getId()));

        if (optional.get().getAnexo1() != null)
            anexo1Service.deleteAnexosList(optional.get().getAnexo1());

        proyectoRepository.delete(optional.get());
    }

    @Transactional
    public void updateRequisitos(Long id, List<RequisitoslistProyecto> requisitos) {

        Optional<ProyectoPPP> optional = proyectoRepository.findById(id);

        if (optional.isPresent()) {
            List<RequisitosProyecto> proyecto = optional.get().getRequisitosProyecto();
            proyecto.forEach(r -> {
                Optional<RequisitoslistProyecto> exists = requisitos
                        .stream()
                        .filter(req -> req.getDescripcion().equalsIgnoreCase(r.getDescripcion()))
                        .findAny();
                if (exists.isEmpty()) {
                    requisitosRepository.delete(r);
                }
            });

            requisitos.forEach(request -> {
                String descripcion = request.getDescripcion();
                Optional<RequisitosProyecto> exists = proyecto
                        .stream()
                        .filter(r -> r.getDescripcion().equalsIgnoreCase(descripcion))
                        .findAny();
                if (exists.isEmpty()) {
                    RequisitosProyecto save = new RequisitosProyecto();
                    save.setProyectoPPP(optional.get());
                    save.setDescripcion(request.getDescripcion());
                    requisitosRepository.save(save);
                }
            });
        } else {
            throw new BadRequestException("El proyecto con id: " + id + ", no existe");
        }
    }

    @Transactional
    public void updateActividades(Long id, List<ActividadeslistProyecto> requisitos) {

        Optional<ProyectoPPP> optional = proyectoRepository.findById(id);

        if (optional.isPresent()) {
            List<ActividadesProyecto> proyecto = optional.get().getActividadesProyecto();
            proyecto.forEach(r -> {
                Optional<ActividadeslistProyecto> exists = requisitos
                        .stream()
                        .filter(req -> req.getDescripcion().equalsIgnoreCase(r.getDescripcion()))
                        .findAny();
                if (exists.isEmpty()) {
                    actividadesRepository.delete(r);
                }
            });

            requisitos.forEach(request -> {
                String descripcion = request.getDescripcion();
                Optional<ActividadesProyecto> exists = proyecto
                        .stream()
                        .filter(r -> r.getDescripcion().equalsIgnoreCase(descripcion))
                        .findAny();
                if (exists.isEmpty()) {
                    ActividadesProyecto save = new ActividadesProyecto();
                    save.setProyectoPPP(optional.get());
                    save.setDescripcion(request.getDescripcion());
                    actividadesRepository.save(save);
                }
            });
        } else {
            throw new BadRequestException("El proyecto con id: " + id + ", no existe");
        }
    }

    public List<ProyectoResponse> getProyectoCIApoyoo(String cedula) {
        Optional<TutorAcademicoDelegados> op = tutorAcademicoRepository.findByCedula(cedula);
        if (op.isPresent() && op.get().isEstado()) {
            Optional<ProyectoPPP> optional = proyectoRepository.findById(op.get().getProyectoPPP().getId());
            if (optional.isPresent()) {
                List<ProyectoResponse> response = new ArrayList<>();
                ProyectoResponse materiasList = new ProyectoResponse();
                materiasList.setParticipantes(optional.get().getParticipantes());
                materiasList.setId(optional.get().getId());
                materiasList.setCodigo(optional.get().getCodigo());
                materiasList.setNombre(optional.get().getNombre());
                materiasList.setCodigocarrera(optional.get().getCodigocarrera());
                materiasList.setCarrera(getNombreCarrea(optional.get().getCodigocarrera()));
                materiasList.setEstado(optional.get().isEstado());

                materiasList.setNombreempresa(optional.get().getEmpresa().toString());
                materiasList.setNombreresponsable(nombreResponsable(optional.get().getResponsablePPP().getId()));

                materiasList.setEmpresa(optional.get().getEmpresa());
                materiasList.setNombresolicitante(optional.get().getNombresolicitante());
                materiasList.setCargosolicitante(optional.get().getCargosolicitante());
                materiasList.setDocumento(optional.get().getDocumento());


                materiasList.setNombreTutoremp(optional.get().getNombreTutoremp());
                materiasList.setCedulaTutoremp(optional.get().getCedulaTutoremp());
                materiasList.setTituloTutoremp(optional.get().getTituloTutoremp());

                materiasList.setHoras(optional.get().getHoras());
//                materiasList.setParticipantes(optional.get().getParticipantes());
                materiasList.setFechaFin(optional.get().getFechaFin());
                materiasList.setFechaInicio(optional.get().getFechaInicio());
                materiasList.setPlazoEjecucion(optional.get().getPlazoEjecucion());

                materiasList.setTutorAcademicoResponse(getDocenteApoyo(optional.get()));
                materiasList.setActividadeslistProyectos(getActividadeByProject(optional.get()));
                materiasList.setRequisitoslistProyectos(getRequisitosByProject(optional.get()));
                materiasList.setActividadesEmpresaProyecto(getActividadesEmpresaByProyecto(optional.get()));
                materiasList.setFechaat(optional.get().getFechaat());
                response.add(materiasList);
                return response;
            }
            throw new ResponseNotFoundException("Proyecto PPP", "id", op.get().getProyectoPPP().getId() + "");
        }
        throw new ResponseNotFoundException("Docente apoyo ", "cedula", cedula);
    }

    public ProyectoResponse getProyectoCIApoyo(String cedula) {
        Optional<TutorAcademicoDelegados> op = tutorAcademicoRepository.findByCedula(cedula);
        if (op.isPresent()) {
            Optional<ProyectoPPP> optional = proyectoRepository.findById(op.get().getProyectoPPP().getId());
            if (optional.isPresent()) {
                return toProyectoResponse(optional.get());
            }
            throw new ResponseNotFoundException("Proyecto PPP", "id", op.get().getProyectoPPP().getId() + "");
        } else {
            throw new ResponseNotFoundException("Docente apoyo ", "cedula", cedula);
        }
    }


    @Transactional(readOnly = true)
    public List<ProyectoResponse> allByApoyoCedula(String cedula) {
        if (tutorAcademicoRepository.existsByCedula(cedula)) {
            List<TutorAcademicoDelegados> docentes = tutorAcademicoRepository.findAllByCedula(cedula);
            return docentes.stream()
                    .map(d -> toProyectoResponse(d.getProyectoPPP()))
                    .collect(Collectors.toList());
        }
        throw new BadRequestException("El docente de apoyo con c??dula:" + cedula + " no existe");
    }

    private ProyectoResponse toProyectoResponse(ProyectoPPP proyectoPPP) {

        ProyectoResponse response = new ProyectoResponse();
        response.setParticipantes(proyectoPPP.getParticipantes());
        response.setId(proyectoPPP.getId());
        response.setCodigo(proyectoPPP.getCodigo());
        response.setNombre(proyectoPPP.getNombre());
        response.setCarrera(getNombreCarrea(proyectoPPP.getCodigocarrera()));
        response.setEstado(proyectoPPP.isEstado());
        response.setNombreempresa(proyectoPPP.getEmpresa().toString());
        response.setNombreresponsable(nombreResponsable(proyectoPPP.getResponsablePPP().getId()));
        response.setEmpresa(proyectoPPP.getEmpresa());
        response.setNombresolicitante(proyectoPPP.getNombresolicitante());
        response.setCargosolicitante(proyectoPPP.getCargosolicitante());

        response.setNombreTutoremp(proyectoPPP.getNombreTutoremp());
        response.setCedulaTutoremp(proyectoPPP.getCedulaTutoremp());
        response.setTituloTutoremp(proyectoPPP.getTituloTutoremp());

        response.setHoras(proyectoPPP.getHoras());
        response.setFechaInicio(proyectoPPP.getFechaInicio());
        response.setFechaFin(proyectoPPP.getFechaFin());
        response.setPlazoEjecucion(proyectoPPP.getPlazoEjecucion());
        response.setCodigocarrera(proyectoPPP.getCodigocarrera());

        response.setDocumento(proyectoPPP.getDocumento());

        response.setTutorAcademicoResponse(getDocenteApoyo(proyectoPPP));
        response.setActividadeslistProyectos(getActividadeByProject(proyectoPPP));
        response.setRequisitoslistProyectos(getRequisitosByProject(proyectoPPP));
        response.setActividadesEmpresaProyecto(getActividadesEmpresaByProyecto(proyectoPPP));
        response.setFechaat(proyectoPPP.getFechaat());
        return response;
    }

}


//
//@Slf4j
//@Service
//public class ProyectosService {
//
//    @Autowired
//    private ProyectoRepository proyectoRepository;
//
//    @Autowired
//    private ActividadesRepository actividadesRepository;
//
//    @Autowired
//    private RequisitosRepository requisitosRepository;
//
////    @Autowired
////    private TutorEmpProyectoRepository tutorEmpProyectoRepository;
//
//    @Autowired
//    private ResponsablePPPRepository responsablePPPRepository;
//
//    @Autowired
//    private PersonasRepository personasRepository;
//
//    @Autowired
//    private CarrerasRepository carrerasRepository;
//
//    @Autowired
//    private TutorAcademicoRepository tutorAcademicoRepository;
//
//    //    @Autowired
////    private ObjetivosRepository objetivosRepository;
////
//    @Autowired
//    private ActividadesEmpresaRepository actividadesEmpresaRepository;
//
//    @Autowired
//    //private DocenteRepository docenteRepository;
//    private DocentesAllRepository docenteAllRepository;
//
//    @Autowired
//    private MateriasRepository materiasRepository;
//
//    @Autowired
//    private CoordinadorRepository coordinadorRepository;
//
//    @Autowired
//    private UsuarioRepository usuarioRepository;
//
//    @Autowired
//    private EmailService emailService;
//
//    @Autowired
//    private CoordinadorVRepository coordinadorVRepository;
//
//    @Autowired
//    private CoordinadorVinculacionRepository coordinadorVinculacionRepository;
//
//    @Autowired
//    private AutoridadesRepository autoridadesRepository;
//
//    @Autowired
//    private DocentesService docentesService;
//
//    @Autowired
//    private TutorEmpService tutorEmpService;
//
//    @Autowired
//    private Anexo1Service anexo1Service;
//
//    @Autowired
//    private ObjetivosRepository objetivosRepository;
//
//
//
//
//    public void saveProyecto(ProyectoRequest proyectoRequest, ResponsablePPP responsablePPP) {
//        ProyectoPPP proyectoPPP = new ProyectoPPP();
//        proyectoPPP.setCodigo(proyectoRequest.getCodigo());
//        proyectoPPP.setNombre(proyectoRequest.getNombre());
//        proyectoPPP.setFechaat(proyectoRequest.getFechaat());
//        proyectoPPP.setResponsablePPP(responsablePPP);
//        proyectoPPP.setEstado(proyectoRequest.isEstado());
//        proyectoPPP.setCodigocarrera(proyectoRequest.getCodigocarrera());
//        proyectoPPP.setCarrera(proyectoRequest.getCarrera());
//        proyectoPPP.setEmpresa(proyectoRequest.getEmpresa());
//        proyectoPPP.setNombreempresa(proyectoRequest.getNombreempresa());
//        proyectoPPP.setNombresolicitante(proyectoRequest.getNombresolicitante());
//        proyectoPPP.setCargosolicitante(proyectoRequest.getCargosolicitante());
//        proyectoPPP.setDocumento(proyectoRequest.getDocumento());
//        proyectoPPP.setPlazoEjecucion(proyectoRequest.getPlazoEjecucion());
//        proyectoPPP.setFechaInicio(proyectoRequest.getFechaInicio());
//        proyectoPPP.setFechaFin(proyectoRequest.getFechaFin());
//        proyectoPPP.setParticipantes(proyectoRequest.getParticipantes());
//        proyectoPPP.setHoras(proyectoRequest.getHoras());
//        try {
//            ProyectoPPP saved = proyectoRepository.save(proyectoPPP);
//            saveObjetivosEspecificos(proyectoRequest.getActividadesEmpresaProyecto().stream().map(objetivosEspeciicoslistProyecto ->
//            {
//                ActividadesempresaProyecto a = new ActividadesempresaProyecto();
//                a.setDescripcion(objetivosEspeciicoslistProyecto.getDescripcion());
//                a.setProyectoPPP(saved);
//                return a;
//            }).collect(Collectors.toList()), saved);
//            String coordinadorCedula = findCordinador(proyectoRequest.getCodigocarrera());
//            if (coordinadorCedula.isEmpty()) {
//                throw new BadRequestException("No existe el coordinador de la carrera: " + proyectoRequest.getCodigocarrera());
//            }
//
//
//            Optional<CoordinadorCarrera> coordinadorCarrera = coordinadorRepository.findByCedula(proyectoRequest.getCoordinadorCedula());
//
////            if (coordinadorCarrera.isEmpty()) {
////                throw new BadRequestException("No se encontr???? al coordinador de carrera con c????dula: " + coordinadorCedula);
////            }
////            saveRoles(proyectoRequest.getDocentesDelegados(), saved.getId(), coordinadorCarrera.get());
//
//        } catch (Exception ex) {
//            ex.printStackTrace();
//            throw new BadRequestException("No se guard???? el proyecto" + ex);
//        }
//    }
//
//    private void saveRoles(List<DocenteRequest> docentes, long idProyecto) {
//        DocenteRolesList rolesList = new DocenteRolesList();
//        rolesList.setIdProyecto(idProyecto);
//        rolesList.setDocentes(docentes);
//        docentesService.saveRolesApoyo(rolesList);
//
////        Optional<DocenteRequest> directorRequest = docentes.stream().filter(d -> d.getCargo().equalsIgnoreCase("DP")).findAny();
////        if (directorRequest.isPresent()) {
////           TutorEmpProyectoRequest request = new TutorEmpProyectoRequest();
////            request.setCedula(directorRequest.get().getCedula());
////            request.setEstado(true);
////            request.setIdProyecto(idProyecto);
////            request.setCoordinador_id(coordinadorCarrera.getCedula());
////            tutorEmpService.saveRolTutoremp(request);
////        }
//    }
//
//    private DocenteRolesList delegadosToRolesList(List<TutorAcademicoDelegados> apoyoDelegados) {
//        DocenteRolesList rolesList = new DocenteRolesList();
//        rolesList.setDocentes(apoyoDelegados.stream().map(docente -> {
//            DocenteRequest request = new DocenteRequest();
//            request.setEstado(docente.isEstado());
//            request.setCedula(docente.getCedula());
//            request.setCargo("apoyo");
//            return request;
//        }).collect(Collectors.toList()));
//        return rolesList;
//    }
//
//    private String findCordinador(String carreraCod) {
//        Optional<VCarrerasppp> optional = carrerasRepository.findByCarreraCodigo(carreraCod);
//        if (optional.isPresent()) {
//            return optional.get().getCedula();
//        }
//        return "";
//    }
//
//    @Transactional
//    public boolean saveRolesApoyo(DocenteRolesList docenteRolesList, CoordinadorCarrera coordinadorCarrera) {
//
//        List<TutorAcademicoDelegados> listDocentesApoyo =
//                docenteRolesList.getDocentes()
//                        .stream()
//                        .filter(docenteRequest -> docenteRequest.getCargo().equalsIgnoreCase("apoyo"))
//                        .map(docenteRequest -> {
//                            if (coordinadorVinculacionRepository.existsByCedulaAndEstado(docenteRequest.getCedula(), true)) {
//                                log.info("Ya existe un CV con la cedula y estado {}", docenteRequest.getCedula());
//                                throw new BadRequestException("Ya se encuentra asignado como Coordinador de Vinculaci????n");
//                            }
//                            if (coordinadorVRepository.existsByCedula(docenteRequest.getCedula())) {
//                                log.info("Ya existe un CC con esa cedula {}", docenteRequest.getCedula());
//                                throw new BadRequestException("Ya se encuentra asignado como Coordinador de Carrera");
//                            }
////                            if (tutorEmpProyectoRepository.existsByCedulaAndEstado(docenteRequest.getCedula(), true)) {
////                                log.info("Ya existe un TE con esa cedula {}", docenteRequest.getCedula());
////                                throw new BadRequestException("Ya se encuentra asignado como Director de Proyecto");
////                            }
//                            if (responsablePPPRepository.existsByCedulaAndEstado(docenteRequest.getCedula(), true)) {
//                                log.info("Ya existe un RPPP con esa cedula {}", docenteRequest.getCedula());
//                                throw new BadRequestException("Ya se encuentra asignado como Responsable de Practicas");
//                            }
//                            if (autoridadesRepository.existsByCedulaAndEstado(docenteRequest.getCedula(), true)) {
//                                log.info("Ya existe un AUT con esa cedula {}", docenteRequest.getCedula());
//                                throw new BadRequestException("Ya se encuentra asignado como Autoridad");
//                            }
//                            Optional<ProyectoPPP> optional = proyectoRepository.findById(docenteRolesList.getIdProyecto());
//                            if (optional.isPresent()) {
//                                if (tutorAcademicoRepository.existsByProyectoPPPAndCedula(optional.get(), docenteRequest.getCedula())) {
//                                    throw new BadRequestException("Ya se encuentra asignado a esta empresa");
//                                }
//                                if (docenteAllRepository.existsByCedula(docenteRequest.getCedula())) {
//                                    TutorAcademicoDelegados da = new TutorAcademicoDelegados();
//                                    da.setCedula(docenteRequest.getCedula());
//                                    da.setCoordinadorCarrera(coordinadorCarrera);
//                                    da.setEstado(docenteRequest.isEstado());
//                                    optional.get().getTutorAcademicoDelegados().add(da);
//                                    da.setProyectoPPP(optional.get());
//                                    return da;
//                                }
//                            }
//                            return null;
//                        }).collect(Collectors.toList());
//
//        listDocentesApoyo.removeIf(Objects::isNull);
//
//        if (tutorAcademicoRepository.saveAll(listDocentesApoyo).size() > 0) {
//            listDocentesApoyo.stream().forEach(docentestutorDelegados -> {
//                Optional<Usuario> getEmail = usuarioRepository.findByCedula(docentestutorDelegados.getCedula());
//                if (getEmail.isPresent()) {
//                    EmailBody e = new EmailBody();
//                    e.setEmail(List.of(getEmail.get().getEmail()));
//                    e.setContent("Usted ha sido designado como docente de apoyo");
//                    e.setText2(" Ingrese al sistema dando clic en el siguiente bot????n:");
//                    e.setSubject("Designaci????n para proyectos de vinculaci????n");
//                    emailService.sendEmail(e);
//                } else {
//                    System.out.println("NO HAY EL EMAIL");
//                }
//            });
//            return true;
//        }
//        log.error("Error al guardar docentes de apoyo");
//        return false;
//    }
//
//
//    private void saveObjetivosEspecificos(List<ActividadesempresaProyecto> request, ProyectoPPP proyectoPPP) {
//        List<ActividadesempresaProyecto> objetivos = request.stream().map(o -> {
//            ActividadesempresaProyecto objetivo = new ActividadesempresaProyecto();
//            objetivo.setDescripcion(o.getDescripcion());
//            objetivo.setProyectoPPP(proyectoPPP);
//            return objetivo;
//        }).collect(Collectors.toList());
//        try {
//            actividadesEmpresaRepository.saveAll(objetivos);
//        } catch (Exception e) {
//            log.error("Error");
//            throw new BadRequestException("Error al guardar objetivo espec????fico");
//        }
//    }
//
//
//    /**
//     * Llamar desde controlador y enviar un proyecto al metodo de saveProyecto
//     *
//     * @param proyectoRequest
//     * @return
//     */
//    public boolean saveProyectos(ProyectoRequest proyectoRequest) {
//        ResponsablePPP responsablePPP = getIdResponsable(proyectoRequest.getResponsablePPP());
//        saveProyecto(proyectoRequest, responsablePPP);
//        return true;
//    }
//
//
//    public ResponsablePPP getIdResponsable(Long id) {
//        Optional<ResponsablePPP> optional = responsablePPPRepository.findById(id);
//        if (optional.isPresent()) {
//            return optional.get();
//        }
//        log.error("No existe el id del responsable: {}", id);
//        throw new BadRequestException("No existe el responsable");
//    }
//
//
//    public boolean updateProyecto(ProyectoRequest proyectoRequest) {
//        ProyectoPPP proyectoPPP = getProyecto(proyectoRequest.getId());
//        proyectoPPP.setEstado(proyectoRequest.isEstado());
//
//        ProyectoPPP saved = proyectoRepository.save(proyectoPPP);
//
//
////        String coordinadorCedula = findCordinador(proyectoRequest.getCodigocarrera());
////        if (coordinadorCedula.isEmpty()) {
////            throw new BadRequestException("No existe el coordinador de la carrera: " + proyectoRequest.getCodigocarrera());
////        }
////
////        Optional<CoordinadorCarrera> coordinadorCarrera = coordinadorRepository.findByCedula(proyectoRequest.getCoordinadorCedula());
////        if (coordinadorCarrera.isEmpty()) {
////            throw new BadRequestException("No se encontr???? al coordinador de carrera con c????dula: " + coordinadorCedula);
////        }
//
//        saveRoles(proyectoRequest.getDocentesDelegados(), saved.getId());
//
//        return true;
//    }
//
//    public boolean updateProyecto2MS(ProyectoRequest proyectoRequest) {
//        ProyectoPPP proyectoPPP = getProyecto(proyectoRequest.getId());
//        proyectoPPP.setEstado(proyectoRequest.isEstado());
//        proyectoPPP.setCodigo(proyectoRequest.getCodigo());
//        proyectoPPP.setNombre(proyectoRequest.getNombre());
//        proyectoPPP.setFechaat(proyectoRequest.getFechaat());
//        proyectoPPP.setCodigocarrera(proyectoRequest.getCodigocarrera());
//        proyectoPPP.setEmpresa(proyectoRequest.getEmpresa());
//        proyectoPPP.setNombresolicitante(proyectoRequest.getNombresolicitante());
//        proyectoPPP.setCodigocarrera(proyectoRequest.getCodigocarrera());
//        proyectoPPP.setCargosolicitante(proyectoRequest.getCargosolicitante());
//        proyectoPPP.setDocumento(proyectoRequest.getDocumento());
//        proyectoPPP.setPlazoEjecucion(proyectoRequest.getPlazoEjecucion());
//        proyectoPPP.setFechaInicio(proyectoRequest.getFechaInicio());
//        proyectoPPP.setFechaFin(proyectoRequest.getFechaFin());
//        proyectoPPP.setParticipantes(proyectoRequest.getParticipantes());
//        proyectoPPP.setHoras(proyectoRequest.getHoras());
//
//        ProyectoPPP saved = proyectoRepository.save(proyectoPPP);
//        updateObjetivosEspecificos(proyectoRequest.getId(), proyectoRequest.getActividadesEmpresaProyecto());
//
////        String coordinadorCedula = findCordinador(proyectoRequest.getCodigocarrera());
////        if (coordinadorCedula.isEmpty()) {
////            throw new BadRequestException("No existe el coordinador de la carrera: " + proyectoRequest.getCodigocarrera());
////        }
////
////        Optional<CoordinadorCarrera> coordinadorCarrera = coordinadorRepository.findByCedula(proyectoRequest.getCoordinadorCedula());
////        if (coordinadorCarrera.isEmpty()) {
////            throw new BadRequestException("No se encontr???? al coordinador de carrera con c????dula: " + coordinadorCedula);
////        }
//
//        saveRoles(proyectoRequest.getDocentesDelegados(), saved.getId());
//
//        return true;
//    }
//
//    public boolean updatetutoresacademicos(ProyectoRequest proyectoRequest) {
//        ProyectoPPP proyectoPPP = getProyecto(proyectoRequest.getId());
//
//        ProyectoPPP saved = proyectoRepository.save(proyectoPPP);
//        updateObjetivosEspecificos(proyectoRequest.getId(), proyectoRequest.getActividadesEmpresaProyecto());
//
////        String coordinadorCedula = findCordinador(proyectoRequest.getCodigocarrera());
////        if (coordinadorCedula.isEmpty()) {
////            throw new BadRequestException("No existe el coordinador de la carrera: " + proyectoRequest.getCodigocarrera());
////        }
////        Optional<CoordinadorCarrera> coordinadorCarrera = coordinadorRepository.findByCedula(proyectoRequest.getCoordinadorCedula());
////        if (coordinadorCarrera.isEmpty()) {
////            throw new BadRequestException("No se encontr???? al coordinador de carrera con c????dula: " + coordinadorCedula);
////        }
//        saveRoles(proyectoRequest.getDocentesDelegados(), saved.getId());
//
//        return true;
//    }
//    public boolean updateestado(ProyectoRequest proyectoRequest) {
//        try {
//            ProyectoPPP proyectoPPP = getProyecto(proyectoRequest.getId());
//            proyectoPPP.setEstado(proyectoRequest.isEstado());
//
//            ProyectoPPP saved = proyectoRepository.save(proyectoPPP);
//            return true;
//        }catch (Exception e){
//            throw new BadRequestException("no se actualizo estado: " + e);
//        }
//    }
//
//
//    @Transactional
//    public void updateObjetivosEspecificos(Long id, List<ActividadesEmpresalistProyecto> requisitos) {
//        Optional<ProyectoPPP> optional = proyectoRepository.findById(id);
//        if (optional.isPresent()) {
//            List<ActividadesempresaProyecto> proyecto = optional.get().getActividadesempresaProyecto();
//            proyecto.forEach(r -> {
//                Optional<ActividadesEmpresalistProyecto> exists = requisitos
//                        .stream()
//                        .filter(req -> req.getDescripcion().equalsIgnoreCase(r.getDescripcion()))
//                        .findAny();
//                if (exists.isEmpty()) {
//                    actividadesEmpresaRepository.delete(r);
//                }
//            });
//            requisitos.forEach(request -> {
//                String descripcion = request.getDescripcion();
//                Optional<ActividadesempresaProyecto> exists = proyecto
//                        .stream()
//                        .filter(r -> r.getDescripcion().equalsIgnoreCase(descripcion))
//                        .findAny();
//                if (exists.isEmpty()) {
//                    ActividadesempresaProyecto save = new ActividadesempresaProyecto();
//                    save.setProyectoPPP(optional.get());
//                    save.setDescripcion(request.getDescripcion());
//                    actividadesEmpresaRepository.save(save);
//                }
//            });
//        } else {
//            throw new BadRequestException("El proyecto con id: " + id + ", no existe");
//        }
//    }
//
//    /**
//     * obtenemos el proyecto por id para actualizar
//     *
//     * @param id
//     * @return
//     */
//    public ProyectoPPP getProyecto(Long id) {
//        Optional<ProyectoPPP> optional = proyectoRepository.findById(id);
//        if (optional.isPresent()) {
//            return optional.get();
//        }
//        throw new BadRequestException("No existe un proyecto");
//    }
//
//    /**
//     * Lista todos los proyectos
//     *
//     * @return
//     */
//    public List<ProyectoResponse> listaProyectos() {
//        List<ProyectoPPP> listabase = proyectoRepository.findAll();
//        return listabase.stream()
//                .map(this::toProyectoResponse)
//                .collect(Collectors.toList());
//    }
//
//    private List<TutorAcademicoResponse> getDocenteApoyo(ProyectoPPP proyectoPPP) {
//        List<TutorAcademicoDelegados> lista = tutorAcademicoRepository.findAllByProyectoPPP(proyectoPPP);
//        return lista.stream().map(docentesApoyoDelegados -> {
//            TutorAcademicoResponse d = new TutorAcademicoResponse();
//            d.setId(docentesApoyoDelegados.getId());
//            d.setCedula(docentesApoyoDelegados.getCedula());
//            d.setEstado(docentesApoyoDelegados.isEstado());
//            d.setFechaDesignacion(docentesApoyoDelegados.getFecha_designacion());
//            d.setNombres(getNombreDocente(docentesApoyoDelegados.getCedula()));
//            return d;
//        }).collect(Collectors.toList());
//    }
//
//    private String getNombreDocente(String cedula) {
//        Optional<VPersonasppp> optional = personasRepository.findByCedula(cedula);
//        String name = "";
//        if (optional.isPresent()) {
//            name = optional.get().getNombres() + " " + optional.get().getApellidos();
//        }
//        return name;
//    }
//
//    /**
//     * Metodo para listar las actividades de un proyecto
//     *
//     * @param proyectoPPP
//     * @return
//     */
//    private List<ActividadeslistProyecto> getActividadeByProject(ProyectoPPP proyectoPPP) {
//        List<ActividadesProyecto> listAc = actividadesRepository.findAllByProyectoPPP(proyectoPPP);
//        return listAc.stream().map(actividadesProyecto -> {
//            ActividadeslistProyecto alp = new ActividadeslistProyecto();
//            alp.setDescripcion(actividadesProyecto.getDescripcion());
//            return alp;
//        }).collect(Collectors.toList());
//    }
//
//    /**
//     * Metodo para listar las requisitos de un proyecto
//     *
//     * @param proyectoPPP
//     * @return
//     */
//    private List<RequisitoslistProyecto> getRequisitosByProject(ProyectoPPP proyectoPPP) {
//        List<RequisitosProyecto> listRe = requisitosRepository.findAllByProyectoPPP(proyectoPPP);
//        return listRe.stream().map(requisitosProyecto -> {
//            RequisitoslistProyecto alp = new RequisitoslistProyecto();
//            alp.setId(requisitosProyecto.getId());
//            alp.setDescripcion(requisitosProyecto.getDescripcion());
//            return alp;
//        }).collect(Collectors.toList());
//    }
//
//    private List<ActividadesEmpresalistProyecto> getActividadesEmpreSAByProyecto(ProyectoPPP proyectoPPP) {
//        List<ActividadesempresaProyecto> listRe = objetivosRepository.findAllByProyectoPPP(proyectoPPP);
//        return listRe.stream().map(one -> {
//            ActividadesEmpresalistProyecto a = new ActividadesEmpresalistProyecto();
//            a.setDescripcion(one.getDescripcion());
//            return a;
//        }).collect(Collectors.toList());
//    }
//
//    /**
//     * Metodo para mostrar el nombre de Carrera al momento de listar proyectos
//     *
//     * @param codigo
//     * @return
//     */
//    private String getNombreCarrea(String codigo) {
//        Optional<VCarrerasppp> optional = carrerasRepository.findByCarreraCodigo(codigo);
//        if (optional.isPresent()) {
//            return optional.get().getNombre_carrera();
//        }
//        return " ";
//    }
//
//    /**
//     * Metodo para obtener le nombre del responsable en el metodo listar
//     *
//     * @param id
//     * @return
//     */
//    public String nombreResponsable(Long id) {
//        Optional<ResponsablePPP> optional = responsablePPPRepository.findById(id);
//        if (optional.isPresent()) {
//            Optional<VPersonasppp> optionalP = personasRepository.findByCedula(optional.get().getCedula());
//            if (optionalP.isPresent()) {
//                return optionalP.get().getNombres() + " " + optionalP.get().getApellidos();
//            }
//        }
//        throw new BadRequestException("No se encontr???? el nombre del responsable");
//    }
//
//    /**
//     * Metodo para obtener le nombre del tutor empresarial en el metodo listar
//     *
//     * @param id
//     * @return
//     */
////    public String nombreDirector(Long id) {
////        Optional<TutorEmp> optional = tutorEmpProyectoRepository.findById(id);
////        if (optional.isPresent()) {
////            Optional<VPersonasppp> optionalP = personasRepository.findByCedula(optional.get().getCedula());
////            if (optionalP.isPresent()) {
////                return optionalP.get().getNombres() + " " + optionalP.get().getApellidos();
////            }
////        }
////        return " ";
////    }
//
//
//    public ProyectoResponse listaProyectoById(Long id) {
//        Optional<ProyectoPPP> optional = proyectoRepository.findById(id);
//        if (optional.isPresent()) {
//            ProyectoResponse materiasList = new ProyectoResponse();
//            materiasList.setParticipantes(optional.get().getParticipantes());
//            materiasList.setId(optional.get().getId());
//            materiasList.setCodigo(optional.get().getCodigo());
//            materiasList.setNombre(optional.get().getNombre());
//            materiasList.setCodigocarrera(optional.get().getCodigocarrera());
//            materiasList.setCarrera(getNombreCarrea(optional.get().getCodigocarrera()));
//            materiasList.setEstado(optional.get().isEstado());
//            materiasList.setNombreempresa(optional.get().getEmpresa().toString());
//            materiasList.setNombreresponsable(nombreResponsable(optional.get().getResponsablePPP().getId()));
//            materiasList.setEmpresa(optional.get().getEmpresa());
//            materiasList.setNombresolicitante(optional.get().getNombresolicitante());
//            materiasList.setCargosolicitante(optional.get().getCargosolicitante());
//            materiasList.setDocumento(optional.get().getDocumento());
//            materiasList.setHoras(optional.get().getHoras());
//            materiasList.setParticipantes(optional.get().getParticipantes());
//            materiasList.setFechaFin(optional.get().getFechaFin());
//            materiasList.setFechaInicio(optional.get().getFechaInicio());
////            materiasList.setPlazoEjecucion(optional.get().getPlazoEjecucion());
////                if (optional.get().getTutorEmp() != null) {
////                materiasList.setNombretutoremp(nombreDirector(optional.get().getTutorEmp().getId()));
////            } else {
////                materiasList.setNombretutoremp("");
////            }
//            materiasList.setTutorAcademicoResponse(getDocenteApoyo(optional.get()));
//            materiasList.setActividadeslistProyectos(getActividadeByProject(optional.get()));
//            materiasList.setRequisitoslistProyectos(getRequisitosByProject(optional.get()));
//            materiasList.setActividadesEmpresaProyecto(getActividadesEmpreSAByProyecto(optional.get()));
//
//            materiasList.setFechaat(optional.get().getFechaat());
//            return materiasList;
//        }
//        throw new ResponseNotFoundException("Proyecto PPP", "id", id.toString());
//    }
//
//    public void deleteDocenteApoyo(Long id) {
//        Optional<TutorAcademicoDelegados> optional = tutorAcademicoRepository.findById(id);
//        if (optional.isPresent()) {
//            tutorAcademicoRepository.deleteById(id);
//            return;
//        }
//        throw new BadRequestException("El tutor academico con el id: " + id + ", no existe");
//    }
//
//    @Transactional
//    public void deleteProyecoById(Long id) {
//        Optional<ProyectoPPP> optional = proyectoRepository.findById(id);
//        if (optional.isEmpty()) {
//            throw new BadRequestException("El proyecto con id: " + id + ", no existe");
//        }
//        optional.get().getTutorAcademicoDelegados()
//                .forEach(d -> docentesService.deleteDocenteApoyoById(d.getId()));
//
//        optional.get().getActividadesempresaProyecto()
//                .forEach(o -> actividadesEmpresaRepository.deleteById(o.getId()));
//
//        if (optional.get().getAnexo1() != null)
//            anexo1Service.deleteAnexosList(optional.get().getAnexo1());
//
//        proyectoRepository.delete(optional.get());
//    }
//
//    @Transactional
//    public void updateRequisitos(Long id, List<RequisitoslistProyecto> requisitos) {
//
//        Optional<ProyectoPPP> optional = proyectoRepository.findById(id);
//
//        if (optional.isPresent()) {
//            List<RequisitosProyecto> proyecto = optional.get().getRequisitosProyecto();
//            proyecto.forEach(r -> {
//                Optional<RequisitoslistProyecto> exists = requisitos
//                        .stream()
//                        .filter(req -> req.getDescripcion().equalsIgnoreCase(r.getDescripcion()))
//                        .findAny();
//                if (exists.isEmpty()) {
//                    requisitosRepository.delete(r);
//                }
//            });
//
//            requisitos.forEach(request -> {
//                String descripcion = request.getDescripcion();
//                Optional<RequisitosProyecto> exists = proyecto
//                        .stream()
//                        .filter(r -> r.getDescripcion().equalsIgnoreCase(descripcion))
//                        .findAny();
//                if (exists.isEmpty()) {
//                    RequisitosProyecto save = new RequisitosProyecto();
//                    save.setProyectoPPP(optional.get());
//                    save.setDescripcion(request.getDescripcion());
//                    requisitosRepository.save(save);
//                }
//            });
//        } else {
//            throw new BadRequestException("El proyecto con id: " + id + ", no existe");
//        }
//    }
//
//    @Transactional
//    public void updateActividades(Long id, List<ActividadeslistProyecto> requisitos) {
//
//        Optional<ProyectoPPP> optional = proyectoRepository.findById(id);
//
//        if (optional.isPresent()) {
//            List<ActividadesProyecto> proyecto = optional.get().getActividadesProyecto();
//            proyecto.forEach(r -> {
//                Optional<ActividadeslistProyecto> exists = requisitos
//                        .stream()
//                        .filter(req -> req.getDescripcion().equalsIgnoreCase(r.getDescripcion()))
//                        .findAny();
//                if (exists.isEmpty()) {
//                    actividadesRepository.delete(r);
//                }
//            });
//
//            requisitos.forEach(request -> {
//                String descripcion = request.getDescripcion();
//                Optional<ActividadesProyecto> exists = proyecto
//                        .stream()
//                        .filter(r -> r.getDescripcion().equalsIgnoreCase(descripcion))
//                        .findAny();
//                if (exists.isEmpty()) {
//                    ActividadesProyecto save = new ActividadesProyecto();
//                    save.setProyectoPPP(optional.get());
//                    save.setDescripcion(request.getDescripcion());
//                    actividadesRepository.save(save);
//                }
//            });
//        } else {
//            throw new BadRequestException("El proyecto con id: " + id + ", no existe");
//        }
//    }
//
//    public List<ProyectoResponse> getProyectoCIApoyoo(String cedula) {
//        Optional<TutorAcademicoDelegados> op = tutorAcademicoRepository.findByCedula(cedula);
//        if (op.isPresent() && op.get().isEstado()) {
//            Optional<ProyectoPPP> optional = proyectoRepository.findById(op.get().getProyectoPPP().getId());
//            if (optional.isPresent()) {
//                List<ProyectoResponse> response = new ArrayList<>();
//                ProyectoResponse materiasList = new ProyectoResponse();
//                materiasList.setParticipantes(optional.get().getParticipantes());
//                materiasList.setId(optional.get().getId());
//                materiasList.setCodigo(optional.get().getCodigo());
//                materiasList.setNombre(optional.get().getNombre());
//                materiasList.setCodigocarrera(optional.get().getCodigocarrera());
//                materiasList.setCarrera(getNombreCarrea(optional.get().getCodigocarrera()));
//                materiasList.setEstado(optional.get().isEstado());
//                materiasList.setNombreempresa(optional.get().getEmpresa().toString());
//                materiasList.setNombreresponsable(nombreResponsable(optional.get().getResponsablePPP().getId()));
//                materiasList.setEmpresa(optional.get().getEmpresa());
//                materiasList.setNombresolicitante(optional.get().getNombresolicitante());
//                materiasList.setCargosolicitante(optional.get().getCargosolicitante());
//                materiasList.setDocumento(optional.get().getDocumento());
//                materiasList.setHoras(optional.get().getHoras());
//                materiasList.setParticipantes(optional.get().getParticipantes());
//                materiasList.setFechaFin(optional.get().getFechaFin());
//                materiasList.setFechaInicio(optional.get().getFechaInicio());
//                materiasList.setPlazoEjecucion(optional.get().getPlazoEjecucion());
////                if (optional.get().getTutorEmp() != null) {
////                    materiasList.setNombretutoremp(nombreDirector(optional.get().getTutorEmp().getId()));
////                } else {
////                    materiasList.setNombretutoremp("");
////                }
//                materiasList.setTutorAcademicoResponse(getDocenteApoyo(optional.get()));
//                materiasList.setActividadeslistProyectos(getActividadeByProject(optional.get()));
//                materiasList.setRequisitoslistProyectos(getRequisitosByProject(optional.get()));
//                materiasList.setActividadesEmpresaProyecto(getActividadesEmpreSAByProyecto(optional.get()));
//                materiasList.setFechaat(optional.get().getFechaat());
//                response.add(materiasList);
//                return response;
//            }
//            throw new ResponseNotFoundException("Proyecto PPP", "id", op.get().getProyectoPPP().getId() + "");
//        }
//        throw new ResponseNotFoundException("Docente apoyo ", "cedula", cedula);
//    }
//
//    public ProyectoResponse getProyectoCIApoyo(String cedula) {
//        Optional<TutorAcademicoDelegados> op = tutorAcademicoRepository.findByCedula(cedula);
//        if (op.isPresent()) {
//            Optional<ProyectoPPP> optional = proyectoRepository.findById(op.get().getProyectoPPP().getId());
//            if (optional.isPresent()) {
//                return toProyectoResponse(optional.get());
//            }
//            throw new ResponseNotFoundException("Proyecto PPP", "id", op.get().getProyectoPPP().getId() + "");
//        } else {
//            throw new ResponseNotFoundException("tutor academico ", "cedula", cedula);
//        }
//    }
//
////    @Transactional(readOnly = true)
////    public List<ProyectoResponse> allByTutorempCedula(String cedula) {
////        Optional<TutorEmp> optional = tutorEmpProyectoRepository.findByCedula(cedula);
////        if (optional.isPresent()) {
////            return optional.get()
////                    .getProyectoPPP()
////                    .stream()
////                    .map(this::toProyectoResponse)
////                    .collect(Collectors.toList());
////        }
////        throw new BadRequestException("El tutor empresarial con c????dula: " + cedula + ", no existe");
////    }
//
//    @Transactional(readOnly = true)
//    public List<ProyectoResponse> allByApoyoCedula(String cedula) {
//        if (tutorAcademicoRepository.existsByCedula(cedula)) {
//            List<TutorAcademicoDelegados> docentes = tutorAcademicoRepository.findAllByCedula(cedula);
//            return docentes.stream()
//                    .map(d -> toProyectoResponse(d.getProyectoPPP()))
//                    .collect(Collectors.toList());
//        }
//        throw new BadRequestException("tutor academico con c????dula:" + cedula + " no existe");
//    }
//
//    private ProyectoResponse toProyectoResponse(ProyectoPPP proyectoPPP) {
//        ProyectoResponse response = new ProyectoResponse();
//        response.setParticipantes(proyectoPPP.getParticipantes());
//        response.setId(proyectoPPP.getId());
//        response.setCodigo(proyectoPPP.getCodigo());
//        response.setNombre(proyectoPPP.getNombre());
//        response.setCarrera(getNombreCarrea(proyectoPPP.getCodigocarrera()));
//        response.setEstado(proyectoPPP.isEstado());
//        response.setNombreempresa(proyectoPPP.getEmpresa().toString());
//        response.setNombreresponsable(nombreResponsable(proyectoPPP.getResponsablePPP().getId()));
//        response.setEmpresa(proyectoPPP.getEmpresa());
//        response.setNombresolicitante(proyectoPPP.getNombresolicitante());
//        response.setCargosolicitante(proyectoPPP.getCargosolicitante());
//        response.setCodigocarrera(proyectoPPP.getCodigocarrera());
//        response.setDocumento(proyectoPPP.getDocumento());
//        response.setHoras(proyectoPPP.getHoras());
//        response.setFechaInicio(proyectoPPP.getFechaInicio());
//        response.setFechaFin(proyectoPPP.getFechaFin());
//        response.setPlazoEjecucion(proyectoPPP.getPlazoEjecucion());
//        response.setParticipantes(proyectoPPP.getParticipantes());
//        response.setCodigocarrera(proyectoPPP.getCodigocarrera());
////        if (proyectoPPP.getTutorEmp() != null) {
////            response.setNombretutoremp(nombreDirector(proyectoPPP.getTutorEmp().getId()));
////        } else {
////            response.setNombretutoremp("");
////        }
//        response.setTutorAcademicoResponse(getDocenteApoyo(proyectoPPP));
//        response.setActividadeslistProyectos(getActividadeByProject(proyectoPPP));
//        response.setRequisitoslistProyectos(getRequisitosByProject(proyectoPPP));
//        response.setActividadesEmpresaProyecto(getActividadesEmpreSAByProyecto(proyectoPPP));
//        response.setFechaat(proyectoPPP.getFechaat());
//        return response;
//    }




//}