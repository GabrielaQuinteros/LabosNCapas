package com.example.l2_hotel.Repository;

import com.example.l2_hotel.Domain.Entities.Guest;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.UUID;

@Transactional
public interface iGuestRepository extends iGenericRepository<Guest, UUID> {

    // JPA
    Guest findByUser_Email(String email);

    // Query Hibrida
    @Query("SELECT g FROM Guest g WHERE g.user.firstName = :name")
    Guest findByFirstNameJPQL(@Param("name") String name);

    // Query Nativa
    @Query(value = "SELECT * FROM guest g JOIN user_data u ON g.user_id = u.id_user WHERE u.first_name = :name", nativeQuery = true)
    Guest findByFirstNameNative(@Param("name") String name);
}