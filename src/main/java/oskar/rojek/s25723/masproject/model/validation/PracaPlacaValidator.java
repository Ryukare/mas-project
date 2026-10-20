package oskar.rojek.s25723.masproject.model.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import oskar.rojek.s25723.masproject.model.Praca;

public class PracaPlacaValidator implements ConstraintValidator<ValidPlaca, Praca> {

    @Override
    public void initialize(ValidPlaca constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(Praca praca, ConstraintValidatorContext constraintValidatorContext) {
        return praca.getPlaca() >= Praca.getPlacaMinimalna();
    }
}
