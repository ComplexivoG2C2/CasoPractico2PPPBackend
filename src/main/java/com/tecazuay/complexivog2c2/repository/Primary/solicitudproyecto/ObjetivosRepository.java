package com.tecazuay.complexivog2c2.repository.Primary.solicitudproyecto;

import com.tecazuay.complexivog2c2.model.Primary.solicitudproyecto.ActividadesempresaProyecto;
import com.tecazuay.complexivog2c2.model.Primary.solicitudproyecto.ProyectoPPP;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ObjetivosRepository extends JpaRepository<ActividadesempresaProyecto,Long> {

    Optional<ActividadesempresaProyecto> findById(Long id);

    List<ActividadesempresaProyecto> findAllByProyectoPPP(ProyectoPPP proyectoPPP);
}
