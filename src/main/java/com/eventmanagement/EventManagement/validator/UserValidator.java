package com.eventmanagement.EventManagement.validator;

import com.eventmanagement.EventManagement.DataAccessObjects.HostDAO;
import com.eventmanagement.EventManagement.DataAccessObjects.StudentDAO;
import com.eventmanagement.EventManagement.model.Host;
import com.eventmanagement.EventManagement.model.Student;
import com.eventmanagement.EventManagement.model.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;

@Component
public class UserValidator {

    @Autowired
    private StudentDAO studentDAO;

    @Autowired
    private HostDAO hostDAO;

    public void validate(Object object, Errors errors)
    {
        User user = (User) object;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "NotEmpty");
        if (user.getEmail().length() < 6 || user.getEmail().length() > 32) {
            errors.rejectValue("email", "Size.user.email");
        }

        if(user.getUserType().equals("Host"))
        {
            if (Host.findByEmail(hostDAO, user.getEmail()) != null)
            {
                errors.rejectValue("email", "Duplicate.user.email");
            }
        }
        else
        {
            if (Student.findByEmail(studentDAO, user.getEmail()) != null)
            {
                errors.rejectValue("email", "Duplicate.user.email");
            }
        }

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "NotEmpty");
        if (user.getPassword().length() < 8 || user.getPassword().length() > 32) {
            errors.rejectValue("password", "Size.user.password");
        }

        if (!user.getPasswordConfirm().equals(user.getPassword())) {
            errors.rejectValue("passwordConfirm", "Diff.user.passwordConfirm");
        }

    }
}
