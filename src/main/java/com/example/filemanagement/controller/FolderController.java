package com.example.filemanagement.controller;

import com.example.filemanagement.model.Folder;
import com.example.filemanagement.service.FolderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/folders")
public class FolderController {

    private final FolderService folderService;

    @Autowired
    public FolderController(FolderService folderService) {
        this.folderService = folderService;
    }

    @PostMapping
    public ResponseEntity<Folder> createFolder(@RequestBody Folder folder) {
        Folder newFolder = folderService.createFolder(folder.getName(), folder.getUserId());
        return ResponseEntity.ok(newFolder);
    }

}

