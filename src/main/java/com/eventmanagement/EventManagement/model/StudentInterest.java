package com.eventmanagement.EventManagement.model;

import com.eventmanagement.EventManagement.DataAccessObjects.interfaces.IStudentInterestDAO;
import com.eventmanagement.EventManagement.model.interfaces.IStudentInterest;

import java.util.List;

public class StudentInterest implements IStudentInterest
{
    private Integer studentInterestId;
    private Integer studentId;
    private String categoryId;
    private IStudentInterestDAO studentInterestDAO;

    public StudentInterest()
    {

    }

    public StudentInterest(IStudentInterestDAO studentInterestDAO)
    {
        this.studentInterestDAO = studentInterestDAO;
    }

    public Integer getStudentInterestId()
    {
        return this.studentInterestId;
    }

    public void setStudentInterestId(Integer studentInterestId)
    {
        this.studentInterestId = studentInterestId;
    }

    public Integer getStudentId()
    {
        return this.studentId;
    }

    public void setStudentId(Integer studentId)
    {
        this.studentId = studentId;
    }

    public String getCategoryId()
    {
        return this.categoryId;
    }

    public void setCategoryId(String categoryId)
    {
        this.categoryId = categoryId;
    }

    @Override
    public List<IStudentInterest> getStudentInterests(int studentId)
    {
        List<IStudentInterest> studentInterestsList = this.studentInterestDAO.getStudentInterests(studentId);
        return studentInterestsList;
    }

    @Override
    public void addStudentInterest(int studentId, String  categoryId)
    {
        this.studentInterestDAO.addStudentInterest(studentId, categoryId);
    }

    @Override
    public void deleteStudentInterest(int studentInterestId)
    {
        this.studentInterestDAO.deleteStudentInterest(studentInterestId);
    }

}
