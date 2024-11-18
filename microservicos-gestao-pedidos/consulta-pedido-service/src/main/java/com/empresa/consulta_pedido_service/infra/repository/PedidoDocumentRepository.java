package com.empresa.consulta_pedido_service.infra.repository;

import com.empresa.consulta_pedido_service.domain.model.PedidoDocument;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PedidoDocumentRepository extends MongoRepository<PedidoDocument, String> {
}
