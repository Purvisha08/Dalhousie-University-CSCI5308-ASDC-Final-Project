package com.eventmanagement.EventManagement.model.interfaces;

import java.util.ArrayList;
import java.util.List;

public interface IStudentInterest
{
    public List<IStudentInterest> getStudentInterests(int studentId);
    public void addStudentInterest(int studentId, String  categoryId);
    public void deleteStudentInterest(int studentInterestId);
}
