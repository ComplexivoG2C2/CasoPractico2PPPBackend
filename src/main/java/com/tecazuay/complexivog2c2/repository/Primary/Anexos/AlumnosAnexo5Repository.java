package com.tecazuay.complexivog2c2.repository.Primary.Anexos;

import com.tecazuay.complexivog2c2.model.Primary.Anexos.AlumnosAnexo5;
import com.tecazuay.complexivog2c2.model.Primary.Anexos.AlumnosAnexo6;
import com.tecazuay.complexivog2c2.model.Primary.Anexos.Anexo5;
import com.tecazuay.complexivog2c2.model.Primary.Anexos.Anexo6;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AlumnosAnexo5Repository extends JpaRepository<AlumnosAnexo5, Long> {

    List<AlumnosAnexo5> findAllByCedulaEstudiante(String cedula);
    Optional<AlumnosAnexo5> findByCedulaEstudiante(String cedula);
    List<AlumnosAnexo5> findAllByAnexo5(Anexo5 anexo5);
}
