package cephei.dev.product_service.unit;

import cephei.dev.product_service.dto.CategoryCreateDto;
import cephei.dev.product_service.dto.CategoryReadDto;
import cephei.dev.product_service.entity.Category;
import cephei.dev.product_service.mapper.CategoryMapper;
import cephei.dev.product_service.repository.CategoryRepository;
import cephei.dev.product_service.service.CategoryService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CategoryUnitTest {

    @Mock
    private CategoryRepository categoryRepository;

    @Mock
    private CategoryMapper categoryMapper;

    @InjectMocks
    private CategoryService categoryService;

    @Test
    public void create() {
        // Arrange
        CategoryCreateDto categoryCreateDto = new CategoryCreateDto("Phone");
        Category category = Category.builder().id(1L).categoryName("Phone").build();
        CategoryReadDto categoryReadDto = new CategoryReadDto(1L, "Phone");

        when(categoryMapper.toEntity(categoryCreateDto)).thenReturn(category);
        when(categoryRepository.save(category)).thenReturn(category);
        when(categoryMapper.toReadDto(category)).thenReturn(categoryReadDto);

        // Act
        CategoryReadDto result = categoryService.create(categoryCreateDto);

        // Assert
        assertEquals(1L, result.id());
        assertEquals("Phone", result.categoryName());

        verify(categoryMapper).toEntity(categoryCreateDto);
        verify(categoryRepository).save(category);
        verify(categoryMapper).toReadDto(category);
    }
}
