package com.example.l2_hotel.Repository;

import com.example.l2_hotel.Domain.Entities.EventHall;
import com.example.l2_hotel.Domain.Entities.EventHall.EventHallState;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.UUID;

@Transactional
public interface iEventHallRepository extends iGenericRepository<EventHall, UUID> {

    // JPA
    EventHall findByName(String name);

    // Query Hibrida
    @Query("SELECT s FROM EventHall s WHERE s.state = :state")
    EventHall findByStateJPQL(@Param("state") EventHallState state);

    // Query Nativa
    @Query(value = "SELECT * FROM event_hall WHERE estado = :state", nativeQuery = true)
    EventHall findByStateNative(@Param("state") String state);
}

