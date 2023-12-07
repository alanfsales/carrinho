package com.carrinho.controller;

import com.carrinho.dto.ItemCarrinhoDTO;
import com.carrinho.model.Carrinho;
import com.carrinho.repository.CarrinhoRepository;
import com.carrinho.service.CarrinhoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/carrinho")
public class CarrinhoController {

    @Autowired
    private CarrinhoRepository carrinhoRepository;

    @Autowired
    private CarrinhoService carrinhoService;

    @GetMapping("/{usuarioId}")
    public List<Carrinho> buscarPorUsuario(@PathVariable Long usuarioId){
        return carrinhoService.buscarPorUsuario(usuarioId);
    }

    @PostMapping
    public Carrinho addItemCarrinho(@RequestBody ItemCarrinhoDTO itemCarrinhoDTO){
        return carrinhoService.addItemCarrinho(itemCarrinhoDTO);
    }

    @DeleteMapping
    public Carrinho removeItemCarrinho(@RequestBody ItemCarrinhoDTO itemCarrinhoDTO){
        return carrinhoService.removerItemCarrinho(itemCarrinhoDTO);
    }

}