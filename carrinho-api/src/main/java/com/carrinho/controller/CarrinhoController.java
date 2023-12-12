package com.carrinho.controller;

import com.carrinho.dto.ItemCarrinhoDTO;
import com.carrinho.model.Carrinho;
import com.carrinho.repository.CarrinhoRepository;
import com.carrinho.service.CarrinhoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/carrinho")
public class CarrinhoController {

    @Autowired
    private CarrinhoRepository carrinhoRepository;

    @Autowired
    private CarrinhoService carrinhoService;

    @GetMapping("/usuario/{usuarioId}")
    public List<Carrinho> buscarPorUsuario(@PathVariable Long usuarioId){
        return carrinhoService.buscarPorUsuario(usuarioId);
    }

    @GetMapping("/{carrinhoId}")
    public Carrinho buscarPorId(@PathVariable Long carrinhoId){
        return carrinhoService.buscar(carrinhoId);
    }

    @PostMapping
    public Carrinho addItemCarrinho(@RequestBody ItemCarrinhoDTO itemCarrinhoDTO){
        return carrinhoService.addItemCarrinho(itemCarrinhoDTO);
    }

    @DeleteMapping
    public Carrinho removeItemCarrinho(@RequestBody ItemCarrinhoDTO itemCarrinhoDTO){
        return carrinhoService.removerItemCarrinho(itemCarrinhoDTO);
    }

    @PutMapping("/usuario/{usuarioId}")
    public Carrinho finalizarCarrinho(@PathVariable Long usuarioId){
        return carrinhoService.finalizar(usuarioId);
    }

}
