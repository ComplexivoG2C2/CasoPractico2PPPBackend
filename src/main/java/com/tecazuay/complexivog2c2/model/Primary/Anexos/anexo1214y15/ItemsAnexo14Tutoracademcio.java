package com.tecazuay.complexivog2c2.model.Primary.Anexos.anexo1214y15;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "items_anexo14")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ItemsAnexo14Tutoracademcio implements Serializable {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "tutoraca_1")
    private String tutoracaItem1;


    @Column(name = "tutoraca_2")
    private int tutoracaItem2;


    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "id_anexo", referencedColumnName = "id")
    private Anexo14 anexo14;
}