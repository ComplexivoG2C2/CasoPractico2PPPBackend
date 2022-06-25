package com.tecazuay.complexivog2c2.repository.Primary.Anexos;

import com.tecazuay.complexivog2c2.model.Primary.Anexos.ActividadesAnexo9;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.Optional;

public interface ActividadesAnexo9Repository extends JpaRepository<ActividadesAnexo9,Long> {
    Optional<ActividadesAnexo9> findByFecha(Date Long);

}
