package com.tecazuay.complexivog2c2.controller.Anexos;

import com.tecazuay.complexivog2c2.dto.anexos.Anexo31Request;
import com.tecazuay.complexivog2c2.dto.anexos.Anexo31Response;
import com.tecazuay.complexivog2c2.exception.Mensaje;
import com.tecazuay.complexivog2c2.service.Anexos.Anexo31Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/api/anexo31")
public class Anexo31Controller {

    @Autowired
    private Anexo31Service anexo31Service;

    @PostMapping
    public ResponseEntity<?> create(@RequestBody Anexo31Request request) {
        anexo31Service.save(request);
        return new ResponseEntity(new Mensaje("Anexo 3.1 Creado"), HttpStatus.CREATED);
    }
//
//    @PutMapping
//    public ResponseEntity<?> update(@RequestBody Anexo31Request request) {
//        anexo3_1Service.update(request);
//        return new ResponseEntity(new Mensaje("Anexo 3.1 Actualiazado"), HttpStatus.CREATED);
//    }
//
//    @GetMapping("/allByProyecto/{proyectoId}")
//    public ResponseEntity<Anexo31Response> listAnexosPro(@PathVariable Long proyectoId) {
//        Anexo31Response anexos = anexo3_1Service.anexoIdProyecto(proyectoId);
//        return new ResponseEntity<>(anexos, HttpStatus.OK);
//    }

    @GetMapping("/all")
    public ResponseEntity<List<Anexo31Response>> listAll() {
        List<Anexo31Response> anexos = anexo31Service.listAll();
        return new ResponseEntity<List<Anexo31Response>>(anexos, HttpStatus.OK);
    }


//
//    @DeleteMapping("/{id}")
//    public ResponseEntity<?> deleteAnexo3_1(@PathVariable Long id){
//        anexo3_1Service.deleteAnexo3_1ById(id);
//        return  new ResponseEntity<>(new Mensaje("Anexo 3.1  eliminado"),HttpStatus.OK);
//
//    }

}
