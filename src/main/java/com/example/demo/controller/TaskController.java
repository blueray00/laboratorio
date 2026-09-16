package com.example.demo.controller;

import com.example.demo.dto.TaskResponseDTO;
import com.example.demo.model.Tarefa;
import com.example.demo.service.TaskService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/tarefas")
public class TaskController {
    private final TaskService service;
    public TaskController(TaskService service) {
        this.service = service;
    }
    @PostMapping
    public ResponseEntity<TaskResponseDTO> criar(@RequestBody Map<String, String>
     TaskResponse                                            corpo) {
    }
    @GetMapping
    public ResponseEntity<List<Tarefa>> listar() {
        System.out.println("[CONTROLLER] Requisição recebida: GET /tarefas");
        return ResponseEntity.ok(service.listar());
    }

    // Retorna todas as tarefas concluidas
    @GetMapping("/concluidos")
    public ResponseEntity<List<Tarefa>> listarConcluidos(){
       System.out.println("[Controller] Requisição recebida: GET /tarefas/concluidos");
       return ResponseEntity.ok(service.listarConcluidos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Tarefa> buscar(@PathVariable Long id) {
        System.out.println("[CONTROLLER] Requisição recebida: GET /tarefas/" + id);
        return ResponseEntity.ok(service.buscarPorId(id));
    }
}