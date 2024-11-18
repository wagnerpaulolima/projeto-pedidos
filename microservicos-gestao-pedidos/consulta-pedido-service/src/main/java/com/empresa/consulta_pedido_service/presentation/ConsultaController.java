package com.empresa.consulta_pedido_service.presentation;

import com.empresa.consulta_pedido_service.domain.model.PedidoDocument;
import com.empresa.consulta_pedido_service.domain.service.ConsultaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/consulta")
public class ConsultaController {

    @Autowired
    private ConsultaService consultaService;

    @GetMapping("/{id}")
    public ResponseEntity<PedidoDocument> obterPedido(@PathVariable String id) {
        PedidoDocument pedido = consultaService.obterPedido(id);
        if (pedido != null) {
            return ResponseEntity.ok(pedido);
        }
        return ResponseEntity.notFound().build();
    }
}
