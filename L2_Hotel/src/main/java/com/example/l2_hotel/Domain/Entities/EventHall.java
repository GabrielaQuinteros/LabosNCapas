package com.example.l2_hotel.Domain.Entities;

import jakarta.persistence.*;
import lombok.*;
import java.util.UUID;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "event_hall")
public class EventHall {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID idEventHall;

    @Column(nullable = false, length = 100)
    private String name;

    @Column(nullable = false)
    private int capacity;

    @Column(columnDefinition = "TEXT")
    private String ornaments;

    @Column(columnDefinition = "TEXT")
    private String description;

    @ManyToOne
    @JoinColumn(name = "floor_id", nullable = false, foreignKey = @ForeignKey(name = "FK_eventhall_floor"))
    private Floor floor;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private EventHallState state = EventHallState.AVAILABLE;

    public enum EventHallState {
        AVAILABLE,
        OCCUPIED,
        MAINTENANCE
    }
}
