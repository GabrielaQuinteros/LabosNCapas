package com.example.l2_hotel.Repository;

import com.example.l2_hotel.Domain.Entities.GuestReward;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;
import java.time.LocalDate;

@Transactional
public interface iGuestRewardRepository extends iGenericRepository<GuestReward, UUID> {

    // JPA
    List<GuestReward> findByGuest_IdGuest(UUID idGuest);

    // Query Hibrida
    @Query("SELECT gr FROM GuestReward gr WHERE gr.redeemDate > :date")
    List<GuestReward> findByRedeemDateAfterJPQL(@Param("date") LocalDate date);

    // Query Nativa
    @Query(value = "SELECT * FROM guest_reward WHERE redeem_date > :date", nativeQuery = true)
    List<GuestReward> findByRedeemDateAfterNative(@Param("date") LocalDate date);
}
