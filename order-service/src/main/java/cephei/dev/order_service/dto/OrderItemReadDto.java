package cephei.dev.order_service.dto;

import java.math.BigDecimal;

public record OrderItemReadDto (
        Long id,
        String productName,
        BigDecimal price,
        Integer amount
) {
}
