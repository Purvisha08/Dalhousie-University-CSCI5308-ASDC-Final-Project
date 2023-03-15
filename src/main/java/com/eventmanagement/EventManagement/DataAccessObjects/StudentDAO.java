package com.eventmanagement.EventManagement.DataAccessObjects;

import com.eventmanagement.EventManagement.DataAccessObjects.interfaces.IStudentDAO;
import com.eventmanagement.EventManagement.DataAccessObjects.interfaces.IUserDAO;
import com.eventmanagement.EventManagement.model.DatabaseDataSource;
import com.eventmanagement.EventManagement.model.Student;
import com.eventmanagement.EventManagement.model.factory.UserFactory;
import com.eventmanagement.EventManagement.model.interfaces.IUser;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;
import java.util.*;
import java.util.stream.Collectors;

@Repository
public class StudentDAO implements IStudentDAO, IUserDAO
{
    public JdbcTemplate jdbcTemplate ;

    public StudentDAO()
    {
        jdbcTemplate = new JdbcTemplate(DatabaseDataSource.instance().getDriverManagerDataSource());
    }

    public List<Student> findAll() {
        String query = "CALL FindAllStudents()";
        return jdbcTemplate.query(query, UserFactory.instance().makeStudentRowMapper());
    }

    public Optional<Student> findByEmail(String email) {
        String query = "CALL sel_student_by_email(?)";
        return jdbcTemplate.query(query, UserFactory.instance().makeStudentRowMapper(), email)
                .stream()
                .findFirst();
    }

    public Optional<Student> findById(int id) {
        String query = "CALL usp_student_findById(?)";
        return jdbcTemplate.query(query, UserFactory.instance().makeStudentRowMapper(), id)
                .stream()
                .findFirst();
    }

    public List<Student> findAllByIds(List<Integer> studentIds) {
        if(studentIds.size() == 0) {
            return new ArrayList<Student>();
        }
        String ids = studentIds.stream().map(Object::toString).collect(Collectors.joining(","));
        String query = "CALL usp_student_findAllByIds(?)";
        return jdbcTemplate.query(query, UserFactory.instance().makeStudentRowMapper(), ids);
    }

    public Boolean create(IUser student) {
        Object currentDateTime = new java.sql.Timestamp(new Date().getTime());
        String sql = "CALL AddStudent(?, ?, ?, ?, ?)";
        Integer result = jdbcTemplate.update(sql, student.getName(),
                student.getEmail(),
                student.getPassword(),
                currentDateTime,
                currentDateTime);
        return result == 1;
    }

    public int updateStudent(int id, Student student) {
        String sql = "CALL UpdateStudent(?, ?, ?, ?)";
        return jdbcTemplate.update(sql, student.getName(),
                student.getEmail(),
                student.getPassword(),
                student.getUpdatedAt());
    }

    public int deleteStudent(int id) {
        String sql = "CALL DeleteStudent(?)";
        return jdbcTemplate.update(sql, id);
    }

    public Boolean verifyCredentials(String email, String password)
    {
        SimpleJdbcCall jdbcCall = new SimpleJdbcCall(jdbcTemplate).withProcedureName("authenticate_student");
        HashMap<String, Object> inParameters = new HashMap<String, Object>();
        inParameters.put("email",email);
        inParameters.put("pwd",password);
        SqlParameterSource in = new MapSqlParameterSource().addValues(inParameters);
        Map<String, Object> outParameters = jdbcCall.execute(in);
        Boolean result = false;
        if(outParameters.containsKey("res"))
        {
            result = (Boolean) outParameters.get("res");
        }
        return result;
    }

    public Double getWalletBalance(Integer studentId)
    {
        Double balance= Double.valueOf(0);
        SimpleJdbcCall jdbcCall = new SimpleJdbcCall(jdbcTemplate).withProcedureName("sel_wallet_balance");
        HashMap<String, Object> inParameters = new HashMap<String, Object>();
        inParameters.put("studentId",studentId);
        SqlParameterSource in = new MapSqlParameterSource().addValues(inParameters);
        Map<String, Object> outParameters = jdbcCall.execute(in);
        if(outParameters.containsKey("balance"))
        {
            balance = (Double)outParameters.get("balance");
        }
        return balance;
    }
}
