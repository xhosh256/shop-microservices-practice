package cephei.dev.product_service.service;


import cephei.dev.product_service.dto.ProductCreateDto;
import cephei.dev.product_service.dto.ProductReadDto;
import cephei.dev.product_service.entity.Category;
import cephei.dev.product_service.entity.Product;
import cephei.dev.product_service.mapper.ProductMapper;
import cephei.dev.product_service.repository.CategoryRepository;
import cephei.dev.product_service.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final ProductMapper productMapper;

    @Transactional
    public ProductReadDto create(ProductCreateDto productCreateDto) {
        Category category = categoryRepository.findById(productCreateDto.categoryId())
                .orElseThrow(IllegalArgumentException::new);
        Product product = productMapper.toEntity(productCreateDto);
        product.setCategory(category);

        return productMapper.toReadDto(productRepository.save(product));
    }

    public Page<ProductReadDto> findAll(Pageable pageable) {
        return productRepository.findAll(pageable)
                .map(productMapper::toReadDto);
    }

    public ProductReadDto findById(Long id) {
        return productRepository.findById(id)
                .map(productMapper::toReadDto)
                .orElse(null);
    }
}
