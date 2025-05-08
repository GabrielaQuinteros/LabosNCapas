package com.example.l2_hotel.Repository;

import com.example.l2_hotel.Domain.Entities.Role;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.UUID;

@Transactional
public interface iRoleRepository extends iGenericRepository<Role, UUID> {

    // JPA
    Role findByName(String name);

    // Query Hibrida
    @Query("SELECT r FROM Role r WHERE r.description LIKE %:desc%")
    Role findByDescriptionLikeJPQL(@Param("desc") String desc);

    // Query Nativa
    @Query(value = "SELECT * FROM role WHERE description ILIKE %:desc%", nativeQuery = true)
    Role findByDescriptionNative(@Param("desc") String desc);
}