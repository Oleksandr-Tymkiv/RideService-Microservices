package com.rideserive.users.mapper;


import com.rideserive.users.dto.UserDTO;
import com.rideserive.users.entities.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface UserMapper {

    User toEntity(UserDTO dto);

    UserDTO toDto(User user);

    @Mapping(target = "id", ignore = true)
    void updateEntity(@MappingTarget User user, UserDTO dto);
}
