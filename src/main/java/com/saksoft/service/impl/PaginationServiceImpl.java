package com.saksoft.service.impl;

import com.saksoft.dto.PaginationDTO;
import com.saksoft.service.PaginationService;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class PaginationServiceImpl implements PaginationService {
    @Override
    public Pageable getPagination(PaginationDTO paginationDTO) {
        if (Objects.isNull(paginationDTO.getOffset()) && Objects.isNull(paginationDTO.getPageNumber())) {
            throw new IllegalArgumentException("offset and page no is required !");
        }
        return PageRequest.of(paginationDTO.getPageNumber(), paginationDTO.getOffset());
    }
}
