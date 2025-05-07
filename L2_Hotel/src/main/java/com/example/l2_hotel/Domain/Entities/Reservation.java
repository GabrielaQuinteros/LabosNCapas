package com.example.l2_hotel.Domain.Entities;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "reservation")
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID idReservation;

    @ManyToOne
    @JoinColumn(name = "guest_id", nullable = false, foreignKey = @ForeignKey(name = "FK_reservation_guest"))
    private Guest guest;

    @ManyToOne
    @JoinColumn(name = "room_id", foreignKey = @ForeignKey(name = "FK_reservation_room"))
    private Room room;

    @ManyToOne
    @JoinColumn(name = "event_hall_id", foreignKey = @ForeignKey(name = "FK_reservation_eventhall"))
    private EventHall eventHall;

    @Column(name = "check_in_date", nullable = false)
    private LocalDate checkInDate;

    @Column(name = "check_out_date", nullable = false)
    private LocalDate checkOutDate;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private ReservationStatus status = ReservationStatus.ACTIVE;

    public enum ReservationStatus {
        ACTIVE,
        COMPLETED,
        CANCELLED
    }
}
