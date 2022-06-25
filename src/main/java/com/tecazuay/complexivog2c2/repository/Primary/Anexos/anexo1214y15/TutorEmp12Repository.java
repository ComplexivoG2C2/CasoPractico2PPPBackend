package com.tecazuay.complexivog2c2.repository.Primary.Anexos.anexo1214y15;

import com.tecazuay.complexivog2c2.model.Primary.Anexos.anexo1214y15.Anexo12;
import com.tecazuay.complexivog2c2.model.Primary.Anexos.anexo1214y15.ItemsAnexo12Tutoremp;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TutorEmp12Repository extends JpaRepository<ItemsAnexo12Tutoremp,Long> {
    List<ItemsAnexo12Tutoremp> findAllByAnexo12(Anexo12 anexo12);

}
