package com.eventmanagement.EventManagement.controller;

import com.eventmanagement.EventManagement.model.CurrentSession;
import com.eventmanagement.EventManagement.DataAccessObjects.interfaces.IEventDAO;
import com.eventmanagement.EventManagement.DataAccessObjects.interfaces.IEventRegistrationDAO;
import com.eventmanagement.EventManagement.DataAccessObjects.interfaces.IStudentDAO;
import com.eventmanagement.EventManagement.DataAccessObjects.interfaces.ITransactionDAO;
import com.eventmanagement.EventManagement.model.*;
import com.eventmanagement.EventManagement.model.factory.EventFactory;
import com.eventmanagement.EventManagement.model.factory.MySQLDatabaseFactory;
import com.eventmanagement.EventManagement.model.factory.SessionFactory;
import com.eventmanagement.EventManagement.model.factory.WalletFactory;
import com.eventmanagement.EventManagement.model.interfaces.IRegistrationCancellation;
import com.eventmanagement.EventManagement.model.interfaces.IEventRegistration;
import com.eventmanagement.EventManagement.model.interfaces.IWallet;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class EventRegisterController
{
    private IEventRegistrationDAO eventRegistrationDAO;
    private IEventDAO eventDAO;
    private IStudentDAO studentDAO;
    private ITransactionDAO transactionDAO;

    public EventRegisterController()
    {
        eventRegistrationDAO = MySQLDatabaseFactory.instance().makeEventRegistrationDAO();
        eventDAO = MySQLDatabaseFactory.instance().makeEventDAO();
        studentDAO = MySQLDatabaseFactory.instance().makeStudentDAO();
        transactionDAO = MySQLDatabaseFactory.instance().makeTransactionDAO();
    }

    @GetMapping(path = { "/eventregister/{id}"})
    public String getEventRegister(@PathVariable(value = "id") Integer eventId, Model model, HttpServletRequest request)
    {
        CurrentSession currentSession = SessionFactory.instance().makeCurrentSession(request);
        Integer studentId = currentSession.getUserId();
        IWallet wallet = WalletFactory.instance().makeWallet(studentDAO,transactionDAO);
        IEventRegistration eventRegistration = EventFactory.instance().makeEventRegistration(eventRegistrationDAO,eventDAO);
        eventRegistration.setDetails(eventId,studentId);
        wallet.loadWalletDetails(studentId);
        model.addAttribute("wallet", wallet);
        model.addAttribute("eventregister", eventRegistration);
        return "eventregister";
    }

    @PostMapping(path = { "/eventregister/{id}"})
    public String eventRegister(@PathVariable(value = "id") Integer eventId, Model model, HttpServletRequest request)
    {
        CurrentSession currentSession = SessionFactory.instance().makeCurrentSession(request);
        Integer studentId = currentSession.getUserId();
        IEventRegistration eventRegistration = EventFactory.instance().makeEventRegistration(eventRegistrationDAO,eventDAO);
        eventRegistration.setDetails(eventId,studentId);
        if(eventRegistration.register(eventId,studentId))
        {
            return "redirect:/myevents";
        }
        else
        {
            Wallet wallet = (Wallet) WalletFactory.instance().makeWallet(studentDAO,transactionDAO);
            wallet.loadBalance(studentId);
            model.addAttribute("eventregister", eventRegistration);
            model.addAttribute("wallet", wallet);
            return "eventregister";
        }
    }

    @GetMapping(path = { "/eventregister/cancel/{id}"})
    public String viewCancelEventPage(@PathVariable(value = "id") Integer registrationId, Model model, HttpServletRequest request)
    {
        IRegistrationCancellation eventCancellation = EventFactory.instance().makeRegistrationCancellation(eventRegistrationDAO,eventDAO);
        eventCancellation.loadDetails(registrationId);
        model.addAttribute("eventcancel", eventCancellation);
        return "eventcancel";
    }

    @PostMapping(path = { "/eventregister/cancel/{id}"})
    public String processCancelEventRegisteration(@PathVariable(value = "id") Integer registrationId, Model model, HttpServletRequest request)
    {
        IRegistrationCancellation eventCancellation = EventFactory.instance().makeRegistrationCancellation(eventRegistrationDAO,eventDAO);
        eventCancellation.loadDetails(registrationId);
        eventCancellation.cancel(registrationId);
        model.addAttribute("eventcancel", eventCancellation);
        return "redirect:/wallet";
    }

}
