package com.rideserive.users.mapper;

//import com.rideserive.users.dto.DriverDto;
import com.rideserive.users.dto.UserDTO;
import com.rideserive.users.entities.Driver;
import com.rideserive.users.entities.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface DriverMapper {

    Driver userDtoToDriver(UserDTO dto);

//    DriverDto driverToDto(Driver driver);
}
