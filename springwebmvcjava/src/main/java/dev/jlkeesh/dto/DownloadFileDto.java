package dev.jlkeesh.dto;

import dev.jlkeesh.domain.Upload;
import org.springframework.core.io.Resource;

public record DownloadFileDto(Upload upload, Resource resource) {
}
