package cephei.dev.user_service.controller;

import cephei.dev.user_service.dto.UserCreateDto;
import cephei.dev.user_service.dto.UserReadDto;
import cephei.dev.user_service.service.ProfileService;
import cephei.dev.user_service.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserRestController {

    private final UserService userService;
    private final ProfileService profileService;

    @GetMapping
    public Page<UserReadDto> findAll(@PageableDefault(size = 5, page = 0) Pageable pageable) {
        return userService.findAll(pageable);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserReadDto> findById(@PathVariable Integer id) {
        return userService.findById(id)
                .map((userReadDto) -> ResponseEntity
                        .status(HttpStatus.OK)
                        .body(userReadDto))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserReadDto create(@RequestBody UserCreateDto userCreateDto) {
        return userService.create(userCreateDto);
    }
}
