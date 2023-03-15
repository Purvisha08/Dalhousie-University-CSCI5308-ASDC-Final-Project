package com.eventmanagement.EventManagement.model;

import com.eventmanagement.EventManagement.DataAccessObjects.EventDAOMock;
import com.eventmanagement.EventManagement.DataAccessObjects.EventRegistrationDAOMock;
import com.eventmanagement.EventManagement.DataAccessObjects.interfaces.IEventDAO;
import com.eventmanagement.EventManagement.DataAccessObjects.interfaces.IEventRegistrationDAO;
import com.eventmanagement.EventManagement.model.interfaces.IRegistrationCancellation;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class RegistrationCancellationTest
{
    private IEventRegistrationDAO eventRegistrationDAO;
    private IEventDAO eventDAO;

    public RegistrationCancellationTest()
    {
        this.eventRegistrationDAO = new EventRegistrationDAOMock();
        this.eventDAO = new EventDAOMock();
    }

    @Test
    public void cancelSuccessTest()
    {
        int registerationId = 1;
        IRegistrationCancellation eventCancellation = new RegistrationCancellation(eventRegistrationDAO,eventDAO);
        eventCancellation.loadDetails(registerationId);
        boolean result = eventCancellation.cancel(registerationId);
        Assertions.assertEquals(true, result);
    }

    @Test
    public void cancelFailTest()
    {
        int registerationId = 5;
        IRegistrationCancellation eventCancellation = new RegistrationCancellation(eventRegistrationDAO,eventDAO);
        eventCancellation.loadDetails(registerationId);
        boolean result = eventCancellation.cancel(registerationId);
        Assertions.assertEquals(false,result);
    }

}
