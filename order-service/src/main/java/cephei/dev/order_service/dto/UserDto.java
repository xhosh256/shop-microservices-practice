package cephei.dev.order_service.dto;

public record UserDto (
        Integer id,
        String username,
        String firstname,
        String lastname
) {
}
