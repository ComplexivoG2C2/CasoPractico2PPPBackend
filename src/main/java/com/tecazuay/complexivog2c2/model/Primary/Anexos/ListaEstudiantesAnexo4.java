package com.tecazuay.complexivog2c2.model.Primary.Anexos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;
import java.io.Serializable;
import javax.persistence.*;
@Entity
@Table(name="listaEst_anexo4")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ListaEstudiantesAnexo4 implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String cedula;

    private String nombre;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="id_anexo", referencedColumnName = "id")
    private Anexo4 anexo4;
    @Column(length = 10485760)
    private String documento;
}
