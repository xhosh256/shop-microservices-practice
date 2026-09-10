package cephei.dev.order_service.service;

import cephei.dev.order_service.dto.ProductDto;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Service
public class ProductClient {

    private final RestClient restClient;

    public ProductClient() {
        this.restClient = RestClient.builder()
                .baseUrl("http://localhost:8082")
                .build();
    }

    public ProductDto getProduct(Long productId) {
        return restClient.get()
                .uri("/api/v1/products/{id}", productId)
                .retrieve()
                .body(ProductDto.class);
    }
}
