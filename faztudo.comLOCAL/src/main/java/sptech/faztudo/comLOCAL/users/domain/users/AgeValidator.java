package sptech.faztudo.comLOCAL.users.domain.users;


import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.time.LocalDate;
import java.time.Period;

public class AgeValidator implements ConstraintValidator<AgeConstraint, LocalDate> {
    private int minAge;

    @Override
    public void initialize(AgeConstraint ageConstraint) {
        this.minAge = ageConstraint.minAge();
    }

    @Override
    public boolean isValid(LocalDate dateOfBirth, ConstraintValidatorContext context) {
        if (dateOfBirth == null) {
            return false; // Não pode ser nulo
        }

        LocalDate today = LocalDate.now();
        Period age = Period.between(dateOfBirth, today);

        return age.getYears() >= minAge;
    }

    
}

