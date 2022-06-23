package com.tecazuay.complexivog2c2.model.Primary.convenios;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="actividades_realizar")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ActividadesRealizar implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String actividadesRealizar;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="id_registroConvenio", referencedColumnName = "id")
    private RegistroConvenios registroConvenios;
}
