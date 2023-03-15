package com.eventmanagement.EventManagement.DataAccessObjects;

import com.eventmanagement.EventManagement.DataAccessObjects.interfaces.IStudentInterestDAO;
import com.eventmanagement.EventManagement.model.DatabaseDataSource;
import com.eventmanagement.EventManagement.model.interfaces.IStudentInterest;
import com.eventmanagement.EventManagement.rowmapper.StudentInterestRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public class StudentInterestDAO implements IStudentInterestDAO
{
    public JdbcTemplate jdbcTemplate ;

    public StudentInterestDAO()
    {
        jdbcTemplate = new JdbcTemplate(DatabaseDataSource.instance().getDriverManagerDataSource());
    }

    @Override
    public boolean addStudentInterest(int studentId, String categoryId) {
        try
        {
            String sql = "CALL usp_studentinterest_insert(?, ?)";
            jdbcTemplate.update(sql, studentId, categoryId);
            return true;
        }
        catch (Exception ex)
        {
            System.out.println(ex.getMessage());
            return false;
        }
    }

    @Override
    public boolean deleteStudentInterest(int studentInterestId) {
        try
        {
            String sql = "CALL usp_studentinterest_delete(?)";
            jdbcTemplate.update(sql, studentInterestId);
            return true;
        }
        catch (Exception ex)
        {
            System.out.println(ex.getMessage());
            return false;
        }
    }

    @Override
    public List<IStudentInterest> getStudentInterests(int studentId) {
        try
        {
            String sql = "CALL usp_studentinterest_select(?)";
            return jdbcTemplate.query(sql, new StudentInterestRowMapper(),studentId);
        }
        catch (Exception ex)
        {
            System.out.println(ex.getMessage());
            return null;
        }

    }
}
