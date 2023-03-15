package com.eventmanagement.EventManagement.DataAccessObjects;

import com.eventmanagement.EventManagement.DataAccessObjects.interfaces.IEventRegistrationDAO;
import com.eventmanagement.EventManagement.model.*;
import com.eventmanagement.EventManagement.model.factory.EventFactory;
import com.eventmanagement.EventManagement.model.interfaces.IEventRegistration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public class EventRegistrationDAO implements IEventRegistrationDAO
{

    public JdbcTemplate jdbcTemplate;
    public EventRegistrationDAO()
    {
        jdbcTemplate = new JdbcTemplate(DatabaseDataSource.instance().getDriverManagerDataSource());
    }

    @Override
    public boolean addRegistration(int eventId, int studentId, Date registrationDate, String description, Double price)
    {
        try
        {
            String sql = "CALL usp_registration_add(?, ?, ?, ?, ?)";
            jdbcTemplate.update(sql, eventId,
                    studentId,
                    registrationDate,
                    description,
                    price);
            return true;
        }
        catch (Exception ex)
        {
            System.out.println(ex.getMessage());
            return false;
        }
    }

    @Override
    public int updateRegistration(EventRegistration registration) {
        String sql = "CALL usp_registration_update(?, ?, ?)";
        return jdbcTemplate.update(
                sql,
                registration.getRegistrationId(),
                registration.getStatus(),
                registration.getRating()
            );
    }

    @Override
    public List<IEventRegistration> getAllRegistrationByStudentId(Integer studentId)
    {
        try
        {
            String sql = "CALL usp_registration_select_by_student(?)";
            return jdbcTemplate.query(sql, EventFactory.instance().makeEventRegistrationRowMapper(),studentId);
        }
        catch (Exception ex)
        {
            System.out.println(ex.getMessage());
            return null;
        }
    }

    @Override
    public List<IEventRegistration> findByEventId(Integer eventId) {
        String query = "CALL usp_registration_findByEventId(?)";
        return jdbcTemplate.query(query, EventFactory.instance().makeEventRegistrationRowMapper(), eventId);
    }

    @Override
    public Optional<IEventRegistration> getRegistrationById(Integer registrationId)
    {
        try
        {
            String sql = "CALL usp_registration_select_by_registrationId(?)";
            return jdbcTemplate.query(sql, EventFactory.instance().makeEventRegistrationRowMapper(),registrationId).stream().findFirst();
        }
        catch (Exception ex)
        {
            System.out.println(ex.getMessage());
            return Optional.empty();
        }
    }

    @Override
    public Optional<IEventRegistration> getRegistrationByStudentAndEventId(Integer studentId, Integer eventId)
    {
        try
        {
            String sql = "CALL usp_registration_select_by_student_event_id(?,?)";
            return jdbcTemplate.query(sql, EventFactory.instance().makeEventRegistrationRowMapper(),studentId,eventId).stream().findFirst();
        }
        catch (Exception ex)
        {
            System.out.println(ex.getMessage());
            return Optional.empty();
        }
    }

    @Override
    public boolean cancelRegistration(int registrationId, double refundPrice, String description)
    {
        try
        {
            String sql = "CALL usp_registration_cancel(?,?,?)";
            jdbcTemplate.update(sql, registrationId,
                    refundPrice,
                    description);
            return true;
        }
        catch (Exception ex)
        {
            System.out.println(ex.getMessage());
            return false;
        }
    }

}
