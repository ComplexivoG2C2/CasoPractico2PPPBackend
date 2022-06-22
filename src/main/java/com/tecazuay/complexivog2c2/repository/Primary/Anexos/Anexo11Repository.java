package com.tecazuay.complexivog2c2.repository.Primary.Anexos;

import com.tecazuay.complexivog2c2.model.Primary.Anexos.Anexo11;
import com.tecazuay.complexivog2c2.model.Primary.solicitudproyecto.ProyectoPPP;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface Anexo11Repository extends JpaRepository<Anexo11, Long> {
        List<Anexo11> findAllByProyectoPPP(ProyectoPPP proyectoPPP);
        }