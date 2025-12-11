package com.rideserive.users.service;

import com.rideserive.users.dto.UserDTO;
import com.rideserive.users.dto.UserRegistrationDTO;
import com.rideserive.users.entities.User;


public interface PassengerService {

    void save(User user, UserRegistrationDTO dto);

}
