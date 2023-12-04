package com.carrinho.service;

import com.carrinho.exception.EntidadeNaoExisteException;
import com.carrinho.model.Produto;
import com.carrinho.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    public Produto buscar(Long id){
        return produtoRepository.findById(id).orElseThrow(() ->
                new EntidadeNaoExisteException("Não existe um produto com o id = " + id));
    }

}
