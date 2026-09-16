package com.example.demo.service;

import com.example.demo.dto.TaskRequestDTO;
import com.example.demo.dto.TaskResponseDTO;
import com.example.demo.model.Task;
import com.example.demo.repository.TaskRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {

    private final TaskRepository repository;

    public TaskService(TaskRepository repository) {
        this.repository = repository;
    }

    public TaskResponseDTO criar(TaskRequestDTO dto) {
        Task tarefa = new Task(
                dto.titulo(),
                dto.descricao(),
                dto.prazo()
        );

        Task salva = repository.salvar(tarefa);

        return toResponseDTO(salva);
    }

    public List<TaskResponseDTO> listarTodas() {
        return repository.listarTodas().stream()
                .map(this::toResponseDTO)
                .toList();
    }

    private TaskResponseDTO toResponseDTO(Task tarefa) {
        return new TaskResponseDTO(
                tarefa.getId(),
                tarefa.getTitulo(),
                tarefa.isConcluida(),
                tarefa.getPrioridade()
        );
    }
}