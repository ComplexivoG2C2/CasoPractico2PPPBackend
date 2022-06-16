package com.tecazuay.complexivog2c2.service.Anexos;

import com.tecazuay.complexivog2c2.dto.anexos.*;
import com.tecazuay.complexivog2c2.exception.BadRequestException;
import com.tecazuay.complexivog2c2.exception.ResponseNotFoundException;
import com.tecazuay.complexivog2c2.model.Primary.Anexos.*;
import com.tecazuay.complexivog2c2.model.Primary.desigaciones.TutorEmp;
import com.tecazuay.complexivog2c2.model.Primary.solicitudproyecto.ProyectoPPP;
import com.tecazuay.complexivog2c2.model.Secondary.carreras.VCarrerasAllppp;
import com.tecazuay.complexivog2c2.model.Secondary.personas.VPersonasppp;
import com.tecazuay.complexivog2c2.repository.Primary.Anexos.Anexo3Repository;
import com.tecazuay.complexivog2c2.repository.Primary.Anexos.EstudiantesInformeInicialRepository;
import com.tecazuay.complexivog2c2.repository.Primary.Anexos.InformeInicialRepository;
import com.tecazuay.complexivog2c2.repository.Primary.alumnos.AlumnosRepository;
import com.tecazuay.complexivog2c2.repository.Primary.designaciones.TutorEmpProyectoRepository;
import com.tecazuay.complexivog2c2.repository.Primary.solicitudproyecto.ProyectoRepository;
import com.tecazuay.complexivog2c2.repository.Primary.usuario.UsuarioRepository;
import com.tecazuay.complexivog2c2.repository.Secondary.alumnos.VCicloAprovadoRepository;
import com.tecazuay.complexivog2c2.repository.Secondary.carreras.CarrerasAllRepository;
import com.tecazuay.complexivog2c2.repository.Secondary.personas.PersonasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class InformeInicialService {
    @Autowired
    private InformeInicialRepository informeInicialRepository;

    @Autowired
    private ProyectoRepository proyectoRepository;

    @Autowired
    private TutorEmpProyectoRepository TutorEmpProyectoRepository;

    @Autowired
    private PersonasRepository personasRepository;

    @Autowired
    private VCicloAprovadoRepository vCicloAprovadoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private CarrerasAllRepository carrerasAllRepository;

    @Autowired
    private EstudiantesInformeInicialRepository estudiantesInformeInicialRepository;

    @Autowired
    private AlumnosRepository alumnosRepository;

    @Autowired
    private Anexo3Repository anexo3Repository;



    public boolean save(InformeInicialRequest request){
        InformeInicial informe= new InformeInicial();

        Optional<ProyectoPPP> optional = proyectoRepository.findById(request.getIdProyectoPPP());
        if(optional.isPresent()){
            Optional<TutorEmp> director = TutorEmpProyectoRepository.findById(optional.get().getTutorEmp().getId());
            if(director.isPresent()){
                Optional<VPersonasppp> persona = personasRepository.findByCedula(director.get().getCedula());
                if(persona.isPresent()){
                    Optional<VCarrerasAllppp> carrera = carrerasAllRepository.findByCarreraCodigo(optional.get().getCodigocarrera());
                    if(carrera.isPresent()){
                        informe.setNombreProyecto(optional.get().getNombre());
                        informe.setNombreDirector(persona.get().getNombres()+" "+persona.get().getApellidos());
                        informe.setNombreCarrera(carrera.get().getCarrera_nombre());
                        informe.setAntecedentes(request.getAntecedentes());
                        informe.setObjetivoGeneral(request.getObjetivoGeneral());
                        informe.setEvidencias(request.getEvidencias());
                        informe.setNombreElaborado(request.getNombreElaborado());
                        informe.setCargoElaborado(request.getCargoElaborado());
                        informe.setFechaElaborado(request.getFechaElaborado());
                        informe.setNombreRevisado(request.getNombreRevisado());
                        informe.setCargoRevisado(request.getCargoRevisado());
                        informe.setFechaRevisado(request.getFechaRevisado());
                        informe.setProyectoPPP(optional.get());

                        List<EstudiantesInformeInicial> list= new ArrayList<>();
                        request.getEstudianteInformeInicial().forEach(a->{
                            Optional<Anexo3> estado = anexo3Repository.findByCedulaEstudianteAndProyectoPPP(a.getCedula(),optional.get());

                            if(estado.isPresent() ){

                                request.getEstudianteInformeInicial().stream().forEach(an ->{
                                    EstudiantesInformeInicial estudiantes = new EstudiantesInformeInicial();
                                    estudiantes.setNombreEstudiante(estado.get().getNombreEstudiante()+" "+estado.get().getApellidoEstudiante());
                                    estudiantes.setEstado(estado.get().getEstado());
                                    estudiantes.setObservaciones(an.getObservaciones());
                                    list.add(estudiantes);
                                });
                                try {
                                    InformeInicial informeInicial = informeInicialRepository.save(informe);
                                    saveEstudiantesInformeInicial(list, informeInicial);
                                   // return true;
                                } catch (Exception e) {
                                    throw new BadRequestException("No se guardó el informe inicial" + e);
                                }
                            }else{
                                throw new BadRequestException("No existe una estudiante con cedula:"+persona.get().getCedula());

                            }
                        });





                    }else{
                        throw new BadRequestException("No existe una carrera con id:"+optional.get().getCodigocarrera());

                    }
                }else{
                    throw new BadRequestException("No existe una persona con id:"+optional.get().getCodigocarrera());

                }
            }else{
                throw new BadRequestException("No existe un director con id:"+optional.get().getTutorEmp().getId());

            }


        }else{
            throw new BadRequestException("No existe un proyecto con id:"+request.getIdProyectoPPP());

        }
        return true;
    }

    private void saveEstudiantesInformeInicial(List<EstudiantesInformeInicial> list, InformeInicial informeInicial) {
        list.stream().forEach(a -> {
            a.setInformeInicial(informeInicial);
            estudiantesInformeInicialRepository.save(a);
        });
    }

    @Transactional
    public List<InformeInicialResponse> listAll(){
        return informeInicialRepository.findAll().stream().map(a->{
            InformeInicialResponse response= new InformeInicialResponse();

            response.setId(a.getId());
            response.setNombreProyecto(a.getNombreProyecto());
            response.setNombreDirector(a.getNombreDirector());
            response.setNombreCarrera(a.getNombreCarrera());
            response.setAntecedentes(a.getAntecedentes());
            response.setObjetivoGeneral(a.getObjetivoGeneral());
            response.setEvidencias(a.getEvidencias());
            response.setNombreElaborado(a.getNombreElaborado());
            response.setCargoElaborado(a.getCargoElaborado());
            response.setFechaElaborado(a.getFechaElaborado());
            response.setNombreRevisado(a.getNombreRevisado());
            response.setCargoRevisado(a.getCargoRevisado());
            response.setFechaRevisado(a.getFechaRevisado());

            List<EstudiantesInformeInicialRequest> list= a.getEstudianteInformeInicial().stream().map(ac ->{
                EstudiantesInformeInicialRequest request = new EstudiantesInformeInicialRequest();
                request.setId(ac.getId());
                request.setNombreEstudiante(ac.getNombreEstudiante());
                request.setEstado(ac.getEstado());
                request.setObservaciones(ac.getObservaciones());
                return request;
            }).collect(Collectors.toList());
            response.setEstudianteInformeInicial(list);
            response.setIdProyectoPPP(a.getProyectoPPP().getId());
            return response;
        } ).collect(Collectors.toList());
    }

    public void update(InformeInicialRequest request){
        Optional<InformeInicial> optional = informeInicialRepository.findById(request.getId());
        if(optional.isPresent()){
            InformeInicial informe = optional.get();

            informe.setNombreProyecto(request.getNombreProyecto());
            informe.setNombreDirector(request.getNombreDirector());
            informe.setNombreCarrera(request.getNombreCarrera());
            informe.setAntecedentes(request.getAntecedentes());
            informe.setObjetivoGeneral(request.getObjetivoGeneral());
            informe.setEvidencias(request.getEvidencias());
            informe.setNombreElaborado(request.getNombreElaborado());
            informe.setCargoElaborado(request.getCargoElaborado());
            informe.setFechaElaborado(request.getFechaElaborado());
            informe.setNombreRevisado(request.getNombreRevisado());
            informe.setCargoRevisado(request.getCargoRevisado());
            informe.setFechaRevisado(request.getFechaRevisado());

            List<EstudiantesInformeInicial> estudiantesInformeInicials= new ArrayList<>();
            request.getEstudianteInformeInicial().stream().forEach(ac ->{
                EstudiantesInformeInicial estudiantes = new EstudiantesInformeInicial();

                estudiantes.setNombreEstudiante(ac.getNombreEstudiante());
                estudiantes.setEstado(ac.getEstado());
                estudiantes.setObservaciones(ac.getObservaciones());
                estudiantesInformeInicials.add(estudiantes);


            });

            try {
                InformeInicial a = informeInicialRepository.save(informe);
                saveEstudiantesInformeInicial(estudiantesInformeInicials,a);
            } catch (Exception ex) {
                throw new BadRequestException("No se guardó el informe inicial" + ex);
            }
        }else
            throw new ResponseNotFoundException("Informe Inicial", "Id:", request.getId() + "");

    }


    public List<InformeInicialResponse> allByProyecto(Long idProyecto) {
        Optional<ProyectoPPP> optionalProyectoPPP = proyectoRepository.findById(idProyecto);
        return optionalProyectoPPP.map(proyectoPPP -> informeInicialRepository.findAllByProyectoPPP(proyectoPPP).stream().map(a -> {
            InformeInicialResponse response = new InformeInicialResponse();

            response.setId(a.getId());
            response.setNombreProyecto(a.getNombreProyecto());
            response.setNombreDirector(a.getNombreDirector());
            response.setNombreCarrera(a.getNombreCarrera());
            response.setAntecedentes(a.getAntecedentes());
            response.setObjetivoGeneral(a.getObjetivoGeneral());
            response.setEvidencias(a.getEvidencias());
            response.setNombreElaborado(a.getNombreElaborado());
            response.setCargoElaborado(a.getCargoElaborado());
            response.setFechaElaborado(a.getFechaElaborado());
            response.setNombreRevisado(a.getNombreRevisado());
            response.setCargoRevisado(a.getCargoRevisado());
            response.setFechaRevisado(a.getFechaRevisado());

            List<EstudiantesInformeInicialRequest> list = a.getEstudianteInformeInicial().stream().map(ac -> {
                EstudiantesInformeInicialRequest request = new EstudiantesInformeInicialRequest();
                request.setId(ac.getId());
                request.setNombreEstudiante(ac.getNombreEstudiante());
                request.setEstado(ac.getEstado());
                request.setObservaciones(ac.getObservaciones());
                return request;
            }).collect(Collectors.toList());
            response.setEstudianteInformeInicial(list);
            response.setIdProyectoPPP(a.getProyectoPPP().getId());
            return response;
        }).collect(Collectors.toList())).orElse(null);
    }
}
