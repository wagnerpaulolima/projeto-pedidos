package com.empresa.gestao_pedidos.repository;

import com.empresa.gestao_pedidos.entity.PedidoMongo;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PedidoMongoRepository extends MongoRepository<PedidoMongo, String> {

    Optional<PedidoMongo> findByPedidoId(Long pedidoId);
}