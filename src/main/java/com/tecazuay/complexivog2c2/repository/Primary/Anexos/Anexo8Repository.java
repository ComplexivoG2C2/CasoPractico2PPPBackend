package com.tecazuay.complexivog2c2.repository.Primary.Anexos;

import com.tecazuay.complexivog2c2.model.Primary.Anexos.Anexo8;
import com.tecazuay.complexivog2c2.model.Primary.solicitudproyecto.ProyectoPPP;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface Anexo8Repository  extends JpaRepository<Anexo8, Long>{
    List <Anexo8>findAllByProyectoPPP(ProyectoPPP proyectoPPP);
List<Anexo8>findAllByCedulaEstudiante(String cedulaEstudiante);
        Optional<Anexo8> findByCedulaEstudianteAndProyectoPPP (String cedulaEstudiante,ProyectoPPP proyectoPPP);



}
