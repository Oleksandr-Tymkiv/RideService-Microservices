package com.rideserive.users.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Table(name = "users")
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "user_id", columnDefinition = "uuid")
    private UUID id;

    @Column(name = "keycloak_id", nullable = false, unique = true, columnDefinition = "uuid")
    private UUID keycloakId;

    @Column(name = "user_first_name")
    private String firstName;

    @Column(name = "user_last_name")
    private String lastName;

    @Column(name = "user_email")
    private String email;

    @Column(name = "user_phonenumber")
    private String phoneNumber;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private Driver driverProfile;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private Passenger passengerProfile;
}
