

package com.example.filemanagement.service;

        import com.example.filemanagement.model.File;
        import com.example.filemanagement.repository.FileRepository;
        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.core.io.Resource;
        import org.springframework.core.io.UrlResource;
        import org.springframework.stereotype.Service;
        import org.springframework.web.multipart.MultipartFile;

        import java.io.IOException;
        import java.net.MalformedURLException;
        import java.nio.file.Files;
        import java.nio.file.Path;
        import java.nio.file.Paths;
        import java.nio.file.StandardCopyOption;

@Service
public class FileService {

    private final FileRepository fileRepository;

    @Autowired
    public FileService(FileRepository fileRepository) {
        this.fileRepository = fileRepository;
    }

    public File uploadFile(MultipartFile multipartFile, String folderId) throws IOException {
        String fileName = multipartFile.getOriginalFilename();
        String directoryPath = "/pleasework/" + folderId;
        Path directory = Paths.get(directoryPath);

        if (!Files.exists(directory)) {
            Files.createDirectories(directory);
        }

        Path filePath = directory.resolve(fileName);
        Files.copy(multipartFile.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

        File file = new File();
        file.setName(fileName);
        file.setSize(multipartFile.getSize());
        file.setFolderId(folderId);
        file.setPath(filePath.toString());

        return fileRepository.save(file);
    }

    public void deleteFile(String fileId) {
        File file = fileRepository.findById(fileId)
                .orElseThrow(() -> new RuntimeException("File not found"));

        Path path = Paths.get(file.getId());
        java.io.File fileToDelete = path.toFile();
        if (fileToDelete.exists()) {
            fileToDelete.delete();
        }

        fileRepository.deleteById(fileId);
    }

    public Resource downloadFile(String fileId) {
        File file = fileRepository.findById(fileId)
                .orElseThrow(() -> new RuntimeException("File not found"));

        Path path = Paths.get(file.getPath());
        Resource resource;
        try {
            resource = new UrlResource(path.toUri());
            if (!resource.exists() || !resource.isReadable()) {
                throw new RuntimeException("Could not read the file!");
            }
            return resource;
        } catch (MalformedURLException ex) {
            throw new RuntimeException("Error: " + ex.getMessage());
        }
    }

}


