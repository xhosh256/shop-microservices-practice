package cephei.dev.order_service.service;

import cephei.dev.order_service.dto.OrderItemAddDto;
import cephei.dev.order_service.dto.OrderReadDto;
import cephei.dev.order_service.dto.ProductDto;
import cephei.dev.order_service.dto.UserDto;
import cephei.dev.order_service.entity.Order;
import cephei.dev.order_service.entity.OrderItem;
import cephei.dev.order_service.mapper.OrderMapper;
import cephei.dev.order_service.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class OrderService {

    private final OrderRepository orderRepository;
    private final ProductClient productClient;
    private final UserClient userClient;
    private final OrderMapper orderMapper;


    @Transactional
    public OrderReadDto addToOrder(OrderItemAddDto orderProductDto) {
        ProductDto product = productClient.getProduct(orderProductDto.productId());
        UserDto user = userClient.getUserById(orderProductDto.userId());

        Order order = orderRepository.findByUserId(user.id())
                .orElseGet(() -> createOrder(orderProductDto.userId()));
        OrderItem item = OrderItem.builder()
                .productId(product.id())
                .productName(product.productName())
                .amount(orderProductDto.amount())
                .price(product.price())
                .build();

        order.add(item);

        order.setTotalPrice(
                order.getTotalPrice().add(
                        item.getPrice()
                                .multiply(BigDecimal.valueOf(item.getAmount()))
                )
        );

        return orderMapper.toReadDto(orderRepository.save(order));
    }

    @Transactional
    public Order createOrder(Integer userId) {
        return Order.builder()
                .userId(userId)
                .totalPrice(BigDecimal.valueOf(0))
                .build();
    }

    @Transactional
    public OrderReadDto showOrder(String username) {
        UserDto user = userClient.getUserByUsername(username);
        Optional<Order> maybeOrder = orderRepository.findByUserId(user.id());
        Order order = null;

        if(maybeOrder.isEmpty()) {
            order = createOrder(user.id());
            orderRepository.save(order);
        } else {
            order = maybeOrder.get();
        }

        return orderMapper.toReadDto(order);
    }
}
