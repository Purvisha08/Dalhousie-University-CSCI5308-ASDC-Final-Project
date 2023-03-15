package com.eventmanagement.EventManagement.model;

import java.util.Date;
import java.util.concurrent.TimeUnit;

public class RegularDiscount extends Discount
{
    @Override
    public boolean isEligibleForDiscount(Event event)
    {
        if(getNumberOfDaysLeft(event) >= 10)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    @Override
    public int getDiscountPercentage(Event event) {
        long numberOfDaysLeft = getNumberOfDaysLeft(event);

        if(numberOfDaysLeft>=10)
        {
            if(numberOfDaysLeft<=49)
            {
                return (int) numberOfDaysLeft/10;
            }
            else
            {
                return 5;
            }
        }
        else
        {
            return 0;
        }
    }

    private int getNumberOfDaysLeft(Event event)
    {
        long numberOfDaysLeft = 0;
        Date startDate = event.getStartDate();
        Date currentDate = new Date();
        long millisecondsDifference = Math.abs(startDate.getTime() - currentDate.getTime());
        numberOfDaysLeft = TimeUnit.DAYS.convert(millisecondsDifference, TimeUnit.MILLISECONDS);
        return (int)numberOfDaysLeft;
    }
}
