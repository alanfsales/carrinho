package com.carrinho.repository;

import com.carrinho.model.Carrinho;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarrinhoRepository extends JpaRepository<Carrinho, Long> {

    Carrinho findByAberto(boolean aberto);

}
