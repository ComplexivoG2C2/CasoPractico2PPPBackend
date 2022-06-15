package com.tecazuay.complexivog2c2.repository.Primary.Anexos;

import com.tecazuay.complexivog2c2.model.Primary.Anexos.ActividadesCumplirAnexo7;
import com.tecazuay.complexivog2c2.model.Primary.Anexos.Anexo7;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ActividadesCumplirAnexo7Repository extends JpaRepository<ActividadesCumplirAnexo7,Long > {
        Boolean deleteByAnexo7 (Anexo7 anexo7);
}
