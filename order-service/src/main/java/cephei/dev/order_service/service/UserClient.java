package cephei.dev.order_service.service;

import cephei.dev.order_service.dto.UserDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "user-service")
public interface UserClient {

    @GetMapping("/api/v1/users/id/{id}")
    UserDto findById(@PathVariable Integer id);

    @GetMapping("/api/v1/users/username/{username}")
    UserDto findByUsername(@PathVariable String username);

    @GetMapping("/api/v1/users/test")
    String test();
}
