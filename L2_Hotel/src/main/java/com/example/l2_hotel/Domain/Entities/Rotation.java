package com.example.l2_hotel.Domain.Entities;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(
        name = "rotation",
        uniqueConstraints = @UniqueConstraint(
                columnNames = {"employee_id", "floor_id", "month"},
                name = "UK_rotation_employee_floor_month"
        )
)
public class Rotation {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID idRotation;

    @ManyToOne
    @JoinColumn(name = "employee_id", nullable = false, foreignKey = @ForeignKey(name = "FK_rotation_employee"))
    private Employee employee;

    @ManyToOne
    @JoinColumn(name = "floor_id", nullable = false, foreignKey = @ForeignKey(name = "FK_rotation_floor"))
    private Floor floor;

    @Column(nullable = false)
    private LocalDate month;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 10)
    private Shift shift;

    @Enumerated(EnumType.STRING)
    @Column(name = "rotation_status", nullable = false, length = 10)
    private RotationStatus rotationStatus = RotationStatus.ACTIVE;

    public enum Shift {
        MORNING,
        AFTERNOON,
        NIGHT
    }

    public enum RotationStatus {
        ACTIVE,
        INACTIVE
    }
}
