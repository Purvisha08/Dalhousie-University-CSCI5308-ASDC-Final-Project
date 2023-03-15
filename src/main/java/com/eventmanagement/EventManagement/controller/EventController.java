package com.eventmanagement.EventManagement.controller;

import com.eventmanagement.EventManagement.model.CurrentSession;
import com.eventmanagement.EventManagement.DataAccessObjects.EventListDAO;
import com.eventmanagement.EventManagement.model.*;
import com.eventmanagement.EventManagement.model.factory.EventFactory;
import com.eventmanagement.EventManagement.model.factory.MySQLDatabaseFactory;
import com.eventmanagement.EventManagement.model.factory.NotificationFactory;
import com.eventmanagement.EventManagement.model.factory.QuestionAnswerFactory;
import com.eventmanagement.EventManagement.model.factory.SessionFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

import com.eventmanagement.EventManagement.DataAccessObjects.interfaces.IEventDAO;
import com.eventmanagement.EventManagement.DataAccessObjects.interfaces.IEventRegistrationDAO;
import com.eventmanagement.EventManagement.DataAccessObjects.interfaces.IFestivalDAO;
import com.eventmanagement.EventManagement.DataAccessObjects.interfaces.IHostDAO;
import com.eventmanagement.EventManagement.DataAccessObjects.interfaces.IQuestionAnswerDAO;
import com.eventmanagement.EventManagement.DataAccessObjects.interfaces.IStudentDAO;
import com.eventmanagement.EventManagement.constants.Options;
import com.eventmanagement.EventManagement.model.interfaces.IEventRegistration;
import com.eventmanagement.EventManagement.model.interfaces.INotification;
import com.eventmanagement.EventManagement.model.interfaces.IQuestionAnswer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import com.eventmanagement.EventManagement.validator.EventValidator;
import javax.servlet.http.HttpServletRequest;


@Controller
public class EventController {

    EventValidator eventValidator;

    @Autowired
    public IEventDAO eventDAO;

    @Autowired
    public IStudentDAO studentDAO;

    @Autowired
    public IEventRegistrationDAO eventRegistrationDAO;

    private IQuestionAnswerDAO questionAnswerDAO;

    @Autowired
    public IFestivalDAO festivalDAO;

    @Autowired
    public IHostDAO hostDAO;
    
    public EventController() {
        this.eventValidator = EventFactory.instance().makeEventValidator();
        this.questionAnswerDAO = MySQLDatabaseFactory.instance().makeQuestionAnswerDAO();
    }

    @GetMapping(path = { "/festival/{festival_id}/event/{event_id}" })
    public String getFestivalEvents(@PathVariable(value = "festival_id") Integer festival_id, @PathVariable(value = "event_id") Integer event_id, Model model) {
        Event event = Event.findById(this.eventDAO, event_id);
        if(event.getCompleted() == true) {
            event.setWinnerStudent(Student.findById(studentDAO, event.getWinner()));
            event.setFirstRunnerUpStudent(Student.findById(studentDAO, event.getFirstRunnerUp()));
            event.setSecondRunnerUpStudent(Student.findById(studentDAO, event.getSecondRunnerUp()));
        }
        List<QuestionAnswer> eventQuestionAnswers = QuestionAnswer.getQuestionAnswerByEventId(questionAnswerDAO, event_id);
        IEventRegistration eventRegistration = EventFactory.instance().makeEventRegistration(this.eventRegistrationDAO, this.eventDAO);
        List<IEventRegistration> eventRegistrationArrayList= eventRegistration.findByEventId(event_id);
        model.addAttribute("eventRegistrationsList",eventRegistrationArrayList);
        model.addAttribute("event", event);
        model.addAttribute("eventQuestionAnswers", eventQuestionAnswers);
        return "event";
    }

    @GetMapping(path = { "/festival/{festival_id}/event/new" })
    public String getNewEvent(@PathVariable(value = "festival_id") Integer festival_id, Model model) {
        model.addAttribute("eventForm", EventFactory.instance().makeEvent());
        model.addAttribute("festival_id", festival_id);
        model.addAttribute("categoryOptions", Options.instance().getEventCategoryOptions());
        return "createEvent";
    }

    @PostMapping(path = { "/festival/{festival_id}/event/new" })
    public String postNewEvent(@PathVariable(value = "festival_id") Integer festival_id,
            @ModelAttribute("eventForm") Event eventForm, BindingResult bindingResult, Model model) {
        Event.validateEventData(eventValidator, eventForm, bindingResult);
        model.addAttribute("categoryOptions", Options.instance().getEventCategoryOptions());
        if (bindingResult.hasErrors()) {
            return "createEvent";
        }
        eventForm.setFestivalId(festival_id);
        Event.create(eventDAO, eventForm);
        return "redirect:/festival/"+festival_id;
    }

    @GetMapping(path = { "/festival/{festival_id}/event/{event_id}/update" })
    public String getUpdateEvent(@PathVariable(value = "festival_id") Integer festival_id,
            @PathVariable(value = "event_id") Integer event_id, Model model) {
        model.addAttribute("event", Event.findById(eventDAO, event_id));
        model.addAttribute("festival_id", festival_id);
        model.addAttribute("categoryOptions", Options.instance().getEventCategoryOptions());
        return "updateEvent";
    }

    @PostMapping(path = { "/festival/{festival_id}/event/{event_id}/update" })
    public String postUpdateEvent(
            @PathVariable(value = "festival_id") Integer festival_id,
            @PathVariable(value = "event_id") Integer event_id,
            @ModelAttribute("event") Event event,
            Model model,
            BindingResult bindingResult) {
        Event.validateEventData(eventValidator, event, bindingResult);
        event.setId(event_id);
        if (bindingResult.hasErrors()) {
            return "updateEvent";
        }
        Event updatedEvent = event.update(eventDAO);
        if (updatedEvent != null) {
            return "redirect:/festival/"+festival_id+"/event/"+event_id;
        } else {
            model.addAttribute("update_error", "Some error occurred. Try again.");
            return "updateEvent";
        }
    }

    @RequestMapping(path = {"/","/events/event"})
    public String getShowEvent(Model model, int id, HttpServletRequest request)
    {
        EventListDAO eventListDAO = EventFactory.instance().makeEventListDAO();
        Event currentEvent = eventListDAO.getEventById(id).get();
        Integer studentId = SessionFactory.instance().makeCurrentSession(request).getUserId();
        Boolean isRegistered;
        IEventRegistration eventRegistration = EventFactory.instance().makeEventRegistration(eventRegistrationDAO,eventDAO);
        IEventRegistration registrationDetail = eventRegistration.getRegistrationDetailByStudentAndEventId(studentId,id);
        if(registrationDetail == null)
        {
            isRegistered = false;
        }
        else
        {
            isRegistered = true;
        }
        model.addAttribute("currentEvent",currentEvent);

        List<QuestionAnswer> questionAnswersList = QuestionAnswer.getQuestionAnswerByEventId(questionAnswerDAO, id);
        model.addAttribute("questionanswers", questionAnswersList);

        IQuestionAnswer questionAnswer = QuestionAnswerFactory.getInstance().makeQuestionAnswer();
        model.addAttribute("questionForm", questionAnswer);

        model.addAttribute("isRegistered",isRegistered);
        model.addAttribute("registrationDetail",registrationDetail);
        return "viewEvent";
    }

    @PostMapping("/festival/{festival_id}/event/{event_id}/qa/{qa_id}")
    public String patchQuestionAnswer(
            @PathVariable(value = "festival_id") Integer festival_id,
            @PathVariable(value = "event_id") Integer event_id,
            @PathVariable(value = "qa_id") Integer qa_id,
            @RequestParam("answerText") String answerText,
            Model model) {

        QuestionAnswer qa = QuestionAnswer.findByID(questionAnswerDAO, qa_id);
        qa.setAnswerText(answerText);
        Boolean updatedQA = qa.answerQuestion(questionAnswerDAO, qa);
        if (updatedQA == true) {
            return "redirect:/festival/"+festival_id+"/event/"+event_id;
        } else {
            model.addAttribute("answer_error", "Some error occurred. Try again.");
            return "redirect:/festival/"+festival_id+"/event/"+event_id;
        }
    }

    @PostMapping("/event/{event_id}/rating/update")
    public String rateByStudent(
            @PathVariable(value = "event_id") Integer event_id,
            @RequestParam("rating") Integer rating,
            HttpServletRequest httpServletRequest,
            Model model) {

        try {
            CurrentSession currentSession = SessionFactory.instance().makeCurrentSession(httpServletRequest);
            Integer student_id = currentSession.getUserId();
            IEventRegistration eventRegistration = EventFactory.instance().makeEventRegistration(eventRegistrationDAO,eventDAO);
            EventRegistration registration = (EventRegistration) eventRegistration.getRegistrationDetailByStudentAndEventId(event_id, student_id);
            registration.setRating(rating);
            registration.update(eventRegistrationDAO);

            Event event = Event.findById(eventDAO, event_id);
            event.updateRating(eventDAO);

            Festival festival = Festival.findById(festivalDAO, event.getFestivalId());
            festival.updateRating(festivalDAO);
            
            Host host = Host.findById(hostDAO, festival.getHostId());
            host.updateRating(hostDAO);

            return "redirect:/events/event/?id="+event_id;
        } catch (Exception e) {
            model.addAttribute("update_error", "Some error occurred. Try again.");
            return "redirect:/events/event/?id="+event_id;
        }
    }

    @GetMapping(path = { "/festival/{festival_id}/event/{event_id}/complete" })
    public String getEventComplete(@PathVariable(value = "festival_id") Integer festival_id,
            @PathVariable(value = "event_id") Integer event_id, Model model) {
        
        Event eventComplete = null;
        List<Student> eventStudents = new ArrayList<Student>();
        try {
            eventComplete = EventFactory.instance().makeEventComplete(Event.findById(eventDAO, event_id));
            IEventRegistration eventRegistration = EventFactory.instance().makeEventRegistration(eventRegistrationDAO,eventDAO);
            List<IEventRegistration> eventRegistrations = eventRegistration.findByEventId(event_id);
            ListIterator<IEventRegistration> listIterator = eventRegistrations.listIterator();
            List<Integer> studentIds = new ArrayList<Integer>();
            while (listIterator.hasNext())
            {
                EventRegistration er = (EventRegistration) listIterator.next();
                studentIds.add(er.getStudentId());
            }
            eventStudents = Student.findAllByIds(studentDAO, studentIds);
        } catch (Exception e) {
            return "redirect:/festival/"+festival_id+"/event/"+event_id;
        }

        model.addAttribute("eventComplete", eventComplete);
        model.addAttribute("eventStudents", eventStudents);
        model.addAttribute("festival_id", festival_id);
        return "eventComplete";
    }

    @PostMapping(path = { "/festival/{festival_id}/event/{event_id}/complete" })
    public String postEventComplete(
            @PathVariable(value = "festival_id") Integer festival_id,
            @PathVariable(value = "event_id") Integer event_id,
            @RequestParam("messageToParticipants") String messageToParticipants,
            @RequestParam("declareWinners") Integer declareWinners,
            @ModelAttribute("eventComplete") Event event,
            Model model,
            BindingResult bindingResult) {

        event.setId(event_id);
        event.setCompleted(true);
        INotification notification = NotificationFactory.instance().makeNotification();
        notification.sendNotification(ReceipientType.Participants, event.getId(), messageToParticipants);
        Event updatedEvent = event.update(eventDAO);
        if (updatedEvent != null) {
            return "redirect:/festival/"+festival_id+"/event/"+event_id;
        } else {
            model.addAttribute("update_error", "Some error occurred. Try again.");
            return "updateEvent";
        }
    }
}
