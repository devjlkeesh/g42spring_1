package dev.jlkeesh.controller;

import dev.jlkeesh.dto.ProductCreateDto;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Valid;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Set;

@Controller
@RequestMapping("/product")
public class ProductController {

    @ResponseBody
    @PostMapping("/add")
    public String addProduct(@ModelAttribute ProductCreateDto dto) {
        String errorMessage = "";
        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        Validator validator = validatorFactory.getValidator();
        Set<ConstraintViolation<ProductCreateDto>> violations = validator.validate(dto);
        for (ConstraintViolation<ProductCreateDto> violation : violations) {
            System.out.println("violation = " + violation);
            errorMessage += violation.getPropertyPath().toString() + ":" + violation.getMessage() + "\n";
        }
        if (errorMessage != "")
            return errorMessage;
        return "success";
    }

    @ResponseBody
    @PostMapping("/add/two")
    public String addProduct2(@Valid @ModelAttribute ProductCreateDto dto, BindingResult bindingResult) {
        String errorMessage = "";
        if (bindingResult.hasErrors()) {
            for (FieldError fieldError : bindingResult.getFieldErrors()) {
                Object rejectedValue = fieldError.getRejectedValue();
                String field = fieldError.getField();
                String message = fieldError.getDefaultMessage();
                errorMessage += field + ":" + message + "rejected value:" + rejectedValue + "<br>";
            }
        }
        if (errorMessage != "")
            return errorMessage;
        return "success";
    }
}
