package com.saksoft.service.impl;

import com.saksoft.dto.EmployeeManagementDTO;
import com.saksoft.entity.EmployeeManagement;
import com.saksoft.exception.EMException;
import com.saksoft.repository.EmployeeManagementRepository;
import com.saksoft.service.EmployeeManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeManagementServiceImpl implements EmployeeManagementService {

    @Autowired
    private EmployeeManagementRepository employeeManagementRepository;

    @Value("${ems.employee.not.found.code}")
    private String empNotFoundCode;
    @Value("${ems.employee.not.found.message}")
    private String empNotFoundMessage;
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

    @Override
    public EmployeeManagementDTO getEmployeeById(Long id) throws EMException {
        Optional<EmployeeManagement> employee = employeeManagementRepository.findById(id);
        if(employee.isEmpty()){
            throw new EMException(empNotFoundCode, empNotFoundMessage, HttpStatus.NOT_FOUND);
        }
        return new EmployeeManagementDTO(employee.get());
    }
}
