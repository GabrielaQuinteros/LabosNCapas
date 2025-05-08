package com.example.l2_hotel.Repository;

import com.example.l2_hotel.Domain.Entities.Employee;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

@Transactional
public interface iEmployeeRepository extends iGenericRepository<Employee, UUID> {

    // JPA
    List<Employee> findByActive(boolean active);

    // Query Hibrida
    @Query("SELECT e FROM Employee e WHERE e.role.title = :title")
    List<Employee> findByRoleTitleJPQL(@Param("title") String title);

    // Query Nativa
    @Query(value = "SELECT * FROM employee e JOIN role r ON e.role_id = r.id_role WHERE r.title = :title", nativeQuery = true)
    List<Employee> findByRoleTitleNative(@Param("title") String title);
}
