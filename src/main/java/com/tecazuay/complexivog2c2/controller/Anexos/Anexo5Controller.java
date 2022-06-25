package com.tecazuay.complexivog2c2.controller.Anexos;

import com.tecazuay.complexivog2c2.dto.anexos.Anexo5Request;
import com.tecazuay.complexivog2c2.dto.anexos.Anexo5Response;
import com.tecazuay.complexivog2c2.dto.anexos.Anexo6Response;
import com.tecazuay.complexivog2c2.dto.anexos.anexo1214y15.Anexo15Response;
import com.tecazuay.complexivog2c2.exception.Mensaje;
import com.tecazuay.complexivog2c2.service.Anexos.Anexo5Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/api/anexo5")
public class Anexo5Controller {

    @Autowired
    private Anexo5Service anexo5Service;

    @PostMapping
    public ResponseEntity<?> create(@RequestBody Anexo5Request request){
        anexo5Service.save(request);
        return new ResponseEntity(new Mensaje("Anexo 5 Creado"), HttpStatus.CREATED);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Anexo5Response>> listAll(){
        List<Anexo5Response> anexo = anexo5Service.listAll();
        return new ResponseEntity<List<Anexo5Response>>(anexo,HttpStatus.OK);
    }

    @GetMapping("/allByProyecto/{proyectoId}")
    public ResponseEntity<List<Anexo5Response>> listAnexosPro(@PathVariable Long proyectoId) {
        List<Anexo5Response> anexos = anexo5Service.anexoIdProyecto(proyectoId);
        return new ResponseEntity<List<Anexo5Response>>(anexos, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteAnexo5(@PathVariable Long id){
        anexo5Service.deleteById(id);
        return new ResponseEntity<>(new Mensaje("Anexo 5 eliminado"), HttpStatus.OK);
    }


    ///
    @GetMapping("/pornombre/{nombreTutor}")
    public ResponseEntity<List<Anexo5Response>> findAllBynombre(@PathVariable String nombreTutor) {
        List<Anexo5Response> anexos = anexo5Service.findAllByNombre(nombreTutor);
        return new ResponseEntity<List<Anexo5Response>>(anexos, HttpStatus.OK);
    }

    @GetMapping("/poridempresa/{idEmpresa}")
    public ResponseEntity<List<Anexo5Response>> findAllByidempresa(@PathVariable Long idEmpresa) {
        List<Anexo5Response> anexos = anexo5Service.findAllByidempresa(idEmpresa);
        return new ResponseEntity<List<Anexo5Response>>(anexos, HttpStatus.OK);
    }
}
