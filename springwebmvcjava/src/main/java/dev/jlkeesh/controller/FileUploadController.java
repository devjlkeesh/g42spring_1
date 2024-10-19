package dev.jlkeesh.controller;

import dev.jlkeesh.domain.Upload;
import dev.jlkeesh.dto.DownloadFileDto;
import dev.jlkeesh.service.FileStorageService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/uploads")
@RequiredArgsConstructor
public class FileUploadController {
    private final FileStorageService fileStorageService;

    @ResponseBody
    @PostMapping
    public String uploadFile(@RequestParam("file") MultipartFile file) {
        return fileStorageService.upload(file).toString();
    }

    @ResponseBody
    @PostMapping("/multi")
    public String uploadFile(@RequestParam("files") MultipartFile[] files) {
        return fileStorageService.upload(files[0]).toString();
    }

    @ResponseBody
    @GetMapping("/{generatedName}")
    public ResponseEntity<Resource> download(@PathVariable("generatedName") String id) {
        DownloadFileDto dto = fileStorageService.get(id);
        Resource resource = dto.resource();
        Upload upload = dto.upload();
        HttpHeaders headers = new HttpHeaders();
//        headers.set("Content-Disposition", "attachment; filename=\"" + upload.getOriginalName() + "\"");
        headers.set("Content-Length", String.valueOf(upload.getSize()));
        headers.set("Content-Type", upload.getMimeType());
        return new ResponseEntity<>(
                resource, headers, 200
        );
    }
}
