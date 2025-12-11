package com.rideserive.users.mapper;

import com.rideserive.users.dto.UserDTO;
import com.rideserive.users.dto.UserRegistrationDTO;
import com.rideserive.users.entities.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Mappings;

import java.util.UUID;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(target = "keycloakId", source = "keycloakId")
    User toEntity(UserRegistrationDTO dto, UUID keycloakId);

    UserDTO toDto(User user);

    @Mapping(target = "id", ignore = true)
    void updateEntity(@MappingTarget User user, UserDTO dto);
}
