package com.example.l2_hotel.Domain.Entities;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "payment_receipt")
public class PaymentReceipt {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID idPaymentReceipt;

    @OneToOne
    @JoinColumn(name = "reservation_id", nullable = false, unique = true, foreignKey = @ForeignKey(name = "FK_payment_receipt_reservation"))
    private Reservation reservation;

    @Column(name = "issue_date", nullable = false)
    private LocalDate issueDate = LocalDate.now();

    @Column(name = "total_amount", nullable = false, precision = 10, scale = 2)
    private BigDecimal totalAmount;
}
