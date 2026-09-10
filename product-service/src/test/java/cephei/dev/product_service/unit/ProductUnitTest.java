package cephei.dev.product_service.unit;

import cephei.dev.product_service.dto.ProductCreateDto;
import cephei.dev.product_service.dto.ProductReadDto;
import cephei.dev.product_service.entity.Category;
import cephei.dev.product_service.entity.Product;
import cephei.dev.product_service.mapper.ProductMapper;
import cephei.dev.product_service.repository.CategoryRepository;
import cephei.dev.product_service.repository.ProductRepository;
import cephei.dev.product_service.service.ProductService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ProductUnitTest {

    @Mock
    private ProductRepository productRepository;
    @Mock
    private CategoryRepository categoryRepository;
    @Mock
    private ProductMapper productMapper;

    @InjectMocks
    private ProductService productService;

    @Test
    public void create() {
        // Arrange
        ProductCreateDto productCreateDto = new ProductCreateDto("IPhone 18 Pro Max", 1L);
        Category category = Category.builder().id(1L).categoryName("Phone").build();
        Product product = Product.builder().id(1L).productName("IPhone 18 Pro Max").build();
        ProductReadDto productReadDto = new ProductReadDto(1L, "IPhone 18 Pro Max", "Phone");

        when(categoryRepository.findById(1L)).thenReturn(Optional.of(category));
        when(productMapper.toEntity(productCreateDto)).thenReturn(product);
        when(productRepository.save(product)).thenReturn(product);
        when(productMapper.toReadDto(product)).thenReturn(productReadDto);

        // Act

        ProductReadDto result = productService.create(productCreateDto);

        // Assert

        assertEquals(1L, result.id());
        assertEquals("IPhone 18 Pro Max", result.productName());
        assertEquals("Phone", result.category());

        verify(categoryRepository).findById(1L);
        verify(productMapper).toEntity(productCreateDto);
        verify(productRepository).save(product);
        verify(productMapper).toReadDto(product);
    }
}
