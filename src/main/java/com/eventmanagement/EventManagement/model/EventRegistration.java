package com.eventmanagement.EventManagement.model;

import com.eventmanagement.EventManagement.DataAccessObjects.interfaces.IEventDAO;
import com.eventmanagement.EventManagement.DataAccessObjects.interfaces.IEventRegistrationDAO;
import com.eventmanagement.EventManagement.constants.AppConstants;
import com.eventmanagement.EventManagement.model.factory.DiscountFactory;
import com.eventmanagement.EventManagement.model.interfaces.IEventRegistration;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public class EventRegistration implements IEventRegistration
{
    private Integer registrationId;
    private Integer eventId;
    private Integer studentId;
    private String studentName;
    private Date registrationDate;
    private Integer transactionId;
    private Double originalPrice;
    private Double finalPrice;
    private Integer discountPercentage;
    private String eventName;
    private Date eventStartDate;
    private String description;
    private String status;
    private Integer rating;

    private IEventRegistrationDAO eventRegistrationDAO;
    private IEventDAO eventDAO;

    // getters and setters
    public Integer getRegistrationId()
    {
        return this.registrationId;
    }

    public void setRegistrationId(Integer registrationId)
    {
        this.registrationId = registrationId;
    }

    public Integer getStudentId()
    {
        return this.studentId;
    }

    public void setStudentId(Integer studentId)
    {
        this.studentId = studentId;
    }

    public String getStudentName()
    {
        return this.studentName;
    }

    public void setStudentName(String studentName)
    {
        this.studentName = studentName;
    }

    public Integer getEventId()
    {
        return this.eventId;
    }

    public void setEventId(Integer eventId)
    {
        this.eventId = eventId;
    }

    public Integer getTransactionId()
    {
        return this.transactionId;
    }

    public void setTransactionId(Integer transactionId)
    {
        this.transactionId = transactionId;
    }

    public Date getRegistrationDate()
    {
        return this.registrationDate;
    }

    public void setRegistrationDate(Date registrationDate)
    {
        this.registrationDate = registrationDate;
    }

    public Double getOriginalPrice()
    {
        return this.originalPrice;
    }

    public void setOriginalPrice(Double originalPrice)
    {
        this.originalPrice = originalPrice;
    }

    public Double getFinalPrice() {return this.finalPrice; }

    public void setFinalPrice(Double finalPrice)
    {
        this.finalPrice = finalPrice;
    }

    public Integer getDiscountPercentage() {return this.discountPercentage; }

    public String getEventName() { return this.eventName; }

    public void setEventName(String eventName) { this.eventName = eventName; }

    public Date getEventStartDate() {return this.eventStartDate; }

    public void setEventStartDate(Date eventStartDate) {this.eventStartDate = eventStartDate; }

    public String getDescription() {return this.description;}

    public String getStatus()
    {
        return this.status;
    }

    public Integer getRating() {
        return this.rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public void setStatus(String status)
    {
        this.status = status;
    }

    public EventRegistration() {}

    public EventRegistration(IEventRegistrationDAO eventRegistrationDAO, IEventDAO eventDAO)
    {
        this.eventRegistrationDAO = eventRegistrationDAO;
        this.eventDAO = eventDAO;
    }


    // operations
    public void setDetails(int eventId, int studentId)
    {
        this.eventId = eventId;
        this.studentId = studentId;
        Event event = this.eventDAO.findById(this.eventId).get();
        setEventDetails(event);
        setDiscountDetails(event);
    }

    private void setEventDetails(Event event)
    {
        this.eventName = event.getName();
        this.originalPrice  = Double.valueOf(event.getPrice());
        this.eventStartDate = event.getStartDate();
        this.description = String.format(AppConstants.EVENT_REGISTER_DESCRIPTION, event.getName());
    }

    public void setDiscountDetails(Event event)
    {
        Discount discount;
        if(getPreviousRegistrationCount()>0)
        {
            discount = DiscountFactory.instance().makeRegularDiscount();
        }
        else
        {
            discount = DiscountFactory.instance().makeFirstRegistrationDiscount();
        }
        this.finalPrice = discount.getFinalPrice(event);
        this.discountPercentage = discount.getDiscountPercentage();
    }

    @Override
    public boolean register(int eventId, int studentId)
    {
        this.registrationDate = new Date();
        this.setDetails(eventId,studentId);
        return eventRegistrationDAO.addRegistration(eventId, studentId, registrationDate, description, finalPrice);
    }

    @Override
    public int getPreviousRegistrationCount()
    {
        int count = getPreviousRegistrations(this.studentId).size();
        return count;
    }

    @Override
    public List<IEventRegistration> getPreviousRegistrations(int studentId)
    {
        List<IEventRegistration> eventRegistrations= this.eventRegistrationDAO.getAllRegistrationByStudentId(studentId);
        return eventRegistrations;
    }

    @Override
    public List<IEventRegistration> findByEventId(int eventId)
    {
        List<IEventRegistration> eventRegistrations = this.eventRegistrationDAO.findByEventId(eventId);
        return eventRegistrations;
    }

    @Override
    public  IEventRegistration getRegistrationDetailById(int registrationId)
    {
        Optional<IEventRegistration> eventRegistration = this.eventRegistrationDAO.getRegistrationById(registrationId);
        if(eventRegistration.isPresent())
        {
            return eventRegistration.get();
        }
        else
        {
            return null;
        }
    }

    @Override
    public IEventRegistration getRegistrationDetailByStudentAndEventId(int studentId, int eventId) {
        Optional<IEventRegistration> eventRegistration = this.eventRegistrationDAO.getRegistrationByStudentAndEventId(studentId,eventId);
        if(eventRegistration.isPresent())
        {
            return eventRegistration.get();
        }
        else
        {
            return null;
        }
    }

    public EventRegistration update(IEventRegistrationDAO eventRegistrationDAO) {
        Integer result = eventRegistrationDAO.updateRegistration(this);
        EventRegistration updatedEventRegistration = this;
        if(result.equals(0)) {
            updatedEventRegistration = null;
        }
        return updatedEventRegistration;
    }

}
