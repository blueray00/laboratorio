package com.example.demo.repository;

import com.example.demo.model.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository {

    User salvar(User usuario);

    List<User> listarTodas();

    Optional<User> buscarPorId(Long id);

    boolean remover(Long id);
}