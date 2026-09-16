package com.example.demo.repository;

import com.example.demo.model.Task;
import org.springframework.stereotype.Repository;
import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class TaskRepository {
    private final Map<Long, Task> banco = new LinkedHashMap<>();
    private final AtomicLong sequencia = new AtomicLong();
    public Task salvar(String titulo) {
        System.out.println("[REPOSITORY] Salvando tarefa em memória: " + titulo);
        Long id = sequencia.incrementAndGet();
        Task tarefa = new Task(id, titulo, false);
        banco.put(id, tarefa);
        return tarefa;
    }
    public List<Task> listarTodas() {
        System.out.println("[REPOSITORY] Buscando todas as tarefas em memória");
        return new ArrayList<>(banco.values());
    }

    public Optional<Task> buscarPorId(Long id) {
        System.out.println("[REPOSITORY] Buscando tarefa por id: " + id);
        return Optional.ofNullable(banco.get(id));
    }
}
