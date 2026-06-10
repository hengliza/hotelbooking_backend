package com.example.demo.controller;

import com.example.demo.dto.MediaResponse;
import com.example.demo.service.MediaService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.util.List;

@RestController
@RequestMapping("/api/medias")
@RequiredArgsConstructor
public class MediaController {

    private final MediaService mediaService;

    @PostMapping
    public MediaResponse upload(@RequestPart MultipartFile file) {
        return mediaService.upload(file);
    }

    @PostMapping("/upload-multiple")
    public List<MediaResponse> uploadMultiple(@RequestParam("files") List<MultipartFile> files) {
        return mediaService.uploadMultiple(files);
    }
    @GetMapping("/download/{filename}")
    public ResponseEntity<Resource> downloadFile(@PathVariable("filename") String filename) {
        return mediaService.downloadByName(filename);
    }
    @DeleteMapping("/delete/{filename}")
    public ResponseEntity<String> deleteFile(@PathVariable String filename) {
        boolean deleted = mediaService.delete(filename);

        if (deleted) {
            return ResponseEntity.ok("File deleted successfully");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("File not found or could not be deleted");
        }
    }

}
