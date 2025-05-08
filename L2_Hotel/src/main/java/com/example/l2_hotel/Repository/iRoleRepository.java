package com.example.l2_hotel.Repository;

import com.example.l2_hotel.Domain.Entities.Role;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.UUID;
import java.util.List;

@Transactional
public interface iRoleRepository extends iGenericRepository<Role, UUID> {

    // JPA
    Role findByTitle(String title);

    // Query Hibrida
    @Query("SELECT r FROM Role r WHERE r.department = :department")
    List<Role> findByDepartmentJPQL(@Param("department") String department);

    // Query Nativa
    @Query(value = "SELECT * FROM role WHERE department = :department", nativeQuery = true)
    List<Role> findByDepartmentNative(@Param("department") String department);
}
