package com.empresa.pedido_service.domain.service;

import com.empresa.pedido_service.domain.model.Pedido;

import com.empresa.pedido_service.domain.model.PedidoMongo;
import com.empresa.pedido_service.domain.model.ProdutoMongo;
import com.empresa.pedido_service.infra.repository.PedidoDocumentRepository;
import com.empresa.pedido_service.infra.repository.PedidoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private PedidoDocumentRepository pedidoDocumentRepository;

    @Transactional
    public Pedido criarPedido(Pedido pedido) {
        Pedido pedidoSalvo = pedidoRepository.save(pedido);

        // Criando o documento para o MongoDB
        PedidoMongo pedidoDocument = new PedidoMongo();
        pedidoDocument.setId(pedidoSalvo.getId().toString());
        pedidoDocument.setStatus(pedidoSalvo.getStatus());
        pedidoDocument.setValorTotal(pedidoSalvo.getValorTotal());
        //passando a lista de produtos para o mongo
        List<ProdutoMongo> produtosMongo = pedido.getProdutos().stream().map(produto -> {
            ProdutoMongo produtoMongo = new ProdutoMongo();
            produtoMongo.setNome(produto.getNome());
            produtoMongo.setPreco(produto.getPreco());
            produtoMongo.setQuantidade(produto.getQuantidade());
            return produtoMongo;
        }).toList();
        pedidoDocument.setProdutos(produtosMongo);

        pedidoDocumentRepository.save(pedidoDocument);

        return pedidoSalvo;
    }
}
