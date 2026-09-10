package cephei.dev.order_service.dto;

import java.math.BigDecimal;
import java.util.List;

public record OrderReadDto (
        Long id,
        Integer userId,
        BigDecimal totalPrice,
        List<OrderItemReadDto> items
) {
}
