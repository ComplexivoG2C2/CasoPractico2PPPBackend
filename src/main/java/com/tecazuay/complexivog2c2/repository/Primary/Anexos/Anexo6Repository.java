package com.tecazuay.complexivog2c2.repository.Primary.Anexos;

import com.tecazuay.complexivog2c2.model.Primary.Anexos.Anexo6;
import com.tecazuay.complexivog2c2.model.Primary.solicitudproyecto.ProyectoPPP;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface Anexo6Repository extends JpaRepository<Anexo6,Long > {
    List<Anexo6> findByProyectoPPP(ProyectoPPP proyectoPPP);
    Boolean existsByProyectoPPP(ProyectoPPP proyectoPPP);
    List<Anexo6> findAllByCedulaDocenteApoyo(String cedula);
    Optional<Anexo6> findByProyectoPPPAndCedulaDocenteApoyo(ProyectoPPP proyectoPPP, String cedula);

}
