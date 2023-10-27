package com.saksoft.service.impl;

import com.saksoft.dto.EmployeeManagementDTO;
import com.saksoft.entity.EmployeeManagement;
import com.saksoft.repository.EmployeeManagementRepository;
import com.saksoft.service.EmployeeManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class EmployeeManagementServiceImpl implements EmployeeManagementService {

    @Autowired
    private EmployeeManagementRepository employeeManagementRepository;
    @Override
    public EmployeeManagementDTO saveEmployee(EmployeeManagementDTO employeeManagementDTO) {
        EmployeeManagement savedEmployee =
                employeeManagementRepository.save(new EmployeeManagement(employeeManagementDTO));
        return new EmployeeManagementDTO(savedEmployee);
    }

    @Override
    public List<EmployeeManagementDTO> getAllEmployee() {
        List<EmployeeManagement> allEmployee = employeeManagementRepository.findAll();
        return allEmployee.stream().map(EmployeeManagementDTO::new).toList();
    }
}
