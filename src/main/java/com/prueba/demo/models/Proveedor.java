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
@Table(name="proveedor")
public class Proveedor {
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    
    private String nit_proveedor;
    
    private String nombre;
    
    @ManyToOne(cascade = CascadeType.ALL)
    private TipoPersona tipoPersona;
    
    @ManyToOne(cascade = CascadeType.ALL)
    private Municipio municipio;
    
    @OneToMany(mappedBy = "proveedor")
    private List<InsumoProveedor> insumoProveedor;
}
