package cephei.dev.user_service.dto;

public record UserReadDto(
        Integer id,
        String username,
        String firstname,
        String lastname
) {}
