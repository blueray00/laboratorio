package com.example.demo.repository;

import com.example.demo.model.User;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class InMemoryUserRepository implements UserRepository {

    private final Map<Long, User> banco = new LinkedHashMap<>();
    private final AtomicLong contador = new AtomicLong(1);

    @Override
    public User salvar(User usuario) {
        Long id = usuario.getId();

        if (id == null) {
            id = contador.getAndIncrement();
            usuario = new User(
                    id,
                    usuario.getNome(),
                    usuario.getEmail(),
                    usuario.getCargo()
            );
        }

        System.out.println("[REPOSITORY] Salvando usuario: " + usuario.getNome());
        banco.put(id, usuario);
        return usuario;
    }

    @Override
    public List<User> listarTodas() {
        System.out.println("[REPOSITORY] Buscando todos os usuarios em memória");
        return new ArrayList<>(banco.values());
    }

    @Override
    public Optional<User> buscarPorId(Long id) {
        System.out.println("[REPOSITORY] Buscando usuario por id: " + id);
        return Optional.ofNullable(banco.get(id));
    }

    @Override
    public boolean remover(Long id) {
        System.out.println("[REPOSITORY] Removendo usuario por id: " + id);
        return banco.remove(id) != null;
    }
}