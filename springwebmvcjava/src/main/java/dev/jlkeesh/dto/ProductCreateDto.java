package dev.jlkeesh.dto;

import dev.jlkeesh.config.validation.ProductSize;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;


public record ProductCreateDto(
        @NotBlank(message = "name can not me null") String name,
        @Min(value = 2, message = "price must be greater then or equal {value}") Long price,
        @NotNull(message = "size can not be null")
        @ProductSize(values = {"S", "M", "L","XL"}, message = "size {values} lardan biri bolishi kerak") String size) {
}
