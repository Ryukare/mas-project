package oskar.rojek.s25723.masproject.model.validation;


import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy=BronOwnershipValidator.class)
public @interface ValidPlaca {
    String message() default "Płaca powinna być wyższa od płacy minimalnej";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
