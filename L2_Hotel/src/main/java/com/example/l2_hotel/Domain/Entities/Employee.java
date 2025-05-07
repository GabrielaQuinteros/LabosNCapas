package com.example.l2_hotel.Domain.Entities;

import jakarta.persistence.*;
import lombok.*;
import java.util.UUID;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "employee")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false, unique = true, foreignKey = @ForeignKey(name = "FK_employee_user"))
    private User user;

    @ManyToOne
    @JoinColumn(name = "role_id", nullable = false, foreignKey = @ForeignKey(name = "FK_employee_role"))
    private Role role;

    @ManyToOne
    @JoinColumn(name = "branch_id", nullable = false, foreignKey = @ForeignKey(name = "FK_employee_branch"))
    private Branch branch;

    @ManyToOne
    @JoinColumn(name = "supervisor_id", foreignKey = @ForeignKey(name = "FK_employee_supervisor"))
    private Employee supervisor;

    @Column(name = "is_coordinator", nullable = false)
    private boolean isCoordinator = false;

    @Column(length = 100)
    private String department;

    @Column(nullable = false)
    private boolean active = true;
}
