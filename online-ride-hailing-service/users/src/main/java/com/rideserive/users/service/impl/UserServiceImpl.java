package com.rideserive.users.service.impl;

import com.rideserive.users.dto.UserDTO;
import com.rideserive.users.entities.User;
import com.rideserive.users.exceptions.ResourceNotFoundException;
import com.rideserive.users.exceptions.UserAlreadyExistsException;
import com.rideserive.users.mapper.UserMapper;
import com.rideserive.users.repository.UserRepository;
import com.rideserive.users.service.UserService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository repo;
    private final UserMapper mapper;

    @Override
    public List<UserDTO> findAll() {
        return repo.findAll().stream().map(mapper::toDto).toList();
    }

    @Override
    public UserDTO findById(UUID id) {
        User user = repo.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("User", "Id", String.valueOf(id))
        );

        return mapper.toDto(user);
    }

    @Override
    @Transactional
    public void save(UserDTO dto) {
        if(repo.existsById(dto.id()))
            throw new UserAlreadyExistsException("User with id "+dto.id()+" already exists");

        repo.saveAndFlush(mapper.toEntity(dto));
    }

    @Override
    public void update(UserDTO dto) {
        User fetchedUser = repo.findById(dto.id()).orElseThrow(
                () -> new ResourceNotFoundException("User", "Id", String.valueOf(dto.id()))
        );

        mapper.updateEntity(fetchedUser, dto);
        repo.save(fetchedUser);
    }

    @Override
    public void delete(UUID id) {
        if(!repo.existsById(id))
            throw new ResourceNotFoundException("User", "Id", String.valueOf(id));
        repo.deleteById(id);
    }
}
