package com.saksoft.dto;

import antlr.StringUtils;
import com.fasterxml.jackson.databind.util.BeanUtil;
import com.saksoft.entity.EmployeeManagement;
import lombok.*;
import org.springframework.beans.BeanUtils;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class EmployeeManagementDTO {
    public EmployeeManagementDTO(EmployeeManagement employeeManagement) {
        BeanUtils.copyProperties(employeeManagement, this);
    }

    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private Integer age;
}
