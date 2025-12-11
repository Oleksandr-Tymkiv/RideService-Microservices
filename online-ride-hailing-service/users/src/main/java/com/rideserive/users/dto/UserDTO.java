package com.rideserive.users.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;

import java.util.UUID;

@Builder
public record UserDTO(@NotEmpty(message = "You must to enter your first name !")
                      String firstName,
                      @NotEmpty(message = "You must to enter your last name !")
                      String lastName,
                      @Email(message = "You used invalid format for email !")
                      @NotEmpty(message = "You must to enter your email !")
                      String email,
                      @NotEmpty(message = "You must to enter your phonenumber!")
                      String phoneNumber
) { }
