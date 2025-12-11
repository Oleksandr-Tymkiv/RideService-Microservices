package com.rideserive.users.service.impl;

import com.rideserive.users.dto.UserDTO;
import com.rideserive.users.dto.UserRegistrationDTO;
import com.rideserive.users.entities.Driver;
import com.rideserive.users.entities.User;
import com.rideserive.users.mapper.DriverMapper;
import com.rideserive.users.repository.DriverRepository;
import com.rideserive.users.service.DriverService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class DriverServiceImpl implements DriverService {

    private final DriverRepository driverRepository;
    private final DriverMapper driverMapper;

    @Override
    public void save(User user, UserRegistrationDTO dto) {
//        Driver newDriver = driverMapper.userDtoToDriver(dto);
//        driverRepository.save();
    }
}
