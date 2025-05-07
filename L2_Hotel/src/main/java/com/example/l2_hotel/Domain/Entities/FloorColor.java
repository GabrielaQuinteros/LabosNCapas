package com.example.l2_hotel.Domain.Entities;

import jakarta.persistence.*;
import lombok.*;
import java.util.UUID;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "floor_color", uniqueConstraints = @UniqueConstraint(columnNames = "name", name = "UK_floorcolor_name"))
public class FloorColor {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID idFloorColor;

    @Column(nullable = false, length = 50)
    private String name;

    @Column(name = "magic_number", nullable = false)
    private int magicNumber;
}
