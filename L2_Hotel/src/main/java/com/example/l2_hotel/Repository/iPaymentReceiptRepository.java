package com.example.l2_hotel.Repository;

import com.example.l2_hotel.Domain.Entities.PaymentReceipt;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Transactional
public interface iPaymentReceiptRepository extends iGenericRepository<PaymentReceipt, UUID> {

    // JPA
    PaymentReceipt findByIssueDate(LocalDate issueDate);

    // Query Hibrida
    @Query("SELECT p FROM PaymentReceipt p WHERE p.totalAmount = :amount")
    PaymentReceipt findByTotalAmountJPQL(@Param("amount") BigDecimal amount);

    // Query Nativa
    @Query(value = "SELECT * FROM payment_receipt WHERE total_amount = :amount", nativeQuery = true)
    PaymentReceipt findByTotalAmountNative(@Param("amount") BigDecimal amount);
}
