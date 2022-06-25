package com.tecazuay.complexivog2c2.model.Primary.Anexos.anexo121;

import com.tecazuay.complexivog2c2.model.Primary.Anexos.Anexo2;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;


@Entity
@Table(name="actividades_anexo121")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ActividadesAnexo121 implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String descripcion;


    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="id_anexo", referencedColumnName = "id")
    private Anexo121certificado anexo121certificados;
}
