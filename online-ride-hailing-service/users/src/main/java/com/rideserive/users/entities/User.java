package com.rideserive.users.entities;

import com.rideserive.users.role.Roles;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Table
@Entity
@SequenceGenerator(
        name = "user_seq",
        sequenceName = "user_seq",
        allocationSize = 1
)
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class User {

    @Id
    @GeneratedValue(
            generator = "user_seq",
            strategy = GenerationType.SEQUENCE
    )
    @Column(name = "user_id")
    private UUID id;

    @Column(name = "user_first_name")
    private String firstName;

    @Column(name = "user_last_name")
    private String lastName;

    @Column(name = "user_email")
    private String email;

    @Column(name = "user_password")
    private String password;

    @Column(name = "user_role")
    @Enumerated(EnumType.STRING)
    private Roles role;
}
