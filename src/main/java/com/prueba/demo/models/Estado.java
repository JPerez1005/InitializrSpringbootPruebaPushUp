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
@Table(name="estado")
public class Estado {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne(cascade = CascadeType.ALL)
    private TipoEstado tipoEstado;
    
    @OneToMany(mappedBy = "estado")
    private List<Orden> orden;
    
    @OneToMany(mappedBy = "estado")
    private List<Prenda> prenda;
    
    @OneToMany(mappedBy = "estado")
    private List<DetalleOrden> detalleOrden;
}
