package dev.jlkeesh.dto;

import java.util.UUID;

public record FileShortDto(UUID id, String originalName, long size, String mimeType, String downloadUrl) {
}
