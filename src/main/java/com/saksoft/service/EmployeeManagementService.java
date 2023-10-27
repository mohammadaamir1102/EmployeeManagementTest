package com.saksoft.service;

import com.saksoft.dto.EmployeeManagementDTO;
import com.saksoft.exception.EMException;

import java.util.List;

public interface EmployeeManagementService {
    EmployeeManagementDTO saveEmployee(EmployeeManagementDTO employeeManagementDTO);
    List<EmployeeManagementDTO> getAllEmployee();
    EmployeeManagementDTO getEmployeeById(Long id) throws EMException;
}
