package cephei.dev.user_service.service;

import cephei.dev.user_service.dto.UserCreateDto;
import cephei.dev.user_service.dto.UserReadDto;
import cephei.dev.user_service.entity.Profile;
import cephei.dev.user_service.entity.User;
import cephei.dev.user_service.mapper.ProfileMapper;
import cephei.dev.user_service.mapper.UserMapper;
import cephei.dev.user_service.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final ProfileMapper profileMapper;

    public Page<UserReadDto> findAll(Pageable pageable) {
        return userRepository
                .findAll(pageable)
                .map(userMapper::toReadDto);
    }

    public Optional<UserReadDto> findById(Integer id) {
        return userRepository
                .findById(id)
                .map(userMapper::toReadDto);
    }

    @Transactional
    public UserReadDto create(UserCreateDto userCreateDto) {
        User user = userMapper.toEntity(userCreateDto);
        Profile profile = profileMapper.toEntity(userCreateDto);
        profile.setUser(user);

        userRepository.save(user);

        return userMapper.toReadDto(user);
    }
}
