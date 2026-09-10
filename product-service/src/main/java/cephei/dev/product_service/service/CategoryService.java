package cephei.dev.product_service.service;

import cephei.dev.product_service.dto.CategoryCreateDto;
import cephei.dev.product_service.dto.CategoryReadDto;
import cephei.dev.product_service.mapper.CategoryMapper;
import cephei.dev.product_service.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    @Transactional
    public CategoryReadDto create(CategoryCreateDto categoryCreateDto) {
        return Optional.of(categoryCreateDto)
                .map(categoryMapper::toEntity)
                .map(categoryRepository::save)
                .map(categoryMapper::toReadDto)
                .orElse(null);
    }
}
