package com.tecazuay.complexivog2c2.controller.Anexos;

import com.tecazuay.complexivog2c2.dto.anexos.*;
import com.tecazuay.complexivog2c2.exception.Mensaje;
import com.tecazuay.complexivog2c2.service.Anexos.InformeInicialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/api/informeInicial")
public class InformeInicialController {
    @Autowired
    private InformeInicialService informeInicialService;

    @PostMapping
    public ResponseEntity<?> create(@RequestBody InformeInicialRequest request){
        informeInicialService.save(request);
        return new ResponseEntity(new Mensaje("Informe Inicial Creado"), HttpStatus.CREATED);
    }

    @GetMapping("/all")
    public ResponseEntity<List<InformeInicialResponse>> listAll() {
        List<InformeInicialResponse> informe = informeInicialService.listAll();
        return new ResponseEntity<List<InformeInicialResponse>>(informe, HttpStatus.OK);
    }

    @GetMapping("/proyecto/{id}")
    public ResponseEntity<List<InformeInicialResponse>> allByProyecto(@PathVariable Long id) {
        return new ResponseEntity<List<InformeInicialResponse>>(informeInicialService.allByProyecto(id), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<?> update(@RequestBody InformeInicialRequest request) {
        informeInicialService.update(request);
        return new ResponseEntity(new Mensaje("Informe Inicial Actualizado"), HttpStatus.CREATED);
    }


}
