package com.eventmanagement.EventManagement.DataAccessObjects.interfaces;

import com.eventmanagement.EventManagement.model.StudentPastInterest;
import java.util.ArrayList;

public interface IStudentPastInterestDAO
{
    public ArrayList<StudentPastInterest> getPastInterest(int userId);
}
