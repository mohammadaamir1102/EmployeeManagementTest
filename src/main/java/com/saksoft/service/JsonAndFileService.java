package com.saksoft.service;

import com.saksoft.dto.JsonAndFileDataDTO;
import com.saksoft.entity.JsonAndFileData;
import com.saksoft.repository.JsonAndFileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface JsonAndFileService {

    JsonAndFileData saveData(JsonAndFileDataDTO jsonAndFileDataDTO, MultipartFile image, MultipartFile pdf) throws IOException;


}
