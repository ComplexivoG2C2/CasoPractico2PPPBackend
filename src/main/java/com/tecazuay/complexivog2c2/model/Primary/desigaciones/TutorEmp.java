package com.tecazuay.complexivog2c2.model.Primary.desigaciones;


import com.tecazuay.complexivog2c2.model.Primary.empresa.Empresa;
import com.tecazuay.complexivog2c2.model.Primary.solicitudproyecto.ProyectoPPP;
import com.tecazuay.complexivog2c2.model.Primary.usuario.Usuario;
import com.tecazuay.complexivog2c2.model.Primary.coordinadores.CoordinadorCarrera;
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

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String cedula;

    private String apellidos;

    private String nombres;

    private boolean estado;

    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "coordinador_id", referencedColumnName = "id")
    private CoordinadorCarrera coordinadorCarrera;

    @Temporal(TemporalType.DATE)
    private Date fecha_designacion;

    @OneToMany(targetEntity = ProyectoPPP.class, mappedBy = "tutorEmp")
    private List<ProyectoPPP> proyectoPPP;

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

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "usuario_id", referencedColumnName = "id")
    private Usuario usuario;

    public boolean getEstado() {
        return this.estado;
    }

//    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
//    @JoinColumn(name = "proyecto_id", referencedColumnName = "id")
//    private ProyectoPPP proyecto;
}
