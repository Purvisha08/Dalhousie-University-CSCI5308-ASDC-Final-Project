package com.eventmanagement.EventManagement.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.eventmanagement.EventManagement.model.factory.EventFactory;


@SpringBootTest
public class FirstRegistrationDiscountTest
{
    @Test
    public void getFinalPriceTest()
    {
        Event event = EventFactory.instance().makeEvent();
        event.setPrice(100);
        Discount discount = new FirstRegistrationDiscount();
        double newPrice = discount.getFinalPrice(event);
        Assertions.assertEquals(80, newPrice );
    }

    @Test
    public void getDiscountPercentageTest()
    {
        Event event = EventFactory.instance().makeEvent();
        event.setPrice(100);
        Discount discount = new FirstRegistrationDiscount();
        discount.getFinalPrice(event);
        int discountPercentage  = discount.getDiscountPercentage();
        Assertions.assertEquals(20, discountPercentage);
    }

    @Test
    public void isDiscountAppliedTest()
    {
        Event event = EventFactory.instance().makeEvent();
        event.setPrice(100);
        Discount discount = new FirstRegistrationDiscount();
        discount.getFinalPrice(event);
        boolean isDiscountApplied = discount.isDiscountApplied();
        Assertions.assertEquals(true, isDiscountApplied);
    }

}
