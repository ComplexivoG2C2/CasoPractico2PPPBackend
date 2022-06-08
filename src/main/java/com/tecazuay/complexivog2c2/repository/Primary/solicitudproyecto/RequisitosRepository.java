package com.tecazuay.complexivog2c2.repository.Primary.solicitudproyecto;

import com.tecazuay.complexivog2c2.model.Primary.solicitudproyecto.ProyectoPPP;
import com.tecazuay.complexivog2c2.model.Primary.solicitudproyecto.RequisitosProyecto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface RequisitosRepository extends JpaRepository<RequisitosProyecto,Long> {

    @Override
    Optional<RequisitosProyecto> findById(Long id);

    List<RequisitosProyecto> findAllByProyectoPPP(ProyectoPPP proyectoPPP);

}
