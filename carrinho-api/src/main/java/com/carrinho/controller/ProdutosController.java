package com.carrinho.controller;

import com.carrinho.model.Produto;
import com.carrinho.repository.ProdutoRepository;
import com.carrinho.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/produtos")
public class ProdutosController {

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private ProdutoService produtoService;

    @GetMapping
    public List<Produto> listar(){
        return  produtoRepository.findAll();
    }

    @GetMapping("/{id}")
    public Produto buscar(@PathVariable Long id){
        return produtoService.buscar(id);
    }

    @GetMapping(params = "nome")
    public List<Produto> buscarPorNOme(@RequestParam String nome){
        return produtoService.buscarPorNome(nome);
    }
}
