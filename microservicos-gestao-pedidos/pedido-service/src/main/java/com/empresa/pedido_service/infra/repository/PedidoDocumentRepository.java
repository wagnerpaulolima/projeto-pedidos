package com.empresa.pedido_service.infra.repository;


import com.empresa.pedido_service.domain.model.PedidoMongo;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PedidoDocumentRepository extends MongoRepository<PedidoMongo, String> {
}