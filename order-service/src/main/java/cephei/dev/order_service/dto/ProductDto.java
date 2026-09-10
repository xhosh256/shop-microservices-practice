package cephei.dev.order_service.dto;

import java.math.BigDecimal;

public record ProductDto (
        Long id,
        String productName,
        BigDecimal price,
        Long categoryId
) {
}
