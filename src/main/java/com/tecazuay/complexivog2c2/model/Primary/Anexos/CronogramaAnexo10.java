package com.tecazuay.complexivog2c2.model.Primary.Anexos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "cronograma_anexo10")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CronogramaAnexo10 implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int nro;

    @Column(name = "fecha_seguimiento")
    @Temporal(TemporalType.DATE)
    private Date fechaSeguimiento;

    private String actividades;

    @Column(name = "fecha_fin_prevista")
    @Temporal(TemporalType.DATE)
    private Date fechaFinPrevista;

    @Column(name = "porcentaje_avance")
    private int porcentajeAvance;

    private String observaciones;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="id_anexo", referencedColumnName = "id")
    private Anexo10 anexo10;

}
