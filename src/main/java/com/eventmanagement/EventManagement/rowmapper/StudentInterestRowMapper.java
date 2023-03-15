package com.eventmanagement.EventManagement.rowmapper;

import com.eventmanagement.EventManagement.model.StudentInterest;
import com.eventmanagement.EventManagement.model.interfaces.IStudentInterest;
import org.springframework.jdbc.core.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;

public class StudentInterestRowMapper implements RowMapper<IStudentInterest>
{
    @Override
    public IStudentInterest mapRow(ResultSet rs, int rowNum) throws SQLException
    {
        StudentInterest studentInterest = new StudentInterest();
        studentInterest.setStudentInterestId(rs.getInt("studentInterestId"));
        studentInterest.setStudentId(Integer.valueOf(rs.getString("studentId")));
        studentInterest.setCategoryId(rs.getString("categoryId"));
        return studentInterest;
     }
}
