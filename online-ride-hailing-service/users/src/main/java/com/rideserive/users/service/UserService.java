package com.rideserive.users.service;

import com.rideserive.users.dto.UserDTO;

import java.util.List;
import java.util.UUID;

public interface UserService {

    List<UserDTO> findAll();

    UserDTO findById(UUID id);

    void save(UserDTO user);

    void update(UserDTO user);

    void delete(UUID id);
}
