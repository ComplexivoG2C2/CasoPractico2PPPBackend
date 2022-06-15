package com.tecazuay.complexivog2c2.model.Primary.Anexos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "cronograma_actividades_anexo7")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CronogramaActividadesAnexo7 implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "actividad_realizar")
    private String actividadRealizar;

    private int semanas;

    private int nrohoras;

    @Column(name = "horas_totales")
    private int horasTotales;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="id_anexo", referencedColumnName = "id")
    private Anexo7 anexo7;

}
