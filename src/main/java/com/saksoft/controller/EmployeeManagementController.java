package com.saksoft.controller;

import com.saksoft.dto.EmployeeManagementDTO;
import com.saksoft.dto.PaginationDTO;
import com.saksoft.entity.EmployeeManagement;
import com.saksoft.exception.EMException;
import com.saksoft.repository.EmployeeManagementRepository;
import com.saksoft.service.EmployeeManagementService;
import com.saksoft.vo.EmployeeManagementResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/employee")
public class EmployeeManagementController {

    @Autowired
    private EmployeeManagementService employeeManagementService;

    @Autowired
    private EmployeeManagementRepository employeeManagementRepository;

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

    @GetMapping("/getEmployeeByFilter")
    public EmployeeManagementResponse<EmployeeManagement> getStudents(
            @RequestParam(required = false) String email,
            @RequestParam(required = false, defaultValue = "id") String sortBy,
            @RequestParam(required = false, defaultValue = "desc") String order,
            @RequestParam(required = false, defaultValue = "0") int page,
            @RequestParam(required = false, defaultValue = "10") int size) {
        return getAllEmployees(email, sortBy, order, page, size);
    }

    public EmployeeManagementResponse<EmployeeManagement> getAllEmployees(String email, String sortBy, String order, int page, int size) {
        Sort.Direction direction = order.equalsIgnoreCase("desc") ? Sort.Direction.DESC : Sort.Direction.ASC;
        Pageable pageable = PageRequest.of(page, size, Sort.by(direction, sortBy));
        Page<EmployeeManagement> employeeManagements;
        if (email != null) {
            employeeManagements = employeeManagementRepository.findByEmail(email, pageable);
        } else {
            employeeManagements = employeeManagementRepository.findAll(pageable);
        }
        return new EmployeeManagementResponse<>(
                employeeManagements.getContent(),
                employeeManagements.getTotalElements(),
                employeeManagements.getTotalPages(),
                employeeManagements.getNumber(),
                employeeManagements.getSize()
        );
    }

}
