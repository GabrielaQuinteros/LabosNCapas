package com.example.l2_hotel.Repository;

import com.example.l2_hotel.Domain.Entities.Rotation;
import com.example.l2_hotel.Domain.Entities.Rotation.RotationStatus;
import com.example.l2_hotel.Domain.Entities.Rotation.Shift;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.UUID;

@Transactional
public interface iRotationRepository extends iGenericRepository<Rotation, UUID> {

    // JPA
    Rotation findByMonth(LocalDate month);

    // Query Hibrida
    @Query("SELECT r FROM Rotation r WHERE r.shift = :shift AND r.rotationStatus = :status")
    Rotation findByShiftAndStatusJPQL(@Param("shift") Shift shift, @Param("status") RotationStatus status);

    // Query Nativa
    @Query(value = "SELECT * FROM rotation WHERE turno = :shift AND rotation_status = :status", nativeQuery = true)
    Rotation findByShiftAndStatusNative(@Param("shift") String shift, @Param("status") String status);
}
