package cephei.dev.user_service.dto;

public record UserCreateDto(
        String username,
        String firstname,
        String lastname
) {
}
