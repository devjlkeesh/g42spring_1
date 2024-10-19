package dev.jlkeesh.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Upload {
    private String id;
    private String originalName;
    private String generatedName;
    private long size;
    private String mimeType;
    private String downloadUrl;
    private LocalDateTime uploadDate;
}
