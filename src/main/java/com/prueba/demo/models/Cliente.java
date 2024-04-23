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
@Table(name="cliente")
public class Cliente {
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    
    private String nombre;
    
    private String id_cliente;
    
    private Date fecha_registro;
    
    @ManyToOne(cascade = CascadeType.ALL)
    private Municipio municipio;
    
    @ManyToOne(cascade = CascadeType.ALL)
    private TipoPersona tipoPersona;
    
    @OneToMany(mappedBy = "cliente")
    private List<Orden> orden;
    
    @OneToMany(mappedBy = "cliente")
    private List<Venta> venta;
}
