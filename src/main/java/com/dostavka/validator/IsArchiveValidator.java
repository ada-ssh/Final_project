package com.dostavka.validator;

import com.dostavka.annotation.IsArchive;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class IsArchiveValidator implements ConstraintValidator<IsArchive, Integer> {
    @Override
    public void initialize(IsArchive constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(Integer value, ConstraintValidatorContext constraintValidatorContext) {
        return value >= 0;
    }
}
