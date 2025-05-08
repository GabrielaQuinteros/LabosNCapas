package com.example.l2_hotel.Repository;

import com.example.l2_hotel.Domain.Entities.GuestPoints;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.UUID;

@Transactional
public interface iGuestPointsRepository extends iGenericRepository<GuestPoints, UUID> {

    // JPA
    GuestPoints findByGuest_IdGuest(UUID guestId);

    // Query Hibrida
    @Query("SELECT gp FROM GuestPoints gp WHERE gp.amount >= :minPoints")
    GuestPoints findByMinPointsJPQL(@Param("minPoints") int minPoints);

    // Query Nativa
    @Query(value = "SELECT * FROM guest_points WHERE cantidad >= :minPoints", nativeQuery = true)
    GuestPoints findByMinPointsNative(@Param("minPoints") int minPoints);
}