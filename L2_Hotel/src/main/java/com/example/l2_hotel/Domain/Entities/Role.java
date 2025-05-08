package com.example.l2_hotel.Domain.Entities;

import jakarta.persistence.*;
import lombok.*;
import java.util.UUID;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "role")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID idRole;

    @Column(nullable = false, length = 50)
    private String title;

    @Column(nullable = false, length = 100)
    private String department; 
}
