package com.eventmanagement.EventManagement.model.interfaces;

import com.eventmanagement.EventManagement.model.EventRegistration;

public interface IRefundCalculator
{
    public double getRefundPrice(EventRegistration eventRegistration);
}
