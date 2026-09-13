package cephei.dev.auth_service.mapper;

import cephei.dev.auth_service.dto.AccountCreateDto;
import cephei.dev.auth_service.entity.Credentials;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CredentialsMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "role", ignore = true)
    Credentials toEntity(AccountCreateDto accountCreateDto);
}
