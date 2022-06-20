package com.tecazuay.complexivog2c2.repository.Primary.Anexos;

import com.tecazuay.complexivog2c2.model.Primary.Anexos.Anexo81;
import com.tecazuay.complexivog2c2.model.Primary.Anexos.TutorEmp;
import com.tecazuay.complexivog2c2.model.Primary.empresa.Empresa;
import com.tecazuay.complexivog2c2.model.Primary.solicitudproyecto.ProyectoPPP;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TutorEmpProyectoRepository extends JpaRepository<TutorEmp, Long> {

    Optional<TutorEmp> findByCedula(String cedula);


    List<TutorEmp> findByEmpresa(Empresa empresa);

    Optional<TutorEmp> findByProyectoPPPAndEstado(ProyectoPPP proyectoPPP, boolean estado);

    Boolean existsByCedula(String cedula);

    Boolean existsByCedulaAndEstado(String cedula, Boolean estado);

    Boolean existsByProyectoPPP(ProyectoPPP proyectoppp);
    Boolean existsByEmpresa(Empresa empresa);
    Optional<TutorEmp> findBycorreo(String correo);


    List<TutorEmp> findAllByProyectoPPP(ProyectoPPP proyectoPPP);

//    @Query('SELECT correo, clave from tutor_empresarial')
//    List<TutorEmpProyectoRequest> getCorreoClave();
}