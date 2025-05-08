package com.example.l2_hotel.Repository;

import com.example.l2_hotel.Domain.Entities.User;
import com.example.l2_hotel.Domain.Entities.User.UserType;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.UUID;

@Transactional
public interface iUserRepository extends iGenericRepository<User, UUID> {

    // JPA
    User findByFirstName(String firstName);

    // Query Hibrida
    @Query("SELECT u FROM User u WHERE u.type = :type")
    User findByUserType(@Param("type") UserType type);

    // Query Nativa
    @Query(value = "SELECT * FROM user_data WHERE type = :type", nativeQuery = true)
    User findByUserTypeNative(@Param("type") String type);
}

