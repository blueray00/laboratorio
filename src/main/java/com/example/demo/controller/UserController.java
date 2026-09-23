package com.example.demo.controller;

import com.example.demo.dto.UserRequestDTO;
import com.example.demo.dto.UserResponseDTO;
import com.example.demo.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService service;
    public UserController(UserService service) {
        this.service = service;
    }
    @PostMapping
    public ResponseEntity<UserResponseDTO> criar(@RequestBody UserRequestDTO corpo) {
        UserResponseDTO criada=service.criar(corpo);
        return ResponseEntity.status(HttpStatus.CREATED).body(criada);
    }
    @GetMapping
    public ResponseEntity<List<User>> listar() {
        System.out.println("[CONTROLLER] Requisição recebida: GET /users");
        return ResponseEntity.ok(service.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> buscar(@PathVariable Long id) {
        System.out.println("[CONTROLLER] Requisição recebida: GET /users/" + id);
        return ResponseEntity.ok(service.buscarPorId(id));
    }
    @PutMapping("/{id}")
    public ResponseEntity<UserResponseDTO> atualizar(@PathVariable Long id, @RequestBody UserRequestDTO corpo) {
        System.out.println("[CONTROLLER] Requisição recebida: PUT /usuarios/" + id);

        UserResponseDTO user = service.atualizar(id, corpo);

        if (user == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(user);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> remover(@PathVariable Long id) {
        System.out.println("[CONTROLLER] Requisição recebida: DELETE /user/" + id);

        boolean removido = service.remover(id);

        if (!removido) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.noContent().build();
    }
}