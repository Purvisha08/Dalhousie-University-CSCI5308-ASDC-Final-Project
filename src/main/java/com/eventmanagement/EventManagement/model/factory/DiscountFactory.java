package com.eventmanagement.EventManagement.model.factory;

import com.eventmanagement.EventManagement.model.Discount;
import com.eventmanagement.EventManagement.model.FirstRegistrationDiscount;
import com.eventmanagement.EventManagement.model.RegularDiscount;
import com.eventmanagement.EventManagement.model.interfaces.IDiscountFactory;

public class DiscountFactory implements IDiscountFactory
{
    private static DiscountFactory singleInstance;

    private DiscountFactory()
    {

    }

    public static DiscountFactory instance()
    {
        if(singleInstance == null)
        {
            singleInstance = new DiscountFactory();
        }
        return singleInstance;
    }

    @Override
    public Discount makeFirstRegistrationDiscount() {
        return new FirstRegistrationDiscount();
    }

    @Override
    public Discount makeRegularDiscount() {
        return new RegularDiscount();
    }
}
