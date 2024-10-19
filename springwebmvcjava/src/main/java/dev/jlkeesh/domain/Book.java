package dev.jlkeesh.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.web.multipart.MultipartFile;


@Getter
@Setter
@ToString
public class Book {
    private long id;
    private String title;
    private String author;
    private Integer pageCount;
}
