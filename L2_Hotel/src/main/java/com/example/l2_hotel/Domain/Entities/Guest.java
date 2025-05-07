package com.example.l2_hotel.Domain.Entities;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "guest")
public class Guest {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID idGuest;

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false, unique = true, foreignKey = @ForeignKey(name = "FK_guest_user"))
    private User user;

    @Column(name = "registration_date", nullable = false)
    private LocalDate registrationDate = LocalDate.now();
}
