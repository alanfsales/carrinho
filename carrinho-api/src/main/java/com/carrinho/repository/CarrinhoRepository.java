package com.carrinho.repository;

import com.carrinho.model.Carrinho;
import com.carrinho.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarrinhoRepository extends JpaRepository<Carrinho, Long> {

    Carrinho findByAberto(boolean aberto);
    List<Carrinho> findByUsuario(Usuario usuario);

}
