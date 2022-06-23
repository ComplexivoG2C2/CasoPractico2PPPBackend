package com.tecazuay.complexivog2c2.controller.Anexos.anexo1214y15;

import com.tecazuay.complexivog2c2.dto.anexos.anexo1214y15.Anexo12Request;
import com.tecazuay.complexivog2c2.dto.anexos.anexo1214y15.Anexo12Response;
import com.tecazuay.complexivog2c2.dto.anexos.anexo1214y15.Anexo12TutorempRequest;
import com.tecazuay.complexivog2c2.exception.Mensaje;
import com.tecazuay.complexivog2c2.service.Anexos.anexo1214y15.Anexo12Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins= {"http://localhost:4200"})
@RestController
@RequestMapping("/api/anexo12")
public class Anexo12Controller {

    @Autowired
    private Anexo12Service anexo12Service;

    @PostMapping
    public ResponseEntity<?> create(@RequestBody Anexo12Request anexo12Request) {
        anexo12Service.save(anexo12Request);
        return new ResponseEntity(new Mensaje("Anexo 12 Creado"), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<?> update(@RequestBody Anexo12Request anexo12Request) {
        anexo12Service.update(anexo12Request);
        return new ResponseEntity(new Mensaje("Anexo 12 Actualizado"), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteAnexo12(@PathVariable Long id) {
        anexo12Service.deleteById(id);
        return new ResponseEntity<>(new Mensaje("Anexo 12 eliminado"), HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Anexo12Response>> listAll() {
        List<Anexo12Response> anexos = anexo12Service.listAll();
        return new ResponseEntity<>(anexos, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Anexo12Response> listAnexo12ById(@PathVariable Long id) {
        Anexo12Response anexo12 = anexo12Service.listAnexo12ById(id);
        return new ResponseEntity<>(anexo12, HttpStatus.OK);
    }
    @GetMapping("/byppp/{proyectoId}")
    public ResponseEntity<Anexo12Response> listAnexo12ByIdppp(@PathVariable Long proyectoId) {
        Anexo12Response anexo12 = anexo12Service.lista1poridppp(proyectoId);
        return new ResponseEntity<>(anexo12, HttpStatus.OK);
    }

    @GetMapping("/allByProyecto/{proyectoId}")
    public ResponseEntity<List<Anexo12Response>> listAnexo12ByPPP(@PathVariable Long proyectoId) {
        List<Anexo12Response> anexos = anexo12Service.listAnexo12ByProyectoPPP(proyectoId);
        return new ResponseEntity<List<Anexo12Response>>(anexos, HttpStatus.OK);
    }

    @PutMapping("/{id}/tutoremp")
    public ResponseEntity<?> updatetutor(@PathVariable Long id, @RequestBody List<Anexo12TutorempRequest> request) {
        anexo12Service.updateTutorEmp(id, request);
        return new ResponseEntity<>(new Mensaje("Anexo 12 tutor e actualizado"), HttpStatus.OK);
    }

}

