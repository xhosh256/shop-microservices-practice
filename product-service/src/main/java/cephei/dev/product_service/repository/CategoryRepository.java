package cephei.dev.product_service.repository;

import cephei.dev.product_service.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CategoryRepository extends JpaRepository<Category, Long> {
}
