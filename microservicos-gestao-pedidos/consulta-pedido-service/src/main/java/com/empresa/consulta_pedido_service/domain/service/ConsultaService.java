package com.empresa.consulta_pedido_service.domain.service;

import com.empresa.consulta_pedido_service.domain.model.PedidoDocument;
import com.empresa.consulta_pedido_service.infra.repository.PedidoDocumentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class ConsultaService {

    @Autowired
    private PedidoDocumentRepository pedidoDocumentRepository;

    @Cacheable(value = "pedidos", key = "#id", unless = "#result == null")
    public PedidoDocument obterPedido(String id) {
        return pedidoDocumentRepository.findById(id).orElse(null);
    }
}
