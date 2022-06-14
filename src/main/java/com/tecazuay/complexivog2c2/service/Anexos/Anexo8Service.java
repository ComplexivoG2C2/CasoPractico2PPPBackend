package com.tecazuay.complexivog2c2.service.Anexos;

import com.tecazuay.complexivog2c2.dto.anexos.Anexo8Request;
import com.tecazuay.complexivog2c2.dto.anexos.Anexo8Response;
import com.tecazuay.complexivog2c2.exception.BadRequestException;
import com.tecazuay.complexivog2c2.exception.ResponseNotFoundException;
import com.tecazuay.complexivog2c2.model.Primary.Anexos.Anexo8;
import com.tecazuay.complexivog2c2.model.Primary.solicitudproyecto.ProyectoPPP;
import com.tecazuay.complexivog2c2.repository.Primary.Anexos.Anexo8Repository;
import com.tecazuay.complexivog2c2.repository.Primary.alumnos.AlumnosRepository;
import com.tecazuay.complexivog2c2.repository.Primary.designaciones.ResponsablePPPRepository;
import com.tecazuay.complexivog2c2.repository.Primary.designaciones.TutorEmpProyectoRepository;
import com.tecazuay.complexivog2c2.repository.Primary.solicitudproyecto.ProyectoRepository;
import com.tecazuay.complexivog2c2.repository.Secondary.personas.PersonasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class Anexo8Service {
    @Autowired
    private Anexo8Repository anexo8Repository;
    @Autowired
    private ProyectoRepository proyectoRepository;
    @Autowired
    private PersonasRepository personasRepository;
    @Autowired
    private TutorEmpProyectoRepository tutorempRepository;
    @Autowired
    private ResponsablePPPRepository responsablePPPRepository;
    @Autowired
    private AlumnosRepository alumnosRepository;


    @Transactional
    public boolean save (Anexo8Request anexo8Request) {
        Optional<ProyectoPPP> optional = proyectoRepository.findById(anexo8Request.getIdProyectoPPP());
        if (optional.isPresent()) {
            if (!optional.get().isEstado())
                throw new BadRequestException("El proyecto ha finalizado, no es posible modificar sus datos");

            Anexo8 an = new Anexo8();
            if (personasRepository.existsByCedula(anexo8Request.getCedulaEstudiante())) {

                an.setFechaRespuesta(anexo8Request.getFechaRespuesta());
                an.setEstudianteTitulo(anexo8Request.getEstudianteTitulo());
                an.setNombreEstudiante(anexo8Request.getNombreEstudiante());
                an.setSiglasCarrera(anexo8Request.getSiglasCarrera());
                an.setNombreProyecto(anexo8Request.getNombreProyecto());
                an.setNombreTutoremp(anexo8Request.getNombreTutoremp());
                an.setNombreRepresentante(anexo8Request.getNombreRepresentante());
                an.setNumeroHoras(anexo8Request.getNumeroHoras());
                an.setNombreResponsable(anexo8Request.getNombreResponsable());
                an.setFechaRecepcionEst(anexo8Request.getFechaRecepcionEst());
                an.setProyectoPPP(optional.get());
                an.setDocumento(anexo8Request.getDocumento());
                an.setCedulaEstudiante(anexo8Request.getCedulaEstudiante());
                an.setObservaciones(anexo8Request.getObservaciones());
                an.setNum_proceso(anexo8Request.getNum_proceso());
                try {
                    anexo8Repository.save(an);
                    return true;
                } catch (Exception ex) {
                    throw new BadRequestException("No se guardó el anexo 8" + ex);
                }


            } else {
                throw new ResponseNotFoundException("ESTUDIANTE", "CEDULA:", "" + anexo8Request.getCedulaEstudiante());

            }

        }
        throw new BadRequestException("No existe el proyecto con id: " + anexo8Request.getIdProyectoPPP());
    }

    public boolean update (Anexo8Request anexo8Request) {
        Optional<Anexo8> an = anexo8Repository.findById(anexo8Request.getId());
        if (an.isPresent()) {
            if (!an.get().getProyectoPPP().isEstado())
                throw new BadRequestException("El proyecto ha finalizado, no es posible modificar sus datos");

            if (personasRepository.existsByCedula(anexo8Request.getCedulaEstudiante())) {

                an.get().setFechaRespuesta(anexo8Request.getFechaRespuesta());
                an.get().setEstudianteTitulo(anexo8Request.getEstudianteTitulo());
                an.get().setNombreEstudiante(anexo8Request.getNombreEstudiante());
                an.get().setSiglasCarrera(anexo8Request.getSiglasCarrera());
                an.get().setNombreProyecto(anexo8Request.getNombreProyecto());
                an.get().setNombreTutoremp(anexo8Request.getNombreTutoremp());
                an.get().setNombreRepresentante(anexo8Request.getNombreRepresentante());
                an.get().setNumeroHoras(anexo8Request.getNumeroHoras());
                an.get().setNombreResponsable(anexo8Request.getNombreResponsable());
                an.get().setFechaRecepcionEst(anexo8Request.getFechaRecepcionEst());
                an.get().setProyectoPPP(proyectoRepository.findById(anexo8Request.getIdProyectoPPP()).orElseThrow(() -> new
                        ResponseNotFoundException("PROYECTO", "ID", anexo8Request.getIdProyectoPPP() + "")));
                an.get().setDocumento(anexo8Request.getDocumento());
                an.get().setCedulaEstudiante(anexo8Request.getCedulaEstudiante());
                an.get().setObservaciones(anexo8Request.getObservaciones());
                an.get().setNum_proceso(anexo8Request.getNum_proceso());
                try {
                    Anexo8 an4 = anexo8Repository.save(an.get());
                    return true;
                } catch (Exception ex) {
                    throw new BadRequestException("No se actualizó el anexo 8" + ex);
                }



            }
            else {
                throw new ResponseNotFoundException("ESTUDIANTE", "CEDULA:", "" + anexo8Request.getCedulaEstudiante());
            }

        }
        throw new ResponseNotFoundException("Anexo8", "ID:", "" + anexo8Request.getId());
    }

    public List<Anexo8Response> listAnexo8(Long id){
        Optional<ProyectoPPP> op= proyectoRepository.findById(id);
        if(op.isPresent()){
            List<Anexo8> lista= anexo8Repository.findAllByProyectoPPP(op.get());
            return lista.stream().map(anexo -> {
                Anexo8Response an = new Anexo8Response();
                an.setId(anexo.getId());
                an.setFechaRespuesta(anexo.getFechaRespuesta());
                an.setEstudianteTitulo(anexo.getEstudianteTitulo());
                an.setNombreEstudiante(anexo.getNombreEstudiante());
                an.setSiglasCarrera(anexo.getSiglasCarrera());
                an.setNombreProyecto(anexo.getNombreProyecto());
                an.setNombreTutoremp(anexo.getNombreTutoremp());
                an.setNombreRepresentante(anexo.getNombreRepresentante());
                an.setNumeroHoras(anexo.getNumeroHoras());
                an.setNombreResponsable(anexo.getNombreResponsable());
                an.setFechaRecepcionEst(anexo.getFechaRecepcionEst());
                an.setIdProyectoPPP(anexo.getProyectoPPP().getId());
                an.setDocumento(anexo.getDocumento());
                an.setCedulaEstudiante(anexo.getCedulaEstudiante());
                an.setObservaciones(anexo.getObservaciones());
                an.setNum_proceso(anexo.getNum_proceso());
                return an;
            }).collect(Collectors.toList());
        }
        throw new BadRequestException("No existe el proyecto");
    }

    public List<Anexo8Response> listAnexo8Cedula(String cedula){
        if(alumnosRepository.existsByCedula(cedula)){
            List<Anexo8> lista= anexo8Repository.findAllByCedulaEstudiante(cedula);
            return lista.stream().map(anexo4 -> {
                Anexo8Response an = new Anexo8Response();
                an.setId(anexo4.getId());
                an.setFechaRespuesta(anexo4.getFechaRespuesta());
                an.setEstudianteTitulo(anexo4.getEstudianteTitulo());
                an.setNombreEstudiante(anexo4.getNombreEstudiante());
                an.setSiglasCarrera(anexo4.getSiglasCarrera());
                an.setNombreProyecto(anexo4.getNombreProyecto());
                an.setNombreTutoremp(anexo4.getNombreTutoremp());
                an.setNombreRepresentante(anexo4.getNombreRepresentante());
                an.setNumeroHoras(anexo4.getNumeroHoras());
                an.setNombreResponsable(anexo4.getNombreResponsable());
                an.setFechaRecepcionEst(anexo4.getFechaRecepcionEst());
                an.setIdProyectoPPP(anexo4.getProyectoPPP().getId());
                an.setDocumento(anexo4.getDocumento());
                an.setCedulaEstudiante(anexo4.getCedulaEstudiante());
                an.setObservaciones(anexo4.getObservaciones());
                an.setNum_proceso(anexo4.getNum_proceso());
                return an;
            }).collect(Collectors.toList());
        }


        throw new BadRequestException("No existe el proyecto");
    }

    public void deleteById(Long id){
        Optional<Anexo8> optional = anexo8Repository.findById(id);
        if(optional.isEmpty()){
            throw new BadRequestException("El anexo 8 con el id " + id + ", no existe");

        }
        if (!optional.get().getProyectoPPP().isEstado())
            throw new BadRequestException("El proyecto ha finalizado, no es posible modificar sus datos");

        anexo8Repository.deleteById(id);
    }

    @Transactional(readOnly = true)
    public List<Anexo8Response> getAll() {
        return anexo8Repository.findAll()
                .stream().map(a -> {
                    Anexo8Response response = new Anexo8Response();
                    response.setId(a.getId());
                    response.setFechaRespuesta(a.getFechaRespuesta());
                    response.setEstudianteTitulo(a.getEstudianteTitulo());
                    response.setNombreEstudiante(a.getNombreEstudiante());
                    response.setSiglasCarrera(a.getSiglasCarrera());
                    response.setNombreProyecto(a.getNombreProyecto());
                    response.setNombreTutoremp(a.getNombreTutoremp());
                    response.setNombreRepresentante(a.getNombreRepresentante());
                    response.setNumeroHoras(a.getNumeroHoras());
                    response.setNombreResponsable(a.getNombreResponsable());
                    response.setFechaRecepcionEst(a.getFechaRecepcionEst());
                    response.setIdProyectoPPP(a.getProyectoPPP().getId());
                    response.setDocumento(a.getDocumento());
                    response.setCedulaEstudiante(a.getCedulaEstudiante());
                    response.setObservaciones(a.getObservaciones());
                    response.setNum_proceso(a.getNum_proceso());
                    return response;
                }).collect(Collectors.toList());
    }
}
