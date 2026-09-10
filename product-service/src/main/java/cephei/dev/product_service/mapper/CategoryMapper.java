package cephei.dev.product_service.mapper;

import cephei.dev.product_service.dto.CategoryCreateDto;
import cephei.dev.product_service.dto.CategoryReadDto;
import cephei.dev.product_service.entity.Category;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CategoryMapper {

    CategoryReadDto toReadDto(Category category);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "products", ignore = true)
    Category toEntity(CategoryCreateDto categoryCreateDto);
}
