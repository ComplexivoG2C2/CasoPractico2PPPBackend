package com.tecazuay.complexivog2c2.controller.Anexos;

import com.tecazuay.complexivog2c2.dto.anexos.Anexo10Request;
import com.tecazuay.complexivog2c2.dto.anexos.Anexo10Response;
import com.tecazuay.complexivog2c2.exception.Mensaje;
import com.tecazuay.complexivog2c2.service.Anexos.Anexo10Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/api/anexo10")
public class Anexo10Controller {
    @Autowired
    private Anexo10Service anexo10Service;

    @PostMapping
    public ResponseEntity<?> create(@RequestBody Anexo10Request request) {
        anexo10Service.save(request);
        return new ResponseEntity(new Mensaje("Anexo 10 Creado"), HttpStatus.CREATED);
    }

    @GetMapping("/allByProyecto/{proyectoId}")
    public ResponseEntity<Anexo10Response> listAnexosPro(@PathVariable Long proyectoId) {
        Anexo10Response anexos = anexo10Service.anexoIdProyecto(proyectoId);
        return new ResponseEntity<>(anexos, HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Anexo10Response>> listAll() {
        List<Anexo10Response> anexos = anexo10Service.listAll();
        return new ResponseEntity<List<Anexo10Response>>(anexos, HttpStatus.OK);
    }
    @GetMapping("/allById/{anexo10id}")
    public ResponseEntity<Anexo10Response> list(@PathVariable Long anexo10id){
        Anexo10Response anexos=anexo10Service.anexo10IdAnexo10(anexo10id);
        return  new ResponseEntity<>(anexos,HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteAnexo10(@PathVariable Long id){
        anexo10Service.deleteAnexo10ById(id);
        return  new ResponseEntity<>(new Mensaje("Anexo 10  eliminado"),HttpStatus.OK);

    }
}
