package com.tecazuay.complexivog2c2.model.Primary.Anexos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name="alumnos_anexo5")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AlumnosAnexo5 {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombreEstudiante;

    private String cedulaEstudiante;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="id_anexo", referencedColumnName = "id")
    private Anexo5 anexo5;
}
