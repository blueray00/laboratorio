package com.example.demo.controller;

import com.example.demo.dto.TaskRequestDTO;
import com.example.demo.dto.TaskResponseDTO;
import com.example.demo.model.Task;
import com.example.demo.service.TaskService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController {
    private final TaskService service;
    public TaskController(TaskService service) {
        this.service = service;
    }
    @PostMapping
    public ResponseEntity<TaskResponseDTO> criar(@RequestBody TaskRequestDTO corpo) {
        TaskResponseDTO criada=service.criar(corpo);
        return ResponseEntity.status(HttpStatus.CREATED).body(criada);
    }
    @GetMapping
    public ResponseEntity<List<Task>> listar() {
        System.out.println("[CONTROLLER] Requisição recebida: GET /tasks");
        return ResponseEntity.ok(service.listar());
    }

    // Retorna todas as tarefas concluidas
    @GetMapping("/concluidos")
    public ResponseEntity<List<Task>> listarConcluidos(){
        System.out.println("[Controller] Requisição recebida: GET /tasks/concluidos");
        return ResponseEntity.ok(service.listarConcluidos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Task> buscar(@PathVariable Long id) {
        System.out.println("[CONTROLLER] Requisição recebida: GET /tasks/" + id);
        return ResponseEntity.ok(service.buscarPorId(id));
    }
}
