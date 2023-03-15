package com.eventmanagement.EventManagement.DataAccessObjects.interfaces;

import com.eventmanagement.EventManagement.model.interfaces.IStudentInterest;
import java.util.List;

public interface IStudentInterestDAO
{
    public boolean addStudentInterest(int studentId, String categoryId);
    public boolean deleteStudentInterest(int studentInterestId);
    public List<IStudentInterest> getStudentInterests(int studentId);
}
