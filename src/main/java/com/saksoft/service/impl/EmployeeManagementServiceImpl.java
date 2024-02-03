package com.saksoft.service.impl;

import com.saksoft.dto.EmployeeManagementDTO;
import com.saksoft.dto.PaginationDTO;
import com.saksoft.entity.EmployeeManagement;
import com.saksoft.exception.EMException;
import com.saksoft.repository.EmployeeManagementRepository;
import com.saksoft.service.EmployeeManagementService;
import com.saksoft.service.PaginationService;
import com.saksoft.util.EMConstant;
import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.*;
import java.util.logging.Logger;

@Service
@Slf4j
public class EmployeeManagementServiceImpl implements EmployeeManagementService {

    @Autowired
    private EmployeeManagementRepository employeeManagementRepository;
    @Autowired
    private PaginationService paginationService;

    @Value("${ems.employee.not.found.code}")
    private String empNotFoundCode;
    @Value("${ems.employee.not.found.message}")
    private String empNotFoundMessage;
     public static List<EmployeeManagement> list = new ArrayList<>();

    static {
        list = List.of(new EmployeeManagement(1L, "hello", "john", "@gmail.com", 10));
    }

    @Override
    public List<EmployeeManagement> getDummyData(){
        return  list;
    }

    @Override
    public EmployeeManagementDTO saveEmployee(EmployeeManagementDTO employeeManagementDTO) {
        EmployeeManagement savedEmployee = employeeManagementRepository.save(new EmployeeManagement(employeeManagementDTO));
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
        log.info("employee is {}", employee);
        if (employee.isEmpty()) {
            throw new EMException(empNotFoundCode, empNotFoundMessage, HttpStatus.NOT_FOUND);
        }
        return new EmployeeManagementDTO(employee.get());
    }

    @Override
    public EmployeeManagementDTO updateEmployee(Long id, EmployeeManagementDTO employeeManagementDTO) throws EMException {
        Optional<EmployeeManagement> employee = employeeManagementRepository.findById(id);
        if (employee.isEmpty()) {
            throw new EMException(empNotFoundCode, empNotFoundMessage, HttpStatus.NOT_FOUND);
        }
        EmployeeManagement employeeManagement = employee.get();
        employeeManagement.setId(id);
        employeeManagement.setFirstName(employeeManagementDTO.getFirstName());
        employeeManagement.setLastName(employeeManagementDTO.getLastName());
        employeeManagement.setEmail(employeeManagementDTO.getEmail());
        employeeManagement.setAge(employeeManagementDTO.getAge());
        return new EmployeeManagementDTO(employeeManagementRepository.save(employeeManagement));
    }

    @Override
    public String deleteEmployee(Long id) throws EMException {
        Optional<EmployeeManagement> employee = employeeManagementRepository.findById(id);
        if (employee.isEmpty()) {
            throw new EMException(empNotFoundCode, empNotFoundMessage, HttpStatus.NOT_FOUND);
        }
        employeeManagementRepository.deleteById(id);
        return id + EMConstant.EMPLOYEE_DELETED;
    }

    @Override
    public EmployeeManagementDTO updateEmployeeByFields(Long id, Map<String, Object> fields) throws EMException {
        Optional<EmployeeManagement> existingEmployee = employeeManagementRepository.findById(id);

        if (existingEmployee.isEmpty()) {
            throw new EMException(empNotFoundCode, empNotFoundMessage, HttpStatus.NOT_FOUND);
        }
        fields.forEach((key, value) -> {
            Field field = ReflectionUtils.findField(EmployeeManagement.class, key);
            assert field != null;
            field.setAccessible(true);
            ReflectionUtils.setField(field, existingEmployee.get(), value);
        });
        return new EmployeeManagementDTO(employeeManagementRepository.save(existingEmployee.get()));
    }

    @Override
    public Map<String, Object> getAllEmployeeByPagination(PaginationDTO paginationDTO) {
        Page<EmployeeManagement> employee =
                employeeManagementRepository.findAll(paginationService.getPagination(paginationDTO));
        Map<String, Object> dataMap = new HashMap<>();
        dataMap.put("data", employee);
        dataMap.put("totalPage", employee.getTotalPages());
        dataMap.put("currentPage", employee.getNumber());
        dataMap.put("totalRecords", employee.getSize());
        return dataMap;
    }
}
