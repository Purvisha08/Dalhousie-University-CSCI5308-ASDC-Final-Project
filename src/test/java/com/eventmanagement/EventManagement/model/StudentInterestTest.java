package com.eventmanagement.EventManagement.model;

import com.eventmanagement.EventManagement.DataAccessObjects.StudentInterestDAOMock;
import com.eventmanagement.EventManagement.DataAccessObjects.interfaces.IStudentInterestDAO;
import com.eventmanagement.EventManagement.model.interfaces.IStudentInterest;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.List;

@SpringBootTest
public class StudentInterestTest
{
    private IStudentInterestDAO studentInterestDAO;

    public StudentInterestTest()
    {
        this.studentInterestDAO = new StudentInterestDAOMock();
    }

    @Test
    public void getStudentInterestsForUserWithInterestsSetTest()
    {
        int studentId = 10;
        List<IStudentInterest> studentInterestsList = this.studentInterestDAO.getStudentInterests(studentId);
        Assertions.assertEquals(4, studentInterestsList.size());
    }

    @Test
    public void getStudentInterestsForUserWithInterestsNotSetTest()
    {
        int studentId = 1;
        List<IStudentInterest> studentInterestsList = this.studentInterestDAO.getStudentInterests(studentId);
        Assertions.assertEquals(0, studentInterestsList.size());
    }

    @Test
    public void addStudentInterestSuccessTest()
    {
        int studentId = 1;
        String categoryId = "MEETUP";
        boolean result = this.studentInterestDAO.addStudentInterest(studentId, categoryId);
        Assertions.assertEquals(true, result);
    }

    @Test
    public void addStudentInterestFailTest()
    {
        int studentId = -1;
        String categoryId = "UNKNOWN";
        boolean result = this.studentInterestDAO.addStudentInterest(studentId, categoryId);
        Assertions.assertEquals(false, result);
    }

    @Test
    public void deleteStudentInterestSuccessTest()
    {
        int studentInterestId = 10;
        boolean result = this.studentInterestDAO.deleteStudentInterest(studentInterestId);
        Assertions.assertEquals(true, result);
    }

    @Test
    public void deleteStudentInterestFailTest()
    {
        int studentInterestId = 0;
        boolean result = this.studentInterestDAO.deleteStudentInterest(studentInterestId);
        Assertions.assertEquals(false, result);
    }

}
