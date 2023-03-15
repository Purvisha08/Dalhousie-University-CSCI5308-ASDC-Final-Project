package com.eventmanagement.EventManagement.model;

import com.eventmanagement.EventManagement.DataAccessObjects.StudentInterestDAO;
import com.eventmanagement.EventManagement.DataAccessObjects.StudentPastInterestDAO;
import com.eventmanagement.EventManagement.DataAccessObjects.interfaces.IEventListDAO;
import com.eventmanagement.EventManagement.model.interfaces.IEventSort;
import com.eventmanagement.EventManagement.model.interfaces.IStudentInterest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class EventSort implements IEventSort {

    public ArrayList<EventScore> eventScoreList;
    private ArrayList<Event> eventList;

    private HashMap<String, Integer> pastInterestMap;

    private IEventListDAO eventListDAO;


    public EventSort(IEventListDAO eventListDAO, int userId){
        this.eventListDAO = eventListDAO;
        pastInterestMap = new HashMap<String, Integer>();
        this.eventScoreList = new ArrayList<EventScore>();
        this.eventList = (ArrayList<Event>) eventListDAO.getEventList();
        populateEventScoreListWithEvents();
        sortEvents(userId);
    }

    public void populateEventScoreListWithEvents(){
        EventScore eventScore = new EventScore();

        for (int i=0; i<this.eventList.size(); i++){
            eventScore.setEvent(this.eventList.get(i));
            eventScore.setScore(0);
            this.eventScoreList.add(eventScore);
            eventScore = new EventScore();
        }
    }

    public void sortEvents(int userId){
        StudentInterestDAO studentInterestDAO = new StudentInterestDAO();

        ArrayList<String> studentInterest = new ArrayList<String>();
        List<IStudentInterest> totalStudentInterestList = studentInterestDAO.getStudentInterests(userId);
        for (int i=0; i<totalStudentInterestList.size(); i++){
            StudentInterest interest = (StudentInterest) totalStudentInterestList.get(i);
            studentInterest.add(interest.getCategoryId());
        }

        setPastInterestByUserId(userId);

        String eventCategory = "";
        int eventScore = 0;
        int interestScore = 0;

        for(int i=0; i<this.eventScoreList.size(); i++){
            eventCategory = this.eventScoreList.get(i).getEvent().getCategory();
            eventScore = this.eventScoreList.get(i).getScore();
            if (studentInterest.contains(eventCategory)){
                this.eventScoreList.get(i).setScore(eventScore+5);
            }

            if (this.pastInterestMap.containsKey(eventCategory)){
                interestScore = this.pastInterestMap.get(eventCategory);
                eventScore = this.eventScoreList.get(i).getScore();
                this.eventScoreList.get(i).setScore(eventScore + interestScore);
            }
        }

    }


    public void setPastInterestByUserId(int userId){
        StudentPastInterestDAO studentPastInterestDAO = new StudentPastInterestDAO();
        ArrayList<StudentPastInterest> studentPastInterestList = new ArrayList<StudentPastInterest>();
        studentPastInterestList = studentPastInterestDAO.getPastInterest(userId);
        String interest;
        int score;

        for (int i=0; i < studentPastInterestList.size(); i++){
            interest = studentPastInterestList.get(i).getInterest();
            if(pastInterestMap.containsKey(interest)){
                score = pastInterestMap.get(interest);
                pastInterestMap.put(interest, score + 1);
            }
            else{
                pastInterestMap.put(interest, 1);
            }
        }
    }

    public ArrayList<Event> sortedEvents(){

        ArrayList<Event> eventListSorted = new ArrayList<Event>();
        EventScore temp = new EventScore();

        for (int i=0; i< this.eventScoreList.size()-1; i++){
            for (int j=0; j<this.eventScoreList.size()-i-1; j++){
                if (this.eventScoreList.get(j).getScore() < this.eventScoreList.get(j+1).getScore()){
                    temp.setEvent(this.eventScoreList.get(j+1).getEvent());
                    temp.setScore(this.eventScoreList.get(j+1).getScore());
                    this.eventScoreList.get(j+1).setEvent(this.eventScoreList.get(j).getEvent());
                    this.eventScoreList.get(j+1).setScore(this.eventScoreList.get(j).getScore());
                    this.eventScoreList.get(j).setEvent(temp.getEvent());
                    this.eventScoreList.get(j).setScore(temp.getScore());
                }
            }
        }

        for (int i=0; i<this.eventScoreList.size();i++){
            eventListSorted.add(this.eventScoreList.get(i).getEvent());
        }

        return eventListSorted;
    }
}
