package com.example.l2_hotel.Repository;

import com.example.l2_hotel.Domain.Entities.Reservation;
import com.example.l2_hotel.Domain.Entities.Reservation.ReservationStatus;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.UUID;

@Transactional
public interface iReservationRepository extends iGenericRepository<Reservation, UUID> {

    // JPA
    Reservation findByCheckInDate(LocalDate checkInDate);

    // Query Hibrida
    @Query("SELECT r FROM Reservation r WHERE r.status = :status")
    Reservation findByStatusJPQL(@Param("status") ReservationStatus status);

    // Query Nativa
    @Query(value = "SELECT * FROM reservation WHERE estado = :status", nativeQuery = true)
    Reservation findByStatusNative(@Param("status") String status);
}

