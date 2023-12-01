package com.carrinho.controller;

import com.carrinho.model.Produto;
import com.carrinho.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/produtos")
public class ProdutosController {

    @Autowired
    private ProdutoRepository produtoRepository;

    @GetMapping
    public List<Produto> listar(){
        return  produtoRepository.findAll();
    }

    @GetMapping("/{id}")
    public Produto buscar(@PathVariable Long id){
        return produtoRepository.findById(id).get();
    }
}
