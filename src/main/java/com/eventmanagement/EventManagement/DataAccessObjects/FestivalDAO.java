package com.eventmanagement.EventManagement.DataAccessObjects;

import com.eventmanagement.EventManagement.DataAccessObjects.interfaces.IFestivalDAO;
import com.eventmanagement.EventManagement.model.DatabaseDataSource;
import com.eventmanagement.EventManagement.model.Event;
import com.eventmanagement.EventManagement.model.Festival;
import com.eventmanagement.EventManagement.model.factory.EventFactory;
import com.eventmanagement.EventManagement.rowmapper.FestivalRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import java.util.*;

@Repository
public class FestivalDAO implements IFestivalDAO {
    public JdbcTemplate jdbcTemplate;

    public FestivalDAO() {
        jdbcTemplate = new JdbcTemplate(DatabaseDataSource.instance().getDriverManagerDataSource());
    }

    public List<Festival> findByHostId(Integer host_id) {
        String query = "CALL usp_festival_findByHostId(?)";
        return jdbcTemplate.query(query, new FestivalRowMapper(), host_id);
    }


    public Optional<Festival> findById(int id) {
        String query = "CALL usp_festival_findById(?)";
        return jdbcTemplate.query(query, new FestivalRowMapper(), id)
                .stream()
                .findFirst();
    }
    
    public List<Festival> filterBySearchQuery(Integer host_id, String searchQuery) {
        String query = "CALL usp_festival_filterBySearchQuery(?, ?)";
        searchQuery = "%"+searchQuery+"%";
        return jdbcTemplate.query(query, new FestivalRowMapper(), host_id, searchQuery);
    }

    public List<Event> findAllEvents(Integer festivalId) {
        String query = "CALL usp_festival_findAllEvents(?)";
        return jdbcTemplate.query(query, EventFactory.instance().makeEventRowMapper(), festivalId);
    }

    @Override
    public Integer updateRating(Festival festival) {
        String sql = "CALL usp_festival_updateRating(?, ?)";
        return jdbcTemplate.update(
                sql,
                festival.getId(),
                festival.getRating()
            );
    }

    public int addFestival(Festival festival) {
        String sql = "CALL usp_festival_insert(?, ?, ?, ?, ?, ?)";
        java.sql.Date startDate = new java.sql.Date(festival.getStartDate().getTime());
        java.sql.Date endDate = new java.sql.Date(festival.getEndDate().getTime());
        jdbcTemplate.update(
                sql,
                festival.getHostId(),
                festival.getName(),
                festival.getDescription(),
                festival.getUniversity(),
                startDate,
                endDate

        );

        long id = jdbcTemplate.queryForObject(" SELECT LAST_INSERT_ID();", long.class);
        return (int) id;
    }

    public Integer updateFestival(Festival festival) {
        String sql = "CALL usp_festival_update(?, ?, ?, ?, ?, ?)";
        return jdbcTemplate.update(
                sql,
                festival.getId(),
                festival.getName(),
                festival.getDescription(),
                festival.getStartDate(),
                festival.getEndDate(),
                festival.getUniversity()

        );
    }

}
