package com.empresa.gestao_pedidos.service;

import com.empresa.gestao_pedidos.entity.Pedido;
import com.empresa.gestao_pedidos.entity.PedidoMongo;
import com.empresa.gestao_pedidos.entity.Produto;
import com.empresa.gestao_pedidos.entity.ProdutoMongo;
import com.empresa.gestao_pedidos.repository.PedidoMongoRepository;
import com.empresa.gestao_pedidos.repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private PedidoMongoRepository pedidoMongoRepository;

    @Cacheable(value = "pedidoCache", key = "#id")
    public PedidoMongo consultarPedido(String id) {
        return pedidoMongoRepository.findByPedidoId(Long.parseLong(id)).orElse(null);
    }

    @Transactional
    public Pedido salvarPedido(Pedido pedido) {
        // Calcula o total
        pedido.setTotal(calculaTotal(pedido.getProdutos()));

        // Salva no PostgreSQL
        Pedido pedidoSalvo = pedidoRepository.save(pedido);

        // Transforma e salva no MongoDB
        PedidoMongo pedidoMongo = transformaParaMongo(pedidoSalvo);
        PedidoMongo save = pedidoMongoRepository.save(pedidoMongo);
        System.out.println("Imprimindo objeto do mongo>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
        System.out.println(save);

        return pedidoSalvo;
    }

    private double calculaTotal(List<Produto> produtos) {
        return produtos.stream().mapToDouble(Produto::getValor).sum();
    }

    private PedidoMongo transformaParaMongo(Pedido pedido) {
        PedidoMongo pedidoMongo = new PedidoMongo();
        pedidoMongo.setPedidoId(pedido.getId());
        pedidoMongo.setStatus(pedido.getStatus());
        pedidoMongo.setTotal(pedido.getTotal());

        // Copia os produtos para a lista de ProdutoMongo
        List<ProdutoMongo> produtosMongo = pedido.getProdutos().stream().map(produto -> {
            ProdutoMongo produtoMongo = new ProdutoMongo();
            produtoMongo.setDescricao(produto.getDescricao());
            produtoMongo.setValor(produto.getValor());
            return produtoMongo;
        }).toList();
        pedidoMongo.setProdutos(produtosMongo);

        return pedidoMongo;
    }

}

