package com.saksoft.repository;

import com.saksoft.entity.EmployeeManagement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeManagementRepository extends JpaRepository<EmployeeManagement, Long> {
}
