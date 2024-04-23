package com.prueba.demo.models;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
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
@Table(name="detalle_orden")
public class DetalleOrden {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    
    private int cantidad_producir;
    
    private int cantidad_producida;
    
    @ManyToOne(cascade = CascadeType.ALL)
    private Orden orden;
    
    @ManyToOne(cascade = CascadeType.ALL)
    private Prenda prenda;
    
    @ManyToOne(cascade = CascadeType.ALL)
    private Color color;
    
    @ManyToOne(cascade = CascadeType.ALL)
    private Estado estado;
}
