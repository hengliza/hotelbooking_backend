package com.example.demo.service;

import com.example.demo.dto.MediaResponse;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface MediaService {
    MediaResponse upload(MultipartFile file);
    List<MediaResponse> uploadMultiple(List<MultipartFile> files);
    ResponseEntity<Resource> downloadByName(String fileNameWithExt);
    boolean delete(String fileNameAndExt);
}
