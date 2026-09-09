package com.example.demo.repository;

import com.example.demo.model.Tarefa;
import org.springframework.stereotype.Repository;
import java.util.*;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

@Repository
public class TarefaRepository {
    private final Map<Long, Tarefa> banco = new LinkedHashMap<>();
    private final AtomicLong sequencia = new AtomicLong();
    public Tarefa salvar(String titulo) {
        System.out.println("[REPOSITORY] Salvando tarefa em memória: " + titulo);
        Long id = sequencia.incrementAndGet();
        Tarefa tarefa = new Tarefa(id, titulo, false);
        banco.put(id, tarefa);
        return tarefa;
    }
    public List<Tarefa> listarTodas() {
        System.out.println("[REPOSITORY] Buscando todas as tarefas em memória");
        return new ArrayList<>(banco.values());
    }

    public Optional<Tarefa> buscarPorId(Long id) {
        System.out.println("[REPOSITORY] Buscando tarefa por id: " + id);
        return Optional.ofNullable(banco.get(id));
    }
}
