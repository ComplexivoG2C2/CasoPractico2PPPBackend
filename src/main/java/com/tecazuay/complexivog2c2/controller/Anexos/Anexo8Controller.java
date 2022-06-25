package com.tecazuay.complexivog2c2.controller.Anexos;

import com.tecazuay.complexivog2c2.dto.anexos.Anexo8Request;
import com.tecazuay.complexivog2c2.dto.anexos.Anexo8Response;
import com.tecazuay.complexivog2c2.exception.Mensaje;
import com.tecazuay.complexivog2c2.service.Anexos.Anexo8Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@CrossOrigin(origins= {"http://localhost:4200"})
@RestController
@RequestMapping("/api/anexo8")
public class Anexo8Controller  {
    @Autowired
    private Anexo8Service anexo8Service;

    @PostMapping
    public ResponseEntity<?> create(@RequestBody Anexo8Request anexo8Request){
        anexo8Service.save(anexo8Request);
        return new ResponseEntity(new Mensaje("Anexo 8 Creado"), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<?> update(@RequestBody Anexo8Request anexo8Request){
        anexo8Service.update(anexo8Request);
        return new ResponseEntity(new Mensaje("Anexo 8 Actualizado"), HttpStatus.CREATED);
    }

    @GetMapping("/allByAnexo8/{idProyecto}")
    public ResponseEntity<List<Anexo8Response>>listAnexo8(@PathVariable Long idProyecto){
        List<Anexo8Response> anexos=anexo8Service.listAnexo8(idProyecto);
        return new ResponseEntity<List<Anexo8Response>>(anexos,HttpStatus.OK);
    }

    @GetMapping("/allByCedulaAnexo8/{cedula}")
    public ResponseEntity<List<Anexo8Response>>listAnexosCedula(@PathVariable String cedula){
        List<Anexo8Response> anexos=anexo8Service.listAnexo8Cedula(cedula);
        return new ResponseEntity<List<Anexo8Response>>(anexos,HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteAnexo8(@PathVariable Long id){
        anexo8Service.deleteById(id);
        return  new ResponseEntity<>(new Mensaje("Anexo 8 eliminado"),HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<?> getAll() {
        return new ResponseEntity<>(anexo8Service.getAll(), HttpStatus.OK);
    }
}
