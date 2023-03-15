package com.eventmanagement.EventManagement.model;

import com.eventmanagement.EventManagement.DataAccessObjects.interfaces.IEventDAO;
import com.eventmanagement.EventManagement.DataAccessObjects.interfaces.IEventRegistrationDAO;
import com.eventmanagement.EventManagement.constants.AppConstants;
import com.eventmanagement.EventManagement.model.factory.NotificationFactory;
import com.eventmanagement.EventManagement.model.interfaces.INotification;
import com.eventmanagement.EventManagement.model.interfaces.IRegistrationCancellation;
import com.eventmanagement.EventManagement.model.factory.EventFactory;
import com.eventmanagement.EventManagement.model.interfaces.IEventRegistration;
import com.eventmanagement.EventManagement.model.interfaces.IRefundCalculator;

public class RegistrationCancellation implements IRegistrationCancellation
{
    private Integer registrationId = -1;
    private Double refundPrice;
    private String description;
    public EventRegistration registsrationDetail;
    private IRefundCalculator refundCalculator;
    private IEventRegistrationDAO eventRegistrationDAO;
    private IEventDAO eventDAO;

    public Integer getRegistrationId()
    {
        return this.registrationId;
    }

    public void setRegistrationId(Integer registrationId) { this.registrationId = registrationId; }

    public Double getRefundPrice() {return this.refundPrice; }

    public void setRefundPrice(Double refundPrice)
    {
        this.refundPrice = refundPrice;
    }

    public String getDescription() {return this.description;}

    public void setDescription(String description)
    {
        this.description = description;
    }

    public RegistrationCancellation()
    {

    }

    public RegistrationCancellation(IEventRegistrationDAO eventRegistrationDAO, IEventDAO eventDAO)
    {
        this.eventRegistrationDAO = eventRegistrationDAO;
        this.eventDAO = eventDAO;
        this.refundCalculator = new RefundCalculator();
    }

    public void loadDetails(int registrationId)
    {
        this.registrationId = registrationId;
        loadRegistrationDetail();
        calculateRefundPrice();
        setDescription();
    }

    private void calculateRefundPrice()
    {
        this.refundPrice = this.refundCalculator.getRefundPrice(this.registsrationDetail);
    }

    private void loadRegistrationDetail()
    {
        IEventRegistration eventRegistration = EventFactory.instance().makeEventRegistration(eventRegistrationDAO,eventDAO);
        this.registsrationDetail = (EventRegistration) eventRegistration.getRegistrationDetailById(this.registrationId);
    }

    private void setDescription()
    {
        this.description = String.format(AppConstants.REFUND_CANCEL_EVENT_DESCRIPTION, this.registsrationDetail.getEventName());
    }

    public boolean cancel(int registrationId)
    {
        if(this.registrationId == -1)
        {
            this.loadDetails(registrationId);
        }
        INotification notification = NotificationFactory.instance().makeNotification();
        String notificationMessage = String.format(AppConstants.WAITLIST_NOTIFICATION_TEXT, this.registsrationDetail.getEventName());
        notification.sendNotification(ReceipientType.Waitlist, this.registsrationDetail.getEventId(), notificationMessage);
        return this.eventRegistrationDAO.cancelRegistration(registrationId, refundPrice, description);
    }
}
