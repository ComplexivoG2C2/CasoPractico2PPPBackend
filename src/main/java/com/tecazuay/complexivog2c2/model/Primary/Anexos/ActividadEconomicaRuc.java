package com.tecazuay.complexivog2c2.model.Primary.Anexos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="actividad_economica")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ActividadEconomicaRuc implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String codActividad;

    private String actividades;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="id_registroConvenio", referencedColumnName = "id")
    private RegistroConvenios registroConvenios;

}
