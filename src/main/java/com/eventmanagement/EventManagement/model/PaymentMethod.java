package com.eventmanagement.EventManagement.model;

import com.eventmanagement.EventManagement.constants.AppConstants;
import org.springframework.validation.Errors;

public abstract class PaymentMethod
{
    protected String amountRequested = AppConstants.AMOUNT_TO_ADD_DEFAULT_STRING_VALUE;
    public abstract String getSummary();
    public abstract TransactionSource getTransactionSource();
    public abstract boolean validate(Errors errors);

    public String getAmountRequested()
    {
        return this.amountRequested;
    }

    public void setAmountRequested(String amountRequested)
    {
        this.amountRequested = amountRequested;
    }

    protected void validateAmountRequested(Errors errors)
    {
        if (getAmountRequested().trim().isEmpty())
        {
            errors.rejectValue("amountRequested", "Size.cardForm.amountRequested");
        }
        else
        {
            Double amountRequested = Double.parseDouble(getAmountRequested());
            if(amountRequested < AppConstants.AMOUNT_TO_ADD_DEFAULT_VALUE)
            {
                errors.rejectValue("amountRequested", "Value.cardForm.amountRequested");
            }
        }
    }

}
