package com.tecazuay.complexivog2c2.repository.Primary.Anexos;

import com.tecazuay.complexivog2c2.model.Primary.Anexos.Anexo1;
import com.tecazuay.complexivog2c2.model.Primary.Anexos.Anexo2;
import com.tecazuay.complexivog2c2.model.Primary.Anexos.Anexo3_1;
import com.tecazuay.complexivog2c2.model.Primary.solicitudproyecto.ProyectoPPP;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface Anexo3_1Repository extends JpaRepository<Anexo3_1,Long > {
    Optional<Anexo3_1> findByProyectoPPP(ProyectoPPP proyectoPPP);
    Boolean existsByProyectoPPP(ProyectoPPP proyectoPPP);

    List<Anexo3_1> findAllByProyectoPPP(ProyectoPPP proyectoPPP);
}
