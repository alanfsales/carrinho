package com.carrinho.repository;

import com.carrinho.model.ItemCarrinho;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemCarrinhoRepository extends JpaRepository<ItemCarrinho, Long> {

}
