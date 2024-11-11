package com.empresa.gestao_pedidos.controller;

import com.empresa.gestao_pedidos.entity.Pedido;
import com.empresa.gestao_pedidos.entity.PedidoMongo;
import com.empresa.gestao_pedidos.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class PedidoController {

    @Autowired
    private PedidoService pedidoService;

    @PostMapping("/cadastrar")
    public ResponseEntity<?> cadastrarPedido(@RequestBody Pedido pedido) {
        Pedido pedidoCadastrado = pedidoService.salvarPedido(pedido);
        return ResponseEntity.ok(pedidoCadastrado);
    }

    @GetMapping("/consultar/{id}")
    public ResponseEntity<?> consultarPedido(@PathVariable String id) {
        PedidoMongo pedido = pedidoService.consultarPedido(id);
        return ResponseEntity.ok(pedido);
    }
}

