package com.tecazuay.complexivog2c2.repository.Primary.Anexos;

import com.tecazuay.complexivog2c2.model.Primary.Anexos.Anexo5;
import com.tecazuay.complexivog2c2.model.Primary.solicitudproyecto.ProyectoPPP;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface Anexo5Repository extends JpaRepository<Anexo5, Long> {

    List<Anexo5> findByProyectoPPP(ProyectoPPP proyectoPPP);

    Boolean existsByProyectoPPP(ProyectoPPP proyectoPPP);

}
