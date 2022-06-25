package com.tecazuay.complexivog2c2.repository.Primary.Anexos.anexo1214y15;


import com.tecazuay.complexivog2c2.model.Primary.Anexos.anexo1214y15.Anexo12;
import com.tecazuay.complexivog2c2.model.Primary.solicitudproyecto.ProyectoPPP;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface Anexo12Repository extends JpaRepository<Anexo12,Long> {
    List<Anexo12> findByProyectoPPP(ProyectoPPP proyectoPPP);
    List<Anexo12> findAllById(ProyectoPPP proyectoPPP);
    Optional<Anexo12> findByCedulaEstudiante(String cedula);
}
