package com.bernardpaula.LojaEletrodomesticos.validations;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = NotEmptyListValidator.class)
public @interface NotEmptyList {

	String message() default "A lista não pode ser vazia";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};

}
