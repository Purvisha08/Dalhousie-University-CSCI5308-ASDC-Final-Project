package com.eventmanagement.EventManagement.model;

import com.eventmanagement.EventManagement.constants.AppConstants;

public class FirstRegistrationDiscount extends Discount
{
    private Integer discountPercentage = AppConstants.FIRST_TIME_REGISTRATION_DISCOUNT;

    @Override
    public boolean isEligibleForDiscount(Event event) {
        return true;
    }

    @Override
    public int getDiscountPercentage(Event event) {
        return this.discountPercentage;
    }
}
