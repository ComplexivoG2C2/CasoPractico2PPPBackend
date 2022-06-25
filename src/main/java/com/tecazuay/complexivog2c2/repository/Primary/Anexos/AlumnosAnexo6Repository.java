package com.tecazuay.complexivog2c2.repository.Primary.Anexos;

import com.tecazuay.complexivog2c2.model.Primary.Anexos.AlumnosAnexo6;
import com.tecazuay.complexivog2c2.model.Primary.Anexos.Anexo6;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AlumnosAnexo6Repository  extends JpaRepository<AlumnosAnexo6,Long> {
    List<AlumnosAnexo6> findAllByCedulaEstudiante(String cedula);
    Optional<AlumnosAnexo6> findByCedulaEstudiante(String cedula);
    List<AlumnosAnexo6> findAllByAnexo6(Anexo6 anexo6);
}
