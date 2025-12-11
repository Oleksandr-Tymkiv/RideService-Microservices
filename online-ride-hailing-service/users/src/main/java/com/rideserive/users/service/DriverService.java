package com.rideserive.users.service;

import com.rideserive.users.dto.UserDTO;
import com.rideserive.users.dto.UserRegistrationDTO;
import com.rideserive.users.entities.User;

import java.util.UUID;

public interface DriverService {

    void save(User user, UserRegistrationDTO dto);

}