package com.tecazuay.complexivog2c2.controller.Anexos;

import com.tecazuay.complexivog2c2.dto.anexos.Anexo2Codigo;
import com.tecazuay.complexivog2c2.dto.anexos.Anexo2Request;
import com.tecazuay.complexivog2c2.dto.anexos.Anexo2Response;
import com.tecazuay.complexivog2c2.exception.Mensaje;
import com.tecazuay.complexivog2c2.service.Anexos.Anexo2Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/api/anexo2")
public class Anexo2Controller {

    @Autowired
    private Anexo2Service anexo2Service;

    @PostMapping
    public ResponseEntity<?> create(@RequestBody Anexo2Request request) {
        anexo2Service.save(request);
        return new ResponseEntity(new Mensaje("Anexo 2 Creado"), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<?> update(@RequestBody Anexo2Request request) {
        anexo2Service.update(request);
        return new ResponseEntity(new Mensaje("Anexo 2 Actualiazado"), HttpStatus.CREATED);
    }

    @GetMapping("/allByProyecto/{proyectoId}")
    public ResponseEntity<Anexo2Response> listAnexosPro(@PathVariable Long proyectoId) {
        Anexo2Response anexos = anexo2Service.anexoIdProyecto(proyectoId);
        return new ResponseEntity<>(anexos, HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Anexo2Response>> listAll() {
        List<Anexo2Response> anexos = anexo2Service.listAll();
        return new ResponseEntity<List<Anexo2Response>>(anexos, HttpStatus.OK);
    }

    @GetMapping("listarconvocatorias/all")
    public ResponseEntity<List<Anexo2Response>> listAllc() {
        List<Anexo2Response> anexos = anexo2Service.listAll();
        return new ResponseEntity<List<Anexo2Response>>(anexos, HttpStatus.OK);
    }

    @GetMapping("/codigo/{codeca}")
    public ResponseEntity<Anexo2Codigo> ane(@PathVariable String codeca){
        Anexo2Codigo anexo= new Anexo2Codigo();

        anexo.setCodigo(LocalDate.now().getYear() + " - " +anexo2Service.generateCode(codeca)+"");
        return new ResponseEntity<>(anexo,HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteAnexo2(@PathVariable Long id){
        anexo2Service.deleteAnexo2ById(id);
        return  new ResponseEntity<>(new Mensaje("Anexo 2  eliminado"),HttpStatus.OK);

    }


}
