package com.tecazuay.complexivog2c2.repository.Primary.solicitudproyecto;

import com.tecazuay.complexivog2c2.model.Primary.solicitudproyecto.ActividadesProyecto;
import com.tecazuay.complexivog2c2.model.Primary.solicitudproyecto.ProyectoPPP;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ActividadesRepository extends JpaRepository<ActividadesProyecto,Long> {

    @Override
    List<ActividadesProyecto> findAll();

    List<ActividadesProyecto> findAllByProyectoPPP(ProyectoPPP proyectoPPP);

}
