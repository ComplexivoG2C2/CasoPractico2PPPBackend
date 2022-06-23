package com.tecazuay.complexivog2c2.model.Primary.Anexos;

import com.tecazuay.complexivog2c2.model.Primary.empresa.Empresa;
import com.tecazuay.complexivog2c2.model.Primary.solicitudproyecto.ProyectoPPP;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "anexo5")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Anexo5 {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Temporal(TemporalType.DATE)
    @Column(name = "fecha_emision")
    private Date fechaEmision;

    @Column(name = "tituloTutor")
    private String tituloTutor;

    @Column(name = "nombreTutor")
    private String nombreTutor;

    @Column(length = 10485760)
    private String documento;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "proyecto_id", referencedColumnName = "id")
    private ProyectoPPP proyectoPPP;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "empresa_id", referencedColumnName = "id")
    private Empresa empresa;

    @OneToMany(targetEntity = AlumnosAnexo5.class, mappedBy = "anexo5")
    private List<AlumnosAnexo5> alumnosAnexo5;
}
