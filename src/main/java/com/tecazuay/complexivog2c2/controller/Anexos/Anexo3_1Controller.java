package com.tecazuay.complexivog2c2.controller.Anexos;

import com.tecazuay.complexivog2c2.dto.anexos.Anexo3_1Request;
import com.tecazuay.complexivog2c2.dto.anexos.Anexo3_1Response;
import com.tecazuay.complexivog2c2.exception.Mensaje;
import com.tecazuay.complexivog2c2.service.Anexos.Anexo3_1Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/api/anexo3_1")
public class Anexo3_1Controller {

    @Autowired
    private Anexo3_1Service anexo3_1Service;

    @PostMapping
    public ResponseEntity<?> create(@RequestBody Anexo3_1Request request) {
        anexo3_1Service.save(request);
        return new ResponseEntity(new Mensaje("Anexo 3.1 Creado"), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<?> update(@RequestBody Anexo3_1Request request) {
        anexo3_1Service.update(request);
        return new ResponseEntity(new Mensaje("Anexo 3.1 Actualiazado"), HttpStatus.CREATED);
    }

    @GetMapping("/allByProyecto/{proyectoId}")
    public ResponseEntity<Anexo3_1Response> listAnexosPro(@PathVariable Long proyectoId) {
        Anexo3_1Response anexos = anexo3_1Service.anexoIdProyecto(proyectoId);
        return new ResponseEntity<>(anexos, HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Anexo3_1Response>> listAll() {
        List<Anexo3_1Response> anexos = anexo3_1Service.listAll();
        return new ResponseEntity<List<Anexo3_1Response>>(anexos, HttpStatus.OK);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteAnexo3_1(@PathVariable Long id){
        anexo3_1Service.deleteAnexo3_1ById(id);
        return  new ResponseEntity<>(new Mensaje("Anexo 3.1  eliminado"),HttpStatus.OK);

    }

}
