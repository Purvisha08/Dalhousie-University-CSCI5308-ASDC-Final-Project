package com.eventmanagement.EventManagement.DataAccessObjects.interfaces;

import com.eventmanagement.EventManagement.model.Student;

import java.util.*;

public interface IStudentDAO
{
    public List<Student> findAll();
    public Optional<Student> findByEmail(String email);
    public Optional<Student> findById(int id);
    public List<Student> findAllByIds(List<Integer> studentIds);
    public int updateStudent(int id, Student student);
    public int deleteStudent(int id);
    public Double getWalletBalance(Integer studentId);
}
