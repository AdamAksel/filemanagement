package com.example.filemanagement.controller;

import com.example.filemanagement.model.File;
import com.example.filemanagement.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/files")
public class FileController {

    private final FileService fileService;

    @Autowired
    public FileController(FileService fileService) {
        this.fileService = fileService;
    }

    @PostMapping("/upload")
    public ResponseEntity<File> uploadFile(@RequestParam("file") MultipartFile file, @RequestParam("folderId") String folderId) throws IOException {
        File uploadedFile = fileService.uploadFile(file, folderId);
        return ResponseEntity.ok(uploadedFile);
    }


}

