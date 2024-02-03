package com.saksoft.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.saksoft.dto.EmployeeManagementDTO;
import lombok.*;
import org.springframework.beans.BeanUtils;

import javax.persistence.*;


@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity(name = "EMPLOYEE_MANAGEMENT")
@ToString(exclude = {"id","email"})
public class EmployeeManagement {

    public EmployeeManagement(EmployeeManagementDTO employeeManagementDTO) {
        BeanUtils.copyProperties(employeeManagementDTO, this);
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private Integer age;
}
