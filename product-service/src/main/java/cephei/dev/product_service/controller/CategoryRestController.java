package cephei.dev.product_service.controller;

import cephei.dev.product_service.dto.CategoryCreateDto;
import cephei.dev.product_service.dto.CategoryReadDto;
import cephei.dev.product_service.entity.Category;
import cephei.dev.product_service.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/categories")
@RequiredArgsConstructor
public class CategoryRestController {

    private final CategoryService categoryService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<CategoryReadDto> create(
            @RequestBody CategoryCreateDto categoryCreateDto
    ) {
        return ResponseEntity.ok(categoryService.create(categoryCreateDto));
    }
}
