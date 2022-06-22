package com.tecazuay.complexivog2c2.repository.Primary.Anexos;

import com.tecazuay.complexivog2c2.model.Primary.Anexos.Anexo10;
import com.tecazuay.complexivog2c2.model.Primary.Anexos.CronogramaAnexo10;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CronogrmaAnexo10Repository extends JpaRepository<CronogramaAnexo10,Long > {
    Boolean deleteByAnexo10 (Anexo10 anexo10);
}
