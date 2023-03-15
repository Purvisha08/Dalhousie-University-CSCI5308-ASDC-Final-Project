package com.eventmanagement.EventManagement.model;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import com.eventmanagement.EventManagement.validator.FestivalValidator;
import org.springframework.format.annotation.DateTimeFormat;
import com.eventmanagement.EventManagement.DataAccessObjects.FestivalDAO;
import com.eventmanagement.EventManagement.DataAccessObjects.interfaces.IFestivalDAO;
import org.springframework.validation.BindingResult;

public class Festival {

    @Override
    public String toString() {
        return "Festival{" +
                "festival_id=" + festival_id +
                ", host_id=" + host_id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", university='" + university + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                '}';
    }

    private Integer festival_id;
    private String name;
    private String description;
    private Integer host_id;
    private String university;
    private Integer eventCount;
    private Integer totalParticipants;

    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date startDate;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date endDate;

    private double rating;

    // getters and setters
    public Integer getId() {
        return festival_id;
    }

    public void setId(Integer festival_id) {
        this.festival_id = festival_id;
    }

    public Integer getHostId() {
        return this.host_id;
    }

    public void setHostId(Integer host_id) {
        this.host_id = host_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUniversity() {
        return this.university;
    }

    public void setUniversity(String university) {
        this.university = university;
    }

    public Date getStartDate() {
        return this.startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return this.endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public void setEventCount(Integer eventCount) {
        this.eventCount = eventCount;
    }

    public Integer getEventCount() {
        return this.eventCount;
    }

    public void setTotalParticipants(Integer totalParticipants) {
        this.totalParticipants = totalParticipants;
    }

    public Integer getTotalParticipants() {
        return this.totalParticipants;
    }

    public Double getRating() {
        return this.rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    // operations
    public static void validateFestivalData(FestivalValidator festivalValidator, Festival festivalData, BindingResult bindingResult) {
        festivalValidator.validate(festivalData, bindingResult);
    }

    public static List<Festival> findByhostId(FestivalDAO festivalDAO, Integer festival_id) {
        return festivalDAO.findByHostId(festival_id);
    }

    public static Festival findById(IFestivalDAO festivalDAO, Integer id) {
        Optional<Festival> festival = festivalDAO.findById(id);
        if(festival.isPresent()) {
            return festival.get();
        } else {
            return null;
        }
    }

    public static Festival findByFestivald(FestivalDAO festivalDAO, Integer id) {
        Optional<Festival> festival = festivalDAO.findById(id);
        if(festival.isPresent()) {
            return festival.get();
        } else {
            return null;
        }
    }

    public static List<Festival> filterBySearchQuery(FestivalDAO festivalDAO, Integer festival_id, String searchQuery) {
        return festivalDAO.filterBySearchQuery(festival_id, searchQuery);
    }


    public Boolean updateRating(IFestivalDAO festivalDAO) {
        List<Event> result = festivalDAO.findAllEvents(this.getId());
        Double totalRating = 0.0;
        for (Event event : result) {
            Event castedEvent = (Event) event;
            totalRating += castedEvent.getRating();
        }

        Double eventRating = (double) totalRating/result.size();
        this.setRating(eventRating);
        Integer updated = festivalDAO.updateRating(this);
        return updated == 1;
    }
    
    public static Festival create(FestivalDAO festivalDAO, Festival festivalData) {
        Integer result = festivalDAO.addFestival(festivalData);
        Festival newFestival = null;
        if(!result.equals(0)) {
            newFestival = festivalDAO.findById(result).get();
        }
        return newFestival;
    }

    public Festival update(IFestivalDAO festivalDAO) {
        Integer result = festivalDAO.updateFestival(this);
        Festival updatedFestival = null;
        if(!result.equals(0)) {
            updatedFestival = festivalDAO.findById(this.festival_id).get();
        }
        return updatedFestival;
    }

}
