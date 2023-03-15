package com.eventmanagement.EventManagement.model.interfaces;

import com.eventmanagement.EventManagement.model.Discount;

public interface IDiscountFactory
{
    public Discount makeFirstRegistrationDiscount();
    public Discount makeRegularDiscount();
}
