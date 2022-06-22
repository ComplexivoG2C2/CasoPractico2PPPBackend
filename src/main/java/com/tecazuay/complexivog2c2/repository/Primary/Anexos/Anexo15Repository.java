package com.tecazuay.complexivog2c2.repository.Primary.Anexos;
import com.tecazuay.complexivog2c2.model.Primary.Anexos.Anexo15;
import com.tecazuay.complexivog2c2.model.Primary.proyecto.ProyectoPPP;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface Anexo15Repository extends JpaRepository <Anexo15, Long> {
    Optional<Anexo15> findByProyectoPPP(ProyectoPPP proyectoPPP);
}
