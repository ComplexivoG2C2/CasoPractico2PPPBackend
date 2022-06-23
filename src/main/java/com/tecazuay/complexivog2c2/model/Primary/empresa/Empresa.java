package com.tecazuay.complexivog2c2.model.Primary.empresa;

import com.tecazuay.complexivog2c2.model.Primary.Anexos.Anexo13;
import com.tecazuay.complexivog2c2.model.Primary.Anexos.Anexo5;
import com.tecazuay.complexivog2c2.model.Primary.Anexos.TutorEmp;
import com.tecazuay.complexivog2c2.model.Primary.coordinadores.CoordinadorVinculacion;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Table(name="empresa")
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Empresa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;

    private String representante;

    @Column(name="email_empresa")
    private String emailEmpresa;

    private String clave;



    @Column(name="email_representante")
    private String emailRepresentante;

    @Column(name="telefono_empresa")
    private String telefonoEmpresa;

    @Column(name="celular_representante")
    private String celularRepresentante;

    @Column(name="fecha_creacion")
    @Temporal(TemporalType.DATE)
    private Date fechaCreacion;

    private String ciudad;

    private String direccion;


    @Column(name="descripcion_empresa",length = 10485760)
    private String descripcionEmpresa;

    @Column(name="titulorepresentante",length = 10485760)
    private String titulorepresentante;

    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name="coordinadorV_id", referencedColumnName = "id")
    private CoordinadorVinculacion coordinadorVinculacion;

    @OneToMany(targetEntity = TutorEmp.class, mappedBy = "empresa")
    private List<TutorEmp> tutoremp;

    @OneToMany(targetEntity = Anexo5.class, mappedBy = "empresa")
    private List<Anexo5> anexo5;

    @OneToMany(targetEntity = Anexo13.class, mappedBy = "empresa")
    private List<Anexo13> anexo13;
}
