package oskar.rojek.s25723.masproject.model.validation;


import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy=PracaPlacaValidator.class)
public @interface ValidBronOwnership {
    String message() default "Odkrywca ma zbyt małe statystyki by dzierżyć broń";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
