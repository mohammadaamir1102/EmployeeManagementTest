package com.saksoft.vo;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class EmployeeManagementResponse<T> {
    private List<T> data;
    private long totalRecords;
    private int totalPages;
    private int currentPage;
    private int pageSize;
}
