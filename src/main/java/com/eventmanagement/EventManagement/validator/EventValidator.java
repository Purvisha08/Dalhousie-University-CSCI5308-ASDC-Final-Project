package com.eventmanagement.EventManagement.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.eventmanagement.EventManagement.model.Event;

@Component
public class EventValidator implements Validator {

    @Override
    public boolean supports(Class<?> aClass) {
        return Event.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        Event event = (Event) o;
        
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "NotEmpty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "description", "NotEmpty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "category", "NotEmpty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "venue", "NotEmpty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "price", "NotEmpty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "capacity", "NotEmpty");

        if (event.getName().length() < 5 || event.getName().length() > 32) {
            errors.rejectValue("name", "Size.eventForm.name");
        }

        if (event.getDescription().length() < 5 || event.getDescription().length() > 100) {
            errors.rejectValue("description", "Size.eventForm.description");
        }

        if (event.getEndDate() != null && event.getEndDate().before(event.getStartDate())) {
            errors.rejectValue("endDate", "Before.eventForm.endDate");
        }

    }
}
