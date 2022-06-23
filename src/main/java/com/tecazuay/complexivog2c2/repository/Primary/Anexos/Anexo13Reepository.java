package com.tecazuay.complexivog2c2.repository.Primary.Anexos;

import com.tecazuay.complexivog2c2.model.Primary.Anexos.Anexo13;
import com.tecazuay.complexivog2c2.model.Primary.solicitudproyecto.ProyectoPPP;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface Anexo13Reepository extends JpaRepository<Anexo13, Long> {

    List<Anexo13> findAnexo13ByProyectoPPP(ProyectoPPP proyectoPPP);

    Boolean existsByProyectoPPP(ProyectoPPP proyectoPPP);
}
