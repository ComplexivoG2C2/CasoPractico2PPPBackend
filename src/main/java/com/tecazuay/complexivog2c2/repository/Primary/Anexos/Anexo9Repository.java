package com.tecazuay.complexivog2c2.repository.Primary.Anexos;

import com.tecazuay.complexivog2c2.model.Primary.Anexos.Anexo9;
import com.tecazuay.complexivog2c2.model.Primary.solicitudproyecto.ProyectoPPP;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface Anexo9Repository  extends JpaRepository<Anexo9,Long> {
    Optional<Anexo9> findByProyectoPPP(ProyectoPPP proyectoPPP);
    Boolean existsByProyectoPPP(ProyectoPPP proyectoPPP);
    List<Anexo9> findAllByCedulaEstudiante(String cedula);

    List<Anexo9> findAllByProyectoPPP(ProyectoPPP proyectoPPP);

    boolean existsByProyectoPPPAndCedulaEstudiante(ProyectoPPP proyectoPPP, String cedulaEstudiante);
}
