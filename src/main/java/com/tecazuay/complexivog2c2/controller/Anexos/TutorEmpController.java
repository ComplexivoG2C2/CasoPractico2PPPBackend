package com.tecazuay.complexivog2c2.controller.Anexos;


import com.tecazuay.complexivog2c2.dto.anexos.TutorEmpRequest;
import com.tecazuay.complexivog2c2.dto.anexos.TutorEmpResponse;
import com.tecazuay.complexivog2c2.exception.Mensaje;
import com.tecazuay.complexivog2c2.service.Anexos.TutorEmpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins= {"http://localhost:4200"})
    @RestController
    @RequestMapping("/api/tutorEmpresarial")
    public class TutorEmpController {

        @Autowired
        private TutorEmpService tutorService;

        @PostMapping
        public ResponseEntity<?> create(@RequestBody TutorEmpRequest tutorRequest){
            tutorService.save(tutorRequest);
            return new ResponseEntity(new Mensaje("Tutor Creado"), HttpStatus.CREATED);
        }

        @GetMapping("/all")
        public ResponseEntity<List<TutorEmpResponse>>lisTutores(){
            List<TutorEmpResponse> tutores = tutorService.listTutor();
            return new ResponseEntity<List<TutorEmpResponse>>(tutores,HttpStatus.OK);
        }
    @GetMapping("/allByProyecto/{idProyecto}")
    public ResponseEntity<List<TutorEmpResponse>> listtutoresporproyecto(@PathVariable Long idProyecto) {
        List<TutorEmpResponse> anexos= tutorService.listporidppp(idProyecto);
        return  new ResponseEntity<List<TutorEmpResponse>>(anexos,HttpStatus.OK);
    }

    @PutMapping("/actualizaridproyecto")
    public ResponseEntity<?> updateidtutor(@RequestBody TutorEmpRequest tRequest) {
        tutorService.updateidsolicitud(tRequest);
        return new ResponseEntity<>(new Mensaje("ACTUALIZADO"), HttpStatus.CREATED);
    }
    }