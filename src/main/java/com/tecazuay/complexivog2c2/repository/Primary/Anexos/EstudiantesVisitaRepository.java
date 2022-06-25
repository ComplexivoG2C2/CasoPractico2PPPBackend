package com.tecazuay.complexivog2c2.repository.Primary.Anexos;

import com.tecazuay.complexivog2c2.model.Primary.Anexos.Anexo11;
import com.tecazuay.complexivog2c2.model.Primary.Anexos.EstudiantesVisita;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EstudiantesVisitaRepository extends JpaRepository<EstudiantesVisita, Long> {
    List<EstudiantesVisita> findAllByAnexo11(Anexo11 anexo11);
}