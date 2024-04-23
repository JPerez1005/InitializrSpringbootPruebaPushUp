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
@Table(name="prenda")
public class Prenda {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    
    private String nombre;
    
    private double ValorUnitCop;
    
    private double ValorUnitUsd;
    
    private String codigo;
    
    @ManyToOne(cascade = CascadeType.ALL)
    private Estado estado;
    
    @ManyToOne(cascade = CascadeType.ALL)
    private TipoProteccion tipoProteccion;
    
    @ManyToOne(cascade = CascadeType.ALL)
    private Genero genero;
    
    @OneToMany(mappedBy = "prenda")
    private List<DetalleOrden> detalleOrden;
    
    @OneToMany(mappedBy = "prenda")
    private List<InsumoPrendas> insumoPrendas;
    
    @OneToMany(mappedBy = "prenda")
    private List<Inventario> inventario;

}
