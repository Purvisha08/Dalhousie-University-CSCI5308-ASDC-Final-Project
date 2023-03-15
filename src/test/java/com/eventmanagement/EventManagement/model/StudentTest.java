package com.eventmanagement.EventManagement.model;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import com.eventmanagement.EventManagement.DataAccessObjects.StudentDAOMock;
import com.eventmanagement.EventManagement.DataAccessObjects.UserDAOMock;
import com.eventmanagement.EventManagement.DataAccessObjects.interfaces.IStudentDAO;
import com.eventmanagement.EventManagement.DataAccessObjects.interfaces.IUserDAO;
import com.eventmanagement.EventManagement.model.factory.UserFactory;
import com.eventmanagement.EventManagement.model.interfaces.IUser;

public class StudentTest {

    @Test
    public void findByIdTest() throws Exception {
		try {
			IStudentDAO studentDAO = new StudentDAOMock();
			Student Student1 = Student.findById(studentDAO, 1);
			Assertions.assertNotNull(Student1);
            Assertions.assertEquals(Student1.getName(), "Student 1");
            Assertions.assertEquals(Student1.getEmail(), "student1@example.com");

            Student Student2 = Student.findById(studentDAO, 20);
            Assertions.assertNull(Student2);

		} catch (Exception e) {
			Assertions.fail("Test 'GetStudentById' failed:" + e.getMessage());
		}
    }

    @Test
    public void findByEmailTest() throws Exception {
        try {
			IStudentDAO studentDAO = new StudentDAOMock();
			Student Student1 = Student.findById(studentDAO, 1);
			Assertions.assertNotNull(Student1);
            Assertions.assertEquals(Student1.getName(), "Student 1");
            Assertions.assertEquals(Student1.getEmail(), "student1@example.com");

            Student Student2 = Student.findById(studentDAO, 20);
            Assertions.assertNull(Student2);

		} catch (Exception e) {
			Assertions.fail("Test 'GetStudentById' failed:" + e.getMessage());
		}
    }

    @Test
    public void findAllByIdsTest() throws Exception {

        try {
            Integer[] presentIds = {1, 2, 3};
            List<Integer> mockStudentIds = Arrays.asList(presentIds);
			IStudentDAO studentDAO = new StudentDAOMock();
			List<Student> students= Student.findAllByIds(studentDAO, mockStudentIds);
			Assertions.assertNotNull(students);
            Assertions.assertEquals(presentIds.length, students.size());

            Integer[] notPresentIds = {10, 20, 30, 40, 50};
            mockStudentIds = Arrays.asList(notPresentIds);
            List<Student> studentList2 = Student.findAllByIds(studentDAO, mockStudentIds);
            Assertions.assertNotNull(studentList2);
            Assertions.assertEquals(0, studentList2.size());

		} catch (Exception e) {
			Assertions.fail("Test 'GetStudentByFestivalId' failed:" + e.getMessage());
		}
    }

    @Test
    public void createStudentTest() throws Exception {
        try {
            IUserDAO studentDAO = new UserDAOMock();
            IUser student = UserFactory.instance().makeStudent();
            student.setName("New User");
            student.setEmail("student1@example.com");
            student.setPassword("strongpassword");
            student.setPasswordConfirm("strongpassword");
            Boolean created = student.create(studentDAO);
            Assertions.assertTrue(created);

            student.setName("Old Student");
            created = student.create(studentDAO);
            Assertions.assertFalse(created);

		} catch (Exception e) {
			Assertions.fail("Test 'CreateStudentAccountTest' failed:" + e.getMessage());
		}
    }

    @Test
    public void verifyCredentailsTest() throws Exception {
        try {
            IUserDAO studentDAO = new UserDAOMock();
            IUser user = UserFactory.instance().makeUser();
            user.setEmail("validUser@example.com");
            user.setPassword("validPassword");
            Boolean loggedIn = user.verifyCredentials(studentDAO);
            Assertions.assertTrue(loggedIn);

            user.setEmail("invalidUser@example.com");
            loggedIn = user.verifyCredentials(studentDAO);
            Assertions.assertFalse(loggedIn);

		} catch (Exception e) {
			Assertions.fail("Test 'LoginTest' failed:" + e.getMessage());
		}
    }

}
