package cephei.dev.auth_service.controller;

import cephei.dev.auth_service.dto.AccountCreateDto;
import cephei.dev.auth_service.dto.AccountLoginDto;
import cephei.dev.auth_service.dto.UserSessionReadDto;
import cephei.dev.auth_service.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<Void> register(
            @RequestBody AccountCreateDto accountCreateDto
    ) {
        authService.register(accountCreateDto);
    }

    @PostMapping("/login")
    public ResponseEntity<UserSessionReadDto> login(
            @RequestBody AccountLoginDto accountLoginDto
    ) {
        return Optional.ofNullable(authService.verify(accountLoginDto))
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(HttpStatus.BAD_REQUEST).build());
    }
}
