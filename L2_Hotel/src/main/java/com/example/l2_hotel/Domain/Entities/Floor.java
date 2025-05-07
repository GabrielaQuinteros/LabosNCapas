package com.example.l2_hotel.Domain.Entities;

import jakarta.persistence.*;
import lombok.*;
import java.util.UUID;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(
        name = "floor",
        uniqueConstraints = @UniqueConstraint(columnNames = {"branch_id", "floor_number"}, name = "UK_floor_branch_number")
)
public class Floor {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID idFloor;

    @Column(nullable = false, length = 100)
    private String name;

    @ManyToOne
    @JoinColumn(name = "color_id", nullable = false, foreignKey = @ForeignKey(name = "FK_floor_color"))
    private FloorColor color;

    @Column(name = "floor_number", nullable = false)
    private int floorNumber;

    @ManyToOne
    @JoinColumn(name = "branch_id", nullable = false, foreignKey = @ForeignKey(name = "FK_floor_branch"))
    private Branch branch;
}
