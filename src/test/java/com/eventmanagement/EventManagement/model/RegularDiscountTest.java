package com.eventmanagement.EventManagement.model;

import org.assertj.core.util.DateUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.eventmanagement.EventManagement.model.factory.EventFactory;

import java.util.Calendar;
import java.util.Date;


@SpringBootTest
public class RegularDiscountTest
{
    @Test
    public void getFinalPriceNoDiscountTest()
    {
        Event event = EventFactory.instance().makeEvent();
        event.setPrice(100);
        event.setStartDate(new Date());
        Discount discount = new RegularDiscount();
        double newPrice = discount.getFinalPrice(event);
        Assertions.assertEquals(100, newPrice );
    }

    @Test
    public void getFinalPriceAfterOnePercentDiscountTest()
    {
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.add(Calendar.DATE, 15);
        Date eventStartDate = cal.getTime();
        Event event = EventFactory.instance().makeEvent();
        event.setPrice(100);
        event.setStartDate(eventStartDate);
        Discount discount = new RegularDiscount();
        double newPrice = discount.getFinalPrice(event);
        Assertions.assertEquals(99,newPrice);
    }

    @Test
    public void getFinalPriceAfterFivePercentDiscountTest()
    {
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.add(Calendar.DATE, 100);
        Date eventStartDate = cal.getTime();
        Event event = EventFactory.instance().makeEvent();
        event.setPrice(100);
        event.setStartDate(eventStartDate);
        Discount discount = new RegularDiscount();
        double newPrice = discount.getFinalPrice(event);
        Assertions.assertEquals(95, newPrice);
    }

    @Test
    public void getDiscountPercentageNoDiscountTest()
    {
        Event event = EventFactory.instance().makeEvent();
        event.setPrice(100);
        event.setStartDate(new Date());
        Discount discount = new RegularDiscount();
        discount.getFinalPrice(event);
        int discountPercentage  = discount.getDiscountPercentage();
        Assertions.assertEquals(0, discountPercentage);
    }

    @Test
    public void getDiscountPercentageOnePercentageTest()
    {
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.add(Calendar.DATE, 15);
        Date eventStartDate = cal.getTime();
        Event event = EventFactory.instance().makeEvent();
        event.setPrice(100);
        event.setStartDate(eventStartDate);
        Discount discount = new RegularDiscount();
        discount.getFinalPrice(event);
        int discountPercentage  = discount.getDiscountPercentage();
        Assertions.assertEquals(1, discountPercentage);
    }

    @Test
    public void getDiscountPercentageFivePercentageTest()
    {
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.add(Calendar.DATE, 100);
        Date eventStartDate = cal.getTime();
        Event event = EventFactory.instance().makeEvent();
        event.setPrice(100);
        event.setStartDate(eventStartDate);
        Discount discount = new RegularDiscount();
        discount.getFinalPrice(event);
        int discountPercentage  = discount.getDiscountPercentage();
        Assertions.assertEquals(5, discountPercentage);
    }

    @Test
    public void isDiscountAppliedFalseTest()
    {
        Event event = EventFactory.instance().makeEvent();
        event.setPrice(100);
        event.setStartDate(new Date());
        Discount discount = new RegularDiscount();
        discount.getFinalPrice(event);
        boolean isDiscountApplied = discount.isDiscountApplied();
        Assertions.assertEquals(false, isDiscountApplied);
    }

    @Test
    public void isDiscountAppliedTrueTest()
    {
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.add(Calendar.DATE, 35);
        Date eventStartDate = cal.getTime();
        Event event = EventFactory.instance().makeEvent();
        event.setPrice(100);
        event.setStartDate(eventStartDate);
        Discount discount = new RegularDiscount();
        discount.getFinalPrice(event);
        boolean isDiscountApplied = discount.isDiscountApplied();
        Assertions.assertEquals(true, isDiscountApplied);
    }

}
