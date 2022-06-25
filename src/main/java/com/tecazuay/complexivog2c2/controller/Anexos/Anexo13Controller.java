package com.tecazuay.complexivog2c2.controller.Anexos;

import com.tecazuay.complexivog2c2.dto.anexos.Anexo13Request;
import com.tecazuay.complexivog2c2.dto.anexos.Anexo13Response;
import com.tecazuay.complexivog2c2.dto.anexos.Anexo5Response;
import com.tecazuay.complexivog2c2.exception.Mensaje;
import com.tecazuay.complexivog2c2.service.Anexos.Anexo13Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/api/anexo13")
public class Anexo13Controller {

    @Autowired
    private Anexo13Service anexo13Service;

    @PostMapping
    public ResponseEntity<?> create(@RequestBody Anexo13Request request){
        anexo13Service.save(request);
        return new ResponseEntity(new Mensaje("Anexo 13 Creado"), HttpStatus.CREATED);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Anexo13Response>> listAll(){
        List<Anexo13Response> anexo = anexo13Service.listAll();
        return new ResponseEntity<List<Anexo13Response>>(anexo, HttpStatus.OK);
    }

    @GetMapping("/allByProyecto/{proyectoId}")
    public ResponseEntity<List<Anexo13Response>> listAnexosPro(@PathVariable Long proyectoId){
        List<Anexo13Response> anexos = anexo13Service.anexoIdProyecto(proyectoId);
        return new ResponseEntity<List<Anexo13Response>>(anexos, HttpStatus.OK);
    }
}
