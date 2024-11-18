package com.empresa.consulta_pedido_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@SpringBootApplication
public class ConsultaPedidoServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ConsultaPedidoServiceApplication.class, args);
	}

}
