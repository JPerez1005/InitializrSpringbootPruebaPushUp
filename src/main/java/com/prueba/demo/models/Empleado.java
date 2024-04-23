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
@Table(name="empleado")
public class Empleado {
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    
    private String nombre;
    
    private Date fecha_ingreso;
    
    @ManyToOne(cascade = CascadeType.ALL)
    private Municipio municipio;
    
    @ManyToOne(cascade = CascadeType.ALL)
    private Cargos cargos;
    
    @OneToMany(mappedBy = "empleado")
    private List<Orden> orden;
    
    @OneToMany(mappedBy = "empleado")
    private List<Venta> venta;
}
