package com.carrinho.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Data
@Entity
public class ItemCarrinho {

    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int quantidade;
    private BigDecimal precoUnitario;
    private BigDecimal precoTotal;

    @JsonIgnore
    @ManyToOne
    private Carrinho carrinho;

    @ManyToOne
    private Produto produto;

}
