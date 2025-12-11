package com.rideserive.users.service.impl;

import com.rideserive.users.dto.UserRegistrationDTO;
import com.rideserive.users.service.KeycloakService;
import com.rideserive.users.service.UserService;
import jakarta.ws.rs.core.Response;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.query.Page;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.resource.RolesResource;
import org.keycloak.admin.client.resource.UsersResource;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.RoleRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class KeycloakServiceImpl implements KeycloakService {

    private final Keycloak keycloak;

    @Value("${keycloak.realm}")
    private String targetRealm;

    @Override
    public String createUserInKeycloak(UserRegistrationDTO userRegistrationDTO) {
        UserRepresentation user = getUserRepresentation(userRegistrationDTO);

        UsersResource usersResource = keycloak.realm(targetRealm).users();

        try(Response response = usersResource.create(user)) {

            if(response.getStatus() == 201){
                String path = response.getLocation().getPath();

                return path.substring(path.lastIndexOf('/') + 1);
            } else {
                throw new RuntimeException("Failed to create user in Keycloak. Status: " + response.getStatus());
            }

        }
    }

    private UserRepresentation getUserRepresentation(UserRegistrationDTO userRegistrationDTO) {
        UserRepresentation user = new UserRepresentation();
        user.setEnabled(true);
        user.setUsername(userRegistrationDTO.getEmail());
        user.setEmail(userRegistrationDTO.getEmail());
        user.setFirstName(userRegistrationDTO.getFirstName());
        user.setLastName(userRegistrationDTO.getLastName());
        user.setEmailVerified(true);

        CredentialRepresentation credential = new CredentialRepresentation();
        credential.setType(CredentialRepresentation.PASSWORD);
        credential.setValue(userRegistrationDTO.getPassword());
        credential.setTemporary(false);
        user.setCredentials(List.of(credential));
        return user;
    }

    @Override
    public void assignRoles(String userId, List<String> roleNames) {
        RolesResource rolesResource = keycloak.realm(targetRealm).roles();

        List<RoleRepresentation> roles = new ArrayList<>();
        for(String roleName : roleNames){
            try{
                RoleRepresentation role = rolesResource.get(roleName).toRepresentation();
                roles.add(role);
            } catch(Exception e){
                log.error("Role not found in Keycloak: {}", roleName);
                throw new RuntimeException("System role not configured: " + roleName);
            }
        }

        if(!roles.isEmpty())
            keycloak.realm(targetRealm)
                    .users()
                    .get(userId)
                    .roles()
                    .realmLevel()
                    .add(roles);
    }
}