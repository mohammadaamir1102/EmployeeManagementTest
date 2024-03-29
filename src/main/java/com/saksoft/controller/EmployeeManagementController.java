package com.saksoft.controller;

import com.saksoft.dto.EmployeeManagementDTO;
import com.saksoft.dto.PaginationDTO;
import com.saksoft.entity.EmployeeManagement;
import com.saksoft.exception.EMException;
import com.saksoft.service.EmployeeManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/employee")
public class EmployeeManagementController {

    @Autowired
    private EmployeeManagementService employeeManagementService;

    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    public EmployeeManagementDTO saveEmployee(@RequestBody EmployeeManagementDTO employeeManagementDTO) {
        return employeeManagementService.saveEmployee(employeeManagementDTO);
    }

    @GetMapping("/")
    @ResponseStatus(HttpStatus.OK)
    public List<EmployeeManagementDTO> getAllEmployee() {
        return employeeManagementService.getAllEmployee();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public EmployeeManagementDTO getEmployeeById(@PathVariable Long id) throws EMException {
        return employeeManagementService.getEmployeeById(id);
    }

    @GetMapping(value = "/getAllEmployee/{page}/{offset}")
    @ResponseStatus(HttpStatus.OK)
    public Map<String, Object> getAllEmployeeByPagination(@PathVariable Long page, @PathVariable Long offset) {
        PaginationDTO paginationDTO = new PaginationDTO();
        paginationDTO.setOffset(offset.intValue());
        paginationDTO.setPageNumber(page.intValue());
        return employeeManagementService.getAllEmployeeByPagination(paginationDTO);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public EmployeeManagementDTO updateEmployee(
            @PathVariable Long id,
            @RequestBody EmployeeManagementDTO employeeManagementDTO) throws EMException {
        return employeeManagementService.updateEmployee(id, employeeManagementDTO);
    }

    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public EmployeeManagementDTO updateEmployeeByFields(
            @PathVariable Long id, @RequestBody Map<String, Object> fields) throws EMException {
        return employeeManagementService.updateEmployeeByFields(id, fields);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public String deleteEmployee(@PathVariable Long id) throws EMException {
        return employeeManagementService.deleteEmployee(id);
    }

    @GetMapping("/getDummyData")
    public List<EmployeeManagement> getEmployeeDummyData(){
        return employeeManagementService.getDummyData();
    }

}
