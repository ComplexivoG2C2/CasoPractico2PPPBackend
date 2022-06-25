package com.tecazuay.complexivog2c2.repository.Primary.Anexos.anexo1214y15;

import com.tecazuay.complexivog2c2.model.Primary.Anexos.anexo1214y15.Anexo15;
import com.tecazuay.complexivog2c2.model.Primary.solicitudproyecto.ProyectoPPP;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface Anexo15Repository extends JpaRepository<Anexo15,Long> {
    Optional<Anexo15> findByProyectoPPP(ProyectoPPP proyectoPPP);

    Boolean existsByProyectoPPP(ProyectoPPP proyectoPPP);

    List<Anexo15> findAllByProyectoPPP(ProyectoPPP proyectoPPP);

}
