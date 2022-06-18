package com.tecazuay.complexivog2c2.repository.Primary.solicitudproyecto;

import com.tecazuay.complexivog2c2.model.Primary.solicitudproyecto.ProyectoPPP;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProyectoRepository extends JpaRepository<ProyectoPPP, Long> {


    Optional<ProyectoPPP> findByCodigo(String codigo);

    Optional<ProyectoPPP> findByIdAndEstado(Long id, boolean estado);
}
