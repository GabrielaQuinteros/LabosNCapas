package com.example.l2_hotel.Domain.Entities;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "guest_reward")
public class GuestReward {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID idGuestReward;

    @ManyToOne
    @JoinColumn(name = "guest_id", nullable = false, foreignKey = @ForeignKey(name = "FK_guestreward_guest"))
    private Guest guest;

    @ManyToOne
    @JoinColumn(name = "reward_id", nullable = false, foreignKey = @ForeignKey(name = "FK_guestreward_reward"))
    private Reward reward;

    @Column(name = "redeem_date", nullable = false)
    private LocalDate redeemDate = LocalDate.now();

    @Column(name = "points_used", nullable = false)
    private int pointsUsed;
}
