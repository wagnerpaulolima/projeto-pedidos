package com.empresa.pedido_service.Presentation;

import com.empresa.pedido_service.domain.model.Pedido;
import com.empresa.pedido_service.domain.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {

    @Autowired
    private PedidoService pedidoService;

    @PostMapping
    public ResponseEntity<Pedido> criarPedido(@RequestBody Pedido pedido) {
        Pedido pedidoCriado = pedidoService.criarPedido(pedido);
        return ResponseEntity.ok(pedidoCriado);
    }

}
