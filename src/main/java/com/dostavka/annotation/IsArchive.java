package com.dostavka.annotation;

import com.dostavka.validator.IsArchiveValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = IsArchiveValidator.class)
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface IsArchive {
    String message() default "Number of orders cannot be negative!";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };
}
