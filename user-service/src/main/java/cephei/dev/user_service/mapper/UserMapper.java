package cephei.dev.user_service.mapper;

import cephei.dev.user_service.dto.UserCreateDto;
import cephei.dev.user_service.dto.UserReadDto;
import cephei.dev.user_service.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(source = "profile.firstname", target = "firstname")
    @Mapping(source = "profile.lastname", target = "lastname")
    UserReadDto toReadDto(User user);

    @Mapping(target = "id", ignore = true)
    User toEntity(UserCreateDto userCreateDto);
}
