package com.eventmanagement.EventManagement;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.eventmanagement.EventManagement.controller.HomePageController;

@SpringBootTest
class EventManagementApplicationTests {

	@Autowired
    private HomePageController homePageController;

	@Test
	void contextLoads() {
		assertNotNull(homePageController);
	}

}
