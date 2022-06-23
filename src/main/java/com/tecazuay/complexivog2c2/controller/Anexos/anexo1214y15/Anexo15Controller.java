package com.tecazuay.complexivog2c2.controller.Anexos.anexo1214y15;

import com.tecazuay.complexivog2c2.dto.anexos.anexo1214y15.Anexo15Request;
import com.tecazuay.complexivog2c2.dto.anexos.anexo1214y15.Anexo15Response;
import com.tecazuay.complexivog2c2.exception.Mensaje;
import com.tecazuay.complexivog2c2.service.Anexos.anexo1214y15.Anexo15Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins= {"http://localhost:4200"})
@RestController
@RequestMapping("/api/anexo15")
public class Anexo15Controller {

    @Autowired
    private Anexo15Service anexo15Service;

    @PostMapping
    public ResponseEntity<?> create(@RequestBody Anexo15Request anexo15Request) {
        anexo15Service.save(anexo15Request);
        return new ResponseEntity(new Mensaje("Anexo 15 Creado"), HttpStatus.CREATED);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteAnexo15(@PathVariable Long id) {
        anexo15Service.deleteAnexo15ById(id);
        return new ResponseEntity<>(new Mensaje("Anexo 15 eliminado"), HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Anexo15Response>> listAll() {
        List<Anexo15Response> anexos = anexo15Service.listAll();
        return new ResponseEntity<>(anexos, HttpStatus.OK);
    }

    @GetMapping("/allByProyecto/{proyectoId}")
    public ResponseEntity<Anexo15Response> listAnexosPro(@PathVariable Long proyectoId) {
        Anexo15Response anexos = anexo15Service.anexoIdProyecto(proyectoId);
        return new ResponseEntity<>(anexos, HttpStatus.OK);
    }

    @GetMapping("/allById/{anexo10id}")
    public ResponseEntity<Anexo15Response> list(@PathVariable Long anexo10id) {
        Anexo15Response anexos = anexo15Service.anexo15IdAnexo(anexo10id);
        return new ResponseEntity<>(anexos, HttpStatus.OK);
    }
}