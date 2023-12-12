package com.carrinho.service;

import com.carrinho.dto.ItemCarrinhoDTO;
import com.carrinho.exception.EntidadeNaoExisteException;
import com.carrinho.model.Carrinho;
import com.carrinho.model.ItemCarrinho;
import com.carrinho.model.Produto;
import com.carrinho.model.Usuario;
import com.carrinho.repository.CarrinhoRepository;
import com.carrinho.repository.ItemCarrinhoRepository;
import jakarta.transaction.Transactional;
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

    @Autowired
    private ItemCarrinhoRepository itemCarrinhoRepository;

    public Carrinho buscar(Long carrinhoId){
         return carrinhoRepository.findById(carrinhoId).orElseThrow(() ->
            new EntidadeNaoExisteException("Não existe um carrinho com o id = " + carrinhoId));
    }

    public List<Carrinho> buscarPorUsuario(Long usuarioId){
        Usuario usuario = usuarioService.buscar(usuarioId);
        return carrinhoRepository.findByUsuario(usuario);
    }

    public Carrinho addItemCarrinho(ItemCarrinhoDTO itemCarrinhoDTO){
        Carrinho carrinho = pegaCarrinhoAbrerto();

        validarItem(itemCarrinhoDTO, carrinho);

        for (ItemCarrinho iten : carrinho.getItens()) {
            iten.setCarrinho(carrinho);
            calcularPrecoTotalItem(iten);
        }
        calcularValorTotalCarrinho(carrinho);

        return carrinhoRepository.save(carrinho);
    }

    @Transactional
    public Carrinho removerItemCarrinho(ItemCarrinhoDTO itemCarrinhoDTO){
        Produto produto = produtoService.buscar(itemCarrinhoDTO.getProdutoId());
        Carrinho carrinho = pegaCarrinhoAbrerto();

        ItemCarrinho itemRemover = new ItemCarrinho();

        for (ItemCarrinho item: carrinho.getItens()){
            if (produto.equals(item.getProduto())){
                itemRemover =item;
            }
        }

        carrinho.getItens().remove(itemRemover);
        itemCarrinhoRepository.delete(itemRemover);

       calcularValorTotalCarrinho(carrinho);
       return carrinhoRepository.save(carrinho);
    }

    public Carrinho finalizar(Long usuarioId){
        Carrinho carrinho = pegaCarrinhoAbrerto();
        carrinho.setAberto(false);
        return carrinhoRepository.save(carrinho);
    }
    private Carrinho pegaCarrinhoAbrerto(){
        Carrinho carrinho = carrinhoRepository.findByAberto(true);
        if (carrinho == null){
            carrinho= new Carrinho();
        }
        return carrinho;
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
