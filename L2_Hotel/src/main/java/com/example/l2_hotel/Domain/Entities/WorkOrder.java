package com.example.l2_hotel.Domain.Entities;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "work_order")
public class WorkOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID idWorkOrder;

    @ManyToOne
    @JoinColumn(name = "reporter_id", nullable = false, foreignKey = @ForeignKey(name = "FK_workorder_reporter"))
    private Employee reporter;

    @ManyToOne
    @JoinColumn(name = "room_id", foreignKey = @ForeignKey(name = "FK_workorder_room"))
    private Room room;

    @ManyToOne
    @JoinColumn(name = "event_hall_id", foreignKey = @ForeignKey(name = "FK_workorder_eventhall"))
    private EventHall eventHall;

    @Column(nullable = false)
    private LocalDate date;

    @Column(nullable = false)
    private LocalTime time;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private WorkOrderStatus status = WorkOrderStatus.PENDING;

    public enum WorkOrderStatus {
        PENDING,
        IN_PROCESS,
        COMPLETED
    }
}
