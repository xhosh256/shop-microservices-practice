package cephei.dev.product_service.mapper;

import cephei.dev.product_service.dto.ProductCreateDto;
import cephei.dev.product_service.dto.ProductReadDto;
import cephei.dev.product_service.entity.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "category", ignore = true)
    Product toEntity(ProductCreateDto productCreateDto);

    @Mapping(source = "category.categoryName", target = "category")
    ProductReadDto toReadDto(Product save);
}
