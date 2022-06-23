package com.tecazuay.complexivog2c2.controller.Anexos.anexo121;

import com.tecazuay.complexivog2c2.dto.anexos.anexo121.Anexo121certificadoRequest;
import com.tecazuay.complexivog2c2.dto.anexos.anexo121.Anexo121certificadoResponse;
import com.tecazuay.complexivog2c2.exception.Mensaje;
import com.tecazuay.complexivog2c2.service.Anexos.anexo121.Anexo121Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins= {"http://localhost:4200"})
@RestController
@RequestMapping("/api/anexo121")
public class Anexo121Controller {

    @Autowired
    private Anexo121Service anexo121Service;

    @PostMapping
    public ResponseEntity<?> create(@RequestBody Anexo121certificadoRequest anexo121Request) {
        anexo121Service.save(anexo121Request);
        return new ResponseEntity(new Mensaje("Anexo 12.1 Creado"), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<?> update(@RequestBody Anexo121certificadoRequest anexo121Request) {
        anexo121Service.update(anexo121Request);
        return new ResponseEntity(new Mensaje("Anexo 12.1 Actualizado"), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteAnexo121(@PathVariable Long id) {
        anexo121Service.deleteAnexo121ById(id);
        return new ResponseEntity<>(new Mensaje("Anexo 12.1 eliminado"), HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Anexo121certificadoResponse>> listAll() {
        List<Anexo121certificadoResponse> anexos = anexo121Service.listAll();
        return new ResponseEntity<>(anexos, HttpStatus.OK);
    }

//    @GetMapping("/{id}")
//    public ResponseEntity<Anexo121certificadoResponse> listAnexo121ById(@PathVariable Long id) {
//        Anexo121certificadoResponse anexo12 = anexo121Service.l(id);
//        return new ResponseEntity<>(anexo12, HttpStatus.OK);
//    }

    @GetMapping("/allByProyecto/{proyectoId}")
    public ResponseEntity<List<Anexo121certificadoResponse>> listAnexo121ByPPP(@PathVariable Long proyectoId) {
        List<Anexo121certificadoResponse> anexos = anexo121Service.listAnexo12ByProyectoPPP(proyectoId);
        return new ResponseEntity<List<Anexo121certificadoResponse>>(anexos, HttpStatus.OK);
    }


}
