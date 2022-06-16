package com.tecazuay.complexivog2c2.repository.Primary.convenios;

import com.tecazuay.complexivog2c2.model.Primary.convenios.RegistroConvenios;
import com.tecazuay.complexivog2c2.model.Primary.empresa.Empresa;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface RegistroConveniosRepository extends JpaRepository<RegistroConvenios, Long> {

   // List<RegistroConvenios> findByConvenios(RegistroConvenios registroConvenios);

    //Optional<RegistroConvenios> findByConvenios (Long id);

    //Boolean existsByEmailcomConvenios (Long id);
}
