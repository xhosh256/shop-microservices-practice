package cephei.dev.order_service.service;

import cephei.dev.order_service.dto.ProductDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.client.RestClient;

@FeignClient(
        name = "product-service",
        url = "http://localhost:8082"
)
public interface ProductClient {

    @GetMapping("/api/v1/products/{id}")
    ProductDto getProduct(@PathVariable Long id);
}
