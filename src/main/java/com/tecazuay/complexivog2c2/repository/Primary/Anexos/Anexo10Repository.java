package com.tecazuay.complexivog2c2.repository.Primary.Anexos;

import com.tecazuay.complexivog2c2.model.Primary.Anexos.Anexo10;
import com.tecazuay.complexivog2c2.model.Primary.solicitudproyecto.ProyectoPPP;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface Anexo10Repository extends JpaRepository<Anexo10,Long > {
    Optional<Anexo10> findByProyectoPPP(ProyectoPPP proyectoPPP);

    Boolean existsByProyectoPPP(ProyectoPPP proyectoPPP);

    List<Anexo10> findAllByProyectoPPP(ProyectoPPP proyectoPPP);
}
