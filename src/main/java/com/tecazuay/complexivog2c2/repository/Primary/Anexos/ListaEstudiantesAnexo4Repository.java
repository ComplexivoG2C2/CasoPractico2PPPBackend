package com.tecazuay.complexivog2c2.repository.Primary.Anexos;

import com.tecazuay.complexivog2c2.model.Primary.Anexos.Anexo4;
import com.tecazuay.complexivog2c2.model.Primary.Anexos.ListaEstudiantesAnexo4;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ListaEstudiantesAnexo4Repository extends JpaRepository<ListaEstudiantesAnexo4,Long> {
        Boolean deleteByAnexo4 (Anexo4 anexo4);
}
