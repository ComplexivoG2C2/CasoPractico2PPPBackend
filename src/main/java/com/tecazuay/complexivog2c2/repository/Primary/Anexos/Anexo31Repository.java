package com.tecazuay.complexivog2c2.repository.Primary.Anexos;

import com.tecazuay.complexivog2c2.model.Primary.Anexos.Anexo31;
import com.tecazuay.complexivog2c2.model.Primary.Anexos.Anexo4;
import com.tecazuay.complexivog2c2.model.Primary.solicitudproyecto.ProyectoPPP;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface Anexo31Repository extends JpaRepository<Anexo31,Long > {
    Optional<Anexo4> findAllByProyectoPPP(ProyectoPPP proyectoPPP);

    Boolean existsByProyectoPPP(ProyectoPPP proyectoPPP);
}

