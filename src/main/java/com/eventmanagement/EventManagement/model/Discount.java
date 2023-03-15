package com.eventmanagement.EventManagement.model;


public abstract class Discount
{
    private Integer discountPercentage = 0;
    public abstract boolean isEligibleForDiscount(Event event);
    public abstract int getDiscountPercentage(Event event);

    public Double getFinalPrice(Event event)
    {
        double oldPrice = (double)event.getPrice();
        double finalPrice = oldPrice;
        if(isEligibleForDiscount(event))
        {
            this.discountPercentage = getDiscountPercentage(event);
            finalPrice = finalPrice * (100 - this.discountPercentage)/100;
        }
        return finalPrice;
    }

    public Integer getDiscountPercentage()
    {
        return this.discountPercentage;
    }

    public boolean isDiscountApplied()
    {
        if(this.discountPercentage>0)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

}
