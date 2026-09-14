package cephei.dev.order_service.controller;

import cephei.dev.order_service.dto.OrderItemAddDto;
import cephei.dev.order_service.dto.OrderReadDto;
import cephei.dev.order_service.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/orders")
@RequiredArgsConstructor
public class OrderRestController {

    private final OrderService orderService;

    @GetMapping("/testEureka")
    public String testEureka() {
        return orderService.testEureka();
    }

    @PostMapping("/items")
    public OrderReadDto addToOrder(
           @RequestBody OrderItemAddDto orderProductDto
    ) {
        return orderService.addToOrder(orderProductDto);
    }

    @GetMapping("/items")
    public OrderReadDto showOrder(
            @AuthenticationPrincipal String username
            ) {
        return orderService.showOrder(username);
    }
}
