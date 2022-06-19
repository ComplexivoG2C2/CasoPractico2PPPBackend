package com.tecazuay.complexivog2c2.model.Primary.Anexos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name="actividades_anexo9")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ActividadesAnexo9 implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Temporal(TemporalType.DATE)
    @Column(name = "fecha_actividad")
    private Date fecha;

    @Column(name = "descripcion_actividad")
    private String descripcionActividad;

    @Column(name = "lugar")
    private String lugar;

    @Column(name = "hora_llegada")
    private String horallegada;
    @Column(name = "hora_salida")
    private String horasalida;

    @Column(name = "numero_horas")
    private int numHoras;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_Anexo",referencedColumnName = "id")
    private Anexo9 anexo9;
}