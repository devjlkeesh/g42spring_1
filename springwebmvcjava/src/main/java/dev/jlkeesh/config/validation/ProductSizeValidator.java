package dev.jlkeesh.config.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class ProductSizeValidator implements ConstraintValidator<ProductSize, String> {
    private  String[] acceptableValues;

    @Override
    public void initialize(ProductSize constraintAnnotation) {
        this.acceptableValues = constraintAnnotation.values();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null) {
            return true;
        }
        for (String acceptableValue : acceptableValues) {
            if (acceptableValue.equalsIgnoreCase(value)) {
                return true;
            }
        }
        return false;
    }
}
