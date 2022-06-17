package com.tecazuay.complexivog2c2.controller.Anexos;

import com.tecazuay.complexivog2c2.dto.anexos.Anexo2Request;
import com.tecazuay.complexivog2c2.dto.anexos.Anexo2Response;
import com.tecazuay.complexivog2c2.dto.anexos.Anexo8_1Request;
import com.tecazuay.complexivog2c2.dto.anexos.Anexo8_1Response;
import com.tecazuay.complexivog2c2.exception.Mensaje;
import com.tecazuay.complexivog2c2.service.Anexos.Anexo8_1Service;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/api/anexo8_1")
public class Anexo8_1Controller {
    private Anexo8_1Service anexo8_1Service;

    @PostMapping
    public ResponseEntity<?> create(@RequestBody Anexo8_1Request request) {
        anexo8_1Service.save(request);
        return new ResponseEntity(new Mensaje("Anexo 8.1 Creado"), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<?> update(@RequestBody Anexo8_1Request request) {
        anexo8_1Service.update(request);
        return new ResponseEntity(new Mensaje("Anexo 8.1 Actualiazado"), HttpStatus.CREATED);
    }

    @GetMapping("/allByProyecto/{proyectoId}")
    public ResponseEntity<Anexo8_1Response> listAnexosPro(@PathVariable Long proyectoId) {
        Anexo8_1Response anexos = anexo8_1Service.anexoIdProyecto(proyectoId);
        return new ResponseEntity<>(anexos, HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Anexo8_1Response>> listAll() {
        List<Anexo8_1Response> anexos = anexo8_1Service.listAll();
        return new ResponseEntity<List<Anexo8_1Response>>(anexos, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteAnexo8_1(@PathVariable Long id){
        anexo8_1Service.deleteAnexo8_1ById(id);
        return  new ResponseEntity<>(new Mensaje("Anexo 2  eliminado"),HttpStatus.OK);

    }
}
