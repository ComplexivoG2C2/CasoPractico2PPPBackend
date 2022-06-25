package com.tecazuay.complexivog2c2.service.Anexos.anexo1214y15;

import com.tecazuay.complexivog2c2.dto.anexos.*;
import com.tecazuay.complexivog2c2.dto.anexos.anexo1214y15.Anexo15Request;
import com.tecazuay.complexivog2c2.dto.anexos.anexo1214y15.Anexo15Response;
import com.tecazuay.complexivog2c2.exception.BadRequestException;
import com.tecazuay.complexivog2c2.model.Primary.Anexos.anexo1214y15.Anexo15;
import com.tecazuay.complexivog2c2.model.Primary.solicitudproyecto.ProyectoPPP;
import com.tecazuay.complexivog2c2.repository.Primary.Anexos.anexo1214y15.Anexo15Repository;
import com.tecazuay.complexivog2c2.repository.Primary.solicitudproyecto.ProyectoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class Anexo15Service {
     @Autowired
    private Anexo15Repository anexo15Repository;


    @Autowired
    private ProyectoRepository proyectoRepository;



    @Transactional
    public boolean save (Anexo15Request request) {
        Optional<ProyectoPPP> optional = proyectoRepository.findById(request.getIdProyecto());
        if (optional.isPresent()) {

            Anexo15 anexo = new Anexo15();


            anexo.setCarrera(request.getCarrera());
            anexo.setNombresEstudiante(request.getNombresEstudiante());
            anexo.setCedulaEstudiante(request.getCedulaEstudiante());
            anexo.setNombretutoracademico(request.getNombretutoracademico());
            anexo.setCedulatutoracademico(request.getCedulatutoracademico());

            anexo.setNombretutoremp(request.getNombretutoremp());
            anexo.setCedulatutoremp(request.getCedulatutoremp());

            anexo.setDocumento(request.getDocumento());

            anexo.setCiclo(request.getCiclo());
            anexo.setEmpresa(request.getEmpresa());
            anexo.setFechaEvaluacion(request.getFechaEvaluacion());

            anexo.setTotalHoras(request.getTotalHoras());
            anexo.setSiglascarrera(request.getSiglascarrera());
            anexo.setPeriodoacademico(request.getPeriodoacademico());
            anexo.setNotaTutorA(request.getNotaTutorA());
            anexo.setNotaTutorE(request.getNotaTutorE());
            anexo.setPorcentajeTutorA(request.getPorcentajeTutorA());
            anexo.setPorcentajeTutorA(request.getPorcentajeTutorA());
            anexo.setPromediofinal(request.getPromediofinal());
            anexo.setPromediofinaldecimal(request.getPromediofinaldecimal());

            anexo.setProyectoPPP(optional.get());
                try {
                    anexo15Repository.save(anexo);
                    return true;
                } catch (Exception ex) {
                    throw new BadRequestException("No se guardó el anexo 8" + ex);
                }
        }
        throw new BadRequestException("No existe solicitud con id: " + request.getIdProyecto());
    }




    @Transactional
    public void deleteAnexo15ById(Long id) {
        Optional<Anexo15> optional = anexo15Repository.findById(id);
        if (!optional.isPresent()) {
            throw new BadRequestException("El anexo15 con id: " + id + ", no existe");
        }
        if (!optional.get().getProyectoPPP().isEstado())
            throw new BadRequestException("La solictiud ha finalizado");


        anexo15Repository.delete(optional.get());
    }

    @Transactional
    public List<Anexo15Response> listAll() {
        return anexo15Repository.findAll().stream().map(a -> {
            Anexo15Response response = new Anexo15Response();

            response.setId(a.getId());
            response.setCarrera(a.getCarrera());
            response.setNombresEstudiante(a.getNombresEstudiante());
            response.setCedulaEstudiante(a.getCedulaEstudiante());
            response.setNombretutoracademico(a.getNombretutoracademico());
            response.setCedulatutoracademico(a.getCedulatutoracademico());
            response.setDocumento(a.getDocumento());
            response.setNombretutoremp(a.getNombretutoremp());
            response.setCedulatutoremp(a.getCedulatutoremp());
            response.setCiclo(a.getCiclo());
            response.setEmpresa(a.getEmpresa());
            response.setFechaEvaluacion(a.getFechaEvaluacion());

            response.setTotalHoras(a.getTotalHoras());
            response.setSiglascarrera(a.getSiglascarrera());
            response.setPeriodoacademico(a.getPeriodoacademico());
            response.setNotaTutorA(a.getNotaTutorA());
            response.setNotaTutorE(a.getNotaTutorE());
            response.setPorcentajeTutorA(a.getPorcentajeTutorA());
            response.setPorcentajeTutorA(a.getPorcentajeTutorA());
            response.setPromediofinal(a.getPromediofinal());
            response.setPromediofinaldecimal(a.getPromediofinaldecimal());

            response.setIdProyecto(a.getProyectoPPP().getId());
            return response;
        }).collect(Collectors.toList());
    }








    public Anexo15Response anexo15IdAnexo (Long id){

        Optional<Anexo15> a = anexo15Repository.findById(id);
        if(a.isPresent()){
            Anexo15Response response = new Anexo15Response();
            response.setId(a.get().getId());
            response.setCarrera(a.get().getCarrera());
            response.setSiglascarrera(a.get().getSiglascarrera());


            response.setNombresEstudiante(a.get().getNombresEstudiante());
            response.setCedulaEstudiante(a.get().getCedulaEstudiante());
            response.setNombretutoracademico(a.get().getNombretutoracademico());
            response.setCedulatutoracademico(a.get().getCedulatutoracademico());
            response.setDocumento(a.get().getDocumento());
            response.setNombretutoremp(a.get().getNombretutoremp());
            response.setCedulatutoremp(a.get().getCedulatutoremp());
            response.setCiclo(a.get().getCiclo());
            response.setEmpresa(a.get().getEmpresa());
            response.setFechaEvaluacion(a.get().getFechaEvaluacion());

            response.setTotalHoras(a.get().getTotalHoras());
            response.setPeriodoacademico(a.get().getPeriodoacademico());
            response.setNotaTutorA(a.get().getNotaTutorA());
            response.setNotaTutorE(a.get().getNotaTutorE());
            response.setPorcentajeTutorA(a.get().getPorcentajeTutorA());
            response.setPorcentajeTutorA(a.get().getPorcentajeTutorA());
            response.setPromediofinal(a.get().getPromediofinal());
            response.setPromediofinaldecimal(a.get().getPromediofinaldecimal());



            response.setIdProyecto(a.get().getProyectoPPP().getId());
            return response;
        }
        throw new BadRequestException("No existe el anexo 15 con id: " +id);

    }




    public Anexo15Response anexoIdProyecto(Long proyectoId) {
        Optional<ProyectoPPP> optional = proyectoRepository.findById(proyectoId);
        if (optional.isPresent()) {
            Optional<Anexo15> a = anexo15Repository.findByProyectoPPP(optional.get());
            if (a.isPresent()) {
                Anexo15Response response = new Anexo15Response();

                response.setId(a.get().getId());
                response.setCarrera(a.get().getCarrera());
                response.setSiglascarrera(a.get().getSiglascarrera());


                response.setNombresEstudiante(a.get().getNombresEstudiante());
                response.setCedulaEstudiante(a.get().getCedulaEstudiante());
                response.setNombretutoracademico(a.get().getNombretutoracademico());
                response.setCedulatutoracademico(a.get().getCedulatutoracademico());
                response.setDocumento(a.get().getDocumento());
                response.setNombretutoremp(a.get().getNombretutoremp());
                response.setCedulatutoremp(a.get().getCedulatutoremp());
                response.setCiclo(a.get().getCiclo());
                response.setEmpresa(a.get().getEmpresa());
                response.setFechaEvaluacion(a.get().getFechaEvaluacion());

                response.setTotalHoras(a.get().getTotalHoras());
                response.setPeriodoacademico(a.get().getPeriodoacademico());
                response.setNotaTutorA(a.get().getNotaTutorA());
                response.setNotaTutorE(a.get().getNotaTutorE());
                response.setPorcentajeTutorA(a.get().getPorcentajeTutorA());
                response.setPorcentajeTutorA(a.get().getPorcentajeTutorA());
                response.setPromediofinal(a.get().getPromediofinal());
                response.setPromediofinaldecimal(a.get().getPromediofinaldecimal());

                response.setIdProyecto(a.get().getProyectoPPP().getId());
                return response;
            }
            throw new BadRequestException("No existe el anexo con id de la solicitud: " + proyectoId);
        }

        throw new BadRequestException("No existe la solicitud: " + proyectoId);
    }

}
