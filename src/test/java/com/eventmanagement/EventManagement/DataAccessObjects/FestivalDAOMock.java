package com.eventmanagement.EventManagement.DataAccessObjects;

import com.eventmanagement.EventManagement.DataAccessObjects.interfaces.IFestivalDAO;
import com.eventmanagement.EventManagement.model.Event;
import com.eventmanagement.EventManagement.model.Festival;
import org.springframework.stereotype.Repository;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

@Repository
public class FestivalDAOMock implements IFestivalDAO {

    @Override
    public List<Festival> findByHostId(Integer host_id) {
        DateFormat formatter = new SimpleDateFormat("dd-MMM-yy");
        List<Festival> festivals = new ArrayList<Festival>();
        if (host_id == 1) {
            Festival festival = new Festival();
            festival.setId(11);
            festival.setName("Festival 1");
            festival.setDescription("Festival 1 description");
            festival.setUniversity("Dalhousie");
            festival.setHostId(1);
            try {
                festival.setStartDate(formatter.parse("01-01-2022"));
                festival.setEndDate(formatter.parse("01-02-2022"));
            } catch (Exception e) {
                festival.setStartDate(null);
                festival.setEndDate(null);
            }
            festivals.add(festival);
        }
        return festivals;
    }

    @Override
    public Optional<Festival> findById(int id) {
        DateFormat formatter = new SimpleDateFormat("dd-MMM-yy");
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        if (id == 10) {
            Festival festival = new Festival();
            festival.setName("Updated Festival");
            festival.setDescription("New Description");
            festival.setUniversity("New University");
            return Optional.of(festival);
        } else if (id == 11) {
            Festival festival = new Festival();
            festival.setId(id);
            festival.setName("Festival 1");
            festival.setDescription("Festival 1 description");
            festival.setUniversity("Festival 1 University");
            festival.setHostId(1);
            try {
                festival.setStartDate(formatter.parse("01-01-2022"));
                festival.setEndDate(formatter.parse("01-02-2022"));
            } catch (Exception e) {
                festival.setStartDate(null);
                festival.setEndDate(null);
            }
            return Optional.of(festival);
        } else if (id == 1) {
            Festival festival = new Festival();
            festival.setId(id);
            festival.setName("Festival 1");
            festival.setDescription("Some description for festival 1");
            festival.setUniversity("Dalhousie");
            festival.setHostId(1);
            calendar.add(Calendar.DATE, 5);
            festival.setStartDate(calendar.getTime());
            calendar.add(Calendar.DATE, 2);
            festival.setEndDate(calendar.getTime());
            return Optional.of(festival);
        } else {
            return Optional.empty();
        }
    }

    @Override
    public List<Festival> filterBySearchQuery(Integer host_id, String searchQuery) {
        return null;
    }

    @Override
    public List<Event> findAllEvents(Integer festivalId) {
        return null;
    }

    @Override
    public Integer updateRating(Festival festival) {
        if (festival.getId() == 1) {
            festival.setRating(100.0);
            return 1;
        } else {
            return 0;
        }
    }

    @Override
    public Integer updateFestival(Festival festival) {
        if (festival.getId() == 10) {
            festival.setName("Updated Festival");
            return 1;
        } else {
            return 0;
        }

    }

    @Override
    public int addFestival(Festival festival) {
        if (festival.getId() == 1) {
            festival.setName("New Festival");
            return 1;
        } else {
            return 0;
        }
    }

}
