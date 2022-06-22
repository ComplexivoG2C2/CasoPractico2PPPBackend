package com.tecazuay.complexivog2c2.service.Anexos;

import com.tecazuay.complexivog2c2.dto.anexos.Anexo81Response;
import com.tecazuay.complexivog2c2.dto.anexos.TutorEmpRequest;
import com.tecazuay.complexivog2c2.dto.anexos.TutorEmpResponse;
import com.tecazuay.complexivog2c2.dto.solicitudproyectos.ProyectoRequest;
import com.tecazuay.complexivog2c2.exception.BadRequestException;
import com.tecazuay.complexivog2c2.model.Primary.Anexos.Anexo81;
import com.tecazuay.complexivog2c2.model.Primary.Anexos.TutorEmp;
import com.tecazuay.complexivog2c2.model.Primary.empresa.Empresa;
import com.tecazuay.complexivog2c2.model.Primary.solicitudproyecto.ProyectoPPP;
import com.tecazuay.complexivog2c2.repository.Primary.Anexos.TutorEmpProyectoRepository;
import com.tecazuay.complexivog2c2.repository.Primary.empresa.EmpresaRepository;
import com.tecazuay.complexivog2c2.repository.Primary.solicitudproyecto.ProyectoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TutorEmpService {

    @Autowired
    private TutorEmpProyectoRepository tutorEmpProyectoRepository;


    @Autowired
    private EmpresaRepository empresaRepository;

    @Autowired
    private ProyectoRepository proyectoRepository;



    public boolean save(TutorEmpRequest teRequest) {
        Optional<Empresa> optional = empresaRepository.findById(teRequest.getEmpresa_id());
        if (optional.isPresent()) {

            TutorEmp eb = new TutorEmp();
            eb.setCedula(teRequest.getCedula());
            eb.setNombres(teRequest.getNombres());
            eb.setApellidos(teRequest.getApellidos());
            eb.setCorreo(teRequest.getCorreo());
            eb.setClave(teRequest.getClave());
            eb.setFecha_designacion(teRequest.getFecha_designacion());
            eb.setEstado(teRequest.isEstado());
            eb.setEmpresa(optional.get());

            try {
                tutorEmpProyectoRepository.save(eb);
                return true;
            } catch (Exception ex) {
                throw new BadRequestException("No se guardó el tutor empresarial" + ex);
            }
        }
        throw new BadRequestException("No existe empresa con id: " + teRequest.getEmpresa_id());
    }



//    public TutorEmp getIdPPP(Long id) {
//        Optional<TutorEmp> optional = tutorEmpProyectoRepository.findById(id);
//        if (optional.isPresent()) {
//
//            return proyectoRepository.findByTutorEmp(optional.get()).orElse(new ());
//        }else{
//            throw new BadRequestException("No se encontró el id del Coordinador de Vinculacion");
//        }
//
//    }





    @Transactional
    public void deleteById(Long id) {
        Optional<TutorEmp> optional = tutorEmpProyectoRepository.findById(id);
        if (optional.isEmpty()) {
            throw new BadRequestException("El tutor de solicitudproyecto con el id: " + id + ", no existe");
        }
        tutorEmpProyectoRepository.delete(optional.get());
    }

//    @Transactional(readOnly = true)
//    public TutorEmpCedulaResponse getCedulaDirectorByProject(Long id) {
//        Optional<ProyectoPPP> optional = proyectoRepository.findById(id);
//        if (optional.isEmpty())
//            throw new BadRequestException("El solicitudproyecto con id: " + id + ", no existe");
//
//        String cedula = optional.get().getTutorEmp().getCedula();
//        return new TutorEmpCedulaResponse(cedula);
//    }

    public List<TutorEmpResponse> listTutor() {
        List<TutorEmp> lista = tutorEmpProyectoRepository.findAll();
        return lista.stream().map(tutores -> {
            TutorEmpResponse te = new TutorEmpResponse();
            te.setId(tutores.getId());
            te.setCedula(tutores.getCedula());
            te.setNombres(tutores.getNombres());
            te.setApellidos(tutores.getApellidos());
            te.setCorreo(tutores.getCorreo());
            te.setClave(tutores.getClave());
            te.setEstado(tutores.getEstado());
            te.setFecha_designacion(tutores.getFecha_designacion());

            return te;
        }).collect(Collectors.toList());
    }

    public boolean updateidsolicitud(TutorEmpRequest tutorEmpRequest) {
        try {
            TutorEmp tutorEmp = gettutor(tutorEmpRequest.getId());
            tutorEmp.setProyectoPPP(proyectoRepository.findById(tutorEmpRequest.getIdProyectoPPP()).orElse(new ProyectoPPP()));
           TutorEmp saved = tutorEmpProyectoRepository.save(tutorEmp);
            return true;
        }catch (Exception e){
            throw new BadRequestException("no se actualizo id pp de tutor: " + e);
        }
    }

    public TutorEmp gettutor(Long id) {
        Optional<TutorEmp> optional = tutorEmpProyectoRepository.findById(id);
        if (optional.isPresent()) {
            return optional.get();
        }
        throw new BadRequestException("No existe el tutor");
    }


    @Transactional
    public List<TutorEmpResponse> listTutorporidsolicitud(Long id) {
        Optional<ProyectoPPP> op = proyectoRepository.findById(id);
        if (op.isPresent()) {
            List<TutorEmp> lista = tutorEmpProyectoRepository.findAllByProyectoPPP(op.get());
            return lista.stream().map(te -> {
                TutorEmpResponse an = new TutorEmpResponse();
                an.setId(te.getId());
                an.setCedula(te.getCedula());
                an.setNombres(te.getNombres());
                an.setApellidos(te.getApellidos());
                an.setCorreo(te.getCorreo());
                an.setEstado(te.getEstado());
                an.setFecha_designacion(te.getFecha_designacion());
                an.setIdProyectoPPP(te.getProyectoPPP().getId());
                return an;
            }).collect(Collectors.toList());

        }
        throw new BadRequestException("No existe el tutor empresarial");
    }
}