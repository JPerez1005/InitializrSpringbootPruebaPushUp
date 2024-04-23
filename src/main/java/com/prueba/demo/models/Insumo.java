package com.prueba.demo.models;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
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
@Table(name="insumo")
public class Insumo {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    
    private String nombre;
    
    private double valor_unit;
    
    private double stock_min;
    
    private double stock_max;
    
    @OneToMany(mappedBy = "insumo")
    private List<InsumoProveedor> insumoProveedor;
    
    @OneToMany(mappedBy = "insumo")
    private List<InsumoPrendas> insumoPrendas;
    
}
