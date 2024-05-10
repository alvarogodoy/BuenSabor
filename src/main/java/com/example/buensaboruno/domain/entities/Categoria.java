package com.example.buensaboruno.domain.entities;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.envers.Audited;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
@Builder
//@Audited
public class Categoria extends Base{

    private String denominacion;

    @ManyToMany(mappedBy = "categorias")
    @Builder.Default
    private Set<Sucursal> sucursales = new HashSet<>();

    @OneToMany
    @JoinColumn(name = "categoria_id")
    @Builder.Default
    private Set<Articulo> articulos = new HashSet<>();

    @OneToMany
    @JoinColumn(name = "categoria_id")
    @Builder.Default
    private Set<Categoria> subCategorias = new HashSet<>();

}
