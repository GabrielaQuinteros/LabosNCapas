package com.example.l2_hotel.Domain.Entities;

import jakarta.persistence.*;
import lombok.*;
import java.util.UUID;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "room")
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID idRoom;

    @Column(nullable = false, length = 100)
    private String name;

    @Column(name = "special_details", columnDefinition = "TEXT")
    private String specialDetails;

    @Column(columnDefinition = "TEXT")
    private String description;

    @ManyToOne
    @JoinColumn(name = "floor_id", nullable = false, foreignKey = @ForeignKey(name = "FK_room_floor"))
    private Floor floor;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private RoomState state = RoomState.AVAILABLE;

    public enum RoomState {
        AVAILABLE,
        OCCUPIED,
        MAINTENANCE
    }
}
