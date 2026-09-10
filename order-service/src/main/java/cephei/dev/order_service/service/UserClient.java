package cephei.dev.order_service.service;

import cephei.dev.order_service.dto.UserDto;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Service
public class UserClient {

    private final RestClient restClient;

    public UserClient() {
        this.restClient = RestClient.builder()
                .baseUrl("http://localhost:8081")
                .build();
    }

    public UserDto getUser(Integer userId) {
        return restClient.get()
                .uri("/api/v1/users/{id}", userId)
                .retrieve()
                .body(UserDto.class);
    }
}
