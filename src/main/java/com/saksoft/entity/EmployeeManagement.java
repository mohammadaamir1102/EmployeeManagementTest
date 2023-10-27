package com.saksoft.entity;

import com.saksoft.dto.EmployeeManagementDTO;
import lombok.*;
import org.springframework.beans.BeanUtils;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity(name = "EMPLOYEE_MANAGEMENT")
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
