package sptech.faztudo.comLOCAL.users.domain.users;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Annotation;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = AgeValidator.class)
@Documented
public @interface AgeConstraint {
    String message() default "Idade inválida";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
    int minAge() default 0;
}
