package com.eventmanagement.EventManagement.DataAccessObjects;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import com.eventmanagement.EventManagement.DataAccessObjects.interfaces.IStudentDAO;
import com.eventmanagement.EventManagement.model.Student;
import com.eventmanagement.EventManagement.model.Wallet;
import com.eventmanagement.EventManagement.model.factory.UserFactory;
import com.eventmanagement.EventManagement.model.interfaces.IUser;

public class StudentDAOMock implements IStudentDAO {

    public List<Student> findAll() {
        List<Student> students = new ArrayList<Student>();
        Integer[] mockStudentIds = {1, 2, 3, 4, 5};

        for(int id: mockStudentIds) {
            Student student = UserFactory.instance().makeStudent();
            student.setId(id);
            student.setName("Student "+id);
            student.setEmail("student"+id+"@example.com");
            try {
                student.setCreatedAt(LocalDateTime.now());
                student.setUpdatedAt(LocalDateTime.now());
            } catch (Exception e) {
                student.setCreatedAt(null);
                student.setUpdatedAt(null);
            }
            students.add(student);
        }
        return students;
    };

    public Optional<Student> findByEmail(String email) {
        if(email == null || email.trim().length() == 0) {
            return Optional.empty();
        }
        Student student = UserFactory.instance().makeStudent();
        student.setId(1);
        student.setName("Student 1");
        student.setEmail(email);
        try {
            student.setCreatedAt(LocalDateTime.now());
            student.setUpdatedAt(LocalDateTime.now());
        } catch (Exception e) {
            student.setCreatedAt(null);
            student.setUpdatedAt(null);
        }
        return Optional.of(student);
    };

    public Optional<Student> findById(int id) {
        if(id == 0) {
            return Optional.empty();
        }
        if(id == 1) {
            Student student = UserFactory.instance().makeStudent();
            student.setId(id);
            student.setName("Student 1");
            student.setEmail("student1@example.com");
            try {
                student.setCreatedAt(LocalDateTime.now());
                student.setUpdatedAt(LocalDateTime.now());
            } catch (Exception e) {
                student.setCreatedAt(null);
                student.setUpdatedAt(null);
            }
            return Optional.of(student);
        } else {
            return Optional.empty();
        }
    };

    public List<Student> findAllByIds(List<Integer> studentIds) {
        Integer[] validIds = {1, 2, 3, 4, 5};
        List<Integer> validIdList = new ArrayList<>(Arrays.asList(validIds));
        List<Student> students = new ArrayList<Student>();

        for(int id: studentIds) {
            if(validIdList.contains(id)) {
                Student student = UserFactory.instance().makeStudent();
                student.setId(id);
                student.setName("Student "+id);
                student.setEmail("student"+id+"@example.com");
                try {
                    student.setCreatedAt(LocalDateTime.now());
                    student.setUpdatedAt(LocalDateTime.now());
                } catch (Exception e) {
                    student.setCreatedAt(null);
                    student.setUpdatedAt(null);
                }
                students.add(student);
            }
        }
        return students;
    };

    public int create(IUser student) {
        if (student.getName().equals("New Student")) {
			student.setId(1);
			return student.getId();
		} else {
            return 0;
        }
    };

    public int updateStudent(int id, Student student) {
        if (student.getName().equals("Update Student")) {
			return student.getId();
		} else {
            return 0;
        }
    };

    public int deleteStudent(int id) {
        if (id == 1) {
			return 1;
		} else {
            return 0;
        }
    };

    public boolean verifyCredentials(String email, String password) {
        List<Student> allStudents = this.findAll();
        for(Student student : allStudents) {
            student.setPassword("strongpassword");
        }

        Student thisStudent = null;
        for(Student student : allStudents) {
            if(student.getEmail().equals(email)) {
                thisStudent = student;
                break;
            }
        }

        if(thisStudent == null){
            return false;
        }

        if(thisStudent.getPassword().equals(password)) {
            return true;
        }

        return false;
    };

    public Double getWalletBalance(Integer studentId) {
        Integer[] mockStudentIds = {1, 2, 3, 4, 5};
        List<Wallet> wallets = new ArrayList<>();
        for(Integer id : mockStudentIds) {
            Wallet wallet = new Wallet(id);
            wallet.setCurrentBalance(Double.valueOf(id*12));
        }

        for(Wallet wallet : wallets) {
            if(wallet.getStudentId() == studentId) {
                return wallet.getCurrentBalance();
            }
        }

        return null;
    };
}
