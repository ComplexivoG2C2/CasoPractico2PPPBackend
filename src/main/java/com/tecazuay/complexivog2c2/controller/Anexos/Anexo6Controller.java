package com.tecazuay.complexivog2c2.controller.Anexos;

import com.tecazuay.complexivog2c2.dto.anexos.Anexo6Request;
import com.tecazuay.complexivog2c2.dto.anexos.Anexo6Response;
import com.tecazuay.complexivog2c2.dto.anexos.TutorAcademicoAnexo6Response;
import com.tecazuay.complexivog2c2.exception.Mensaje;
import com.tecazuay.complexivog2c2.service.Anexos.Anexo6Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/api/anexo6")
public class Anexo6Controller {

    @Autowired
    private Anexo6Service anexo6Service;

    @PostMapping
    public ResponseEntity<?> create(@RequestBody Anexo6Request request) {
        anexo6Service.save(request);
        return new ResponseEntity(new Mensaje("Anexo 6 Creado"), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<?> update(@RequestBody Anexo6Request request) {
        anexo6Service.updateMS(request);
        return new ResponseEntity(new Mensaje("Anexo 6 Actualiazado"), HttpStatus.CREATED);
    }

    @GetMapping("/allByProyecto/{proyectoId}")
    public ResponseEntity<List<Anexo6Response>> listAnexosPro(@PathVariable Long proyectoId) {
        List<Anexo6Response> anexos = anexo6Service.anexoIdProyecto(proyectoId);
        return new ResponseEntity<List<Anexo6Response>>(anexos, HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Anexo6Response>> listAll() {
        List<Anexo6Response> anexos = anexo6Service.listAll();
        return new ResponseEntity<List<Anexo6Response>>(anexos, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Anexo6Response> listanexo5ById(@PathVariable Long id) {
        Anexo6Response anexo5 = anexo6Service.listaAnexo5ById(id);
        return new ResponseEntity<>(anexo5, HttpStatus.OK);
    }

    @GetMapping("/docenteApoyo/{cedula}")
    public ResponseEntity<List<Anexo6Response>> findAllByCedula(@PathVariable String cedula) {
        List<Anexo6Response> anexos = anexo6Service.findAllByCedula(cedula);
        return new ResponseEntity<List<Anexo6Response>>(anexos, HttpStatus.OK);
    }

    @GetMapping("/estudiante/{cedula}/proyecto/{idProyecto}")
    public ResponseEntity<TutorAcademicoAnexo6Response> findAllByCedula(@PathVariable String cedula, @PathVariable Long idProyecto) {
        return new ResponseEntity<>(anexo6Service.docenteApoyoByEstudiante(cedula, idProyecto), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteAnexo5(@PathVariable Long id){
        anexo6Service.deleteById(id);
        return  new ResponseEntity<>(new Mensaje("Anexo 6 eliminado"),HttpStatus.OK);

    }
}
