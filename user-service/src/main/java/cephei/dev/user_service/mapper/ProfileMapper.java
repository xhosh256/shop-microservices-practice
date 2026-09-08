package cephei.dev.user_service.mapper;

import cephei.dev.user_service.dto.UserCreateDto;
import cephei.dev.user_service.entity.Profile;
import cephei.dev.user_service.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ProfileMapper {

    @Mapping(target = "id", ignore = true)
    Profile toEntity(UserCreateDto userCreateDto);
}
