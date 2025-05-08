package com.example.l2_hotel.Repository;

import com.example.l2_hotel.Domain.Entities.WorkOrder;
import com.example.l2_hotel.Domain.Entities.WorkOrder.WorkOrderStatus;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.UUID;

@Transactional
public interface iWorkOrderRepository extends iGenericRepository<WorkOrder, UUID> {

    // Query derivada
    WorkOrder findByDate(LocalDate date);

    // Query híbrida
    @Query("SELECT w FROM WorkOrder w WHERE w.status = :status")
    WorkOrder findByStatusJPQL(@Param("status") WorkOrderStatus status);

    // Query nativa
    @Query(value = "SELECT * FROM work_order WHERE estado = :status", nativeQuery = true)
    WorkOrder findByStatusNative(@Param("status") String status);
}
