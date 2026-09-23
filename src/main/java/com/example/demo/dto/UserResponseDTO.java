package com.example.demo.dto;

public record UserResponseDTO (
        Long id,
        String nome,
        String email,
        String cargo
) {}