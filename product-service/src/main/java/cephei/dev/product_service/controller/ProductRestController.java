package cephei.dev.product_service.controller;

import cephei.dev.product_service.dto.ProductCreateDto;
import cephei.dev.product_service.dto.ProductReadDto;
import cephei.dev.product_service.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductRestController {

    private final ProductService productService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProductReadDto create(@RequestBody ProductCreateDto productCreateDto) {
        return productService.create(productCreateDto);
    }

    @GetMapping
    public Page<ProductReadDto> findAll(@PageableDefault(size = 5, page = 0) Pageable pageable) {
        return productService.findAll(pageable);
    }

    @GetMapping("/{id}")
    public ProductReadDto findById(@PathVariable Long id) {
        return productService.findById(id);
    }
}
