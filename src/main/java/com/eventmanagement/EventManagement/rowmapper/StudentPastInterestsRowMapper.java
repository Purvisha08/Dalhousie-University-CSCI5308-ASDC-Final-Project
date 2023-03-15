package com.eventmanagement.EventManagement.rowmapper;

import com.eventmanagement.EventManagement.model.StudentPastInterest;
import com.eventmanagement.EventManagement.model.factory.StudentPastInterestFactory;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class StudentPastInterestsRowMapper implements RowMapper<StudentPastInterest> {

    @Override
    public StudentPastInterest mapRow(ResultSet rs, int rowNum) throws SQLException {
        StudentPastInterest studentPastInterest = StudentPastInterestFactory.getInstance().makeStudentPastInterest();
        studentPastInterest.setInterest(rs.getString("category"));
        return studentPastInterest;
    }

}
