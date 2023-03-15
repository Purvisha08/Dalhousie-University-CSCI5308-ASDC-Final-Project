package com.eventmanagement.EventManagement.model;

import com.eventmanagement.EventManagement.constants.AppConstants;
import org.springframework.validation.Errors;

public class NetBankingPayment extends PaymentMethod
{

    private String bankName;
    private String userId;
    private String pin;

    public String getBankName()
    {
        return this.bankName;
    }

    public void setBankName(String bankName)
    {
        this.bankName = bankName;
    }

    public String getUserId()
    {
        return this.userId;
    }

    public void setUserId(String userId)
    {
        this.userId = userId;
    }

    public String getPin()
    {
        return this.pin;
    }

    public void setPin(String pin)
    {
        this.pin = pin;
    }

    @Override
    public String getSummary()
    {
        return String.format(AppConstants.ADD_BALANCE_NETBANKING_DESCRIPTION, this.getBankName());
    }

    @Override
    public TransactionSource getTransactionSource() {
        return TransactionSource.NetBanking;
    }

    @Override
    public boolean validate(Errors errors)
    {
        validateAmountRequested(errors);
        validateUserId(errors);
        validatePin(errors);
        validateBankName(errors);

        if(errors.hasErrors())
        {
            return false;
        }
        else
        {
            return true;
        }
    }

    private void validateUserId(Errors errors)
    {
        if(getUserId().trim().isEmpty())
        {
            errors.rejectValue("userId", "Size.netBankingForm.userId");
        }
    }

    private void validatePin(Errors errors)
    {
        if(getPin().trim().isEmpty())
        {
            errors.rejectValue("pin", "Size.netBankingForm.pin");
        }
        else
        {
            if(!getPin().matches("[0-9]+") || getPin().length()!=6)
            {
                errors.rejectValue("pin", "Format.netBankingForm.pin");
            }
        }
    }

    private  void validateBankName(Errors errors)
    {
        if(getBankName().trim().isEmpty())
        {
            errors.rejectValue("bankName", "Size.netBankingForm.bankName");
        }
    }

}
