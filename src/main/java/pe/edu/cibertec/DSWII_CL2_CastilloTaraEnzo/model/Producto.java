package pe.edu.cibertec.DSWII_CL2_CastilloTaraEnzo.model;


import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@NoArgsConstructor
@Data
@Entity
@Table(name = "producto")
public class Producto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer ID;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "descripcion")
    private String descripcion;

    @Column(name = "cantidad")
    private Integer cantidad;

    @Column(name = "fechavencimiento")
    private Date fechavencimiento;
}