package com.tecazuay.complexivog2c2.controller.Anexos;

import com.tecazuay.complexivog2c2.dto.anexos.*;
import com.tecazuay.complexivog2c2.exception.Mensaje;
import com.tecazuay.complexivog2c2.service.Anexos.Anexo7Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/api/anexo7")
public class Anexo7Controller {

    @Autowired
    private Anexo7Service anexo7Service;

    @PostMapping
    public ResponseEntity<?> create(@RequestBody Anexo7Request request) {
        anexo7Service.save(request);
        return new ResponseEntity(new Mensaje("Anexo 7 Creado"), HttpStatus.CREATED);
    }

    @GetMapping("/allByProyecto/{proyectoId}")
    public ResponseEntity<Anexo7Response> listAnexosPro(@PathVariable Long proyectoId) {
        Anexo7Response anexos = anexo7Service.anexoIdProyecto(proyectoId);
        return new ResponseEntity<>(anexos, HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Anexo7Response>> listAll() {
        List<Anexo7Response> anexos = anexo7Service.listAll();
        return new ResponseEntity<List<Anexo7Response>>(anexos, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteAnexo7(@PathVariable Long id){
        anexo7Service.deleteAnexo7ById(id);
        return  new ResponseEntity<>(new Mensaje("Anexo 7  eliminado"),HttpStatus.OK);

    }
}
