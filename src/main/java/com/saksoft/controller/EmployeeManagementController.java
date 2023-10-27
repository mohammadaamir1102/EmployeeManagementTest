package com.saksoft.controller;

import com.saksoft.dto.EmployeeManagementDTO;
import com.saksoft.service.EmployeeManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employee")
public class EmployeeManagementController {

    @Autowired
    private EmployeeManagementService employeeManagementService;

    @PostMapping("/")
    public ResponseEntity<EmployeeManagementDTO> saveEmployee(@RequestBody EmployeeManagementDTO employeeManagementDTO) {
        EmployeeManagementDTO savedEmployee = employeeManagementService.saveEmployee(employeeManagementDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedEmployee);
    }

    @GetMapping("/")
    public ResponseEntity<List<EmployeeManagementDTO>> getAllEmployee() {
        List<EmployeeManagementDTO> allEmployee = employeeManagementService.getAllEmployee();
        return ResponseEntity.ok(allEmployee);
    }
}
