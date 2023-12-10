package com.example.filemanagement.service;

import com.example.filemanagement.model.Folder;
import com.example.filemanagement.repository.FolderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FolderService {

    private final FolderRepository folderRepository;

    @Autowired
    public FolderService(FolderRepository folderRepository) {
        this.folderRepository = folderRepository;
    }

    public Folder createFolder(String name, String userId) {
        Folder folder = new Folder(name, userId);
        return folderRepository.save(folder);
    }

}

