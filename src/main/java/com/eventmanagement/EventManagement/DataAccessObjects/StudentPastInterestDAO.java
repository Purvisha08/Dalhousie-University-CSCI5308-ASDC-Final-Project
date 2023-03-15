package com.eventmanagement.EventManagement.DataAccessObjects;

import com.eventmanagement.EventManagement.DataAccessObjects.interfaces.IStudentPastInterestDAO;
import com.eventmanagement.EventManagement.model.DatabaseDataSource;
import com.eventmanagement.EventManagement.model.StudentPastInterest;
import com.eventmanagement.EventManagement.rowmapper.StudentPastInterestsRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import java.util.ArrayList;

public class StudentPastInterestDAO implements IStudentPastInterestDAO {

    private JdbcTemplate jdbcTemplate;

    public StudentPastInterestDAO()
    {
        jdbcTemplate = new JdbcTemplate(DatabaseDataSource.instance().getDriverManagerDataSource());
    }

    public ArrayList<StudentPastInterest> getPastInterest(int userId){
        String query = "CALL usp_student_past_interest(?)";
        ArrayList<StudentPastInterest> studentPastInterestList = (ArrayList<StudentPastInterest>) this.jdbcTemplate.query(query, new StudentPastInterestsRowMapper(), userId);
        return studentPastInterestList;
    }

}
