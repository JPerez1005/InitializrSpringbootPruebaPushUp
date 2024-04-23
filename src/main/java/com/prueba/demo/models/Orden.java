package com.prueba.demo.models;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.Date;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author perez
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="orden")
public class Orden {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    
    private Date fecha;
    
    @ManyToOne(cascade = CascadeType.ALL)
    private Empleado empleado;
    
    @ManyToOne(cascade = CascadeType.ALL)
    private Estado estado;
    
    @ManyToOne(cascade = CascadeType.ALL)
    private Cliente cliente;
    
    @OneToMany(mappedBy = "orden")
    private List<DetalleOrden> detalleOrden;
}
