package com.carrinho.service;

import com.carrinho.dto.ItemCarrinhoDTO;
import com.carrinho.exception.EntidadeNaoExisteException;
import com.carrinho.model.Carrinho;
import com.carrinho.model.ItemCarrinho;
import com.carrinho.model.Produto;
import com.carrinho.model.Usuario;
import com.carrinho.repository.CarrinhoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class CarrinhoService {

    @Autowired
    private CarrinhoRepository carrinhoRepository;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private ProdutoService produtoService;

    public Carrinho buscar(Long id){
        return carrinhoRepository.findById(id).orElseThrow(() ->
                new EntidadeNaoExisteException("Não existe um carrinho com o id = " + id));
    }

    public Carrinho temCarrinhoAbrerto(){
        return carrinhoRepository.findByAberto(true);
    }

    public Carrinho addItemCarrinho(ItemCarrinhoDTO itemCarrinhoDTO){
        Carrinho carrinho = temCarrinhoAbrerto();
        if (carrinho == null){
            carrinho= new Carrinho();
        }
        validarItem(itemCarrinhoDTO, carrinho);

        List<ItemCarrinho>itens = carrinho.getItens();

        for (ItemCarrinho iten : itens) {
            iten.setCarrinho(carrinho);
            calcularPrecoTotalItem(iten);
        }
        calcularValorTotalCarrinho(carrinho);

        return carrinhoRepository.save(carrinho);
    }

    private void validarItem(ItemCarrinhoDTO itemCarrinhoDTO, Carrinho carrinho){
        Usuario usuario = usuarioService.buscar(itemCarrinhoDTO.getUsuarioId());
        carrinho.setUsuario(usuario);
        Produto produto = produtoService.buscar(itemCarrinhoDTO.getProdutoId());
        ItemCarrinho item = new ItemCarrinho();
        item.setProduto(produto);
        item.setQuantidade(itemCarrinhoDTO.getQuantidade());
        item.setPrecoUnitario(produto.getPreco());
        carrinho.getItens().add(item);
    }

    private void calcularPrecoTotalItem(ItemCarrinho item){
        item.setPrecoTotal(item.getPrecoUnitario()
                .multiply(BigDecimal.valueOf(item.getQuantidade())));
    }

    private void calcularValorTotalCarrinho(Carrinho carrinho){
        BigDecimal total = carrinho.getItens().stream()
                .map(ItemCarrinho::getPrecoTotal).reduce(BigDecimal.ZERO, BigDecimal::add);
        carrinho.setValorTotal(total);
    }
}
