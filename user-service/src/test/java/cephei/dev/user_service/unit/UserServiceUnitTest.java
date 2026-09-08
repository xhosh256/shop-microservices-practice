package test.java.cephei.dev.user_service.unit;

import cephei.dev.user_service.dto.UserCreateDto;
import cephei.dev.user_service.dto.UserReadDto;
import cephei.dev.user_service.entity.Profile;
import cephei.dev.user_service.entity.User;
import cephei.dev.user_service.mapper.ProfileMapper;
import cephei.dev.user_service.mapper.UserMapper;
import cephei.dev.user_service.repository.UserRepository;
import cephei.dev.user_service.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserServiceUnitTest {

    @Mock
    UserRepository userRepository;

    @Mock
    UserMapper userMapper;

    @Mock
    ProfileMapper profileMapper;

    @InjectMocks
    UserService userService;

    @Test
    public void findAll() {
        // Arrange
        Pageable pageable = PageRequest.of(0, 3);
        Profile profile1 = Profile.builder().id(1)
                .firstname("peter")
                .lastname("parker")
                .build();
        User user1 = User.builder().id(1)
                .username("user1")
                .build();
        profile1.setUser(user1);

        Profile profile2 = Profile.builder().id(1)
                .firstname("lenchik")
                .lastname("krasavchik")
                .build();
        User user2 = User.builder().id(1)
                .username("user2")
                .build();
        profile2.setUser(user2);

        Profile profile3 = Profile.builder().id(1)
                .firstname("shhhhluuuuuhaa")
                .lastname("dasdasdasdas")
                .build();
        User user3 = User.builder().id(1)
                .username("user3")
                .build();
        profile3.setUser(user3);

        UserReadDto user1ReadDto = new UserReadDto(
                1, "user1", "peter", "parker"
        );
        UserReadDto user2ReadDto = new UserReadDto(
                2, "user2", "lenchik", "krasavchik"
        );
        UserReadDto user3ReadDto = new UserReadDto(
                3, "user3", "shhhhluuuuuhaa", "dasdasdasdas"
        );

        when(userRepository.findAll(any(Pageable.class))).thenReturn(
                new PageImpl<>(
                        List.of(user1, user2, user3), pageable, 3)
        );
        when(userMapper.toReadDto(user1)).thenReturn(user1ReadDto);
        when(userMapper.toReadDto(user2)).thenReturn(user2ReadDto);
        when(userMapper.toReadDto(user3)).thenReturn(user3ReadDto);

        // Act
        Page<UserReadDto> result = userService.findAll(pageable);

        // Assert
        assertEquals(3, result.getTotalElements());
        assertEquals(1, result.getTotalPages());

        assertEquals(1, result.getContent().get(0).id());
        assertEquals("user1", result.getContent().get(0).username());
        assertEquals("peter", result.getContent().get(0).firstname());
        assertEquals("parker", result.getContent().get(0).lastname());

        assertEquals(2, result.getContent().get(1).id());
        assertEquals("user2", result.getContent().get(1).username());
        assertEquals("lenchik", result.getContent().get(1).firstname());
        assertEquals("krasavchik", result.getContent().get(1).lastname());

        assertEquals(3, result.getContent().get(2).id());
        assertEquals("user3", result.getContent().get(2).username());
        assertEquals("shhhhluuuuuhaa", result.getContent().get(2).firstname());
        assertEquals("dasdasdasdas", result.getContent().get(2).lastname());

        assertEquals(pageable.getPageNumber(), result.getNumber());
        assertEquals(pageable.getPageSize(), result.getSize());
    }

    @Test
    void findById() {
        // Arrange
        Profile profile1 = Profile.builder().id(1)
                .firstname("peter")
                .lastname("parker")
                .build();
        User user1 = User.builder().id(1)
                .username("user1")
                .build();
        profile1.setUser(user1);

        UserReadDto user1ReadDto = new UserReadDto(
                1, "user1", "peter", "parker"
        );

        when(userRepository.findById(1)).thenReturn(Optional.of(user1));
        when(userMapper.toReadDto(user1)).thenReturn(user1ReadDto);

        // Act
        Optional<UserReadDto> maybeUser = userService.findById(1);

        // Assert
        assertThat(maybeUser).isPresent();

        UserReadDto user = maybeUser.get();

        assertEquals(1, user.id());
        assertEquals("user1", user.username());
        assertEquals("peter", user.firstname());
        assertEquals("parker", user.lastname());
    }

    @Test
    void create() {
        // Arrange
        UserCreateDto userCreateDto = new UserCreateDto("user1", "firstname", "lastname");
        User user = User.builder().id(1).username("user1").build();
        Profile profile = Profile.builder().id(1).firstname("peter").lastname("parker").build();
        UserReadDto userReadDto = new UserReadDto(1, "user1", "peter", "parker");

        when(userMapper.toEntity(userCreateDto)).thenReturn(user);
        when(profileMapper.toEntity(userCreateDto)).thenReturn(profile);
        when(userRepository.save(user)).thenReturn(user);
        when(userMapper.toReadDto(user)).thenReturn(userReadDto);

        // Act
        UserReadDto result = userService.create(userCreateDto);

        // Assert
        assertEquals(1, result.id());
        assertEquals("user1", result.username());
        assertEquals("peter", result.firstname());
        assertEquals("parker", result.lastname());

        verify(userRepository).save(user);
    }
}
