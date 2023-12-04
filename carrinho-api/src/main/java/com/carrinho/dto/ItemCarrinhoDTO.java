package com.carrinho.dto;

import lombok.Data;

@Data
public class ItemCarrinhoDTO {

    private Long usuarioId;
    private Long produtoId;
    private int quantidade;

}
