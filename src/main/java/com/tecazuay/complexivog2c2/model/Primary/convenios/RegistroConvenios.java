package com.tecazuay.complexivog2c2.model.Primary.convenios;

import com.tecazuay.complexivog2c2.model.Primary.empresa.Empresa;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "registro_convenios")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RegistroConvenios implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "fecha_convenio")
    @Temporal(TemporalType.DATE)
    private Date fechaConvenio;

    @Column(name = "nombre_cordinador_vinculacion")
    private String nombreCordinadorVinculacion;

    @OneToMany(targetEntity = Empresa.class, mappedBy = "empresa")
    private List<Empresa> registroConvenioEmpresa;


}
