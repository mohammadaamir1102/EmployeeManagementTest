package com.saksoft.service;

import com.saksoft.dto.EmployeeManagementDTO;

import java.util.List;

public interface EmployeeManagementService {
    EmployeeManagementDTO saveEmployee(EmployeeManagementDTO employeeManagementDTO);
    List<EmployeeManagementDTO> getAllEmployee();
}
