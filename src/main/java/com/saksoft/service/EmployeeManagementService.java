package com.saksoft.service;

import com.saksoft.dto.EmployeeManagementDTO;
import com.saksoft.dto.PaginationDTO;
import com.saksoft.entity.EmployeeManagement;
import com.saksoft.exception.EMException;

import java.util.List;
import java.util.Map;

public interface EmployeeManagementService {
    EmployeeManagementDTO saveEmployee(EmployeeManagementDTO employeeManagementDTO);

    List<EmployeeManagementDTO> getAllEmployee();

    EmployeeManagementDTO getEmployeeById(Long id) throws EMException;

    EmployeeManagementDTO updateEmployee(Long id, EmployeeManagementDTO employeeManagementDTO) throws EMException;

    String deleteEmployee(Long id) throws EMException;

    EmployeeManagementDTO updateEmployeeByFields(Long id, Map<String, Object> fields) throws EMException;

    Map<String, Object> getAllEmployeeByPagination(PaginationDTO paginationDTO);

    List<EmployeeManagement> getDummyData();

}
