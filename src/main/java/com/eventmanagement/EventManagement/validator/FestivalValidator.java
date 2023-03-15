package com.eventmanagement.EventManagement.validator;

import com.eventmanagement.EventManagement.model.Festival;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;


@Component
public class FestivalValidator implements Validator {

    @Override
    public boolean supports(Class<?> aClass) {
        return Festival.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        Festival festival = (Festival) o;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "NotEmpty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "description", "NotEmpty");

        if (festival.getName().length() < 5 || festival.getName().length() > 40) {
            errors.rejectValue("name", "Size.festivalForm.name");
        }

        if (festival.getDescription().length() > 100) {
            errors.rejectValue("description", "Size.festivalForm.description");
        } else if (festival.getDescription().length() < 5) {
            errors.rejectValue("description", "Size.festivalForm.description");
        }

        if (festival.getUniversity().length() < 5 || festival.getUniversity().length() > 100) {
            errors.rejectValue("university", "Size.festivalForm.university");
        }


        if (festival.getEndDate() != null && festival.getEndDate().before(festival.getStartDate())) {
            errors.rejectValue("endDate", "Before.festivalForm.endDate");
        }
    }
}
