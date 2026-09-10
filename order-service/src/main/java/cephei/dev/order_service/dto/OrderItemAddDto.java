package cephei.dev.order_service.dto;

public record OrderItemAddDto (
        Long productId,
        Integer userId,
        Integer amount
) {
}
