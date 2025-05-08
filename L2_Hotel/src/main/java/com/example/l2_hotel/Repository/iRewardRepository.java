package com.example.l2_hotel.Repository;

import com.example.l2_hotel.Domain.Entities.Reward;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.UUID;

@Transactional
public interface iRewardRepository extends iGenericRepository<Reward, UUID> {

    // JPA
    Reward findByName(String name);

    // Query Hibrida
    @Query("SELECT r FROM Reward r WHERE r.pointCost <= :maxPoints")
    Reward findAffordableJPQL(@Param("maxPoints") int maxPoints);

    // Query Nativa
    @Query(value = "SELECT * FROM reward WHERE coste_puntos <= :maxPoints", nativeQuery = true)
    Reward findAffordableNative(@Param("maxPoints") int maxPoints);
}