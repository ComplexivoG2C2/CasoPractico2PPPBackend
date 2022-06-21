package com.tecazuay.complexivog2c2.repository.Primary.Anexos;


import com.tecazuay.complexivog2c2.model.Primary.Anexos.Anexo6;
import com.tecazuay.complexivog2c2.model.Primary.Anexos.Anexo81;
import com.tecazuay.complexivog2c2.model.Primary.solicitudproyecto.ProyectoPPP;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface Anexo81Repository extends JpaRepository<Anexo81,Long > {
    Optional<Anexo81> findByProyectoPPP(ProyectoPPP proyectoPPP);

    Boolean existsByProyectoPPP(ProyectoPPP proyectoPPP);

    List<Anexo81> findAllByProyectoPPP(ProyectoPPP proyectoPPP);
    List<Anexo81> findAllByCedulaTutoracademico(String cedula);
}
