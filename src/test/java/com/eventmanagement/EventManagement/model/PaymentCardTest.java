package com.eventmanagement.EventManagement.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;
import java.time.LocalDateTime;

@SpringBootTest
public class PaymentCardTest
{
    @Test
    public void validateEmptyAmountTest()
    {
        PaymentCard paymentCard = new PaymentCard();
        paymentCard.setCardNumber("1234567890987612");
        paymentCard.setCardExpiryYear(2030);
        paymentCard.setCardExpiryMonth(12);
        paymentCard.setAmountRequested("");
        paymentCard.setCardHolderName("card holder name");
        paymentCard.setCvvNumber("123");
        Errors errors = new BeanPropertyBindingResult(paymentCard, "paymentCard");
        paymentCard.validate(errors);
        Assertions.assertEquals(true, errors.hasErrors());
        Assertions.assertEquals(true, errors.hasFieldErrors("amountRequested"));
    }

    @Test
    public void validateLessThanTenAmountTest()
    {
        PaymentCard paymentCard = new PaymentCard();
        paymentCard.setCardNumber("1234567890987612");
        paymentCard.setCardExpiryYear(2030);
        paymentCard.setCardExpiryMonth(12);
        paymentCard.setAmountRequested("9");
        paymentCard.setCardHolderName("card holder name");
        paymentCard.setCvvNumber("123");
        Errors errors = new BeanPropertyBindingResult(paymentCard, "paymentCard");
        paymentCard.validate(errors);
        Assertions.assertEquals(true, errors.hasErrors());
        Assertions.assertEquals(true, errors.hasFieldErrors("amountRequested"));
    }

    @Test
    public void validateEmptyCardNumberTest()
    {
        PaymentCard paymentCard = new PaymentCard();
        paymentCard.setCardNumber("");
        paymentCard.setCardExpiryYear(2030);
        paymentCard.setCardExpiryMonth(12);
        paymentCard.setAmountRequested("10");
        paymentCard.setCardHolderName("card holder name");
        paymentCard.setCvvNumber("123");
        Errors errors = new BeanPropertyBindingResult(paymentCard, "paymentCard");
        paymentCard.validate(errors);
        Assertions.assertEquals(true, errors.hasErrors());
        Assertions.assertEquals(true, errors.hasFieldErrors("cardNumber"));
    }

    @Test
    public void validateLessDigitsCardNumberTest()
    {
        PaymentCard paymentCard = new PaymentCard();
        paymentCard.setCardNumber("123456787612");
        paymentCard.setCardExpiryYear(2030);
        paymentCard.setCardExpiryMonth(12);
        paymentCard.setAmountRequested("10");
        paymentCard.setCardHolderName("card holder name");
        paymentCard.setCvvNumber("123");
        Errors errors = new BeanPropertyBindingResult(paymentCard, "paymentCard");
        paymentCard.validate(errors);
        Assertions.assertEquals(true, errors.hasErrors());
        Assertions.assertEquals(true, errors.hasFieldErrors("cardNumber"));
    }

    @Test
    public void validateCharactersInCardNumberTest()
    {
        PaymentCard paymentCard = new PaymentCard();
        paymentCard.setCardNumber("12345678909876ab");
        paymentCard.setCardExpiryYear(2030);
        paymentCard.setCardExpiryMonth(12);
        paymentCard.setAmountRequested("10");
        paymentCard.setCardHolderName("card holder name");
        paymentCard.setCvvNumber("123");
        Errors errors = new BeanPropertyBindingResult(paymentCard, "paymentCard");
        paymentCard.validate(errors);
        Assertions.assertEquals(true, errors.hasErrors());
        Assertions.assertEquals(true, errors.hasFieldErrors("cardNumber"));
    }

    @Test
    public void validateEmptyCardHolderNameTest()
    {
        PaymentCard paymentCard = new PaymentCard();
        paymentCard.setCardNumber("1234567890987612");
        paymentCard.setCardExpiryYear(2030);
        paymentCard.setCardExpiryMonth(12);
        paymentCard.setAmountRequested("10");
        paymentCard.setCardHolderName("");
        paymentCard.setCvvNumber("123");
        Errors errors = new BeanPropertyBindingResult(paymentCard, "paymentCard");
        paymentCard.validate(errors);
        Assertions.assertEquals(true, errors.hasErrors());
        Assertions.assertEquals(true, errors.hasFieldErrors("cardHolderName"));
    }

    @Test
    public void validateDigitsInCardHolderNameTest()
    {
        PaymentCard paymentCard = new PaymentCard();
        paymentCard.setCardNumber("1234567890987612");
        paymentCard.setCardExpiryYear(2030);
        paymentCard.setCardExpiryMonth(12);
        paymentCard.setAmountRequested("10");
        paymentCard.setCardHolderName("alex 2hales");
        paymentCard.setCvvNumber("123");
        Errors errors = new BeanPropertyBindingResult(paymentCard, "paymentCard");
        paymentCard.validate(errors);
        Assertions.assertEquals(true, errors.hasErrors());
        Assertions.assertEquals(true, errors.hasFieldErrors("cardHolderName"));
    }

    @Test
    public void validateEmptyCVVNumberTest()
    {
        PaymentCard paymentCard = new PaymentCard();
        paymentCard.setCardNumber("1234567890987612");
        paymentCard.setCardExpiryYear(2030);
        paymentCard.setCardExpiryMonth(12);
        paymentCard.setAmountRequested("10");
        paymentCard.setCardHolderName("alex hales");
        paymentCard.setCvvNumber("");
        Errors errors = new BeanPropertyBindingResult(paymentCard, "paymentCard");
        paymentCard.validate(errors);
        Assertions.assertEquals(true, errors.hasErrors());
        Assertions.assertEquals(true, errors.hasFieldErrors("cvvNumber"));
    }

    @Test
    public void validateCharactersInCVVNumberTest()
    {
        PaymentCard paymentCard = new PaymentCard();
        paymentCard.setCardNumber("1234567890987612");
        paymentCard.setCardExpiryYear(2030);
        paymentCard.setCardExpiryMonth(12);
        paymentCard.setAmountRequested("10");
        paymentCard.setCardHolderName("alex hales");
        paymentCard.setCvvNumber("34q");
        Errors errors = new BeanPropertyBindingResult(paymentCard, "paymentCard");
        paymentCard.validate(errors);
        Assertions.assertEquals(true, errors.hasErrors());
        Assertions.assertEquals(true, errors.hasFieldErrors("cvvNumber"));
    }

    @Test
    public void validateInvalidLengthInCVVNumberTest()
    {
        PaymentCard paymentCard = new PaymentCard();
        paymentCard.setCardNumber("1234567890987612");
        paymentCard.setCardExpiryYear(2030);
        paymentCard.setCardExpiryMonth(12);
        paymentCard.setAmountRequested("10");
        paymentCard.setCardHolderName("alex hales");
        paymentCard.setCvvNumber("34123");
        Errors errors = new BeanPropertyBindingResult(paymentCard, "paymentCard");
        paymentCard.validate(errors);
        Assertions.assertEquals(true, errors.hasErrors());
        Assertions.assertEquals(true, errors.hasFieldErrors("cvvNumber"));
    }

    @Test
    public void validateInvalidExpiryMonthTest()
    {
        PaymentCard paymentCard = new PaymentCard();
        LocalDateTime currentdate = LocalDateTime.now();
        paymentCard.setCardNumber("1234567890987612");
        paymentCard.setCardExpiryYear(currentdate.getYear());
        paymentCard.setCardExpiryMonth(currentdate.getMonthValue()-1);
        paymentCard.setAmountRequested("10");
        paymentCard.setCardHolderName("alex hales");
        paymentCard.setCvvNumber("34123");
        Errors errors = new BeanPropertyBindingResult(paymentCard, "paymentCard");
        paymentCard.validate(errors);
        Assertions.assertEquals(true, errors.hasErrors());
        Assertions.assertEquals(true, errors.hasFieldErrors("cardExpiryMonth"));
    }

    @Test
    public void validateInvalidExpiryYearTest()
    {
        PaymentCard paymentCard = new PaymentCard();
        paymentCard.setCardNumber("1234567890987612");
        paymentCard.setCardExpiryYear(1998);
        paymentCard.setCardExpiryMonth(12);
        paymentCard.setAmountRequested("10");
        paymentCard.setCardHolderName("alex hales");
        paymentCard.setCvvNumber("341");
        Errors errors = new BeanPropertyBindingResult(paymentCard, "paymentCard");
        paymentCard.validate(errors);
        Assertions.assertEquals(true, errors.hasErrors());
        Assertions.assertEquals(true, errors.hasFieldErrors("cardExpiryYear"));
    }

    @Test
    public void validateAllCorrectDetailsTest()
    {
        PaymentCard paymentCard = new PaymentCard();
        paymentCard.setCardNumber("1234567890987612");
        paymentCard.setCardExpiryYear(2025);
        paymentCard.setCardExpiryMonth(12);
        paymentCard.setAmountRequested("10");
        paymentCard.setCardHolderName("alex hales");
        paymentCard.setCvvNumber("341");
        Errors errors = new BeanPropertyBindingResult(paymentCard, "paymentCard");
        paymentCard.validate(errors);
        Assertions.assertEquals(false, errors.hasErrors());
    }

}
