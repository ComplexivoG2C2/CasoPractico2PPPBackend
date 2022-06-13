package com.tecazuay.complexivog2c2.controller;


import com.tecazuay.complexivog2c2.dto.solicitudproyectos.ActividadeslistProyecto;
import com.tecazuay.complexivog2c2.dto.solicitudproyectos.ProyectoResponse;
import com.tecazuay.complexivog2c2.dto.solicitudproyectos.ProyectoRequest;
import com.tecazuay.complexivog2c2.dto.solicitudproyectos.RequisitoslistProyecto;
import com.tecazuay.complexivog2c2.exception.Mensaje;
import com.tecazuay.complexivog2c2.service.solicitudproyecto.ProyectosService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/api/solicitudproyectos")
@RequiredArgsConstructor
public class ProyectosRestController {

    private final ProyectosService proyectosService;

    @GetMapping("/all")
    public ResponseEntity<List<ProyectoResponse>> listProyectos() {
        List<ProyectoResponse> proyectoResponses = proyectosService.listaProyectos();
        return new ResponseEntity<>(proyectoResponses, HttpStatus.OK);
    }

    @PostMapping("/save")
    public ResponseEntity<?> responseEntity(@RequestBody ProyectoRequest proyectoRequest) {
        proyectosService.saveProyectos(proyectoRequest);
        return new ResponseEntity<>(new Mensaje("PROYECTO GUARDADO"), HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<?> update(@RequestBody ProyectoRequest proyectoRequest) {
        proyectosService.updateProyecto2MS(proyectoRequest);
        return new ResponseEntity<>(new Mensaje("ACTUALIZADO"), HttpStatus.CREATED);
    }
    @PutMapping("/agregartutoresacademicos")
    public ResponseEntity<?> updatetutoresacademicos(@RequestBody ProyectoRequest proyectoRequest) {
        proyectosService.updatetutoresacademicos(proyectoRequest);
        return new ResponseEntity<>(new Mensaje("ACTUALIZADO"), HttpStatus.CREATED);
    }
    @GetMapping("/{id}")
    public ResponseEntity<ProyectoResponse> listProyectosById(@PathVariable Long id) {
        ProyectoResponse proyectoResponses = proyectosService.listaProyectoById(id);
        return new ResponseEntity<>(proyectoResponses, HttpStatus.OK);
    }

    @GetMapping("/cedula/tutoracademico/{cedula}")
    public ResponseEntity<?> listProyectosByApoyo(@PathVariable String cedula) {
        ProyectoResponse proyectoResponses = proyectosService.getProyectoCIApoyo(cedula);
        return new ResponseEntity<>(proyectoResponses, HttpStatus.OK);
    }

    @DeleteMapping("/tutoracademico/{id}")
    public ResponseEntity<?> deleteDApoyo(@PathVariable Long id) {
        proyectosService.deleteDocenteApoyo(id);
        return new ResponseEntity<>(new Mensaje("Docente de tutor eliminado"), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProyecto(@PathVariable Long id) {
        proyectosService.deleteProyecoById(id);
        return new ResponseEntity<>(new Mensaje("Proyecto eliminado"), HttpStatus.OK);
    }

    @PutMapping("/{id}/requisitos")
    public ResponseEntity<?> updateRequerimientos(@PathVariable Long id, @RequestBody List<RequisitoslistProyecto> requisitos) {
        proyectosService.updateRequisitos(id, requisitos);
        return new ResponseEntity<>(new Mensaje("Requisitos actualizados"), HttpStatus.OK);
    }

    @PutMapping("/{id}/actividades")
    public ResponseEntity<?> updateActividades(@PathVariable Long id, @RequestBody List<ActividadeslistProyecto> actividades) {
        proyectosService.updateActividades(id, actividades);
        return new ResponseEntity<>(new Mensaje("Actividades actualizados"), HttpStatus.OK);
    }

    @GetMapping("/tutoremp/{cedula}")
    public ResponseEntity<?> byDirectorCedula(@PathVariable String cedula) {
        return new ResponseEntity<>(proyectosService.allByTutorempCedula(cedula), HttpStatus.OK);
    }

    @GetMapping("/tutoracademico/{cedula}")
    public ResponseEntity<?> byCedulaApoyo(@PathVariable String cedula) {
        return new ResponseEntity<>(proyectosService.allByApoyoCedula(cedula), HttpStatus.OK);
    }


}
