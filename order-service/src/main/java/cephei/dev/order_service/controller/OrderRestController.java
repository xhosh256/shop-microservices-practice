package cephei.dev.order_service.controller;

import cephei.dev.order_service.dto.OrderItemAddDto;
import cephei.dev.order_service.dto.OrderReadDto;
import cephei.dev.order_service.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/orders")
@RequiredArgsConstructor
public class OrderRestController {

    private final OrderService orderService;

    @PostMapping("/items")
    public OrderReadDto addToOrder(
           @RequestBody OrderItemAddDto orderProductDto
    ) {
        return orderService.addToOrder(orderProductDto);
    }
}
