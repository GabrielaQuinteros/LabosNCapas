package com.example.l2_hotel.Repository;

import com.example.l2_hotel.Domain.Entities.Room;
import com.example.l2_hotel.Domain.Entities.Room.RoomState;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.UUID;

@Transactional
public interface iRoomRepository extends iGenericRepository<Room, UUID> {

    // Query derivada
    Room findByName(String name);

    // Query híbrida
    @Query("SELECT r FROM Room r WHERE r.state = :state")
    Room findByStateJPQL(@Param("state") RoomState state);

    // Query nativa
    @Query(value = "SELECT * FROM room WHERE estado = :state", nativeQuery = true)
    Room findByStateNative(@Param("state") String state);
}
