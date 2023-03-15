package com.eventmanagement.EventManagement.model.factory;

import com.eventmanagement.EventManagement.model.Host;
import com.eventmanagement.EventManagement.model.Student;
import com.eventmanagement.EventManagement.model.User;
import com.eventmanagement.EventManagement.model.interfaces.IUser;
import com.eventmanagement.EventManagement.rowmapper.HostRowMapper;
import com.eventmanagement.EventManagement.rowmapper.StudentRowMapper;

public class UserFactory {
    private static UserFactory singleInstance;

    public static UserFactory instance()
    {
        if(singleInstance == null)
        {
            singleInstance = new UserFactory();
        }
        return singleInstance;
    }

    public IUser makeUser() {
        return new User();
    }

    public Student makeStudent() {
        return new Student();
    }

    public StudentRowMapper makeStudentRowMapper() {
        return new StudentRowMapper();
    }

    public Host makeHost() {
        return new Host();
    }

    public HostRowMapper makeHostRowMapper() {
        return new HostRowMapper();
    }
}
