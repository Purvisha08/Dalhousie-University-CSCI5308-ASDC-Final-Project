package com.eventmanagement.EventManagement.model.interfaces;

import com.eventmanagement.EventManagement.model.Event;

public interface IDiscountCalculator
{
    public boolean isEligibleForDiscount( Event event);
    public Integer getDiscountPercentage(Event event);
}
