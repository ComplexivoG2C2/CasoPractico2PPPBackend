package com.tecazuay.complexivog2c2.controller;

import com.tecazuay.complexivog2c2.dto.convenios.RegistroConveniosRequest;
import com.tecazuay.complexivog2c2.dto.convenios.RegistroConveniosResponse;
import com.tecazuay.complexivog2c2.exception.Mensaje;
import com.tecazuay.complexivog2c2.service.convenios.RegistroConveniosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/api/registroconvenio")
public class RegistroConveniosController {
    @Autowired
    private RegistroConveniosService registroConveniosService;

    @PostMapping
    public ResponseEntity<?> create(@RequestBody RegistroConveniosRequest request) {
        registroConveniosService.save(request);
        return new ResponseEntity(new Mensaje("Registro de Convenio Creado"), HttpStatus.CREATED);
    }

    @GetMapping("/all")
    public ResponseEntity<List<RegistroConveniosResponse>> listAll() {
        List<RegistroConveniosResponse> anexos = registroConveniosService.listAll();
        return new ResponseEntity<List<RegistroConveniosResponse>>(anexos, HttpStatus.OK);
    }

    @GetMapping("registroId/{id}")
    public ResponseEntity<RegistroConveniosResponse> listaById(@PathVariable Long id) {
        RegistroConveniosResponse an = registroConveniosService.ByidRegistro(id);
        return new ResponseEntity<>(an, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteAnexo7(@PathVariable Long id){
        registroConveniosService.deleteById(id);
        return  new ResponseEntity<>(new Mensaje("Registro de Convenio  eliminado"),HttpStatus.OK);
    }
}
