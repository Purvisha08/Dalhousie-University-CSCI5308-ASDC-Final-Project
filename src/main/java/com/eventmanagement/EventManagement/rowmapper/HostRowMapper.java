package com.eventmanagement.EventManagement.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.eventmanagement.EventManagement.model.Host;
import com.eventmanagement.EventManagement.model.factory.UserFactory;

public class HostRowMapper implements RowMapper<Host> {
    @Override
    public Host mapRow(ResultSet rs, int rowNum) throws SQLException {

        Host host = UserFactory.instance().makeHost();
        host.setId(rs.getInt("id"));
        host.setName(rs.getString("name"));
        host.setEmail(rs.getString("email"));
        host.setRating(rs.getDouble("rating"));
        return host;
    }
}
