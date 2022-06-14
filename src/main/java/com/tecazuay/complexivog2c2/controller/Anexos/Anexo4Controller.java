package com.tecazuay.complexivog2c2.controller.Anexos;

import com.tecazuay.complexivog2c2.dto.anexos.Anexo2Response;
import com.tecazuay.complexivog2c2.dto.anexos.Anexo4Request;
import com.tecazuay.complexivog2c2.dto.anexos.Anexo4Response;
import com.tecazuay.complexivog2c2.exception.Mensaje;
import com.tecazuay.complexivog2c2.service.Anexos.Anexo4Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/api/anexo4")
public class Anexo4Controller {

    @Autowired
    private Anexo4Service anexo4Service;

    @PostMapping
    public ResponseEntity<?> create(@RequestBody Anexo4Request request) {
        anexo4Service.save(request);
        return new ResponseEntity(new Mensaje("Anexo 2 Creado"), HttpStatus.CREATED);
    }

    @GetMapping("/allByProyecto/{proyectoId}")
    public ResponseEntity<Anexo4Response> listAnexosPro(@PathVariable Long proyectoId) {
        Anexo4Response anexos = anexo4Service.anexoIdProyecto(proyectoId);
        return new ResponseEntity<>(anexos, HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Anexo4Response>> listAll() {
        List<Anexo4Response> anexos = anexo4Service.listAll();
        return new ResponseEntity<List<Anexo4Response>>(anexos, HttpStatus.OK);
    }
}
