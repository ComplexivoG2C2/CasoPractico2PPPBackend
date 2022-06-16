package com.tecazuay.complexivog2c2.model.Primary.Anexos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="estudiante_informeInicial")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EstudiantesInformeInicial implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre_esdtudiante")
    private String nombreEstudiante;

    @Column(name = "estado")
    private String estado;

    @Column(name = "observaciones")
    private String observaciones;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_Anexo",referencedColumnName = "id")
    private InformeInicial informeInicial;
    @Column(length = 10485760)
    private String documento;
}
