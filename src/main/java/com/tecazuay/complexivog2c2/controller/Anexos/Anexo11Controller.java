package com.tecazuay.complexivog2c2.controller.Anexos;

import com.tecazuay.complexivog2c2.dto.anexos.Anexo10Request;
import com.tecazuay.complexivog2c2.dto.anexos.Anexo10Response;
import com.tecazuay.complexivog2c2.dto.anexos.Anexo11Request;
import com.tecazuay.complexivog2c2.dto.anexos.Anexo11Response;
import com.tecazuay.complexivog2c2.exception.Mensaje;
import com.tecazuay.complexivog2c2.service.Anexos.Anexo11Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/anexo11")
public class Anexo11Controller {

    @Autowired
    private Anexo11Service anexo11Service;

//    @PostMapping
//    public ResponseEntity<?> save(@RequestBody Anexo11Request request) {
//        anexo11Service.save(request);
//        return new ResponseEntity<>(new Mensaje("Anexo 11 guardado"), HttpStatus.CREATED);
//    }
@PostMapping
public ResponseEntity<?> create(@RequestBody Anexo11Request request) {
    anexo11Service.save(request);
    return new ResponseEntity(new Mensaje("Anexo 11 Creado"), HttpStatus.CREATED);
}


    @PostMapping("/agregarVisita")
    public ResponseEntity<?> agregarVisita(@RequestBody Anexo11Request request) {
        anexo11Service.updateSave(request);
        return new ResponseEntity<>(new Mensaje("Anexo 11 guardado con nueva visita"), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<?> update(@RequestBody Anexo11Request request) {
        anexo11Service.update(request);
        return new ResponseEntity<>(new Mensaje("Anexo 13 editado"), HttpStatus.CREATED);
    }

    @GetMapping("/proyecto/{id}")
    public ResponseEntity<?> allByProyecto(@PathVariable Long id) {
        return new ResponseEntity<>(anexo11Service.listByProyecto(id), HttpStatus.OK);
    }

    @GetMapping("/estudiantes/{id}")
    public ResponseEntity<?> allEstudiantesByProyecto(@PathVariable Long id) {
        return new ResponseEntity<>(anexo11Service.listEstudiantesByIdPro(id), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteAnexo11(@PathVariable Long id){
        anexo11Service.deleteById(id);
        return  new ResponseEntity<>(new Mensaje("Anexo 11  eliminado"),HttpStatus.OK);

    }
    @GetMapping("/all")
    public ResponseEntity<List<Anexo11Response>> listAll() {
        List<Anexo11Response> anexos = anexo11Service.listAll();
        return new ResponseEntity<List<Anexo11Response>>(anexos, HttpStatus.OK);
    }
}
