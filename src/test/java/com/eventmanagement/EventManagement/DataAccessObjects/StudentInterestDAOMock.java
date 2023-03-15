package com.eventmanagement.EventManagement.DataAccessObjects;

import com.eventmanagement.EventManagement.DataAccessObjects.interfaces.IStudentInterestDAO;
import com.eventmanagement.EventManagement.model.StudentInterest;
import com.eventmanagement.EventManagement.model.interfaces.IStudentInterest;

import java.util.ArrayList;
import java.util.List;

public class StudentInterestDAOMock implements IStudentInterestDAO
{
    @Override
    public boolean addStudentInterest(int studentId, String categoryId)
    {
        if(studentId < 1 || categoryId.equals("UNKNOWN"))
        {
            return  false;
        }
        return true;
    }

    @Override
    public boolean deleteStudentInterest(int studentInterestId)
    {
        if(studentInterestId < 1)
        {
            return  false;
        }
        return true;
    }

    @Override
    public List<IStudentInterest> getStudentInterests(int studentId)
    {
        List<IStudentInterest> studentInterestList = new ArrayList<>();
        if(studentId == 1)
        {
            return studentInterestList;
        }
        else
        {
            StudentInterest studentInterest = new StudentInterest();
            studentInterest.setStudentInterestId(1);
            studentInterest.setStudentId(studentId);
            studentInterest.setCategoryId("HACKATHON");
            studentInterestList.add(studentInterest);

            studentInterest = new StudentInterest();
            studentInterest.setStudentInterestId(2);
            studentInterest.setStudentId(studentId);
            studentInterest.setCategoryId("HACKATHON");
            studentInterestList.add(studentInterest);

            studentInterest = new StudentInterest();
            studentInterest.setStudentInterestId(3);
            studentInterest.setStudentId(studentId);
            studentInterest.setCategoryId("MEETUP");
            studentInterestList.add(studentInterest);

            studentInterest = new StudentInterest();
            studentInterest.setStudentInterestId(4);
            studentInterest.setStudentId(studentId);
            studentInterest.setCategoryId("SPORTS");
            studentInterestList.add(studentInterest);

            return studentInterestList;
        }
    }
}
