package com.example.l2_hotel.Domain.Entities;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "guest_points")
public class GuestPoints {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID idGuestPoints;

    @Column(nullable = false)
    private int amount;

    @ManyToOne
    @JoinColumn(name = "guest_id", nullable = false, foreignKey = @ForeignKey(name = "FK_guestpoints_guest"))
    private Guest guest;

    @Column(name = "grant_date", nullable = false)
    private LocalDate grantDate = LocalDate.now();

    @ManyToOne
    @JoinColumn(name = "granted_by_id", nullable = false, foreignKey = @ForeignKey(name = "FK_guestpoints_employee"))
    private Employee grantedBy;

    @Column(length = 255)
    private String reason;
}
