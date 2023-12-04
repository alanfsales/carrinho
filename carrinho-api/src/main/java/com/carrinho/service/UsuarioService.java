package com.carrinho.service;

import com.carrinho.exception.EntidadeNaoExisteException;
import com.carrinho.model.Usuario;
import com.carrinho.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    @Autowired
    public UsuarioRepository usuarioRepository;

    public Usuario buscar(Long id){
        return  usuarioRepository.findById(id).orElseThrow(() ->
                new EntidadeNaoExisteException("Não existe um usuario com o id = " + id));
    }
}
