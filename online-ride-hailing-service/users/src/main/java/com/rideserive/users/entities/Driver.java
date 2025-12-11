package com.rideserive.users.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Table(name = "drivers")
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Driver {

    @Id
    @Column(name = "driver_id")
    private UUID id;

    @OneToOne
    @MapsId
    @JoinColumn(name = "driver_id")
    private User user;

    @Column(name = "driver_rating")
    @Builder.Default
    private Double rating = 5.0;

    @Column(name = "driver_car_model", nullable = false)
    private String carModel;

    @Column(name = "driver_car_number", nullable = false)
    private String carNumber;

}
