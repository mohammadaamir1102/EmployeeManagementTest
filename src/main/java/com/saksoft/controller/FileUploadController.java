package com.saksoft.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.saksoft.dto.JsonAndFileDataDTO;
import com.saksoft.service.JsonAndFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
public class FileUploadController {


    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private JsonAndFileService jsonAndFileService;

    @PostMapping("/jsonAndFile")
    public ResponseEntity<?> addJsonAndFile(@RequestParam("json") String json,
                                            @RequestParam("image") MultipartFile image,
                                            @RequestParam("pdf") MultipartFile pdf) throws IOException {
        JsonAndFileDataDTO jsonAndFileDataDTO = null;
        try {
            jsonAndFileDataDTO = objectMapper.readValue(json, JsonAndFileDataDTO.class);
            jsonAndFileService.saveData(jsonAndFileDataDTO, image, pdf);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Something went wrong");
        }
        return ResponseEntity.ok("Saved");
    }


}
