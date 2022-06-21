package com.tecazuay.complexivog2c2.controller.Anexos;
import com.tecazuay.complexivog2c2.dto.anexos.Anexo81Request;
import com.tecazuay.complexivog2c2.dto.anexos.Anexo81Response;
import com.tecazuay.complexivog2c2.exception.Mensaje;
import com.tecazuay.complexivog2c2.service.Anexos.Anexo81Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/api/anexo81")
public class Anexo81Controller {

    @Autowired
    private Anexo81Service anexo81Service;

    @PostMapping
    public ResponseEntity<?> create(@RequestBody Anexo81Request request) {
        anexo81Service.save(request);
        return new ResponseEntity(new Mensaje("Anexo 8.1 Creado"), HttpStatus.CREATED);
    }

    @GetMapping("/allByProyecto/{proyectoId}")
    public ResponseEntity<Anexo81Response> listAnexosPro81(@PathVariable Long proyectoId) {
        Anexo81Response anexos = anexo81Service.anexoIdProyecto(proyectoId);
        return new ResponseEntity<>(anexos, HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Anexo81Response>> listAll() {
        List<Anexo81Response> anexos = anexo81Service.listAll();
        return new ResponseEntity<List<Anexo81Response>>(anexos, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteAnexo81(@PathVariable Long id) {
        anexo81Service.deleteAnexo81ById(id);
        return new ResponseEntity<>(new Mensaje("Anexo 8.1  eliminado"), HttpStatus.OK);

    }
    @GetMapping("/tutoracademico/{cedula}")
    public ResponseEntity<List<Anexo81Response>> findAllByCedula(@PathVariable String cedula) {
        List<Anexo81Response> anexos = anexo81Service.findAllByCedula(cedula);
        return new ResponseEntity<List<Anexo81Response>>(anexos, HttpStatus.OK);
    }
}