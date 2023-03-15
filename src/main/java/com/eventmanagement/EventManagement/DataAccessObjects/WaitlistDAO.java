package com.eventmanagement.EventManagement.DataAccessObjects;

import com.eventmanagement.EventManagement.DataAccessObjects.interfaces.IWaitlistDAO;
import com.eventmanagement.EventManagement.model.DatabaseDataSource;
import com.eventmanagement.EventManagement.model.interfaces.IWaitlist;
import com.eventmanagement.EventManagement.rowmapper.WaitlistRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public class WaitlistDAO implements IWaitlistDAO
{
    public JdbcTemplate jdbcTemplate;
    public WaitlistDAO()
    {
        jdbcTemplate = new JdbcTemplate(DatabaseDataSource.instance().getDriverManagerDataSource());
    }

    @Override
    public boolean addWaitlist(int eventId, int studentId)
    {
        try
        {
            String sql = "CALL usp_waitlist_add(?, ?)";
            jdbcTemplate.update(sql, eventId, studentId);
            return true;
        }
        catch (Exception ex)
        {
            System.out.println(ex.getMessage());
            return false;
        }
    }

    @Override
    public List<IWaitlist> getWaitlistForStudent(Integer studentId) {
        try
        {
            String sql = "CALL usp_waitlist_select_by_student(?)";
            return jdbcTemplate.query(sql, new WaitlistRowMapper(),studentId);
        }
        catch (Exception ex)
        {
            System.out.println(ex.getMessage());
            return null;
        }
    }

    @Override
    public List<IWaitlist> getWaitlistForEvent(Integer eventId) {
        try
        {
            String sql = "CALL usp_waitlist_select_by_event(?)";
            return jdbcTemplate.query(sql, new WaitlistRowMapper(),eventId);
        }
        catch (Exception ex)
        {
            System.out.println(ex.getMessage());
            return null;
        }
    }

    @Override
    public boolean deleteWaitlist(int eventId, int studentId) {
        try
        {
            String sql = "CALL usp_waitlist_delete(?,?)";
            jdbcTemplate.update(sql, eventId, studentId);
            return true;
        }
        catch (Exception ex)
        {
            System.out.println(ex.getMessage());
            return false;
        }
    }
}
