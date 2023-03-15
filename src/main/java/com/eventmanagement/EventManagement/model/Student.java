package com.eventmanagement.EventManagement.model;

import com.eventmanagement.EventManagement.DataAccessObjects.interfaces.IStudentDAO;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;


public class Student extends User {

    public Student()
    {
        super();
    }

    public String getCreatedAtDisplayText()
    {
        return this.getCreatedAt().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }

    // operations
    public static Student findByEmail(IStudentDAO studentDAO, String email)
    {
        if(email == null || email.trim().length() == 0) {
            return null;
        }
        Optional<Student> student = studentDAO.findByEmail(email);
        if(student.isPresent()) {
            return student.get();
        } else {
            return null;
        }
    }

    public static Student findById(IStudentDAO studentDAO, Integer id)
    {
        if(id == null) {
            return null;
        }
        Optional<Student> student = studentDAO.findById(id);
        if(student.isPresent()) {
            return student.get();
        } else {
            return null;
        }
    }

    public static List<Student> findAllByIds(IStudentDAO studentDAO, List<Integer> studentIds) {
        return studentDAO.findAllByIds(studentIds);
    }

}
