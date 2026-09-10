package cephei.dev.product_service.dto;

import java.math.BigDecimal;

public record ProductReadDto (
        Long id,
        String productName,
        BigDecimal price,
        String category
) {
}
