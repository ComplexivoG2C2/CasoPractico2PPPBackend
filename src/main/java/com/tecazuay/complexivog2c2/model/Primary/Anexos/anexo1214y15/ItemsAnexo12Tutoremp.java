package com.tecazuay.complexivog2c2.model.Primary.Anexos.anexo1214y15;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "items_anexo12")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ItemsAnexo12Tutoremp implements Serializable {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "tutoremp_1")
    private String tutorempItem1;


    @Column(name = "tutoremp_2")
    private int tutorempItem2;


    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "id_anexo", referencedColumnName = "id")
    private Anexo12 anexo12;
}