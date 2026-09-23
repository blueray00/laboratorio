package com.example.demo.service;

import com.example.demo.dto.UserRequestDTO;
import com.example.demo.dto.UserResponseDTO;
import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository repository;

    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    public UserResponseDTO criar(UserRequestDTO dto) {
        User User = new User(
                null,
                dto.nome(),
                dto.email(),
                dto.cargo()
        );

        User salvo = repository.salvar(User);

        return toResponseDTO(salvo);
    }

    public List<UserResponseDTO> listar() {
        return repository.listarTodas().stream()
                .map(this::toResponseDTO)
                .toList();
    }

    public UserResponseDTO buscarPorId(Long id) {
        Optional<User> user = repository.buscarPorId(id);

        if (user.isEmpty()) {
            return null;
        }

        return toResponseDTO(user.get());
    }

    public UserResponseDTO atualizar(Long id, UserRequestDTO dto) {
        Optional<User> user = repository.buscarPorId(id);

        if (user.isEmpty()) {
            return null;
        }

        User atualizado = new User(
                id,
                dto.nome(),
                dto.email(),
                dto.cargo()
        );

        User salvo = repository.salvar(atualizado);

        return toResponseDTO(salvo);
    }

    public boolean remover(Long id) {
        return repository.remover(id);
    }

    private UserResponseDTO toResponseDTO(User user) {
        return new UserResponseDTO(
                user.getId(),
                user.getNome(),
                user.getEmail(),
                user.getCargo()
        );
    }
}