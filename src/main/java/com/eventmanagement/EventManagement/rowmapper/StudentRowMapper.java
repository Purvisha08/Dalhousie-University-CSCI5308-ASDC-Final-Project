package com.eventmanagement.EventManagement.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.eventmanagement.EventManagement.model.Student;
import com.eventmanagement.EventManagement.model.factory.UserFactory;
import com.eventmanagement.EventManagement.model.interfaces.IUser;

public class StudentRowMapper implements RowMapper<Student> {

        @Override
        public Student mapRow(ResultSet rs, int rowNum) throws SQLException {

            IUser student = UserFactory.instance().makeStudent();
            student.setId(rs.getInt("ID"));
            student.setName(rs.getString("NAME"));
            student.setEmail(rs.getString("EMAIL"));
            if(rs.getTimestamp("created_at") != null) {
                student.setCreatedAt(rs.getTimestamp("created_at").toLocalDateTime());
            } else {
                student.setCreatedAt(null);
            }

            if(rs.getTimestamp("updated_at") != null) {
                student.setUpdatedAt(rs.getTimestamp("updated_at").toLocalDateTime());
            } else {
                student.setUpdatedAt(null);
            }
            return (Student) student;

    }
}
