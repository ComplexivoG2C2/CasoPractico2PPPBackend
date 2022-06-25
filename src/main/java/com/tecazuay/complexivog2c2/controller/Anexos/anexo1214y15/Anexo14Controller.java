package com.tecazuay.complexivog2c2.controller.Anexos.anexo1214y15;

import com.tecazuay.complexivog2c2.dto.anexos.anexo1214y15.Anexo14Request;
import com.tecazuay.complexivog2c2.dto.anexos.anexo1214y15.Anexo14Response;
import com.tecazuay.complexivog2c2.dto.anexos.anexo1214y15.Anexo14TutorAcaRequest;
import com.tecazuay.complexivog2c2.exception.Mensaje;
import com.tecazuay.complexivog2c2.service.Anexos.anexo1214y15.Anexo14Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins= {"http://localhost:4200"})
@RestController
@RequestMapping("/api/anexo14")
public class Anexo14Controller {

    @Autowired
    private Anexo14Service anexo14Service;

    @PostMapping
    public ResponseEntity<?> create(@RequestBody Anexo14Request anexo14Request) {
        anexo14Service.save(anexo14Request);
        return new ResponseEntity(new Mensaje("Anexo 14 Creado"), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<?> update(@RequestBody Anexo14Request anexo14Request) {
        anexo14Service.update(anexo14Request);
        return new ResponseEntity(new Mensaje("Anexo 14 Actualizado"), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteAnexo14(@PathVariable Long id) {
        anexo14Service.deleteById(id);
        return new ResponseEntity<>(new Mensaje("Anexo 11 eliminado"), HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Anexo14Response>> listAll() {
        List<Anexo14Response> anexos = anexo14Service.listAll();
        return new ResponseEntity<>(anexos, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Anexo14Response> listAnexo12ById(@PathVariable Long id) {
        Anexo14Response anexo14 = anexo14Service.listAnexo14ById(id);
        return new ResponseEntity<>(anexo14, HttpStatus.OK);
    }

    @GetMapping("/allByProyecto/{proyectoId}")
    public ResponseEntity<List<Anexo14Response>> listAnexo12ByPPP(@PathVariable Long proyectoId) {
        List<Anexo14Response> anexos = anexo14Service.listAnexo14ByProyectoPPP(proyectoId);
        return new ResponseEntity<List<Anexo14Response>>(anexos, HttpStatus.OK);
    }

    @PutMapping("/{id}/tutoremp")
    public ResponseEntity<?> updateApoyo(@PathVariable Long id, @RequestBody List<Anexo14TutorAcaRequest> request) {
        anexo14Service.updateTutorAca(id, request);
        return new ResponseEntity<>(new Mensaje("Anexo 14 tutor a actualizado"), HttpStatus.OK);
    }

}
