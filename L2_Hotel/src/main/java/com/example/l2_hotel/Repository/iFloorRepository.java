package com.example.l2_hotel.Repository;

import com.example.l2_hotel.Domain.Entities.Floor;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.UUID;

@Transactional
public interface iFloorRepository extends iGenericRepository<Floor, UUID> {

    // JPA
    Floor findByName(String name);

    // Query Hibrida
    @Query("SELECT f FROM Floor f WHERE f.floorNumber = :number AND f.branch.name = :branchName")
    Floor findByNumberAndBranchJPQL(@Param("number") int number, @Param("branchName") String branchName);

    // Query Nativa
    @Query(value = "SELECT * FROM floor f JOIN branch b ON f.branch_id = b.id_branch WHERE f.floor_number = :number AND b.name = :branchName", nativeQuery = true)
    Floor findByNumberAndBranchNative(@Param("number") int number, @Param("branchName") String branchName);
}