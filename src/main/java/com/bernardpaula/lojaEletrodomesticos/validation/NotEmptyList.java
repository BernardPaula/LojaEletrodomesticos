package com.bernardpaula.lojaEletrodomesticos.validation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.bernardpaula.lojaEletrodomesticos.validation.constraintvalidation.NotEmptyListValidator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = NotEmptyListValidator.class)
public @interface NotEmptyList {

	String message() default "A lista não pode ser vazia";
	
	Class<?>[] groups() default { };

	Class<? extends Payload>[] payload() default { };
	
}
