package com.example.l2_hotel.Repository;

import com.example.l2_hotel.Domain.Entities.FloorColor;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.UUID;

@Transactional
public interface iFloorColorRepository extends iGenericRepository<FloorColor, UUID> {

    // JPA
    FloorColor findByName(String name);

    // Query Hibrida
    @Query("SELECT c FROM FloorColor c WHERE c.magicNumber = :number")
    FloorColor findByMagicNumberJPQL(@Param("number") int number);

    // Query Nativa
    @Query(value = "SELECT * FROM floor_color WHERE numero_magico = :number", nativeQuery = true)
    FloorColor findByMagicNumberNative(@Param("number") int number);
}