package dev.jlkeesh.service;

import dev.jlkeesh.dto.DownloadFileDto;
import dev.jlkeesh.dto.FileShortDto;
import dev.jlkeesh.dao.UploadDao;
import dev.jlkeesh.domain.Upload;
import jakarta.annotation.PostConstruct;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.PathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class FileStorageService {
    private final UploadDao uploadDao;
    private Path rootPath;

    public FileStorageService(UploadDao uploadDao) {
        this.uploadDao = uploadDao;
    }

    @PostConstruct
    public void init() {
        this.rootPath = Path.of("/uploads/g42");
        if (!Files.exists(rootPath)) {
            try {
                Files.createDirectories(rootPath);
            } catch (IOException e) {
                e.printStackTrace();
                System.exit(1);
            }
        }
    }


    public FileShortDto upload(MultipartFile file) {
        String originalFilename = file.getOriginalFilename();
        long size = file.getSize();
        String contentType = file.getContentType();
        UUID id = UUID.randomUUID();
        String generatedName = id + "." + StringUtils.getFilenameExtension(originalFilename);
        String downloadPath = "/uploads/" + generatedName;
        Path uploadPath = rootPath.resolve(generatedName);
        try {
            Files.copy(file.getInputStream(), uploadPath, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            throw new RuntimeException("file can not be uploaded", e);
        }
        Upload upload = new Upload(id.toString(), originalFilename, generatedName, size, contentType, downloadPath, LocalDateTime.now());
        uploadDao.save(upload);
        return new FileShortDto(id, originalFilename, size, contentType, downloadPath);
    }

    public DownloadFileDto get(String generatedName) {
        Upload upload = uploadDao.findByGeneratedName(generatedName)
                .orElseThrow(() -> new RuntimeException("File not found"));
        if (!Files.exists(rootPath.resolve(generatedName))) {
            throw new RuntimeException("file not found:" + generatedName);
        }
        PathResource pathResource = new PathResource(rootPath.resolve(generatedName));
        return new DownloadFileDto(upload, pathResource);
    }
}
