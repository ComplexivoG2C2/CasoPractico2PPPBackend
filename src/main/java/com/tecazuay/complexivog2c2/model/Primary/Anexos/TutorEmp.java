package com.tecazuay.complexivog2c2.model.Primary.Anexos;

import com.tecazuay.complexivog2c2.model.Primary.empresa.Empresa;
import com.tecazuay.complexivog2c2.model.Primary.solicitudproyecto.ProyectoPPP;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Table(name = "tutor_empresarial")
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TutorEmp implements Serializable {
    @Override
    public String toString() {
        return "TutorEmp{" +
                "id=" + id +
                ", cedula='" + cedula + '\'' +
                ", apellidos='" + apellidos + '\'' +
                ", nombres='" + nombres + '\'' +
                ", estado=" + estado +
                ", fecha_designacion=" + fecha_designacion +
                ", proyectoPPP=" + proyectoPPP +
                ", empresa=" + empresa +
                ", correo='" + correo + '\'' +
                ", clave='" + clave + '\'' +
                '}';
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String cedula;

    private String apellidos;

    private String nombres;

    private String titulo;

    private boolean estado;

    @Temporal(TemporalType.DATE)
    private Date fecha_designacion;

    @PrePersist
    public void crear_fecha() {
        this.fecha_designacion = new Date();
    }

    //Llave Foranea
    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "empresa_id", referencedColumnName = "id")
    private Empresa empresa;

    private String correo;

    private String clave;

    public boolean getEstado() {
        return this.estado;
    }

    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "proyecto_id", referencedColumnName = "id")
    private ProyectoPPP proyectoPPP;

    @OneToMany(targetEntity = Anexo5.class, mappedBy = "tutoe")
    private List<Anexo13> anexo13;

}