package com.tecazuay.complexivog2c2.controller.Anexos;

import com.tecazuay.complexivog2c2.dto.anexos.*;
import com.tecazuay.complexivog2c2.exception.Mensaje;
import com.tecazuay.complexivog2c2.service.Anexos.Anexo9Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/api/anexo9")
public class Anexo9Controller {

    @Autowired
    private Anexo9Service anexo9Service;

    @PostMapping
    public ResponseEntity<?> create(@RequestBody Anexo9Request request) {
        anexo9Service.save(request);
        return new ResponseEntity(new Mensaje("Anexo 9 Creado"), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<?> update(@RequestBody Anexo9Request request) {
        anexo9Service.update(request);
        return new ResponseEntity(new Mensaje("Anexo 9 Actualizado"), HttpStatus.CREATED);
    }

    @GetMapping("/allByProyecto/{proyectoId}")
    public ResponseEntity<List<Anexo9Response>> listAnexosPro(@PathVariable Long proyectoId) {
        return new ResponseEntity<>(anexo9Service.anexoIdProyecto(proyectoId), HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Anexo9Response>> listAll() {
        List<Anexo9Response> anexos = anexo9Service.listAll();
        return new ResponseEntity<List<Anexo9Response>>(anexos, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Anexo9Response> listanexo9ById(@PathVariable Long id) {
        Anexo9Response anexo9 = anexo9Service.listaAnexo9ById(id);
        return new ResponseEntity<>(anexo9, HttpStatus.OK);
    }

    @GetMapping("/alumno/{cedula}")
    public ResponseEntity<List<Anexo9Response>> allByAlumno(@PathVariable String cedula) {
        return new ResponseEntity<>(anexo9Service.allByAlumnoCedula(cedula), HttpStatus.OK);
    }

    @DeleteMapping("{idAnexo}/actividad/{idActividad}")
    public ResponseEntity<?> deleteActividad(@PathVariable Long idAnexo, @PathVariable Long idActividad) {
        anexo9Service.deleteActividad(idAnexo, idActividad);
        return new ResponseEntity<>(new Mensaje("Actividad con id: " + idActividad + ", eliminada"), HttpStatus.OK);
    }

    @PutMapping("/{idAnexo}/actividad")
    public ResponseEntity<?> updateActividad(@PathVariable Long idAnexo, @RequestBody ActividadesAnexo9Request request) {
        anexo9Service.updateActividad(idAnexo, request);
        return new ResponseEntity<>(new Mensaje("Actividad con id: " + request.getId() + ", actualizada"), HttpStatus.OK);
    }
}
