package com.tecazuay.complexivog2c2.repository.Primary.Anexos;


import com.tecazuay.complexivog2c2.model.Primary.Anexos.Anexo4;
import com.tecazuay.complexivog2c2.model.Primary.solicitudproyecto.ProyectoPPP;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface Anexo4Repository extends JpaRepository<Anexo4,Long > {

    Optional<Anexo4>findByProyectoPPP(ProyectoPPP proyectoPPP);

    Boolean existsByProyectoPPP(ProyectoPPP proyectoPPP);

}
