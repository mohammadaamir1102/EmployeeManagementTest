package com.saksoft.service;

import com.saksoft.dto.PaginationDTO;
import org.springframework.data.domain.Pageable;

public interface PaginationService {
    Pageable getPagination(PaginationDTO paginationDTO);

}