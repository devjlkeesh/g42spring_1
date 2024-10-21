package dev.jlkeesh.domain;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;


@Getter
@Setter
@ToString
public class Book {
    private long id;
    @NotBlank(message = "title can not be blank")
    private String title;
    @NotBlank(message = "title can not be blank")
    private String author;

    @NotNull(message = "page count can not be null")
    @Min(value = 10, message = "book page count must geq {value}")
    private Integer pageCount;
    private MultipartFile file;
}
