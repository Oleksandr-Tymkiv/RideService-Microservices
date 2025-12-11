package com.rideserive.users.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Table(name = "passengers")
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Passenger {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "passenger_id")
    private UUID id;

    @OneToOne
    @MapsId
    @JoinColumn(name = "passenger_id")
    private User user;

    @Column(name = "passenger_rating")
    @Builder.Default
    private Double rating = 5.0;
}
