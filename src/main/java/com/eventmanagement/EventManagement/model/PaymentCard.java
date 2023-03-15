package com.eventmanagement.EventManagement.model;

import com.eventmanagement.EventManagement.constants.AppConstants;
import org.springframework.validation.Errors;

import java.time.LocalDateTime;

public class PaymentCard extends PaymentMethod
{
    private String cardNumber;
    private String cardHolderName;
    private Integer cardExpiryMonth = 12;
    private Integer cardExpiryYear = 22;
    private String cvvNumber;

    public String getCardNumber()
    {
        return this.cardNumber;
    }

    public void setCardNumber(String cardNumber)
    {
        this.cardNumber = cardNumber;
    }

    public String getCardHolderName()
    {
        return  this.cardHolderName;
    }

    public void setCardHolderName(String cardHolderName)
    {
        this.cardHolderName = cardHolderName;
    }

    public Integer getCardExpiryMonth()
    {
        return this.cardExpiryMonth;
    }

    public void setCardExpiryMonth(Integer cardExpiryMonth)
    {
        this.cardExpiryMonth = cardExpiryMonth;
    }

    public Integer getCardExpiryYear()
    {
        return this.cardExpiryYear;
    }

    public void setCardExpiryYear(Integer cardExpiryYear)
    {
        this.cardExpiryYear = cardExpiryYear;
    }

    public String getCvvNumber()
    {
        return this.cvvNumber;
    }

    public void setCvvNumber(String cvvNumber)
    {
        this.cvvNumber = cvvNumber;
    }

    @Override
    public String getSummary()
    {
        return String.format(AppConstants.ADD_BALANCE_CARD_DESCRIPTION, getCardHolderName(), getLastFourDigitsOfCard());
    }

    @Override
    public TransactionSource getTransactionSource() {
        return TransactionSource.Card;
    }

    @Override
    public boolean validate(Errors errors)
    {
        validateAmountRequested(errors);
        validateCardNumber(errors);
        validateCardHolderName(errors);
        validateCvvNumber(errors);
        validateExpiryDetails(errors);

        if(errors.hasErrors())
        {
            return false;
        }
        else
        {
            return true;
        }
    }

    private void validateCardNumber(Errors errors)
    {
        if(getCardNumber().trim().isEmpty())
        {
            errors.rejectValue("cardNumber", "Size.cardForm.cardNumber");
        }
        else
        {
            if(!getCardNumber().matches("[0-9]+"))
            {
                errors.rejectValue("cardNumber", "Format.cardForm.cardNumber");
            }
            else if(getCardNumber().length()!=16)
            {
                errors.rejectValue("cardNumber", "Length.cardForm.cardNumber");
            }
        }
    }

    private void validateCardHolderName(Errors errors)
    {
        if(getCardHolderName().trim().isEmpty())
        {
            errors.rejectValue("cardHolderName", "Size.cardForm.cardHolderName");
        }
        else
        {
            if(!getCardHolderName().matches("[a-zA-Z ]+"))
            {
                errors.rejectValue("cardHolderName", "Format.cardForm.cardHolderName");
            }
        }
    }

    private void validateCvvNumber(Errors errors)
    {
        if(getCvvNumber().trim().isEmpty())
        {
            errors.rejectValue("cvvNumber", "Size.cardForm.cvvNumber");
        }
        else
        {
            if(!getCvvNumber().matches("[0-9]+"))
            {
                errors.rejectValue("cvvNumber", "Format.cardForm.cvvNumber");
            }
            else if(getCvvNumber().length()!=3)
            {
                errors.rejectValue("cvvNumber", "Length.cardForm.cvvNumber");
            }
        }
    }

    private void validateExpiryDetails(Errors errors)
    {
        LocalDateTime currentdate = LocalDateTime.now();

        if(getCardExpiryYear() == currentdate.getYear())
        {
            if(getCardExpiryMonth() < currentdate.getMonthValue())
            {
                errors.rejectValue("cardExpiryMonth", "Value.cardForm.cardExpiryMonth");
            }
        }

        if(getCardExpiryYear() < currentdate.getYear())
        {
            errors.rejectValue("cardExpiryYear", "Value.cardForm.cardExpiryYear");
        }
    }

    private String getLastFourDigitsOfCard()
    {
        return getCardNumber().substring(12,16);
    }
}
