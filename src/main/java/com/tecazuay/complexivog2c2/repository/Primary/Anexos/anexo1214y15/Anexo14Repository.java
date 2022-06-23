package com.tecazuay.complexivog2c2.repository.Primary.Anexos.anexo1214y15;

import com.tecazuay.complexivog2c2.model.Primary.Anexos.anexo1214y15.Anexo14;
import com.tecazuay.complexivog2c2.model.Primary.solicitudproyecto.ProyectoPPP;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface Anexo14Repository extends JpaRepository<Anexo14,Long> {
    List<Anexo14> findByProyectoPPP(ProyectoPPP proyectoPPP);
    List<Anexo14> findAllById(ProyectoPPP proyectoPPP);

}
