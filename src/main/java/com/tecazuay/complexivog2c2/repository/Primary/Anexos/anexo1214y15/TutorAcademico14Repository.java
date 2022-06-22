package com.tecazuay.complexivog2c2.repository.Primary.Anexos.anexo1214y15;

import com.tecazuay.complexivog2c2.model.Primary.Anexos.anexo1214y15.Anexo14;
import com.tecazuay.complexivog2c2.model.Primary.Anexos.anexo1214y15.ItemsAnexo14Tutoracademcio;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TutorAcademico14Repository  extends JpaRepository<ItemsAnexo14Tutoracademcio,Long> {
        List<ItemsAnexo14Tutoracademcio> findAllByAnexo14(Anexo14 anexo14);
    }