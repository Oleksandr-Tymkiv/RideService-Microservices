package com.rideserive.users.dto;

import jakarta.validation.constraints.NotNull;

public class DriverRegistrationDTO extends UserRegistrationDTO {
    @NotNull
    public String carModel;
    @NotNull
    public String carNumber;
}
