package com.eventmanagement.EventManagement.model;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import com.eventmanagement.EventManagement.DataAccessObjects.HostDAOMock;
import com.eventmanagement.EventManagement.DataAccessObjects.UserDAOMock;
import com.eventmanagement.EventManagement.DataAccessObjects.interfaces.IHostDAO;
import com.eventmanagement.EventManagement.DataAccessObjects.interfaces.IUserDAO;
import com.eventmanagement.EventManagement.model.factory.UserFactory;
import com.eventmanagement.EventManagement.model.interfaces.IUser;

public class HostTest {

    @Test
    public void createHostTest() throws Exception {
        try {
            IUserDAO hostDAO = new UserDAOMock();
            IUser host = UserFactory.instance().makeHost();
            host.setName("New User");
            host.setEmail("host1@example.com");
            host.setPassword("strongpassword");
            host.setPasswordConfirm("strongpassword");
            Boolean created = host.create(hostDAO);
            Assertions.assertTrue(created);

            host.setName("Old Host");
            created = host.create(hostDAO);
            Assertions.assertFalse(created);

		} catch (Exception e) {
			Assertions.fail("Test 'CreateHostTest' failed:" + e.getMessage());
		}
    }

    @Test
    public void verifyCredentailsTest() throws Exception {
        try {
            IUserDAO hostDAO = new UserDAOMock();
            IUser host = UserFactory.instance().makeHost();
            host.setEmail("validUser@example.com");
            host.setPassword("validPassword");
            Boolean validated = host.verifyCredentials(hostDAO);
            Assertions.assertTrue(validated);

            host.setName("Old Host");
            validated = host.create(hostDAO);
            Assertions.assertFalse(validated);

		} catch (Exception e) {
			Assertions.fail("Test 'VerifyCredentailsTest' failed:" + e.getMessage());
		}
    }

    @Test
    public void findByEmailTest() {
        try {
			IHostDAO hostDAO = new HostDAOMock();
			Host host1 = Host.findByEmail(hostDAO, "host1@example.com");
			Assertions.assertNotNull(host1);
            Assertions.assertEquals(host1.getName(), "Host 1");
            Assertions.assertEquals(host1.getEmail(), "host1@example.com");

            Host host2 = Host.findById(hostDAO, 20);
            Assertions.assertNull(host2);

		} catch (Exception e) {
			Assertions.fail("Test 'FindHostByEmail' failed:" + e.getMessage());
		}
    }

    @Test
    public void findByIdTest() {
        try {
			IHostDAO hostDAO = new HostDAOMock();
			Host host1 = Host.findById(hostDAO, 1);
			Assertions.assertNotNull(host1);
            Assertions.assertEquals(host1.getName(), "Host 1");
            Assertions.assertEquals(host1.getEmail(), "host1@example.com");

            Host host2 = Host.findById(hostDAO, 20);
            Assertions.assertNull(host2);

		} catch (Exception e) {
			Assertions.fail("Test 'FindHostById' failed:" + e.getMessage());
		}
    }

    @Test
    public void updateRatingTest() {
        try {
			IHostDAO hostDAO = new HostDAOMock();
			Host host1 = Host.findById(hostDAO, 1);

            Assertions.assertNotNull(host1);

            Boolean updateResult = host1.updateRating(hostDAO);
            Assertions.assertTrue(updateResult);
            Assertions.assertEquals(host1.getRating(), 10.0);

            Host host2 = Host.findById(hostDAO, 20);
            Assertions.assertNull(host2);

		} catch (Exception e) {
			Assertions.fail("Test 'UpdateHostRatingTest' failed:" + e.getMessage());
		}
    }
}
