package com.rideserive.users.service;

import com.rideserive.users.dto.UserRegistrationDTO;

import java.util.List;

public interface KeycloakService {
    String createUserInKeycloak(UserRegistrationDTO userRegistrationDTO);

    void assignRoles(String userId, List<String> roleNames);
}
