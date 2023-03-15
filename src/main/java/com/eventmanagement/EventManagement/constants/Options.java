package com.eventmanagement.EventManagement.constants;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Options {

    private static Options singleInstance;

    public static Options instance() {
        if(singleInstance == null) {
            singleInstance = new Options();
        }
        return singleInstance;
    }

    public List<String> getEventCategoryOptions() {
        String[] categories = {"Bootcamp", "Hackathon", "Meetup"};
        List<String> options = new ArrayList<String>(Arrays.asList(categories));
        return options;
    }

    public List<String> getUniversityOptions() {
        String[] universities = {"Dalhousie", "St. Mary's", "Acadia"};
        List<String> options = new ArrayList<String>(Arrays.asList(universities));
        return options;
    }

    public List<String> getReceipientTypeOptions() {
        String[] universities = {"All", "Waitlist", "Participants"};
        List<String> options = new ArrayList<String>(Arrays.asList(universities));
        return options;
    }
}
