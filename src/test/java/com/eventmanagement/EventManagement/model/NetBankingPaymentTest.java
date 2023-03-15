package com.eventmanagement.EventManagement.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;

@SpringBootTest
public class NetBankingPaymentTest
{

    @Test
    public void validateEmptyUserIdTest()
    {
        NetBankingPayment netBankingPayment = new NetBankingPayment();
        netBankingPayment.setAmountRequested("9");
        netBankingPayment.setBankName("RBI");
        netBankingPayment.setPin("123456");
        netBankingPayment.setUserId("");
        Errors errors = new BeanPropertyBindingResult(netBankingPayment, "netBankingPayment");
        netBankingPayment.validate(errors);
        Assertions.assertEquals(true, errors.hasErrors());
        Assertions.assertEquals(true, errors.hasFieldErrors("userId"));
    }

    @Test
    public void validateEmptyPinTest()
    {
        NetBankingPayment netBankingPayment = new NetBankingPayment();
        netBankingPayment.setAmountRequested("9");
        netBankingPayment.setBankName("RBI");
        netBankingPayment.setPin("");
        netBankingPayment.setUserId("useridsample");
        Errors errors = new BeanPropertyBindingResult(netBankingPayment, "netBankingPayment");
        netBankingPayment.validate(errors);
        Assertions.assertEquals(true, errors.hasErrors());
        Assertions.assertEquals(true, errors.hasFieldErrors("pin"));
    }

    @Test
    public void validateAlphabetsInPinTest()
    {
        NetBankingPayment netBankingPayment = new NetBankingPayment();
        netBankingPayment.setAmountRequested("9");
        netBankingPayment.setBankName("RBI");
        netBankingPayment.setPin("12342a");
        netBankingPayment.setUserId("useridsample");
        Errors errors = new BeanPropertyBindingResult(netBankingPayment, "netBankingPayment");
        netBankingPayment.validate(errors);
        Assertions.assertEquals(true, errors.hasErrors());
        Assertions.assertEquals(true, errors.hasFieldErrors("pin"));
    }

    @Test
    public void validateIncorrectLengthPinTest()
    {
        NetBankingPayment netBankingPayment = new NetBankingPayment();
        netBankingPayment.setAmountRequested("9");
        netBankingPayment.setBankName("RBI");
        netBankingPayment.setPin("123421233");
        netBankingPayment.setUserId("useridsample");
        Errors errors = new BeanPropertyBindingResult(netBankingPayment, "netBankingPayment");
        netBankingPayment.validate(errors);
        Assertions.assertEquals(true, errors.hasErrors());
        Assertions.assertEquals(true, errors.hasFieldErrors("pin"));
    }

    @Test
    public void validateEmptyBankNameTest()
    {
        NetBankingPayment netBankingPayment = new NetBankingPayment();
        netBankingPayment.setAmountRequested("9");
        netBankingPayment.setBankName("");
        netBankingPayment.setPin("123456");
        netBankingPayment.setUserId("useridsample");
        Errors errors = new BeanPropertyBindingResult(netBankingPayment, "netBankingPayment");
        netBankingPayment.validate(errors);
        Assertions.assertEquals(true, errors.hasErrors());
        Assertions.assertEquals(true, errors.hasFieldErrors("bankName"));
    }

    @Test
    public void validateEmptyAmountTest()
    {
        NetBankingPayment netBankingPayment = new NetBankingPayment();
        netBankingPayment.setAmountRequested("");
        netBankingPayment.setBankName("RBI");
        netBankingPayment.setPin("123456");
        netBankingPayment.setUserId("useridsample");
        Errors errors = new BeanPropertyBindingResult(netBankingPayment, "netBankingPayment");
        netBankingPayment.validate(errors);
        Assertions.assertEquals(true, errors.hasErrors());
        Assertions.assertEquals(true, errors.hasFieldErrors("amountRequested"));
    }

    @Test
    public void validateLessThanTenAmountTest()
    {
        NetBankingPayment netBankingPayment = new NetBankingPayment();
        netBankingPayment.setAmountRequested("9");
        netBankingPayment.setBankName("RBI");
        netBankingPayment.setPin("123456");
        netBankingPayment.setUserId("useridsample");
        Errors errors = new BeanPropertyBindingResult(netBankingPayment, "netBankingPayment");
        netBankingPayment.validate(errors);
        Assertions.assertEquals(true, errors.hasErrors());
        Assertions.assertEquals(true, errors.hasFieldErrors("amountRequested"));
    }

    @Test
    public void validateAllCorrectDetailsTest()
    {
        NetBankingPayment netBankingPayment = new NetBankingPayment();
        netBankingPayment.setAmountRequested("15");
        netBankingPayment.setBankName("RBI");
        netBankingPayment.setPin("123456");
        netBankingPayment.setUserId("useridsample");
        Errors errors = new BeanPropertyBindingResult(netBankingPayment, "netBankingPayment");
        netBankingPayment.validate(errors);
        Assertions.assertEquals(false, errors.hasErrors());
    }

}

