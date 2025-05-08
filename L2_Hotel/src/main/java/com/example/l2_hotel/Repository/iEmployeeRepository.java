package com.example.l2_hotel.Repository;

import com.example.l2_hotel.Domain.Entities.Employee;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.UUID;

@Transactional
public interface iEmployeeRepository extends iGenericRepository<Employee, UUID> {

    // JPA
    Employee findByUser_Email(String email);

    // Query Hibrida
    @Query("SELECT e FROM Employee e WHERE e.role.name = :roleName")
    Employee findByRoleNameJPQL(@Param("roleName") String roleName);

    // Query Nativa
    @Query(value = "SELECT e.* FROM employee e JOIN role r ON e.role_id = r.id_role WHERE r.name = :roleName", nativeQuery = true)
    Employee findByRoleNameNative(@Param("roleName") String roleName);
}