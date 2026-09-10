package cephei.dev.order_service.mapper;

import cephei.dev.order_service.dto.OrderReadDto;
import cephei.dev.order_service.entity.Order;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OrderMapper {

    OrderReadDto toReadDto(Order order);
}
