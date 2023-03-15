package com.eventmanagement.EventManagement.model;

import com.eventmanagement.EventManagement.model.interfaces.IRefundCalculator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Calendar;
import java.util.Date;

@SpringBootTest
public class RefundCalculatorTest
{

    @Test
    public void getRefundPriceFullTest()
    {
        IRefundCalculator refundCalculator = new RefundCalculator();
        EventRegistration eventRegistration = new EventRegistration();
        eventRegistration.setFinalPrice(100.0);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.DATE, 15);
        eventRegistration.setEventStartDate(calendar.getTime());
        double refundPrice = refundCalculator.getRefundPrice(eventRegistration);
        Assertions.assertEquals(100, refundPrice);
    }

    @Test
    public void getRefundPriceHalfTest()
    {
        IRefundCalculator refundCalculator = new RefundCalculator();
        EventRegistration eventRegistration = new EventRegistration();
        eventRegistration.setFinalPrice(100.0);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.DATE, 5);
        eventRegistration.setEventStartDate(calendar.getTime());
        double refundPrice = refundCalculator.getRefundPrice(eventRegistration);
        Assertions.assertEquals(true, (refundPrice<=50));
    }

    @Test
    public void getRefundPriceNothingTest()
    {
        IRefundCalculator refundCalculator = new RefundCalculator();
        EventRegistration eventRegistration = new EventRegistration();
        eventRegistration.setFinalPrice(100.0);
        eventRegistration.setEventStartDate(new Date());
        double refundPrice = refundCalculator.getRefundPrice(eventRegistration);
        Assertions.assertEquals(0, refundPrice);
    }

}
