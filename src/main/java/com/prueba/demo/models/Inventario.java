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
@Table(name="inventario")
public class Inventario {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    
    private String CodInv;
    
    private double ValorVtaCop;
    
    private double ValorVtaUsd;
    
    @ManyToOne(cascade = CascadeType.ALL)
    private Prenda prenda;
    
    @ManyToOne(cascade = CascadeType.ALL)
    private Talla talla;
    
    @OneToMany(mappedBy = "inventario")
    private List<InventarioTalla> inventarioTalla;
}
