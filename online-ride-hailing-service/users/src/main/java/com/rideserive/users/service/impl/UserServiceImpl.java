package com.rideserive.users.service.impl;

import com.rideserive.users.dto.DriverRegistrationDTO;
import com.rideserive.users.dto.PassengerRegistrationDTO;
import com.rideserive.users.dto.UserRegistrationDTO;
import com.rideserive.users.entities.User;
import com.rideserive.users.exceptions.UserAlreadyExistsException;
import com.rideserive.users.mapper.UserMapper;
import com.rideserive.users.repository.UserRepository;
import com.rideserive.users.service.DriverService;
import com.rideserive.users.service.KeycloakService;
import com.rideserive.users.service.PassengerService;
import com.rideserive.users.service.UserService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository repo;
    private final UserMapper mapper;
    private final DriverService driverService;
    private final PassengerService passengerService;
    private final KeycloakService keycloakService;

//    @Override
//    public List<UserDTO> findAll() {
//        return repo.findAll().stream().map(mapper::toDto).toList();
//    }
//
//    @Override
//    public UserDTO findById(UUID id) {
//        User user = repo.findById(id).orElseThrow(
//                () -> new ResourceNotFoundException("User", "Id", String.valueOf(id))
//        );
//
//        return mapper.toDto(user);
//    }

    @Override
    @Transactional
    public void save(UserRegistrationDTO dto) {
        if(repo.existsByEmail(dto.getEmail())){
            log.error("User cannot be registered with the email address: {}", dto.getEmail());
            throw new UserAlreadyExistsException("User with id "+dto.getEmail()+" already exists");
        }

        String keycloakId = keycloakService.createUserInKeycloak(dto);
        User newUser = mapper.toEntity(dto, UUID.fromString(keycloakId));
        newUser = repo.saveAndFlush(newUser);
        log.info("Saved user: {}", newUser);

        if(dto instanceof DriverRegistrationDTO driverRegistrationDTO){
            driverService.save(newUser, driverRegistrationDTO);
            passengerService.save(newUser,new PassengerRegistrationDTO());
            log.info("Saved driver: {}", newUser);

            keycloakService.assignRoles(keycloakId, List.of("DRIVER", "PASSENGER"));
            log.info("Assigned role for driver: {}", newUser);
        }
        if(dto instanceof PassengerRegistrationDTO passengerRegistrationDTO){
            passengerService.save(newUser, passengerRegistrationDTO);
            log.info("Saved passenger: {}", newUser);

            keycloakService.assignRoles(keycloakId, List.of("PASSENGER"));
            log.info("Assigned role for driver: {}", newUser);
        }
    }

//    @Override
//    public void update(UserDTO dto) {
//        User fetchedUser = repo.findByEmail(dto.email()).orElseThrow(
//                () -> new ResourceNotFoundException("User", "Email", dto.email())
//        );
//
//        mapper.updateEntity(fetchedUser, dto);
//        repo.save(fetchedUser);
//    }
//
//    @Override
//    public void delete(UUID id) {
//        if(!repo.existsById(id))
//            throw new ResourceNotFoundException("User", "Id", String.valueOf(id));
//        repo.deleteById(id);
//    }
}
