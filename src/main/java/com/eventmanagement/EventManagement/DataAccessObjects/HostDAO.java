package com.eventmanagement.EventManagement.DataAccessObjects;

import com.eventmanagement.EventManagement.DataAccessObjects.interfaces.IHostDAO;
import com.eventmanagement.EventManagement.DataAccessObjects.interfaces.IUserDAO;
import com.eventmanagement.EventManagement.model.DatabaseDataSource;
import com.eventmanagement.EventManagement.model.Event;
import com.eventmanagement.EventManagement.model.Host;
import com.eventmanagement.EventManagement.model.factory.EventFactory;
import com.eventmanagement.EventManagement.model.factory.UserFactory;
import com.eventmanagement.EventManagement.model.interfaces.IUser;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public class HostDAO implements IHostDAO, IUserDAO
{
    public JdbcTemplate jdbcTemplate ;

    public HostDAO()
    {
        jdbcTemplate = new JdbcTemplate(DatabaseDataSource.instance().getDriverManagerDataSource());
    }

    public Optional<Host> findByEmail(String email) {
        String query = "CALL usp_host_findByEmail(?)";
        return jdbcTemplate.query(query, UserFactory.instance().makeHostRowMapper(), email)
                .stream()
                .findFirst();
    }

    public Optional<Host> findById(Integer id) {
        String query = "CALL usp_host_findById(?)";
        return jdbcTemplate.query(query, UserFactory.instance().makeHostRowMapper(), id)
                .stream()
                .findFirst();
    }

    @Override
    public List<Event> findAllEvents(Integer hostId) {
        String query = "CALL usp_host_findAllEvents(?)";
        return jdbcTemplate.query(query, EventFactory.instance().makeEventRowMapper(), hostId);
    }

    @Override
    public Integer updateRating(Host host) {
        String sql = "CALL usp_host_updateRating(?, ?)";
        return jdbcTemplate.update(
                sql,
                host.getId(),
                host.getRating()
            );
    }
    public Boolean create(IUser host)
    {
        Object currentDateTime = new java.sql.Timestamp(new Date().getTime());
        String sql = "CALL usp_host_create(?, ?, ?, ?, ?, ?, ?)";
        int res =  jdbcTemplate.update(
                sql,
                host.getName(),
                host.getEmail(),
                host.getPassword(),
                host.getUniversity(),
                host.getContactNumber(),
                currentDateTime,
                currentDateTime
            );
        if(res == 1)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    @Override
    public Boolean verifyCredentials(String hostEmail, String hostPassword)
    {
        SimpleJdbcCall jdbcCall = new SimpleJdbcCall(jdbcTemplate).withProcedureName("authenticate_host");
        HashMap<String, Object> inParameters = new HashMap<String, Object>();
        inParameters.put("email",hostEmail);
        inParameters.put("pwd",hostPassword);
        SqlParameterSource in = new MapSqlParameterSource().addValues(inParameters);
        Map<String, Object> outParameters = jdbcCall.execute(in);
        Boolean result = false;
        if(outParameters.containsKey("res"))
        {
            result = (Boolean) outParameters.get("res");
        }
        return result;
    }

}
