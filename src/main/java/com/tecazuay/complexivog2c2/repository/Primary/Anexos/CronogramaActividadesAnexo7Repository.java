package com.tecazuay.complexivog2c2.repository.Primary.Anexos;

import com.tecazuay.complexivog2c2.model.Primary.Anexos.Anexo7;
import com.tecazuay.complexivog2c2.model.Primary.Anexos.CronogramaActividadesAnexo7;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CronogramaActividadesAnexo7Repository extends JpaRepository<CronogramaActividadesAnexo7,Long > {
    Boolean deleteByAnexo7 (Anexo7 anexo7);
}
