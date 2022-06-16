package com.tecazuay.complexivog2c2.model.Primary.Anexos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "actividades_anexo7")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ActividadesCumplirAnexo7 implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String area;

    @Column(name = "actividad_realizar")
    private String actividadRealizar;

    @Column(name = "asignatura_relacionada")
    private String asignaturaRelacionada;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="id_anexo", referencedColumnName = "id")
    private Anexo7 anexo7;


}
