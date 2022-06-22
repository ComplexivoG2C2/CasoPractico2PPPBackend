package com.tecazuay.complexivog2c2.repository.Primary.Anexos;

import com.tecazuay.complexivog2c2.model.Primary.Anexos.Anexo14;
import com.tecazuay.complexivog2c2.model.Primary.proyecto.ProyectoPPP;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface Anexo14Repository extends JpaRepository <Anexo14, Long> {

    Optional<Anexo14> findByProyectoPPP(ProyectoPPP proyectoPPP);
}
