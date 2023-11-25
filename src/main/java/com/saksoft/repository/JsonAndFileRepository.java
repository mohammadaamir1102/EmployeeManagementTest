package com.saksoft.repository;

import com.saksoft.entity.JsonAndFileData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JsonAndFileRepository extends JpaRepository<JsonAndFileData, Long> {
}
