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
@Table(name="detalle_venta")
public class DetalleVenta {
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    
    private int cantidad;
    
    private double valor_unit;
    
    @ManyToOne(cascade = CascadeType.ALL)
    private Venta venta;
    
    @ManyToOne(cascade = CascadeType.ALL)
    private Inventario inventario;
    
    @ManyToOne(cascade = CascadeType.ALL)
    private Talla talla;
}
