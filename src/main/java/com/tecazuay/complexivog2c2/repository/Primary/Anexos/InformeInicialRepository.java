package com.tecazuay.complexivog2c2.repository.Primary.Anexos;


import com.tecazuay.complexivog2c2.model.Primary.Anexos.InformeInicial;
import com.tecazuay.complexivog2c2.model.Primary.solicitudproyecto.ProyectoPPP;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InformeInicialRepository extends JpaRepository<InformeInicial,Long> {
    List<InformeInicial> findAllByProyectoPPP(ProyectoPPP proyectoPPP);
}
