package com.saksoft.service.impl;

import com.saksoft.dto.JsonAndFileDataDTO;
import com.saksoft.entity.JsonAndFileData;
import com.saksoft.repository.JsonAndFileRepository;
import com.saksoft.service.JsonAndFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class JsonAndFileServiceImpl implements JsonAndFileService {

    @Autowired
    private JsonAndFileRepository jsonAndFileRepository;

    @Override
    public JsonAndFileData saveData(JsonAndFileDataDTO jsonAndFileDataDTO, MultipartFile image, MultipartFile pdf) throws IOException {

        JsonAndFileData jsonAndFileData = new JsonAndFileData();
        jsonAndFileData.setId(jsonAndFileDataDTO.getId());
        jsonAndFileData.setName(jsonAndFileDataDTO.getName());
        jsonAndFileData.setEmail(jsonAndFileDataDTO.getEmail());
        jsonAndFileData.setPhoneNo(jsonAndFileDataDTO.getPhoneNo());
        jsonAndFileData.setPdf(pdf.getBytes());
        jsonAndFileData.setImage(image.getBytes());
        return jsonAndFileRepository.save(jsonAndFileData);
    }
}
