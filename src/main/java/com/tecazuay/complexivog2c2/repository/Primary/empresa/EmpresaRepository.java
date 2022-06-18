package com.tecazuay.complexivog2c2.repository.Primary.empresa;


import com.tecazuay.complexivog2c2.dto.empresa.EmpresaRequest;
import com.tecazuay.complexivog2c2.dto.empresa.EmpresaResponse;
import com.tecazuay.complexivog2c2.model.Primary.empresa.Empresa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import java.util.List;
import java.util.Optional;


public interface EmpresaRepository extends JpaRepository<Empresa, Long> {

    List<Empresa> findByNombreLikeIgnoreCase(String nombre);

    Optional<Empresa> findByEmailEmpresa(String emailEmpresa);
    Boolean existsByEmailEmpresa(String emailEmpresa);

}

