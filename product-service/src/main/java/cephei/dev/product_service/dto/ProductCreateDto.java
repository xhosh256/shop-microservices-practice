package cephei.dev.product_service.dto;

import java.math.BigDecimal;

public record ProductCreateDto(
        String productName,
        BigDecimal price,
        Long categoryId
) {
}
