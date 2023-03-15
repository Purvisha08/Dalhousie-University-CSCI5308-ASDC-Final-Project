package com.eventmanagement.EventManagement.model;

import com.eventmanagement.EventManagement.DataAccessObjects.FestivalDAOMock;
import com.eventmanagement.EventManagement.DataAccessObjects.interfaces.IFestivalDAO;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
public class FestivalTest {

    @Test
    public void getFestivalByIdTest() throws Exception {
        try {
            IFestivalDAO festivalDao = new FestivalDAOMock();
            Festival festival1= Festival.findById(festivalDao, 11);
            Assertions.assertNotNull(festival1);
            Assertions.assertEquals(festival1.getName(), "Festival 1");
            Assertions.assertEquals(festival1.getDescription(), "Festival 1 description");
            Assertions.assertEquals(festival1.getUniversity(),"Festival 1 University");
            Assertions.assertEquals(festival1.getHostId(), 1);
            Festival festival2 = Festival.findById(festivalDao, 20);
            Assertions.assertNull(festival2);
        } catch (Exception e) {
            Assertions.fail("Test 'GetFEstivalById' failed:" + e.getMessage());
        }
    }


    @Test
    public void updateFestivalTest() throws Exception {
        Festival festival = new Festival();
        festival.setId(10);
        festival.setName("Update Festival");
        try {
            IFestivalDAO festivalDAO = new FestivalDAOMock();
            Festival festival1 = festival.update(festivalDAO);
            Assertions.assertNotNull(festival1);
            Assertions.assertEquals(festival1.getName(), "Updated Festival");
            Assertions.assertEquals(festival1.getDescription(), "New Description");
            Assertions.assertEquals(festival1.getUniversity(),"New University");

            festival.setId(2);
            festival.setName("Don't Update Festival");
            Festival festival2 = festival.update(festivalDAO);
            Assertions.assertNull(festival2);

        } catch (Exception e) {
           Assertions.fail("Test 'UpdateFestivalTest' failed:" + e.getMessage());
       }
    }
}
