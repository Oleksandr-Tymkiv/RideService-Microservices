package com.rideserive.users.dto;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.Data;
import lombok.ToString;

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "role",
        visible = true
)
@JsonSubTypes({
        @JsonSubTypes.Type(value = PassengerRegistrationDTO.class, name = "PASSENGER"),
        @JsonSubTypes.Type(value = DriverRegistrationDTO.class, name = "DRIVER")
})
@Data
public abstract class UserRegistrationDTO {
    private String email;
    @ToString.Exclude
    private String password;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String role;
}