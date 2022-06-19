package com.tecazuay.complexivog2c2.controller;

import com.tecazuay.complexivog2c2.dto.anexos.TutorEmpRequest;
import com.tecazuay.complexivog2c2.dto.anexos.TutorEmpResponse;
import com.tecazuay.complexivog2c2.dto.carreraAlumano.CarreraAlumnoResponse;
import com.tecazuay.complexivog2c2.dto.empresa.EmpresaRequest;
import com.tecazuay.complexivog2c2.dto.empresa.EmpresaResponse;
import com.tecazuay.complexivog2c2.dto.usuarios.RegisterRequest;
import com.tecazuay.complexivog2c2.dto.usuarios.UserRequest;
import com.tecazuay.complexivog2c2.dto.usuarios.UserResponse;
import com.tecazuay.complexivog2c2.exception.Mensaje;
import com.tecazuay.complexivog2c2.service.usuario.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins= {"http://localhost:4200"})
@RestController
@RequestMapping("/api/auth")
public class UsuarioRestController {

    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<UserResponse> login(@RequestBody UserRequest userRequest) throws Exception {
        UserResponse userResponse = authService.login(userRequest);
        if (userResponse == null){
            return new ResponseEntity(new Mensaje("No existe"), HttpStatus.NOT_FOUND);
        }else {
            return new ResponseEntity<>(userResponse, HttpStatus.OK);
        }
    }
    @PostMapping("/login2")
    public ResponseEntity<EmpresaResponse> login2(@RequestBody EmpresaRequest empRequest) throws Exception {
        EmpresaResponse empResponse = authService.login2(empRequest);
        if (empResponse == null){
            return new ResponseEntity(new Mensaje("No existe"), HttpStatus.NOT_FOUND);
        }else {
            return new ResponseEntity<EmpresaResponse>(empResponse, HttpStatus.OK);
        }
    }
    @PostMapping("/logintutor")
    public ResponseEntity<TutorEmpResponse> login3(@RequestBody TutorEmpRequest tutRequest) throws Exception {
        System.out.println("Entra en Login 3");
        TutorEmpResponse tutorResponse = authService.login3(tutRequest);
        if (tutorResponse == null){
            return new ResponseEntity(new Mensaje("No existe"), HttpStatus.NOT_FOUND);
        }else {
            return new ResponseEntity<TutorEmpResponse>(tutorResponse, HttpStatus.OK);
        }
    }
    @PostMapping("/signup")
    public ResponseEntity<UserResponse> signup(@RequestBody RegisterRequest userRequest) throws Exception {
        UserResponse userResponse = authService.signup(userRequest);

        if (userResponse == null){
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(userResponse, HttpStatus.CREATED);
    }

    @GetMapping("/user/{email}")
    public ResponseEntity<UserResponse> getUser(@PathVariable String email){
        UserResponse userResponse=authService.getUser(email);
        if (userResponse == null){
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(userResponse, HttpStatus.OK);
    }

    @GetMapping("/{cedula}")
    public ResponseEntity<CarreraAlumnoResponse> getAlumno (@PathVariable String cedula){
        CarreraAlumnoResponse carrera = authService.enviarAlumno(cedula);
        if(carrera == null){
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(carrera,HttpStatus.OK);
    }

    @GetMapping("/usuario/{cedula}")
    public ResponseEntity<?> getUsuarioByCedula(@PathVariable String cedula) {
        return new ResponseEntity<>(authService.userByCedula(cedula), HttpStatus.OK);
    }

//////////////////////////////////////////////Servicios sin TOken//////////////////////////////////
    @GetMapping("/estudiante/user/{email}")
    public ResponseEntity<UserResponse> getUser2(@PathVariable String email){
        UserResponse userResponse=authService.getUser(email);
        if (userResponse == null){
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(userResponse, HttpStatus.OK);
    }
    @GetMapping("/obtenercarrera/{cedula}")
    public ResponseEntity<CarreraAlumnoResponse> getAlumnocarrera (@PathVariable String cedula){
        CarreraAlumnoResponse carrera = authService.enviarAlumno(cedula);
        if(carrera == null){
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(carrera,HttpStatus.OK);
    }
    //////////////////////////////////////////////////////////////////////////

}
