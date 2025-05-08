package com.example.l2_hotel.Repository;

import com.example.l2_hotel.Domain.Entities.Branch;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.UUID;

@Transactional
public interface iBranchRepository extends iGenericRepository<Branch, UUID> {

    // JPA
    Branch findByName(String name);

    // Query Hibrida
    @Query("SELECT b FROM Branch b WHERE b.location = :location")
    Branch findByLocationJPQL(@Param("location") String location);

    // Query Nativa
    @Query(value = "SELECT * FROM branch WHERE ubicacion = :location", nativeQuery = true)
    Branch findByLocationNative(@Param("location") String location);
}