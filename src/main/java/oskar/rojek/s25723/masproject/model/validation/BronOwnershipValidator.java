package oskar.rojek.s25723.masproject.model.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import oskar.rojek.s25723.masproject.model.Bron;
import oskar.rojek.s25723.masproject.model.Odkrywca;

public class BronOwnershipValidator implements ConstraintValidator<ValidBronOwnership, Odkrywca> {

    @Override
    public void initialize(ValidBronOwnership constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(Odkrywca odkrywca, ConstraintValidatorContext constraintValidatorContext) {

        for(Bron bron : odkrywca.getBronie()){
            if(odkrywca.getSila() < bron.getWymaganaSila() || odkrywca.getZrecznosc() < bron.getWymaganaZrecznosc() ||
            odkrywca.getInteligencja() < bron.getWymaganaInteligencja()){
                return false;
            }
        }
        return true;
    }
}
