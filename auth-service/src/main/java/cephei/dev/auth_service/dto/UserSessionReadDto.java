package cephei.dev.auth_service.dto;

public record UserSessionReadDto(
        String username,
        String jwt
) {
}
