package com.empresa.api_gateway.presentation.controller;

import com.empresa.api_gateway.infra.config.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private JwtUtil jwtUtil;

    // Credenciais em memória para os sistemas A e B
    private static final Map<String, String> CREDENTIALS = new HashMap<>();

    static {
        CREDENTIALS.put("sistemaA", "1234");
        CREDENTIALS.put("sistemaB", "1234");
    }

    @PostMapping("/login")
    public ResponseEntity<?> authenticate(@RequestBody Map<String, String> request) {
        String username = request.get("username");
        String password = request.get("password");

        // Validar credenciais
        if (CREDENTIALS.containsKey(username) && CREDENTIALS.get(username).equals(password)) {
            // Gerar token JWT se as credenciais estiverem corretas
            String token = jwtUtil.generateToken(username);
            return ResponseEntity.ok(Map.of("token", token));
        } else {
            return ResponseEntity.status(401).body("Credenciais inválidas");
        }
    }
}
