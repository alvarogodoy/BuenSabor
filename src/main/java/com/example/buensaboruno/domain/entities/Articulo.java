package com.example.buensaboruno.domain.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.envers.NotAudited;
import java.util.HashSet;
import java.util.Set;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@SuperBuilder
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Articulo extends Base {


    protected String denominacion;
    protected Double precioVenta;

    @OneToMany
    @JoinColumn(name = "articulo_id")
    @Builder.Default
    @NotAudited
    protected Set<ImagenArticulo> imagenesArticulo = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "unidad_medida_id", nullable = false)
    protected UnidadMedida unidadMedida;

    @ManyToOne
    @JoinColumn(name = "Categoria_ID")
    @JsonIgnoreProperties({"subCategorias", "categoriaPadre", "sucursales", "articulos"})
    protected Categoria categoria;

}