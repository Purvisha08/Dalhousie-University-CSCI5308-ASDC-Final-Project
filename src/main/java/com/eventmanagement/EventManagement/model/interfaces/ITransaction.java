package com.eventmanagement.EventManagement.model.interfaces;

import com.eventmanagement.EventManagement.model.TransactionSource;
import com.eventmanagement.EventManagement.model.TransactionType;

public interface ITransaction
{
    public  boolean add(int studentId, double amount, TransactionType transactionType, String description, TransactionSource transactionSource);
}
