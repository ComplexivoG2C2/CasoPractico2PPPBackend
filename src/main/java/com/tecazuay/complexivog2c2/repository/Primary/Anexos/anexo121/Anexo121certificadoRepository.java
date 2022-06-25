package com.tecazuay.complexivog2c2.repository.Primary.Anexos.anexo121;

import com.tecazuay.complexivog2c2.model.Primary.Anexos.anexo121.Anexo121certificado;
import com.tecazuay.complexivog2c2.model.Primary.solicitudproyecto.ProyectoPPP;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface Anexo121certificadoRepository  extends JpaRepository<Anexo121certificado,Long> {
    List<Anexo121certificado> findByProyectoPPP(ProyectoPPP proyectoPPP);
//    Optional<Anexo121certificado> findByProyectoPPP(ProyectoPPP proyectoPPP);
    Boolean existsByProyectoPPP(ProyectoPPP proyectoPPP);
}