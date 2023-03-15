package com.eventmanagement.EventManagement.DataAccessObjects;

import com.eventmanagement.EventManagement.DataAccessObjects.interfaces.IEventListDAO;
import com.eventmanagement.EventManagement.model.DatabaseDataSource;
import com.eventmanagement.EventManagement.model.Event;
import com.eventmanagement.EventManagement.model.factory.EventFactory;
import org.springframework.jdbc.core.JdbcTemplate;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

public class EventListDAO implements IEventListDAO
{

    public JdbcTemplate jdbcTemplate;

    public EventListDAO()
    {
        jdbcTemplate = new JdbcTemplate(DatabaseDataSource.instance().getDriverManagerDataSource());
    }

    public List<Event> getEventList(){
        String query = "CALL sel_event(?)";
        LocalDate localDate = LocalDate.now();
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String currentDate = dateFormat.format(localDate);

        List<Event> eventList = this.jdbcTemplate.query(query,  EventFactory.instance().makeEventRowMapper(), currentDate);
        return eventList;
    }

    public Optional<Event> getEventById(int eventId){
        String query = "CALL sel_event_by_id(?)";
        Optional<Event> event = this.jdbcTemplate.query(query, EventFactory.instance().makeEventRowMapper(), eventId)
                .stream().findFirst();
        return event;
    }


}
