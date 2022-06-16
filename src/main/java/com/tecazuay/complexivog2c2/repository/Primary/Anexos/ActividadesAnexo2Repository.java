package com.tecazuay.complexivog2c2.repository.Primary.Anexos;

import com.tecazuay.complexivog2c2.model.Primary.Anexos.ActividadesAnexo2;
import com.tecazuay.complexivog2c2.model.Primary.Anexos.Anexo2;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ActividadesAnexo2Repository extends JpaRepository<ActividadesAnexo2,Long> {
    Boolean deleteByAnexo2 (Anexo2 anexo2);

}
