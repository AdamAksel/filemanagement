package com.example.filemanagement.service;

import com.example.filemanagement.model.File;
import com.example.filemanagement.repository.FileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class FileService {

    private final FileRepository fileRepository;

    @Autowired
    public FileService(FileRepository fileRepository) {
        this.fileRepository = fileRepository;
    }

    public File uploadFile(MultipartFile multipartFile, String folderId) throws IOException {

        File file = new File();
        file.setName(multipartFile.getOriginalFilename());
        file.setSize(multipartFile.getSize());
        file.setFolderId(folderId);

        return fileRepository.save(file);
    }


}

