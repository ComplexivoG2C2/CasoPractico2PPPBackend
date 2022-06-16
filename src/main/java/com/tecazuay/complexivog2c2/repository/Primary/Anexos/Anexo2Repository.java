package com.tecazuay.complexivog2c2.repository.Primary.Anexos;

import com.tecazuay.complexivog2c2.model.Primary.Anexos.Anexo2;
import com.tecazuay.complexivog2c2.model.Primary.solicitudproyecto.ProyectoPPP;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface Anexo2Repository extends JpaRepository<Anexo2,Long > {
    Optional<Anexo2>findByProyectoPPP(ProyectoPPP proyectoPPP);
    Boolean existsByProyectoPPP(ProyectoPPP proyectoPPP);
    Optional<Anexo2>findFirstBySiglasCarreraOrderByFechaDesc(String siglas);

}
