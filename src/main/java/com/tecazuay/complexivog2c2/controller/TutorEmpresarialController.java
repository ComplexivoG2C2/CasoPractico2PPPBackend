package com.tecazuay.complexivog2c2.controller;

import com.tecazuay.complexivog2c2.dto.empresa.EmpresaRequest;
import com.tecazuay.complexivog2c2.dto.empresa.EmpresaResponse;
import com.tecazuay.complexivog2c2.dto.tutorEmpresarial.tutorEmpresarialRequest;
import com.tecazuay.complexivog2c2.dto.tutorEmpresarial.tutorEmpresarialResponse;
import com.tecazuay.complexivog2c2.exception.Mensaje;
import com.tecazuay.complexivog2c2.service.designaciones.TutorEmpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins= {"http://localhost:4200"})
@RestController
@RequestMapping("/api/tutorEmpresarial")
public class TutorEmpresarialController {

    @Autowired
    private TutorEmpService tutorService;

    @PostMapping
    public ResponseEntity<?> create(@RequestBody tutorEmpresarialRequest tutorRequest){
        tutorService.save(tutorRequest);
        return new ResponseEntity(new Mensaje("Tutor Creado"), HttpStatus.CREATED);
    }

    @GetMapping("/all")
    public ResponseEntity<List<tutorEmpresarialResponse>>lisTutores(){
        List<tutorEmpresarialResponse> tutores = tutorService.listTutor();
        return new ResponseEntity<List<tutorEmpresarialResponse>>(tutores,HttpStatus.OK);
    }
}
