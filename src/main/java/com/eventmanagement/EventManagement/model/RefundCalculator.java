package com.eventmanagement.EventManagement.model;

import com.eventmanagement.EventManagement.model.interfaces.IRefundCalculator;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class RefundCalculator implements IRefundCalculator
{
    @Override
    public double getRefundPrice(EventRegistration eventRegistration)
    {
        int numberOfDaysLeft = getNumberOfDaysLeft(eventRegistration.getEventStartDate());
        double refundPrice = eventRegistration.getFinalPrice();
        if(numberOfDaysLeft<10)
        {
            refundPrice =  (numberOfDaysLeft*10) * refundPrice / 100;
        }
        return Math.min(refundPrice,eventRegistration.getFinalPrice());
    }

    private int getNumberOfDaysLeft(Date startDate)
    {
        long numberOfDaysLeft = 0;
        Date currentDate = new Date();
        long millisecondsDifference = Math.abs(startDate.getTime() - currentDate.getTime());
        numberOfDaysLeft = TimeUnit.DAYS.convert(millisecondsDifference, TimeUnit.MILLISECONDS);
        return (int)numberOfDaysLeft;
    }
}
