package cephei.dev.auth_service.dto;

import lombok.Data;

public record AccountLoginDto(
        String username,
        String password
) {
}
