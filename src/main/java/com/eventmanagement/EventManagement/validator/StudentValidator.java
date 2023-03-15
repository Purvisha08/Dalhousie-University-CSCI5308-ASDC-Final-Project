package com.eventmanagement.EventManagement.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.eventmanagement.EventManagement.DataAccessObjects.StudentDAO;
import com.eventmanagement.EventManagement.model.Student;

@Component
public class StudentValidator implements Validator {

    @Autowired
    public StudentDAO studentDAO;

    @Override
    public boolean supports(Class<?> aClass) {
        return Student.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        Student student = (Student) o;
        
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "NotEmpty");
        if (student.getEmail().length() < 6 || student.getEmail().length() > 32) {
            errors.rejectValue("email", "Size.studentForm.email");
        }

       if (Student.findByEmail(studentDAO, student.getEmail()) != null) {
           errors.rejectValue("email", "Duplicate.studentForm.email");
       }

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "NotEmpty");
        if (student.getPassword().length() < 8 || student.getPassword().length() > 32) {
            errors.rejectValue("password", "Size.studentForm.password");
        }

        if (!student.getPasswordConfirm().equals(student.getPassword())) {
            errors.rejectValue("passwordConfirm", "Diff.studentForm.passwordConfirm");
        }
    }
}
