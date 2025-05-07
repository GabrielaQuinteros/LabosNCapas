package com.example.l2_hotel.Domain.Entities;

import jakarta.persistence.*;
import lombok.*;
import java.util.UUID;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "reward")
public class Reward {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID idReward;

    @Column(nullable = false, length = 100)
    private String name;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(name = "point_cost", nullable = false)
    private int pointCost;

    @Column(nullable = false)
    private boolean available = true;
}
