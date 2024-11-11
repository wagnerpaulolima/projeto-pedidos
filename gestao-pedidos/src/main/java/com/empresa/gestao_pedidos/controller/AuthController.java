package com.empresa.gestao_pedidos.controller;

import com.empresa.gestao_pedidos.service.JWTService;
import com.empresa.gestao_pedidos.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private JWTService jwtService;

    @Autowired
    PedidoService pedidoService;

    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@RequestBody AuthRequest authRequest) {
        // Verificação de autenticação
        if ("sistemaA".equals(authRequest.getUsername()) && "1234".equals(authRequest.getPassword())) {
            String token = jwtService.generateToken(authRequest.getUsername(), "CREATE");
            return ResponseEntity.ok(new AuthResponse(token));
        } else if ("sistemaB".equals(authRequest.getUsername()) && "1234".equals(authRequest.getPassword())) {
            String token = jwtService.generateToken(authRequest.getUsername(), "READ");
            return ResponseEntity.ok(new AuthResponse(token));
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Usuário ou senha inválidos");
        }
    }

    // Classe interna para representar a requisição de autenticação
    public static class AuthRequest {
        private String username;
        private String password;

        // Getters e Setters
        public String getUsername() { return username; }
        public void setUsername(String username) { this.username = username; }
        public String getPassword() { return password; }
        public void setPassword(String password) { this.password = password; }
    }

    // Classe interna para representar a resposta de autenticação
    public static class AuthResponse {
        private String token;

        public AuthResponse(String token) { this.token = token; }

        public String getToken() { return token; }
    }
}

