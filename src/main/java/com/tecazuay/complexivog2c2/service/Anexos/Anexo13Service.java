package com.tecazuay.complexivog2c2.service.Anexos;

import com.tecazuay.complexivog2c2.dto.anexos.Anexo13Request;
import com.tecazuay.complexivog2c2.dto.anexos.Anexo13Response;
import com.tecazuay.complexivog2c2.exception.BadRequestException;
import com.tecazuay.complexivog2c2.model.Primary.Anexos.Anexo13;
import com.tecazuay.complexivog2c2.model.Primary.Anexos.TutorEmp;
import com.tecazuay.complexivog2c2.model.Primary.empresa.Empresa;
import com.tecazuay.complexivog2c2.model.Primary.solicitudproyecto.ProyectoPPP;
import com.tecazuay.complexivog2c2.repository.Primary.Anexos.Anexo13Reepository;
import com.tecazuay.complexivog2c2.repository.Primary.Anexos.TutorEmpProyectoRepository;
import com.tecazuay.complexivog2c2.repository.Primary.empresa.EmpresaRepository;
import com.tecazuay.complexivog2c2.repository.Primary.solicitudproyecto.ProyectoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class Anexo13Service {

    @Autowired
    private Anexo13Reepository anexo13Reepository;

    @Autowired
    private ProyectoRepository proyectoRepository;

    @Autowired
    private EmpresaRepository empresaRepository;

    @Autowired
    private TutorEmpProyectoRepository tutorEmpProyectoRepository;


    public boolean save(Anexo13Request request){
        Optional<ProyectoPPP> optional= proyectoRepository.findById(request.getIdProyectoPPP());
        Optional<Empresa> optionalEmpresa= empresaRepository.findById(request.getIdEmpresa());
        Optional<TutorEmp> optionalTutorEmp= tutorEmpProyectoRepository.findById(request.getIdTutorEmp());

        if (optional.isPresent()) {
            if(!optional.isPresent())
                throw new BadRequestException("El proyecto ha finalizado, no se puede modificar");

            Anexo13 anexo = new Anexo13();
            anexo.setFechaEmision(request.getFechaEmision());
            anexo.setEmpresa(optionalEmpresa.get());
            anexo.setProyectoPPP(optional.get());
            anexo.setTutor(optionalTutorEmp.get());
            anexo.setDocumento(request.getDocumento());

            try{
                anexo13Reepository.save(anexo);
            }catch(Exception e){
                throw new BadRequestException("No se guardó el anexo 13" + e);
            }
        }
        throw new BadRequestException("No existe el proyecto con id: " + request.getIdProyectoPPP());
    }

    @Transactional
    public List<Anexo13Response> listAll(){
        return anexo13Reepository.findAll().stream().map(a->{
            Anexo13Response response = new Anexo13Response();
            response.setId(a.getId());
            response.setDocumento(a.getDocumento());
            response.setIdTutorEmp(a.getTutor().getId());
            response.setIdProyectoPPP(a.getProyectoPPP().getId());
            response.setIdEmpresa(a.getEmpresa().getId());
            return response;
        }).collect(Collectors.toList());
    }

    public List<Anexo13Response> anexoIdProyecto(Long proyectoId){
        Optional<ProyectoPPP> optional= proyectoRepository.findById(proyectoId);
//        Optional<Empresa> optionalEmpresa= empresaRepository.findById(request.getIdEmpresa());
//        Optional<TutorEmp> optionalTutorEmp= tutorEmpProyectoRepository.findById(request.getIdTutorEmp());

        if(optional.isPresent()){
            List<Anexo13> c= anexo13Reepository.findAnexo13ByProyectoPPP(optional.get());

            return c.stream().map(a->{
                Anexo13Response response= new Anexo13Response();
                response.setId(a.getId());
                response.setFechaEmision(a.getFechaEmision());
                response.setDocumento(a.getDocumento());
                response.setIdProyectoPPP(a.getProyectoPPP().getId());
                response.setIdProyectoPPP(a.getProyectoPPP().getId());
                response.setIdTutorEmp(a.getTutor().getId());
                return response;
            }).collect(Collectors.toList());
        }
        throw new BadRequestException("No existe la solicitud: " + proyectoId);
    }


}
