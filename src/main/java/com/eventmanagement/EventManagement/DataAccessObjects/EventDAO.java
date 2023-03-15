package com.eventmanagement.EventManagement.DataAccessObjects;

import com.eventmanagement.EventManagement.DataAccessObjects.interfaces.IEventDAO;
import com.eventmanagement.EventManagement.model.DatabaseDataSource;
import com.eventmanagement.EventManagement.model.Event;
import com.eventmanagement.EventManagement.model.factory.EventFactory;
import com.eventmanagement.EventManagement.model.interfaces.IEventRegistration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import java.util.*;

@Repository
public class EventDAO implements IEventDAO
{
    public JdbcTemplate jdbcTemplate;

   public EventDAO()
   {
       jdbcTemplate = new JdbcTemplate(DatabaseDataSource.instance().getDriverManagerDataSource());
   }

    public List<Event> findByFestivalId(Integer festival_id) {
        String query = "CALL usp_event_findByFestivalId(?)";
        return jdbcTemplate.query(query, EventFactory.instance().makeEventRowMapper(), festival_id);
    }

    public Optional<Event> findById(int id) {
        String query = "CALL usp_event_findById(?)";
        return jdbcTemplate.query(query, EventFactory.instance().makeEventRowMapper(), id)
                .stream()
                .findFirst();
    }

    public int addEvent(Event event) {
        String sql = "CALL usp_event_insert(?, ?, ?, ?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(
                sql,
                event.getFestivalId(),
                event.getName(),
                event.getDescription(),
                event.getVenue(),
                event.getCategory(),
                event.getStartDate(),
                event.getEndDate(),
                event.getPrice(),
                event.getCapacity()
            );

        long id = jdbcTemplate.queryForObject(" SELECT LAST_INSERT_ID();", long.class);
        return (int) id;
    }

    public int updateEvent(Event event) {
        String sql = "CALL usp_event_update(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        return jdbcTemplate.update(
                sql,
                event.getId(),
                event.getName(),
                event.getDescription(),
                event.getVenue(),
                event.getCategory(),
                event.getStartDate(),
                event.getEndDate(),
                event.getPrice(),
                event.getCapacity(),
                event.getCompleted(),
                event.getWinner(),
                event.getFirstRunnerUp(),
                event.getSecondRunnerUp()
            );
    }

    public int deleteEvent(int id) {
        String sql = "CALL usp_event_delete(?)";
        return jdbcTemplate.update(sql, id);
    }

    @Override
    public List<IEventRegistration> findRegistrationsById(Integer eventId) {
        String query = "CALL usp_registration_findByEventId(?)";
        return jdbcTemplate.query(query, EventFactory.instance().makeEventRegistrationRowMapper(), eventId);
    }

    @Override
    public Integer updateRating(Event event) {
        String sql = "CALL usp_event_updateRating(?, ?)";
        return jdbcTemplate.update(
                sql,
                event.getId(),
                event.getRating()
            );
    }

}
